package com.wujie.springbootlayui.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wujie.springbootlayui.bus.entity.HourPass;
import com.wujie.springbootlayui.bus.entity.PassRecord;
import com.wujie.springbootlayui.bus.entity.Staff;
import com.wujie.springbootlayui.bus.entity.recordRank;

import java.util.List;

public interface IPassRecordService extends IService<PassRecord>{

	public void createPassRecordTable(String tName);
	
	public void insertPassRecord(PassRecord record);

	public void deleteTable(String tName);

	/**
	 * 查询员工当天的通行记录
	 */
	public List<PassRecord> getCurrentDayPassRecord(Staff staff);

	public List<HourPass> selectByHour(String tName, Integer id, Integer timeRange, Integer gateId);

	public List<recordRank> recordsRank(String tName, String begin, String end);
}
