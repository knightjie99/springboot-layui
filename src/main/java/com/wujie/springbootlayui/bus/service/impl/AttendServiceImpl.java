package com.wujie.springbootlayui.bus.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.bus.entity.*;
import com.wujie.springbootlayui.bus.mapper.AttendMapper;
import com.wujie.springbootlayui.bus.mapper.AttendRuleMapper;
import com.wujie.springbootlayui.bus.mapper.AttendStaffMapper;
import com.wujie.springbootlayui.bus.service.IAttendService;
import com.wujie.springbootlayui.bus.vo.AttendSumVo;
import com.wujie.springbootlayui.sys.common.DateUtil;
import com.wujie.springbootlayui.sys.common.SpringUtil;
import com.wujie.springbootlayui.sys.common.StringUtil;
import com.wujie.springbootlayui.sys.config.MybatisPlusConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AttendServiceImpl extends ServiceImpl<AttendMapper, Attend> implements IAttendService {
	@Autowired
	private AttendRuleMapper ruleMapper;

	@Autowired
	private AttendStaffMapper attendStaffMapper;


	@Override
	public boolean save(Attend entity) {
		return super.save(entity);
	}

	@Override
	public boolean updateById(Attend entity) {
		return super.updateById(entity);
	}

	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	@Override
	public Attend getById(Serializable id) {
		return super.getById(id);
	}

	@Override
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		return super.removeByIds(idList);
	}

	@Override
	public void createAttendTable(String tName) {
		this.getBaseMapper().createAttendTable(tName);
	}

	@Override
	public void deleteTable(String tName) {
		this.getBaseMapper().deleteTable(tName);
	}

	@Override
	public AttendSum getAttendSumByStaffId(AttendSumVo attendSumVo) {
		return this.getBaseMapper().getAttendSumByStaffId(attendSumVo);
	}

	@Override
	public List<Attend> loadAllAttendByStaffId(Integer staffId,String tName) {
		return this.getBaseMapper().loadAllAttendByStaffId(staffId,tName);
	}

	@Override
	public void insertAttend(Attend attend) {
		this.getBaseMapper().insertAttend(attend);
	}

	@Override
	public void addAttendNoPassrecord(Staff staff) {
		String tName2 = "bus_attend_" + staff.getBelongId();
		MybatisPlusConfig.attendTName.set(tName2);
		Attend attend = new Attend();
		attend.settName(tName2);
		attend.setStaffId(staff.getId());
		attend.setStaffName(staff.getStaffName());
		attend.setDeptId(staff.getDeptId());
		attend.setAttendDate(DateUtil.getDayOfStartTime(new Date()));
		attend.setBelongId(staff.getBelongId());
		attend.setValidAttend(0);
		attend.setOvertimeHours(0d);

		AttendStaffMapper attendStaffMapper = SpringUtil.getBean(AttendStaffMapper.class);
		Integer ruleId = attendStaffMapper.getRuleIdByStaffId(staff.getId());

		// 1-正常 2-迟到 3-早退 4-无签到 5-无签退 6-无班次
		if (ruleId == null) {
			attend.setOnStatus(6);
			attend.setOffStatus(6);
		}else{
			AttendStaff attendStaff = attendStaffMapper.getAttendStaffByStaffId(staff.getId());

			if(attendStaff==null|| StringUtil.isEmpty(attendStaff.getCycleDate())){
				attend.setOnStatus(6);
				attend.setOffStatus(6);
			}else {
				String cycleDate=attendStaff.getCycleDate();
				Integer dayOfWeek=DateUtil.getWeekOfDate(new Date());
				if(cycleDate.contains(dayOfWeek.toString())){
					attend.setOnStatus(4);
					attend.setOffStatus(5);
				}else {
					attend.setOnStatus(6);
					attend.setOffStatus(6);
				}
			}

		}
		attend.setAttendHour(0.0);
		insertAttend(attend);
	}

	@Override
	public Integer onAttendStatus(Integer staffId, Date passTime) {
		AttendStaffMapper attendStaffMapper = SpringUtil.getBean(AttendStaffMapper.class);
		Integer ruleId = attendStaffMapper.getRuleIdByStaffId(staffId);
		if (ruleId == null) {
			return 6;
		}
		AttendRuleMapper ruleMapper = SpringUtil.getBean(AttendRuleMapper.class);
		AttendRule rule = ruleMapper.selectById(ruleId);
		// 签到时间
		Date signTime = DateUtil.pingStringTimeToDate(rule.getSignTime());
		// 开始时间
		Date startSign = DateUtil.pingStringTimeToDate(rule.getStartSign());
		// 结束时间
		Date endSign = DateUtil.pingStringTimeToDate(rule.getEndSign());

		AttendStaff attendStaff = attendStaffMapper.getAttendStaffByStaffId(staffId);
		String cycleDate = attendStaff.getCycleDate();
		Boolean workFlag = false;
		if(cycleDate.contains(DateUtil.getWeekOfDate(new Date()).toString())){
			workFlag=true;
		}

		//无班次
		if(workFlag==false){
			return 6;
		}

		// 无签到
		if (startSign.after(passTime) || endSign.before(passTime)) {
			return 4;
		}
		// 迟到
		if (endSign.after(passTime) && passTime.after(signTime)) {
			return 2;
		}
		return 1;
	}

	@Override
	public OffResult offAttendStatus(Integer staffId, Date passTime) {
		OffResult result = new OffResult();
		AttendStaffMapper attendStaffMapper = SpringUtil.getBean(AttendStaffMapper.class);
		Integer ruleId = attendStaffMapper.getRuleIdByStaffId(staffId);
		if (ruleId == null) {
			result.setResult(6);
			return result;
		}
		AttendRuleMapper ruleMapper = SpringUtil.getBean(AttendRuleMapper.class);
		AttendRule rule = ruleMapper.selectById(ruleId);
		// 签退时间
		Date leaveTime = DateUtil.pingStringTimeToDate(rule.getLeaveTime());
		// 开始签退
		Date startLeave = DateUtil.pingStringTimeToDate(rule.getStartLeave());
		// 结束签退
		Date endLeave = DateUtil.pingStringTimeToDate(rule.getEndLeave());

		AttendStaff attendStaff = attendStaffMapper.getAttendStaffByStaffId(staffId);
		String cycleDate = attendStaff.getCycleDate();
		Boolean workFlag = false;
		if(cycleDate.contains(DateUtil.getWeekOfDate(new Date()).toString())){
			workFlag=true;
		}

		//无班次
		if(workFlag==false){
			result.setResult(6);
			return result;
		}

		if (startLeave.after(passTime) || endLeave.before(passTime)) {
			result.setResult(5);
			return result;
		}
		// 早退
		if (startLeave.before(passTime) && passTime.before(leaveTime)) {
			result.setResult(3);
			return result;
		}

		double overTime = DateUtil.getTwoDatePoor(passTime, leaveTime);
		result.setOverTime(overTime);

		result.setResult(1);
		return result;
	}

	@Override
	public void addAttendOnePassrecord(Staff staff, PassRecord record) {
		// 通行时间
		Date passTime = record.getPassTime();

		String tName2 = "bus_attend_" + staff.getBelongId();
		MybatisPlusConfig.attendTName.set(tName2);
		Attend attend = new Attend();
		attend.settName(tName2);
		attend.setStaffId(staff.getId());
		attend.setStaffName(staff.getStaffName());
		attend.setDeptId(staff.getDeptId());
		attend.setAttendDate(passTime);
		attend.setBelongId(staff.getBelongId());

		Integer staffId = staff.getId();
		// 上班状态
		Integer onStatus = this.onAttendStatus(staffId, passTime);
		// 正常或者迟到 会有相应信息
		if (onStatus == 1 || onStatus == 2) {
			attend.setOnGate(record.getGateId());
			attend.setOnPhoto(staff.getStaffPhoto());
			attend.setOnTime(passTime);
			attend.setValidAttend(1);
		}
		attend.setOnStatus(onStatus);

		// 下班状态
		OffResult offResult=this.offAttendStatus(staffId, passTime);
		Integer offStatus = offResult.getResult();
		Double OverTime =offResult.getOverTime();
		attend.setOvertimeHours(OverTime);
		// 正常或者早退会有相应信息
		if (offStatus == 1 || offStatus == 3) {
			attend.setOffGate(record.getGateId());
			attend.setOffPhoto(staff.getStaffPhoto());
			attend.setOffTime(passTime);
			attend.setValidAttend(1);
		}
		attend.setOffStatus(offStatus);
		attend.setAttendHour(0.0);


		insertAttend(attend);
	}

	@Override
	public void addAttendTwoPassrecord(Staff staff, List<PassRecord> records) {
		String tName2 = "bus_attend_" + staff.getBelongId();
		MybatisPlusConfig.attendTName.set(tName2);
		Attend attend = new Attend();
		attend.settName(tName2);
		attend.setStaffId(staff.getId());
		attend.setStaffName(staff.getStaffName());
		attend.setDeptId(staff.getDeptId());
		attend.setBelongId(staff.getBelongId());

		Integer staffId = staff.getId();
		int size = records.size();
		//第一次打卡
		PassRecord recordFirst = records.get(0);
		Date firstTime = recordFirst.getPassTime();
		// 上班状态
		Integer onStatus = this.onAttendStatus(staffId, firstTime);
		// 正常或者迟到 会有相应信息
		if (onStatus == 1 || onStatus == 2) {
			attend.setOnGate(recordFirst.getGateId());
			attend.setOnPhoto(staff.getStaffPhoto());
			attend.setOnTime(firstTime);
			attend.setValidAttend(1);
		}
		attend.setOnStatus(onStatus);

		//最后一次打卡
		PassRecord recordLast = records.get(size - 1);
		Date lastTime = recordLast.getPassTime();
		// 下班状态
		OffResult offResult=this.offAttendStatus(staffId, lastTime);
		Integer offStatus = offResult.getResult();
		Double OverTime =offResult.getOverTime();
		attend.setOvertimeHours(OverTime);
		// 正常或者早退会有相应信息
		if (offStatus == 1 || offStatus == 3) {
			attend.setOffGate(recordLast.getGateId());
			attend.setOffPhoto(staff.getStaffPhoto());
			attend.setOffTime(lastTime);
			attend.setValidAttend(1);
		}
		attend.setOffStatus(offStatus);
		
		//计算考勤时间
		double attendHour = DateUtil.getTwoDatePoor(recordLast.getPassTime(), recordFirst.getPassTime());
		attend.setAttendHour(attendHour);
		attend.setAttendDate(lastTime);


		insertAttend(attend);
	}

	@Override
	public List<AttendSum> getAttendSumPageList(Page<AttendSum> page, AttendSumVo reqVo) {
		return this.getBaseMapper().getAttendSumPageList(page,reqVo);
	}
}
