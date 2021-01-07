package com.wujie.springbootlayui.bus.controller;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.bus.entity.BlackVisitor;
import com.wujie.springbootlayui.bus.entity.Gate;
import com.wujie.springbootlayui.bus.service.IBlackVisitorService;
import com.wujie.springbootlayui.bus.service.IGateService;
import com.wujie.springbootlayui.bus.service.ITempVisitorService;
import com.wujie.springbootlayui.bus.service.IVipVisitorService;
import com.wujie.springbootlayui.bus.vo.BlackVisitorVo;
import com.wujie.springbootlayui.sys.common.*;
import com.wujie.springbootlayui.sys.websocket.WsHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/blacklist")
public class BlackVisitorController {
	
	@Autowired
	private IBlackVisitorService blackListService;

	@Autowired
	private ITempVisitorService visitorService;

	@Autowired
	private IVipVisitorService vipVisitorService;

	@Autowired
	private IGateService gateService;

	@Autowired
	private WsHandler wsHandler;

	@RequestMapping("loadAllBlackList")
	public DataGridView loadAllBlackVisitor(BlackVisitorVo vo) {
		Subject subject = SecurityUtils.getSubject();
		ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
		Integer belongId = activerUser.getUser().getId();
		IPage<BlackVisitor> page = new Page<BlackVisitor>(vo.getPage(), vo.getLimit());
		QueryWrapper<BlackVisitor> queryWrapper = new QueryWrapper<>();
		if (belongId != 1) {
			queryWrapper.eq("belong_id", belongId);
		}

		queryWrapper.like(StringUtils.isNotBlank(vo.getName()), "name", vo.getName());
		queryWrapper.like(StringUtils.isNotBlank(vo.getPhone()), "phone", vo.getPhone());
		blackListService.page(page, queryWrapper);
		List<BlackVisitor> list = page.getRecords();

		return new DataGridView(page.getTotal(), list);

	}

	@RequestMapping("addBlacklist")
	public ResultObj addBlacklist(@Valid BlackVisitorVo blackVo, BindingResult bResult) {
		try {
			if(bResult.hasErrors()) {
				String errrorMsg = bResult.getFieldError().getDefaultMessage();
				return new ResultObj(400,errrorMsg);
			}
			Subject subject = SecurityUtils.getSubject();
			ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
			Integer belongId = activerUser.getUser().getId();
			String creatorName = activerUser.getUser().getName();
			String idNum = blackVo.getIdNum();
			if (!StringUtil.isEmpty(idNum)) {
				boolean flag1 = visitorService.getValidVisitorByIdNum(idNum, belongId) != null;
				if (flag1) {
					return new ResultObj(500, "Idnum_usedBy_visitor");
				}
				boolean flag2 = vipVisitorService.getVipVisitorByIdNum(idNum, belongId) != null;
				if (flag2) {
					return new ResultObj(500, "Idnum_usedBy_vip");
				}
				boolean flag3 = blackListService.getBlackVisitorByIdNum(idNum, belongId) != null;
				if (flag3) {
					return new ResultObj(500, "Idnum_usedBy_blackList");
				}

			}

			if (blackVo.getVisitorPhoto() != null) {
				String newName = AppFileUtils.renameFile(blackVo.getVisitorPhoto());
				blackVo.setVisitorPhoto(newName);
			}

			blackVo.setCreatorName(creatorName);
			blackVo.setCreateTime(new Date());
			blackVo.setUpdateTime(new Date());
			blackVo.setBelongId(belongId);
			blackListService.save(blackVo);
			String connectedPhone = blackVo.getConnectedPhone();
			if(!StringUtil.isEmpty(connectedPhone)&&StringUtil.isPhone(connectedPhone)) {
					SMSUtil.sendSms(connectedPhone, blackVo.getName(), 4);
			}

			// 发送推送消息
			List<Gate> gates = gateService.getAllOnlineGateByBelong(belongId);
			for (Gate gate : gates) {
				String deviceSn = gate.getDeviceSn();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", 200);
				//type 1-人员 2-闸机  3-下发更新包 4-心跳回复
				map.put("type", 1);
				map.put("identityType", 4);
				map.put("msg", "黑名单信息已更新!");
				wsHandler.sendInfo(deviceSn, map.toString());
			}

			return new ResultObj(Constast.OK,"messages.ADD_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
		}

	}

	@RequestMapping("batchDeleteBlacklist")
	public ResultObj batchDeleteBlacklist(BlackVisitorVo vo) {
		try {
			Collection<Serializable> idList = new ArrayList<Serializable>();
			for (Integer id : vo.getIds()) {
				idList.add(id);
				// 发送推送消息
				Integer belongId = vo.getBelongId();
				List<Gate> gates = gateService.getAllOnlineGateByBelong(belongId);
				for (Gate gate : gates) {
					String deviceSn = gate.getDeviceSn();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("code", 200);
					//type 1-人员 2-闸机  3-下发更新包 4-心跳回复
					map.put("type", 1);
					map.put("identityType", 4);
					map.put("msg", "黑名单信息已更新!");
					wsHandler.sendInfo(deviceSn, map.toString());
				}
			}
			blackListService.removeByIds(idList);
			return new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
		}
	}

	@RequestMapping("deleteBlacklist")
	public ResultObj deleteBlacklist(Integer id) {
		try {

			BlackVisitor black = blackListService.getBlackVisitorById(id);
			// 发送推送消息
			Integer belongId = black.getBelongId();
			List<Gate> gates = gateService.getAllOnlineGateByBelong(belongId);
			for (Gate gate : gates) {
				String deviceSn = gate.getDeviceSn();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", 200);
				//type 1-人员 2-闸机  3-下发更新包 4-心跳回复
				map.put("type", 1);
				map.put("identityType", 4);
				map.put("msg", "黑名单信息已更新!");
				wsHandler.sendInfo(deviceSn, map.toString());
			}

			blackListService.removeById(id);
			return  new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
		}
	}

	@RequestMapping("updateBlacklist")
	public ResultObj updateBlacklist(@Valid BlackVisitorVo vo, BindingResult bResult) {
		try {
			if(bResult.hasErrors()) {
				String errrorMsg = bResult.getFieldError().getDefaultMessage();
				return new ResultObj(400,errrorMsg);
			}

			if (vo.getVisitorPhoto() != null ) {
				String newName = AppFileUtils.renameFile(vo.getVisitorPhoto());
				vo.setVisitorPhoto(newName);
			}
			vo.setUpdateTime(new Date());
			blackListService.updateById(vo);

			// 发送推送消息
			Integer belongId = vo.getBelongId();
			List<Gate> gates = gateService.getAllOnlineGateByBelong(belongId);
			for (Gate gate : gates) {
				String deviceSn = gate.getDeviceSn();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", 200);
				//type 1-人员 2-闸机  3-下发更新包 4-心跳回复
				map.put("type", 1);
				map.put("identityType", 4);
				map.put("msg", "黑名单信息已更新!");
				wsHandler.sendInfo(deviceSn, map.toString());
			}
			return new ResultObj(Constast.OK,"messages.UPDATE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.UPDATE_ERROR");
		}
	}

	@RequestMapping("batchAdd")
	public ResultObj batchAdd(BlackVisitorVo vo) {
		try {
			Subject subject = SecurityUtils.getSubject();
			ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
			Integer belongId = activerUser.getUser().getId();

			if (belongId == null) {
				return new ResultObj(500, "登录失效，请重新登录");
			}
			String mf = vo.getMf();
			if (StringUtil.isEmpty(mf)) {
				return new ResultObj(500, "文件识别失败");
			}
			String newXmlName = AppFileUtils.renameFile(mf);
			String fileDoc = AppFileUtils.UPLOAD_PATH;
			String dir = fileDoc + newXmlName.split("/")[0];
			String extraDir = dir + "/" + belongId;
			File extra = new File(extraDir);
			if (!extra.exists()) {
				extra.mkdirs();
			}
			// 解析ZIP文件
			String filePath = fileDoc + newXmlName;
			String excel = AppFileUtils.extractZip("blackList", filePath, extraDir);
			String[][] excelData = ExcelUtil.getExcelStr(excel);
			if (excelData == null) {
				return new ResultObj(500, "excel_error");
			}

			int suc = 0;
			for (int i = 3; i < excelData.length; i++) {

				String[] strs = excelData[i];
				String name = strs[0];
				String photo = strs[1];
				String idNum = strs[2];
				String phone = strs[3];
				String personGroup = strs[4];
				String connectPhone = strs[5];
				String remark = strs[6];
				
				int idNumCount = 0;
				if (!StringUtil.isEmpty(idNum)) {
					BigDecimal bd = new BigDecimal(idNum);
					idNum = bd.toPlainString();
					boolean flag1 = visitorService.getValidVisitorByIdNum(idNum, belongId) != null;
					if (flag1) {
						idNumCount++;
					}

					boolean flag2 = vipVisitorService.getVipVisitorByIdNum(idNum, belongId) != null;
					if (flag2) {
						idNumCount++;
					}

					boolean flag3 = blackListService.getBlackVisitorByIdNum(idNum, belongId) != null;
					if (flag3) {
						idNumCount++;
					}

				}

				if (!StringUtil.isEmpty(connectPhone)) {
					BigDecimal bd = new BigDecimal(connectPhone);
					connectPhone = bd.toPlainString();
				}

				if (!StringUtil.isEmpty(name) && !StringUtil.isEmpty(photo) && idNumCount == 0) {
					BlackVisitor black = new BlackVisitor();
					black.setBelongId(belongId);
					black.setName(name);
					black.setIdNum(idNum);
					black.setPhone(phone);
					black.setPersonGroup(personGroup);
					black.setConnectedPhone(connectPhone);
					black.setRemark(remark);
					black.setUpdateTime(new Date());
					black.setCreateTime(new Date());
					//创建者名字
					String creatorName = activerUser.getUser().getName();
					black.setCreatorName(creatorName);
					String path = extraDir + "/" + photo;
					String visitorPhoto = AppFileUtils.uploadFile(path);
					if (!StringUtil.isEmpty(visitorPhoto)) {
						black.setVisitorPhoto(visitorPhoto);
						blackListService.save(black);
						suc++;
						//发送短信
						String connectedPhone = black.getConnectedPhone();
						if(!StringUtil.isEmpty(connectedPhone)&&StringUtil.isPhone(connectedPhone)) {
								SMSUtil.sendSms(connectedPhone, black.getName(), 4);
						}
					}
				}

			}
			FileUtil.del(extra);
			FileUtil.del(filePath);

			if (suc > 0) {
				return new ResultObj(200, "successfully_import");
			} else {
				return new ResultObj(500, "no_valid_blacklist");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
		}

	}

}
