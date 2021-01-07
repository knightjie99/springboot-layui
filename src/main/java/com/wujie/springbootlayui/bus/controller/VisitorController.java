package com.wujie.springbootlayui.bus.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wujie.springbootlayui.bus.entity.TempVisitor;
import com.wujie.springbootlayui.bus.service.IBlackVisitorService;
import com.wujie.springbootlayui.bus.service.ITempVisitorService;
import com.wujie.springbootlayui.bus.service.IVipVisitorService;
import com.wujie.springbootlayui.sys.common.*;
import com.wujie.springbootlayui.sys.websocket.WsHandler;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.bus.entity.Gate;
import com.wujie.springbootlayui.bus.service.IGateService;
import com.wujie.springbootlayui.bus.vo.VisitorVo;

import javax.validation.Valid;

@RestController
@RequestMapping("/visitor")
public class VisitorController {

	@Autowired
	private ITempVisitorService visitorService;

	@Autowired
	private IGateService gateService;

	@Autowired
	private IBlackVisitorService blackListService;

	@Autowired
	private IVipVisitorService vipVisitorService;

	@Autowired
	private WsHandler wsHandler;

	/**
	 * 查询所有的访客
	 *
	 * @param visitor
	 * @return
	 */
	@RequestMapping("loadAllVisitor")
	public DataGridView loadAllVisitor(VisitorVo visitorVo) {
		// 查询当前用户信息
		Subject subject = SecurityUtils.getSubject();
		ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
		Integer belongId = activerUser.getUser().getId();

		// 1.声明一个分页page对象
		IPage<TempVisitor> page = new Page<TempVisitor>(visitorVo.getPage(), visitorVo.getLimit());
		// 2.声明一个queryWrapper
		QueryWrapper<TempVisitor> queryWrapper = new QueryWrapper<TempVisitor>();
		// belongId为1时是超级管理员，可以查所有
		if (belongId != 1) {
			queryWrapper.eq("belong_id", belongId);
		}

		queryWrapper.like(StringUtils.isNotBlank(visitorVo.getVisitorName()), "visitor_name",
				visitorVo.getVisitorName());
		queryWrapper.like(StringUtils.isNotBlank(visitorVo.getPhone()), "phone", visitorVo.getPhone());
		queryWrapper.eq(visitorVo.getStatus() != null, "status", visitorVo.getStatus());
		queryWrapper.orderByDesc("create_time");
		visitorService.page(page, queryWrapper);
		List<TempVisitor> list = page.getRecords();
		for (TempVisitor visitor : list) {
			// 更新访客状态
			Date leaveTime = visitor.getLeaveTime();
			Date currentDate = new Date();
			if (leaveTime != null && currentDate != null) {
				if (leaveTime.before(currentDate)) {
					// 说明访客已失效
					visitor.setStatus(2);
					visitorService.updateById(visitor);
				}
			}

			// String gateIds = visitor.getGateIds();
			// List<String> idList = Arrays.asList(gateIds.split(","));
			// List<String> names = new ArrayList<String>();
			// for(String idStr : idList) {
			// Integer gateId = Integer.valueOf(idStr);
			// Gate one = gateService.getGateById(gateId);
			// if(one != null) {
			// names.add(one.getGateName());
			// }
			// }
			// String gateName = StringUtils.join(names,",");
			// if(StringUtil.isEmpty(gateName)) {
			// visitor.setGateName("闸机已删除");
			// }else {
			// visitor.setGateName(gateName);
			// }
			if (!StringUtil.isEmpty(visitor.getGateIds())) {
				List<String> ids = Arrays.asList(visitor.getGateIds().split(","));
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
				visitor.setGateName(name);
			}

		}
		return new DataGridView(page.getTotal(), list);
	}

	/**
	 * 添加一个访客
	 *
	 * @param visitorVo
	 * @return
	 */
	@RequestMapping("addVisitor")
	public ResultObj addVisitor(@Valid VisitorVo visitorVo, BindingResult bResult) {
		try {
			if(bResult.hasErrors()) {
				String errrorMsg = bResult.getFieldError().getDefaultMessage();
				return new ResultObj(400,errrorMsg);
			}
			Subject subject = SecurityUtils.getSubject();
			ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
			Integer belongId = activerUser.getUser().getId();
			String creatorName = activerUser.getUser().getName();
			if (StringUtil.isEmpty(visitorVo.getGateIds())) {
				return new ResultObj(500, "add_at_least_one_gate");
			}
			if (visitorVo.getGateIds().endsWith(",")) {
				visitorVo.setGateIds(visitorVo.getGateIds().substring(0, visitorVo.getGateIds().length() - 1));
			}

			if (!StringUtil.isPhone(visitorVo.getPhone())) {
				return new ResultObj(500, "wrong_number");
			} else {
				QueryWrapper<TempVisitor> verifyWrapper = new QueryWrapper<>();
				verifyWrapper.eq("phone", visitorVo.getPhone());
				IPage<TempVisitor> page = new Page<>();
				if (visitorService.page(page, verifyWrapper).getRecords().size() > 0) {
					return new ResultObj(500, "phone_uesed");
				}
			}
			String idFrom = IDCardUtil.IDCardValidate(visitorVo.getIdNum());
			if (!idFrom.equals("YES")) {
				return new ResultObj(500, idFrom);
			} else {
				String idNum = visitorVo.getIdNum();
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
			}

			visitorVo.setBelongId(belongId);
			if (visitorVo.getVisitorPhoto() != null ) {
				String newName = AppFileUtils.renameFile(visitorVo.getVisitorPhoto());
				visitorVo.setVisitorPhoto(newName);
			}

			visitorVo.setCreatorName(creatorName);
			visitorVo.setStatus(1);
			visitorVo.setCreateTime(new Date());
			visitorVo.setUpdateTime(new Date());
			visitorService.save(visitorVo);
			// 新增推送消息
			String gateIds = visitorVo.getGateIds();
			List<String> list = Arrays.asList(gateIds.split(","));
			for (String gateId : list) {
				Gate gate = gateService.getGateById(Integer.valueOf(gateId));
				if (null != gate) {
					String deviceSn = gate.getDeviceSn();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("code", 200);
					//type 1-人员 2-闸机  3-下发更新包 4-心跳回复
					map.put("type", 1);
					map.put("identityType", 2);
					map.put("msg", "访客信息已更新!");
					wsHandler.sendInfo(deviceSn, map.toString());
				}
			}

			return new ResultObj(Constast.OK,"messages.ADD_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
		}
	}

	/**
	 * 修改一个访客
	 *
	 * @param visitorVo
	 * @return
	 */
	@RequestMapping("updateVisitor")
	public ResultObj updateVisitor(@Valid VisitorVo visitorVo, BindingResult bResult) {
		try {
			if(bResult.hasErrors()) {
				String errrorMsg = bResult.getFieldError().getDefaultMessage();
				return new ResultObj(400,errrorMsg);
			}
			if (visitorVo.getVisitorPhoto() != null) {
				String newName = AppFileUtils.renameFile(visitorVo.getVisitorPhoto());
				visitorVo.setVisitorPhoto(newName);
			}
			if (StringUtil.isEmpty(visitorVo.getGateIds()) && visitorVo.getGateIds() == "") {
				return new ResultObj(500, "select_at_least_one_gate");
			}

			if (visitorVo.getGateIds().endsWith(",")) {
				visitorVo.setGateIds(visitorVo.getGateIds().substring(0, visitorVo.getGateIds().length() - 1));
			}

			visitorVo.setUpdateTime(new Date());
			visitorService.updateById(visitorVo);
			// 发送推送消息
			if (visitorVo.getStatus() == 1) {
				String gateIds = visitorVo.getGateIds();
				List<String> list = Arrays.asList(gateIds.split(","));
				for (String gateId : list) {
					Gate gate = gateService.getGateById(Integer.valueOf(gateId));
					if (null != gate) {
						String deviceSn = gate.getDeviceSn();
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("code", 200);
						//type 1-人员 2-闸机  3-下发更新包 4-心跳回复
						map.put("type", 1);
						map.put("identityType", 2);
						map.put("msg", "访客信息已更新!");
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

	/**
	 * 删除一个访客
	 *
	 * @param id
	 *            访客的ID
	 * @return
	 */
	@RequestMapping("deleteVisitor")
	public ResultObj deleteVisitor(Integer id) {
		try {
			TempVisitor visitor = visitorService.getVisitorById(id);
			// 发送推送
			if (visitor.getStatus() == 1) {
				String gateIds = visitor.getGateIds();
				List<String> list = Arrays.asList(gateIds.split(","));
				for (String gateId : list) {
					Gate gate = gateService.getGateById(Integer.valueOf(gateId));
					if (null != gate) {
						String deviceSn = gate.getDeviceSn();
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("code", 200);
						//type 1-人员 2-闸机  3-下发更新包 4-心跳回复
						map.put("type", 1);
						map.put("identityType", 2);
						map.put("msg", "访客信息已更新!");
						wsHandler.sendInfo(deviceSn, map.toString());
					}
				}
			}
			visitorService.removeById(id);
			return new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
		}
	}

	/**
	 * 批量删除访客
	 *
	 * @param visitorVo
	 *            选中的访客
	 * @return
	 */
	@RequestMapping("batchDeleteVisitor")
	public ResultObj batchDeleteVisitor(VisitorVo visitorVo) {
		try {
			Collection<Serializable> idList = new ArrayList<Serializable>();
			for (Integer id : visitorVo.getIds()) {
				idList.add(id);

				TempVisitor visitor = visitorService.getVisitorById(id);
				// 发送推送
				if (visitor.getStatus() == 1) {
					String gateIds = visitor.getGateIds();
					List<String> list = Arrays.asList(gateIds.split(","));
					for (String gateId : list) {
						Gate gate = gateService.getGateById(Integer.valueOf(gateId));
						if (null != gate) {
							String deviceSn = gate.getDeviceSn();
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("code", 200);
							//type 1-人员 2-闸机  3-下发更新包 4-心跳回复
							map.put("type", 1);
							map.put("identityType", 2);
							map.put("msg", "访客信息已更新!");
							wsHandler.sendInfo(deviceSn, map.toString());
						}
					}
				}
			}
			visitorService.removeByIds(idList);
			return new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
		}
	}
}
