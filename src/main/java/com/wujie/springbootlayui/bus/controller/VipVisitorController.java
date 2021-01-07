package com.wujie.springbootlayui.bus.controller;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.bus.entity.Gate;
import com.wujie.springbootlayui.bus.entity.VipVisitor;
import com.wujie.springbootlayui.bus.service.IBlackVisitorService;
import com.wujie.springbootlayui.bus.service.IGateService;
import com.wujie.springbootlayui.bus.service.ITempVisitorService;
import com.wujie.springbootlayui.bus.service.IVipVisitorService;
import com.wujie.springbootlayui.bus.vo.VipVisitorVo;
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
@RequestMapping("/vipvisitor")
public class VipVisitorController {

	@Autowired
	private IVipVisitorService vipService;

	@Autowired
	private IGateService gateService;
	
	@Autowired
	private IBlackVisitorService blackListService;

	@Autowired
	private ITempVisitorService visitorService;

	@Autowired
	private WsHandler wsHandler;

	@RequestMapping("loadAllVipVisitor")
	public DataGridView loadAllVipVisitor(VipVisitorVo vipVo) {
		// 查询当前用户信息
		Subject subject = SecurityUtils.getSubject();
		ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
		Integer belongId = activerUser.getUser().getId();
		IPage<VipVisitor> page = new Page<VipVisitor>(vipVo.getPage(), vipVo.getLimit());
		QueryWrapper<VipVisitor> queryWrapper = new QueryWrapper<>();
		if (belongId != 1) {
			queryWrapper.eq("belong_id", belongId);
		}
		queryWrapper.like(StringUtils.isNotBlank(vipVo.getVisitorName()), "visitor_name", vipVo.getVisitorName());
		queryWrapper.like(StringUtils.isNotBlank(vipVo.getPhone()), "phone", vipVo.getPhone());
		vipService.page(page, queryWrapper);
		List<VipVisitor> list = page.getRecords();

		for (VipVisitor vip : list) {
			if (!StringUtil.isEmpty(vip.getGateIds())) {
				List<String> ids = Arrays.asList(vip.getGateIds().split(","));
				String name = "";
				for (int i = 0; i < ids.size(); i++) {
					Integer gateId = Integer.parseInt(ids.get(0));
					if (gateId != null) {
						Gate one = gateService.getGateById(gateId);
						if (one != null && i > 0) {
							name += "," + one.getGateName();
						}

						if (one != null && i == 0) {
							name += one.getGateName();
						}
						// else {
						// vip.setGateName("闸机已删除");
						// }
					}
				}
				vip.setGateName(name);
			}
		}
		return new DataGridView(page.getTotal(), list);

	}

	/**
	 * 单个添加访客
	 * 
	 * @param vip
	 * @return
	 */
	@RequestMapping("addVipVisitor")
	public ResultObj addVisitor(@Valid VipVisitor vip, BindingResult bResult) {
		try {
			if(bResult.hasErrors()) {
				String errrorMsg = bResult.getFieldError().getDefaultMessage();
				return new ResultObj(400,errrorMsg);
			}
			Subject subject = SecurityUtils.getSubject();
			ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
			Integer belongId = activerUser.getUser().getId();
			String creatorName = activerUser.getUser().getName();
			String idNum = vip.getIdNum();
			if (!StringUtil.isEmpty(idNum)) {
				boolean flag1 = visitorService.getValidVisitorByIdNum(idNum, belongId) != null;
				if (flag1) {
					return new ResultObj(500, "Idnum_usedBy_visitor");
				}
				boolean flag2 = vipService.getVipVisitorByIdNum(idNum, belongId) != null;
				if (flag2) {
					return new ResultObj(500, "Idnum_usedBy_vip");
				}
				boolean flag3 = blackListService.getBlackVisitorByIdNum(idNum, belongId) != null;
				if (flag3) {
					return new ResultObj(500, "Idnum_usedBy_blackList");
				}

			}

			if (vip.getVisitorPhoto() != null) {
				String newName = AppFileUtils.renameFile(vip.getVisitorPhoto());
				vip.setVisitorPhoto(newName);
			}
			vip.setCreatorName(creatorName);
			vip.setCreateTime(new Date());
			vip.setUpdateTime(new Date());
			vip.setBelongId(belongId);
			if (vip.getCycleDate().endsWith(",")) {
				vip.setCycleDate(vip.getCycleDate().substring(0, vip.getCycleDate().length() - 1));
			}

			if(!StringUtil.isEmpty(vip.getGateIds())&&vip.getGateIds().endsWith(",")){
				vip.setGateIds(vip.getGateIds().substring(0, vip.getGateIds().length() - 1));
			}

			vipService.save(vip);

			// 发送推动消息
			String gateIds = vip.getGateIds();
			List<String> list = Arrays.asList(gateIds.split(","));
			for (String gateId : list) {
				Gate gate = gateService.getGateById(Integer.valueOf(gateId));
				if (null != gate) {
					String deviceSn = gate.getDeviceSn();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("code", 200);
					map.put("type", 1);
					map.put("identityType", 3);
					map.put("msg", "VIP访客信息已更新!");
					wsHandler.sendInfo(deviceSn, map.toString());
				}
			}
			return new ResultObj(Constast.OK,"messages.ADD_SUCCESS");
		} catch (Exception e) {

			e.printStackTrace();
			return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
		}

	}

	@RequestMapping("deleteVip")
	public ResultObj deleteVip(Integer id) {
		try {

			VipVisitor vip = vipService.getVipVisitorById(id);
			// 发送推动消息
			String gateIds = vip.getGateIds();
			if(!StringUtil.isEmpty(vip.getGateIds())){
				List<String> list = Arrays.asList(gateIds.split(","));
				for (String gateId : list) {
					Gate gate = gateService.getGateById(Integer.valueOf(gateId));
					if (null != gate) {
						String deviceSn = gate.getDeviceSn();
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("code", 200);
						map.put("type", 1);
						map.put("identityType", 3);
						map.put("msg", "VIP访客信息已更新!");
						wsHandler.sendInfo(deviceSn, map.toString());
					}
				}
			}
			vipService.removeById(id);
			return new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
		}
	}

	@RequestMapping("updateVip")
	public ResultObj updateVip(@Valid VipVisitor vip, BindingResult bResult) {
		try {
			if(bResult.hasErrors()) {
				String errrorMsg = bResult.getFieldError().getDefaultMessage();
				return new ResultObj(400,errrorMsg);
			}
			if (vip.getVisitorPhoto() != null) {
				String newName = AppFileUtils.renameFile(vip.getVisitorPhoto());
				vip.setVisitorPhoto(newName);
			}
			vip.setUpdateTime(new Date());

			if (vip.getCycleDate().endsWith(",")) {
				vip.setCycleDate(vip.getCycleDate().substring(0, vip.getCycleDate().length() - 1));
			}

			if(!StringUtil.isEmpty(vip.getGateIds())&&vip.getGateIds().endsWith(",")){
				vip.setGateIds(vip.getGateIds().substring(0, vip.getGateIds().length() - 1));
			}

			vipService.updateById(vip);

			// 发送推动消息
			String gateIds = vip.getGateIds();
			List<String> list = Arrays.asList(gateIds.split(","));
			for (String gateId : list) {
				if(!StringUtil.isEmpty(gateId)){

					Gate gate = gateService.getGateById(Integer.valueOf(gateId));
					if (null != gate) {
						String deviceSn = gate.getDeviceSn();
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("code", 200);
						map.put("type", 1);
						map.put("identityType", 3);
						map.put("msg", "VIP访客信息已更新!");
						wsHandler.sendInfo(deviceSn, map.toString());
					}
				}

			}
			return new ResultObj(Constast.OK,"messages.UPDATE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.UPDATE_ERROR");
		}
	}

	@RequestMapping("batchDeleteVip")
	public ResultObj batchDeleteVip(VipVisitorVo vip) {
		try {
			Collection<Serializable> idList = new ArrayList<Serializable>();
			for (Integer id : vip.getIds()) {
				idList.add(id);
				VipVisitor vipVis = vipService.getVipVisitorById(id);
				// 发送推动消息
				String gateIds = vipVis.getGateIds();
				List<String> list = Arrays.asList(gateIds.split(","));
				for (String gateId : list) {
					if(!StringUtil.isEmpty(gateId)){
						Gate gate = gateService.getGateById(Integer.valueOf(gateId));
						if (null != gate) {
							String deviceSn = gate.getDeviceSn();
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("code", 200);
							map.put("type", 1);
							map.put("identityType", 3);
							map.put("msg", "VIP访客信息已更新!");
							wsHandler.sendInfo(deviceSn, map.toString());
						}
					}
				}
			}
			vipService.removeByIds(idList);
			return new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
		}
	}

	@RequestMapping("batchAdd")
	public ResultObj batchAdd(VipVisitorVo vipVo) {
		try {
			Subject subject = SecurityUtils.getSubject();
			ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
			Integer belongId = activerUser.getUser().getId();

			if (belongId == null) {
				return new ResultObj(500, "LOGIN_ERROR");
			}
			if (vipVo.getMf() == null) {
				return new ResultObj(500, "file_error");
			}

			String newXmlName = AppFileUtils.renameFile(vipVo.getMf());
			String fileDoc = AppFileUtils.UPLOAD_PATH;
			String dir = fileDoc + newXmlName.split("/")[0];
			String extraDir = dir + "/" + belongId;
			File extra = new File(extraDir);
			if (!extra.exists()) {
				extra.mkdirs();
			}
			// 解析ZIP文件
			String filePath = fileDoc + newXmlName;
			// 遍历压缩文件内部 文件数量
			String excel = AppFileUtils.extractZip("vip", filePath, extraDir);
			if (StringUtil.isEmpty(excel)) {
				return new ResultObj(500, "decompress_failed");
			}

			String[][] excelData = ExcelUtil.getExcelStr(excel);

			int suc = 0;
			for (int i = 3; i < excelData.length; i++) {
				String[] strs = excelData[i];
				//姓名
				String name = strs[0];
				//识别图像
				String facePhoto = strs[1];
				//身份证号
				String idNum = strs[2];
				//手机号
				String phone = strs[3];
				//人员组
				String personGroup = strs[4];
				//内部关联手机号
				String connectPhone = strs[5];
				//备注
				String remark = strs[6];
				
				int idNumCount = 0;
				if(!StringUtil.isEmpty(phone)){
					BigDecimal bd = new BigDecimal(phone);
					phone = bd.toPlainString();
				}

				if(!StringUtil.isEmpty(connectPhone)){
					BigDecimal bd = new BigDecimal(connectPhone);
					connectPhone = bd.toPlainString();
				}

				if (!StringUtil.isEmpty(idNum)) {
					BigDecimal bd = new BigDecimal(idNum);
					idNum = bd.toPlainString();
					boolean flag1 = visitorService.getValidVisitorByIdNum(idNum, belongId) != null;
					if (flag1) {
						idNumCount++;
					}

					boolean flag2 = vipService.getVipVisitorByIdNum(idNum, belongId) != null;
					if (flag2) {
						idNumCount++;
					}

					boolean flag3 = blackListService.getBlackVisitorByIdNum(idNum, belongId) != null;
					if (flag3) {
						idNumCount++;
					}

				}

				if (!StringUtil.isEmpty(name)
						&& !StringUtil.isEmpty(facePhoto) 
						&& !StringUtil.isEmpty(idNum)
						&& idNumCount == 0) {
					VipVisitor vip = new VipVisitor();
					vip.setBelongId(belongId);
					vip.setUpdateTime(new Date());
					vip.setCreateTime(new Date());
					vip.setConnectedPhone(connectPhone);
					if (!StringUtil.isEmpty(personGroup)) {
						vip.setPersonGroup(personGroup);
					}
					if (!StringUtil.isEmpty(phone)) {
						BigDecimal bd = new BigDecimal(phone);
						phone = bd.toPlainString();
						vip.setPhone(phone);
					}
					if (!StringUtil.isEmpty(connectPhone)) {
						BigDecimal bd = new BigDecimal(connectPhone);
						connectPhone = bd.toPlainString();
						vip.setPhone(connectPhone);
					}
					if(!StringUtil.isEmpty(remark)) {
						vip.setRemark(remark);
					}
					vip.setVisitorName(name);
					vip.setIdNum(idNum);
					if (!StringUtil.isEmpty(vip.getCycleDate())&&vip.getCycleDate().endsWith(",")) {
						vip.setCycleDate(vip.getCycleDate().substring(0, vip.getCycleDate().length() - 1));
					}
					
					String creatorName = activerUser.getUser().getName();
					vip.setCreatorName(creatorName);
					String path = extraDir + "/" + strs[1];
					String visitorPhoto = AppFileUtils.uploadFile(path);
					if(!StringUtil.isEmpty(visitorPhoto)) {
						vip.setVisitorPhoto(visitorPhoto);
						vipService.save(vip);
						suc++;
					}
				}

			}
			FileUtil.del(extra);
			FileUtil.del(filePath);

			if (suc > 0) {
				return new ResultObj(200, "successfully_import");
			} else {
				return new ResultObj(500, "no_valid_visitor");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
		}

	}

}
