package com.wujie.springbootlayui.bus.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wujie.springbootlayui.bus.entity.*;
import com.wujie.springbootlayui.bus.service.*;
import com.wujie.springbootlayui.sys.common.*;
import com.wujie.springbootlayui.sys.config.MybatisPlusConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.sys.entity.App;
import com.wujie.springbootlayui.sys.entity.AppRsp;
import com.wujie.springbootlayui.sys.entity.Dept;
import com.wujie.springbootlayui.sys.entity.User;
import com.wujie.springbootlayui.sys.entity.UserRsp;
import com.wujie.springbootlayui.sys.service.IAppService;
import com.wujie.springbootlayui.sys.service.IDeptService;
import com.wujie.springbootlayui.sys.service.IDeviceService;
import com.wujie.springbootlayui.sys.service.IUserService;

@RestController
@RequestMapping("/android")
public class AndroidController {

	@Value("${photo.download.path}")
	private String downloadPath;

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IStaffService staffService;

	@Autowired
	private IDeptService deptService;

	@Autowired
	private IGateService gateService;

	@Autowired
	private IGateAuthorService gateAuthorService;

	@Autowired
	private ITempVisitorService visitorService;

	@Autowired
	private IPassRecordService passRecordService;

	@Autowired
	private IAttendService attendService;

	@Autowired
	private IPersonErrorService personErrorService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IAppService appService;

	@Autowired
	private IVipVisitorService vipVisitorService;

	@Autowired
	private IBlackVisitorService blackService;

	@RequestMapping(value = "queryAllPerson", method = RequestMethod.POST)
	public ResultObj queryAllStaff(@RequestBody Map<String, String> map) {
		String deviceSn = map.get("deviceSn");
		if (StringUtil.isEmpty(deviceSn)) {
			return new ResultObj(400, "sn_cantBe_null");
		}
		Integer belongId = deviceService.queryBelongIdByDeviceSn(deviceSn);
		Integer gateId = gateService.getIdByDeviceSn(deviceSn);

		List<PersonRsp> personRsp = new ArrayList<PersonRsp>();

		if (gateId != null) {
			List<BlackVisitor> blackLists = blackService.getBlackVisitorByBelongId(belongId);
			for (BlackVisitor black : blackLists) {
				PersonRsp person = new PersonRsp();
				person.setPersonId(black.getId());
				person.setIdentityType(4);
				person.setPersonName(black.getName());
				person.setIdNum(black.getIdNum());
				person.setPhone(black.getPhone());
				person.setPersonPhoto(downloadPath + black.getVisitorPhoto());
				String createTime = DateUtil.parseDateToString(black.getCreateTime());
				person.setCreateTime(createTime);
				String updateTime = DateUtil.parseDateToString(black.getUpdateTime());
				person.setUpdateTime(updateTime);
				personRsp.add(person);
			}

			List<VipVisitor> vipVisitors = vipVisitorService.getValidVipVisitors(belongId, gateId);

			for (VipVisitor vip : vipVisitors) {
				PersonRsp person = new PersonRsp();
				person.setPersonId(vip.getId());
				person.setIdentityType(3);
				person.setPersonName(vip.getVisitorName());
				person.setPersonPhoto(downloadPath + vip.getVisitorPhoto());
				person.setValidTime(vip.getVisitTime());
				person.setInvalidTime(vip.getLeaveTime());
				person.setPhone(vip.getPhone());
				person.setIdNum(vip.getIdNum());
				person.setCycleDate(vip.getCycleDate());
				String createTime = DateUtil.parseDateToString(vip.getCreateTime());
				person.setCreateTime(createTime);
				String updateTime = DateUtil.parseDateToString(vip.getUpdateTime());
				person.setUpdateTime(updateTime);
				personRsp.add(person);
			}

			List<TempVisitor> visitors = visitorService.getValidVisitors(belongId, gateId);

			for (TempVisitor visitor : visitors) {
				PersonRsp person = new PersonRsp();
				person.setPersonId(visitor.getId());
				person.setIdentityType(2);
				person.setPersonName(visitor.getVisitorName());
				person.setPersonPhoto(downloadPath + visitor.getVisitorPhoto());
				String validTime = DateUtil.parseDateToString(visitor.getVisitTime());
				person.setValidTime(validTime);
				String invalidTime = DateUtil.parseDateToString(visitor.getLeaveTime());
				person.setInvalidTime(invalidTime);
				person.setSex(visitor.getSex());
				person.setPhone(visitor.getPhone());
				person.setIdNum(visitor.getIdNum());
				String createTime = DateUtil.parseDateToString(visitor.getCreateTime());
				person.setCreateTime(createTime);
				String updateTime = DateUtil.parseDateToString(visitor.getUpdateTime());
				person.setUpdateTime(updateTime);
				personRsp.add(person);

			}

			Integer authorId = gateAuthorService.getIdByGateId(gateId);
			if (authorId != null) {
				GateAuthor author = gateAuthorService.getGateAuthorById(authorId);
				String staffList = author.getStaffList();
				List<String> ids = Arrays.asList(staffList.split(","));
				List<Staff> staffs = staffService.getStaffsByIds(ids);
				for (Staff staff : staffs) {
					PersonRsp person = new PersonRsp();
					person.setPersonId(staff.getId());
					person.setIdentityType(1);
					person.setPersonName(staff.getStaffName());
					String prompt = staff.getStaffName();
					if(!StringUtil.isEmpty(prompt)) {
						person.setPrompt(prompt);
					}
					person.setSex(staff.getSex());
					person.setPhone(staff.getPhone());
					person.setIdNum(staff.getIdNum());
					person.setPersonPhoto(downloadPath + staff.getStaffPhoto());
					person.setCardNum(staff.getCardNum());
					Integer deptId = staff.getDeptId();
					if (deptId != null) {
						Dept dept = deptService.getDeptById(deptId);
						if (dept != null) {
							person.setDeptName(dept.getName());
						} else {
							person.setDeptName("部门已删除");
						}
					}

					String createTime = DateUtil.parseDateToString(staff.getCreateTime());
					person.setCreateTime(createTime);
					String updateTime = DateUtil.parseDateToString(staff.getUpdateTime());
					person.setUpdateTime(updateTime);
					personRsp.add(person);

				}

			}
		}
		return new ResultObj(personRsp);
	}

	@RequestMapping(value = "addPassRecord", method = RequestMethod.POST)
	public ResultObj addPassRecord(@RequestBody PassRecordReq record) {
		PassRecord newRecord = new PassRecord();
		String deviceSn = record.getDeviceSn();
		if (StringUtil.isEmpty(deviceSn)) {
			return new ResultObj(400, "sn_cantBe_null");
		}
		Integer belongId = deviceService.queryBelongIdByDeviceSn(deviceSn);
		newRecord.setBelongId(belongId);

		// 设置闸机
		Integer gateId = gateService.getIdByDeviceSn(deviceSn);
		newRecord.setGateId(gateId);

		Date passTime = DateUtil.parseStringDayToDate(record.getPassTime());
		newRecord.setPassTime(passTime);
		Integer passWay = record.getPassWay();
		newRecord.setPassWay(passWay);
		String cardNum = record.getCardNum();
		if (!StringUtil.isEmpty(cardNum)) {
			newRecord.setCardNum(cardNum);
		}
		newRecord.setTemperature(record.getTemperature());
		newRecord.setPassDirection(record.getPassDirection());

		Integer personId = record.getPersonId();

		/** 1-员工 2-访客 3-VIP 4-黑名单 */
		Integer identityType = record.getIdentityType();
		newRecord.setIdentityType(identityType);

		switch (identityType) {
		case 1:
			Staff staff = staffService.getStaffById(personId);
			if (staff == null) {
				return new ResultObj(500, "messages.no_valid_staff");
			}
			newRecord.setName(staff.getStaffName());
			newRecord.setSex(staff.getSex());
			newRecord.setIdNum(staff.getIdNum());
			newRecord.setPhone(staff.getPhone());
			newRecord.setFacePhoto(staff.getStaffPhoto());
			newRecord.setCardNum(staff.getCardNum());
			break;
		case 2:
			TempVisitor visitor = visitorService.getVisitorById(personId);
			if (visitor == null) {
				return new ResultObj(500, "messages.no_valid_visitor");
			}
			newRecord.setName(visitor.getVisitorName());
			newRecord.setSex(visitor.getSex());
			newRecord.setIdNum(visitor.getIdNum());
			newRecord.setPhone(visitor.getPhone());
			newRecord.setFacePhoto(visitor.getVisitorPhoto());
			break;
		case 3:
			VipVisitor vip = vipVisitorService.getVipVisitorById(personId);
			if (vip == null) {
				return new ResultObj(500, "messages.no_valid_visitor");
			}
			// 是否短信通知关联人员
			String connectedPhone = vip.getConnectedPhone();
			if (!StringUtil.isEmpty(connectedPhone)&&StringUtil.isPhone(connectedPhone)) {
				QueryWrapper<PassRecord> queryWrapper = new QueryWrapper<PassRecord>();
				queryWrapper.eq("belong_id", belongId);
				queryWrapper.eq("id_num", vip.getIdNum());
				queryWrapper.le("pass_time",DateUtil.getDayOfEndTime(new Date()));
				queryWrapper.ge("pass_time",DateUtil.getDayOfStartTime(new Date()));
				String tName = "bus_pass_record_"+belongId;
				MybatisPlusConfig.passRecordTName.set(tName);
				List<PassRecord> list = passRecordService.list(queryWrapper);
				if (list.size() == 0) {
					SMSUtil.sendSms(connectedPhone,vip.getVisitorName(), 3);
				}
			}
			newRecord.setName(vip.getVisitorName());
			newRecord.setIdNum(vip.getIdNum());
			newRecord.setPhone(vip.getPhone());
			newRecord.setFacePhoto(vip.getVisitorPhoto());
			break;
		case 4:
			BlackVisitor black = blackService.getBlackVisitorById(personId);
			if (black == null) {
				return new ResultObj(500, "messages.no_valid_visitor");
			}
			newRecord.setName(black.getName());
			newRecord.setIdNum(black.getIdNum());
			newRecord.setPhone(black.getPhone());
			newRecord.setFacePhoto(black.getVisitorPhoto());
			break;
		default:
			return new ResultObj(500, "messages.recognize_person_error");
		}
		String tName = "bus_pass_record_"+belongId;
		newRecord.settName(tName);
		passRecordService.insertPassRecord(newRecord);
		return new ResultObj(Constast.OK,"messages.ADD_SUCCESS");
	}

	@RequestMapping(value = "queryStaffAttend", method = RequestMethod.POST)
	public Object queryStaffAttend(@RequestBody Map<String, Object> map) {

		String deviceSn = (String) map.get("deviceSn");
		Integer pageNum = (Integer) map.get("pageNum");
		Integer pageSize = (Integer) map.get("pageSize");
		if (StringUtil.isEmpty(deviceSn)) {
			return new ResultObj(400, "sn_cantBe_null");
		}
		Integer belongId = deviceService.queryBelongIdByDeviceSn(deviceSn);
		String tName2 = "bus_attend_" + belongId;
		MybatisPlusConfig.attendTName.set(tName2);
		IPage<Attend> page = new Page<Attend>(pageNum, pageSize);
		QueryWrapper<Attend> queryWrapper = new QueryWrapper<Attend>();
		queryWrapper.eq("belong_id", belongId);
		queryWrapper.orderByDesc("attend_date");
		attendService.page(page, queryWrapper);
		List<Attend> attends = page.getRecords();
		List<AttendRsp> attendRsp = new ArrayList<>();
		for (Attend attend : attends) {
			AttendRsp rsp = new AttendRsp();
			rsp.setId(attend.getId());
			rsp.setStaffName(attend.getStaffName());
			rsp.setOnStatus(attend.getOnStatus());
			rsp.setOffStatus(attend.getOffStatus());

			Date onTime = attend.getOnTime();
			if (onTime != null) {
				rsp.setOnTime(attend.getOnTime());
			}

			Date offTime = attend.getOffTime();
			if (offTime != null) {
				rsp.setOffTime(offTime);
			}

			Integer deptId = attend.getDeptId();
			if (deptId != null) {
				Dept dept = deptService.getDeptById(deptId);
				if (dept != null) {
					rsp.setDeptName(dept.getName());
				} else {
					rsp.setDeptName("部门已删除");
				}
			}

			Integer ongateId = attend.getOffGate();
			if (ongateId != null) {
				Gate onGate = gateService.getGateById(ongateId);
				if (onGate != null) {
					rsp.setOnGateName(onGate.getGateName());
				} else {
					rsp.setOnGateName("闸机已删除");
				}
			}

			Integer offgateId = attend.getOffGate();
			if (offgateId != null) {
				Gate offGate = gateService.getGateById(offgateId);
				if (offGate != null) {
					rsp.setOffGateName(offGate.getGateName());
				} else {
					rsp.setOffGateName("闸机已删除");
				}
			}

			attendRsp.add(rsp);
		}

		return new PageResult(page.getTotal(), page.getPages(), attendRsp);
	}

	// 获取服务器配置接口
	@RequestMapping(value = "queryGateConfig", method = RequestMethod.POST)
	public ResultObj queryGateConfig(@RequestBody Map<String, String> map) {
		String deviceSn = map.get("deviceSn");
		if (StringUtil.isEmpty(deviceSn)) {
			return new ResultObj(400, "sn_cantBe_null");
		}
		Integer gateId = gateService.getIdByDeviceSn(deviceSn);
		if (gateId == null) {
			return new ResultObj(500, "messages.no_valid_gate");
		}
		Gate gate = gateService.getGateById(gateId);

		GateConfigRsp rsp = new GateConfigRsp();
		rsp.setId(gate.getId());
		rsp.setGateName(gate.getGateName());
		rsp.setFaceSet(gate.getFaceSet());
		rsp.setInfraredSet(gate.getInfraredSet());
		rsp.setTemperatureSet(gate.getTemperatureSet());
		return new ResultObj(rsp);
	}

	// 获取服务器配置接口
	@RequestMapping(value = "updateGateConfig", method = RequestMethod.POST)
	public ResultObj updateGateConfig(@RequestBody GateReq req) {
		String deviceSn = req.getDeviceSn();
		if (StringUtil.isEmpty(deviceSn)) {
			return new ResultObj(400, "sn_cantBe_null");
		}
		Integer gateId = gateService.getIdByDeviceSn(deviceSn);
		if (gateId == null) {
			return new ResultObj(500, "messages.no_valid_gate");
		}
		Gate gate = gateService.getById(gateId);
		String faceSet = req.getFaceSet();
		if (!StringUtil.isEmpty(faceSet)) {
			gate.setFaceSet(faceSet);
		}
		String infraredSet = req.getInfraredSet();
		if (!StringUtil.isEmpty(infraredSet)) {
			gate.setInfraredSet(infraredSet);
		}
		String temperatureSet = req.getTemperatureSet();
		if (!StringUtil.isEmpty(temperatureSet)) {
			gate.setTemperatureSet(temperatureSet);
		}
		gateService.updateById(gate);
		return new ResultObj(200, "messages.UPDATE_SUCCESS");
	}

	/**
	 * 上传人员异常信息
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "addPersonError", method = RequestMethod.POST)
	public ResultObj addPersonError(@RequestBody PersonErrorReq req) {

		Integer personId = req.getPersonId();
		Integer identityType = req.getIdentityType();
		if (null == personId || null == identityType) {
			return new ResultObj(500, "messages.request_param_error");
		}
		String errorMsg = req.getErrorMsg();
		PersonError error = new PersonError();
		if (identityType == 1) {
			// 员工
			Staff staff = staffService.getStaffById(personId);

			if (StringUtil.isEmpty(errorMsg)) {
				staff.setRecogStatus(1);
			} else {
				error.setName(staff.getStaffName());
				error.setIdentityType(1);
				error.setIdNum(staff.getIdNum());
				error.setPhone(staff.getPhone());
				error.setErrorMsg(errorMsg);
				error.setPersonId(personId);
				error.setPersonPhoto(staff.getStaffPhoto());
				error.setCreateTime(new Date());
				error.setBelongId(staff.getBelongId());
				personErrorService.save(error);

				staff.setRecogStatus(2);
			}

			staffService.updateById(staff);
		}

		if (identityType == 2) {
			// 访客
			TempVisitor visitor = visitorService.getById(personId);

			if (StringUtil.isEmpty(errorMsg)) {
				visitor.setRecogStatus(1);
			} else {
				visitor.setRecogStatus(2);
				error.setName(visitor.getVisitorName());
				error.setIdentityType(2);
				error.setIdNum(visitor.getIdNum());
				error.setPhone(visitor.getPhone());
				error.setErrorMsg(errorMsg);
				error.setPersonId(personId);
				error.setPersonPhoto(visitor.getVisitorPhoto());
				error.setCreateTime(new Date());
				error.setBelongId(visitor.getBelongId());
				personErrorService.save(error);
			}
			visitorService.updateById(visitor);
		}
		return new ResultObj(200, "messages.sync_person_success");
	}

	/**
	 * 查询闸机信息
	 */
	@RequestMapping(value = "queryGateInfo", method = RequestMethod.POST)
	public ResultObj queryGateInfo(@RequestBody Map<String, String> map) {
		String deviceSn = map.get("deviceSn");
		if (StringUtil.isEmpty(deviceSn)) {
			return new ResultObj(400, "sn_cantBe_null");
		}
		Integer gateId = gateService.getIdByDeviceSn(deviceSn);
		if (gateId == null) {
			return new ResultObj(500, "messages.no_valid_gate");
		}
		Gate gate = gateService.getGateById(gateId);

		GateInfoRsp rsp = new GateInfoRsp();
		rsp.setId(gate.getId());
		rsp.setGateName(gate.getGateName());
		rsp.setGateStatus(gate.getGateStatus());
		rsp.setInstallLocation(rsp.getInstallLocation());
		rsp.setOnlineStatus(gate.getOnlineStatus());
		rsp.setActiveTime(gate.getActiveTime());
		rsp.setCurrentVer(gate.getCurrentVer());
		rsp.setNewestVer(gate.getNewestVer());
		rsp.setRemark(gate.getRemark());
		rsp.setCreateTime(gate.getCreateTime());
		rsp.setUpdateTime(gate.getUpdateTime());
		return new ResultObj(rsp);
	}

	/**
	 * 获取企业信息
	 */
	@RequestMapping(value = "queryUserInfo", method = RequestMethod.POST)
	public ResultObj queryUserInfo(@RequestBody Map<String, String> map) {
		String deviceSn = map.get("deviceSn");
		if (StringUtil.isEmpty(deviceSn)) {
			return new ResultObj(400, "sn_cantBe_null");
		}
		Integer belongId = deviceService.queryBelongIdByDeviceSn(deviceSn);
		UserRsp rsp = new UserRsp();
		if (belongId != null) {
			User user = userService.getById(belongId);
			rsp.setName(user.getName());
			String photo = user.getUserPhoto();
			if (StringUtil.isEmpty(photo)) {
				rsp.setPhoto(downloadPath + "face.jpg");
			} else {
				rsp.setPhoto(downloadPath + user.getUserPhoto());
			}
		}
		return new ResultObj(rsp);
	}

	/**
	 * 获取升级包最新的版本信息
	 */
	@RequestMapping(value = "downloadApk", method = RequestMethod.POST)
	public ResultObj downloadApk(@RequestBody Map<String, Integer> map) {
		QueryWrapper<App> queryWrapper = new QueryWrapper<App>();
		Integer verType = map.get("verType");
		if(verType != null) {
			queryWrapper.eq("ver_type", verType);
		}
		queryWrapper.orderByDesc("id");
		List<App> apps = appService.list(queryWrapper);
		AppRsp rsp = new AppRsp();
		if (apps.size() > 0) {
			App app = apps.get(0);
			rsp.setApkVer(app.getAppVer());
			rsp.setVerType(app.getVerType());
			rsp.setDownloadPath(downloadPath + app.getAppUrl());
		}
		return new ResultObj(rsp);
	}
}
