package com.wujie.springbootlayui.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.bus.entity.HourPass;
import com.wujie.springbootlayui.bus.entity.PassRecord;
import com.wujie.springbootlayui.bus.entity.Staff;
import com.wujie.springbootlayui.bus.entity.recordRank;
import com.wujie.springbootlayui.bus.mapper.PassRecordMapper;
import com.wujie.springbootlayui.bus.service.IPassRecordService;
import com.wujie.springbootlayui.sys.common.DateUtil;
import com.wujie.springbootlayui.sys.config.MybatisPlusConfig;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Service
public class PassRecordServiceImpl extends ServiceImpl<PassRecordMapper ,PassRecord>implements IPassRecordService{

	@Override
	public boolean save(PassRecord record) {
		return super.save(record);
	}

	@Override
	public void deleteTable(String tName) {
		this.getBaseMapper().deleteTable(tName);
	}

	@Override
	public boolean updateById(PassRecord record) {
		return super.updateById(record);
	}

	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	@Override
	public PassRecord getById(Serializable id) {
		return super.getById(id);
	}

	@Override
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		return super.removeByIds(idList);
	}

	@Override
	public void createPassRecordTable(String tName) {
		this.getBaseMapper().createPassRecordTable(tName);
	}

	@Override
	public void insertPassRecord(PassRecord record) {
		this.getBaseMapper().insertPassRecord(record);
	}

	@Override
	public List<PassRecord> getCurrentDayPassRecord(Staff staff) {
		QueryWrapper<PassRecord> queryRecord = new QueryWrapper<PassRecord>();
		queryRecord.ge("pass_time", DateUtil.getDayOfStartTime(new Date()));
		queryRecord.le("pass_time", DateUtil.getDayOfEndTime(new Date()));
		queryRecord.eq("name", staff.getStaffName());
		queryRecord.eq("id_num", staff.getIdNum());
		queryRecord.eq("belong_id", staff.getBelongId());
		queryRecord.eq("identity_type", 1);
		// 按时间字段升序排列
		queryRecord.orderByAsc("pass_time");
		String tName = "bus_pass_record_" + staff.getBelongId();
		MybatisPlusConfig.passRecordTName.set(tName);
		List<PassRecord> records = this.getBaseMapper().selectList(queryRecord);
		return records;
	}

	@Override
	public List<HourPass> selectByHour(String tName, Integer id, Integer timeRange,Integer gateId) {
		return this.getBaseMapper().selectByHour(tName,id,timeRange,gateId);
	}

	@Override
	public List<recordRank> recordsRank(String tName, String begin, String end) {
		return this.getBaseMapper().recordsRank(tName,begin,end);
	}
}
