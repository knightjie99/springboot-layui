package com.wujie.springbootlayui.bus.controller;

import java.util.*;

import com.wujie.springbootlayui.bus.entity.GateAuthor;
import com.wujie.springbootlayui.bus.entity.VipVisitor;
import com.wujie.springbootlayui.bus.entity.TempVisitor;
import com.wujie.springbootlayui.bus.service.IVipVisitorService;
import com.wujie.springbootlayui.bus.service.ITempVisitorService;
import com.wujie.springbootlayui.sys.common.*;
import com.wujie.springbootlayui.sys.entity.DTree;
import com.wujie.springbootlayui.sys.entity.Device;
import com.wujie.springbootlayui.sys.service.IDeviceService;
import com.wujie.springbootlayui.sys.websocket.WsHandler;
import com.wujie.springbootlayui.sys.websocket.WsSessionManager;

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
import com.wujie.springbootlayui.bus.service.IGateAuthorService;
import com.wujie.springbootlayui.bus.service.IGateService;
import com.wujie.springbootlayui.bus.vo.GateVo;

import javax.validation.Valid;

@RestController
@RequestMapping("/gate")
public class GateController {

	@Autowired
	private IGateService gateService;

	@Autowired
	private IGateAuthorService authorService;

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private ITempVisitorService visitorService;

	@Autowired
	private IVipVisitorService vipVisitorService;

	@Autowired
	private WsHandler wsHandler;
	
	/**
	 * 查询所有的闸机
	 *
	 * @param gateVo
	 * @return
	 */
	@RequestMapping("loadAllGate")
	public DataGridView loadAllGate(GateVo gateVo) {
		// 查询当前用户信息
		Subject subject = SecurityUtils.getSubject();
		ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
		Integer belongId = activerUser.getUser().getId();
		// 1.声明一个分页page对象
		IPage<Gate> page = new Page<Gate>(gateVo.getPage(), gateVo.getLimit());
		// 2.声明一个queryWrapper
		QueryWrapper<Gate> queryWrapper = new QueryWrapper<Gate>();
		queryWrapper.orderByDesc("create_time");
		// belongId为1时是超级管理员，可以查所有
		if (belongId != 1) {
			queryWrapper.eq("belong_id", belongId);
			queryWrapper.eq("available", 1);
		}
		queryWrapper.like(StringUtils.isNotBlank(gateVo.getDeviceSn()), "device_sn", gateVo.getDeviceSn());
		queryWrapper.eq(gateVo.getGateStatus() != null, "gate_status", gateVo.getGateStatus());
		gateService.page(page, queryWrapper);
		List<Gate> list = page.getRecords();
		return new DataGridView(page.getTotal(), list);
	}

	/**
	 * 添加一个闸机
	 *
	 * @param gateVo
	 * @return
	 */
	@RequestMapping("addGate")
	public ResultObj addGate(@Valid GateVo gateVo, BindingResult bResult) {
		try {
			if (bResult.hasErrors()) {
				String errrorMsg = bResult.getFieldError().getDefaultMessage();
				return new ResultObj(400, errrorMsg);
			}

			Subject subject = SecurityUtils.getSubject();
			ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
			Integer belongId = activerUser.getUser().getId();
			gateVo.setBelongId(belongId);

			// 默认设置为2-关闭
			gateVo.setOnlineStatus(2);
			gateVo.setGateStatus(2);
			gateVo.setFaceSet("off");
			gateVo.setInfraredSet("off");
			gateVo.setTemperatureSet("off");
			gateVo.setCreateTime(new Date());
			gateVo.setUpdateTime(new Date());

			// 1-批量导入
			if (gateVo.getInputWay() == 1) {
				if (gateVo.getMf() != null ) {
					String newName = AppFileUtils.renameFile(gateVo.getMf());
					String fileDoc = AppFileUtils.UPLOAD_PATH;
					// 解析xml文件
					String filePath = fileDoc + newName;
					String[][] excelData = ExcelUtil.getExcelStr(filePath);
					// 读取excel文件异常
					if (excelData == null || excelData.length <= 1) {// 只有一行时 视为无数据
						return new ResultObj(500, "excel_error");
					}
					for (int i = 1; i < excelData.length; i++) {
						String newSn = excelData[i][1];
						if (!StringUtil.isEmpty(newSn)) {
							Integer gateId = gateService.getIdByDeviceSn(newSn);
							if (gateId == null) {
//								String deviceSn = gateVo.getDeviceSn();
								Device device = deviceService.queryDeviceByDeviceSn(newSn);
								if (device != null) {
									if (device.getBelongId() == null || device.getBelongId() == belongId) {
										gateVo.setDeviceSn(newSn);
										gateVo.setGateName(excelData[i][2]);
										gateVo.setInstallLocation(excelData[i][3]);
										gateService.save(gateVo);
										// 更新设备列表中,闸机所属用户
										device.setBelongId(belongId);
										deviceService.updateById(device);
									}
								}

							}

						}

					}
				}
			} else {
				String deviceSn = gateVo.getDeviceSn();
				Integer gateId = gateService.getIdByDeviceSn(deviceSn);
				if (gateId != null) {
					return new ResultObj(400, "sn_occupied");
				}
				Device device = deviceService.queryDeviceByDeviceSn(deviceSn);
				if (device == null) {
					return new ResultObj(400, "sn_dont_exist");
				} else {
					if (device.getBelongId() != null && device.getBelongId() != belongId) {
						return new ResultObj(400, "sn_occupied");
					}
					gateService.save(gateVo);
					// 更新设备列表中,闸机所属用户
					device.setBelongId(belongId);
					deviceService.updateById(device);
				}
			}
			return new ResultObj(Constast.OK,"messages.ADD_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
		}
	}

	/**
	 * 修改一个闸机
	 *
	 * @param gateVo
	 * @return
	 */
	@RequestMapping("updateGate")
	public ResultObj updateGate(@Valid GateVo gateVo, BindingResult bResult) {
		try {

			if (bResult.hasErrors()) {
				String errrorMsg = bResult.getFieldError().getDefaultMessage();
				return new ResultObj(400, errrorMsg);
			}
			Integer gateId = gateVo.getId();
			Gate gate = gateService.getGateById(gateId);

			String deviceSn = gate.getDeviceSn();
			if (!StringUtil.isEmpty(deviceSn)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", 200);
				//type 1-人员 2-闸机  3-下发更新包 4-心跳回复
				map.put("type", 2);
				map.put("msg", "闸机信息已更新!");
				map.put("data", gateVo.getId());
				wsHandler.sendInfo(deviceSn, map.toString());
			}
			gateVo.setUpdateTime(new Date());
			gateService.updateById(gateVo);
			return new ResultObj(Constast.OK,"messages.UPDATE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.UPDATE_ERROR");
		}
	}

	@RequestMapping("updateSetting")
	public ResultObj updateSetting(GateVo gateVo) {
		try {
			if (StringUtil.isEmpty(gateVo.getFaceSet())) {
				gateVo.setFaceSet("off");
			}
			if (StringUtil.isEmpty(gateVo.getInfraredSet())) {
				gateVo.setInfraredSet("off");
			}
			if (StringUtil.isEmpty(gateVo.getTemperatureSet())) {
				gateVo.setTemperatureSet("off");
			}

			Integer gateId = gateVo.getId();
			Gate gate = gateService.getGateById(gateId);
			String deviceSn = gate.getDeviceSn();
			if (!StringUtil.isEmpty(deviceSn)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", 200);
				//type 1-人员 2-闸机  3-下发更新包 4-心跳回复
				map.put("type", 2);
				map.put("msg", "闸机信息已更新!");
				map.put("data", gateVo.getId());
				wsHandler.sendInfo(deviceSn, map.toString());
			}
			gateVo.setUpdateTime(new Date());
			gateService.updateById(gateVo);
			return new ResultObj(Constast.OK,"messages.UPDATE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.UPDATE_ERROR");
		}
	}

	@RequestMapping("activeGate")
	public ResultObj activeGate(GateVo gateVo) {
		try {
			String deviceSn = gateVo.getDeviceSn();
			Device device = deviceService.queryDeviceByDeviceSn(deviceSn);
			if (null == device) {
				new ResultObj(Constast.ERROR,"messages.ACTIVE_ERROR");
			}

			// 更新闸机
			gateVo.setActiveTime(new Date());
			gateVo.setGateStatus(1);
			gateVo.setUpdateTime(new Date());
			gateService.updateById(gateVo);
			// 更新设备列表
			device.setStatus(1);
			device.setUpdateTime(new Date());
			deviceService.updateById(device);

			return  new ResultObj(Constast.OK,"messages.ACTIVE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		return 	new ResultObj(Constast.ERROR,"messages.ACTIVE_ERROR");
		}
	}

	/**
	 * 下发更新通知
	 */
	@RequestMapping("updateApk")
	public ResultObj updateApk(Integer id) {
		Gate gate = gateService.getGateById(id);
		if (null == gate) {
			return new ResultObj(Constast.ERROR,"messages.NOTICE_ERROR");
		}
		Integer onlineStatus = gate.getOnlineStatus();
		if(onlineStatus == 2) {
			return new ResultObj(500,"device_offLine_failed_msg");
		}
		String deviceSn = gate.getDeviceSn();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		//type 1-人员 2-闸机  3-下发更新包 4-心跳回复
		map.put("type", 3);
		map.put("msg", "更新版本通知已下发!");
		wsHandler.sendInfo(deviceSn, map.toString());
		return new ResultObj(Constast.OK,"messages.NOTICE_SUCCESS");
	}

	/**
	 * 删除一个闸机
	 *
	 * @param id
	 *            闸机的ID
	 * @return
	 */
	@RequestMapping("deleteGate")
	public ResultObj deleteGate(Integer id) {
		try {

			Gate gate = gateService.getGateById(id);
			Integer onlineStatus = gate.getOnlineStatus();
			// 闸机在线，不可删除
			if (onlineStatus == 1) {
				return new ResultObj(500, "gate_online_cantDelete");
			}
			// 删除相应的授权表
			Integer authorId = authorService.getIdByGateId(id);
			authorService.removeById(authorId);

			Subject subject = SecurityUtils.getSubject();
			ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
			Integer belongId = activerUser.getUser().getId();
			if (belongId == 1) {
				gateService.removeById(id);
				return  new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
			} else {
				gate.setAvailable(0);
				gate.setUpdateTime(new Date());
				gateService.updateById(gate);
			}
			String deviceSn = gate.getDeviceSn();
			WsSessionManager.removeAndClose(deviceSn);
			return  new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
		}
	}

	/**
	 * 加载所有可用的闸机
	 *
	 * @return
	 */
	@RequestMapping("loadAllGateForSelect")
	public DataGridView loadAllGateForSelect() {
		// 查询当前用户信息
		Subject subject = SecurityUtils.getSubject();
		ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
		Integer belongId = activerUser.getUser().getId();

		QueryWrapper<Gate> queryWrapper = new QueryWrapper<Gate>();
		// belongId为1时是超级管理员，可以查所有
		if (belongId != 1) {
			queryWrapper.eq("belong_id", belongId);
		}
		// 1-已激活 2-未激活
		queryWrapper.eq("gate_status", 1);

		List<Gate> list = gateService.list(queryWrapper);

		return new DataGridView(list);
	}

	@RequestMapping("loadAllGateForTree")
	public DataGridView loadAllGateForTree(Integer id, Integer type) { // type 1 访客 type 2 VIP
		// 查询当前用户信息
		Subject subject = SecurityUtils.getSubject();
		ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
		Integer belongId = activerUser.getUser().getId();
		List<String> ids = new ArrayList<>();
		if (id != null && type != null) {

			switch (type) {
			case 1:
				TempVisitor visitor = visitorService.getById(id);

				if (visitor != null && !StringUtil.isEmpty(visitor.getGateIds())) {
					ids = Arrays.asList(visitor.getGateIds().split(","));
				}
				break;
			case 2:
				VipVisitor vip = vipVisitorService.getById(id);

				if (vip != null && !StringUtil.isEmpty(vip.getGateIds())) {
					ids = Arrays.asList(vip.getGateIds().split(","));
				}

			}

		}

		QueryWrapper<Gate> queryWrapper = new QueryWrapper<Gate>();
		// belongId为1时是超级管理员，可以查所有
		if (belongId != 1) {
			queryWrapper.eq("belong_id", belongId);
		}

		queryWrapper.eq("online_status", 1);
		// 1-已激活 2-未激活
		queryWrapper.eq("gate_status", 1);
		List<Gate> list = gateService.list(queryWrapper);
		List<DTree> treeNodes = new ArrayList<>();
		for (Gate gate : list) {
			String Checked = "0";
			for (int i = 0; i < ids.size(); i++) {
				if (gate.getId() == Integer.parseInt(ids.get(i))) {
					Checked = "1";
				}
			}
			DTree node = new DTree(gate.getId() + "", 0 + "", gate.getGateName(), false);
			node.setCheckArr(Checked);
			treeNodes.add(node);
		}

		return new DataGridView(treeNodes);
	}

	/**
	 * 加载所有未授权的闸机
	 *
	 * @return
	 */
	@RequestMapping("loadAllGateForLeft")
	public DataGridView loadAllGateForLeft() {
		// 查询当前用户信息
		Subject subject = SecurityUtils.getSubject();
		ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
		Integer belongId = activerUser.getUser().getId();

		QueryWrapper<Gate> queryWrapper = new QueryWrapper<Gate>();
		// belongId为1时是超级管理员，可以查所有
		if (belongId != 1) {
			queryWrapper.eq("belong_id", belongId);
		}
		// 1-已激活 2-未激活
		queryWrapper.eq("gate_status", 1);
		queryWrapper.eq("online_status", 1);

		QueryWrapper<GateAuthor> authorQueryWrapper = new QueryWrapper<>();
		if (belongId != 1) {
			authorQueryWrapper.eq("belong_id", belongId);
		}
		List<GateAuthor> auth = authorService.list(authorQueryWrapper);
		List<Integer> ids = new ArrayList<>();
		if (auth.size() > 0) {
			for (int i = 0; i < auth.size(); i++) {
				ids.add(auth.get(i).getGateId());
			}
		}

		if (ids.size() > 0) {
			queryWrapper.notIn("id", ids);
		}

		List<Gate> list = gateService.list(queryWrapper);
		return new DataGridView(list);
	}

}
