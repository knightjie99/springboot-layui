package com.wujie.springbootlayui.bus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wujie.springbootlayui.bus.entity.*;
import com.wujie.springbootlayui.bus.vo.AttendSumVo;

import java.util.Date;
import java.util.List;

public interface IAttendService extends IService<Attend>{

	public void createAttendTable(String tName);

	public void deleteTable(String tName);

	AttendSum getAttendSumByStaffId(AttendSumVo attendSumVo);
	
	List<Attend> loadAllAttendByStaffId(Integer staffId, String tName);

	public void insertAttend(Attend attend);
	
	public List<AttendSum> getAttendSumPageList(Page<AttendSum> page, AttendSumVo reqVo);
	
	/**
	 * 无通行记录时考勤
	 */
	public void addAttendNoPassrecord(Staff staff);
	
	/**
	 * 一条考勤记录
	 * @param staff
	 * @param record
	 */
	public void addAttendOnePassrecord(Staff staff, PassRecord record);
	
	/**
	 * 两条或以上考勤记录
	 * @param staff
	 * @param records
	 */
	public void addAttendTwoPassrecord(Staff staff, List<PassRecord> records);
	
	/**
	 * 上班考勤状态
	 */
	public Integer onAttendStatus(Integer staffId, Date passTime);
	
	/**
	 * 下班考勤状态
	 */
	public OffResult offAttendStatus(Integer staffId, Date passTime);
}
