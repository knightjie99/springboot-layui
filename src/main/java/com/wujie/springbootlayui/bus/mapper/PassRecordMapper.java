package com.wujie.springbootlayui.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wujie.springbootlayui.bus.entity.HourPass;
import com.wujie.springbootlayui.bus.entity.PassRecord;
import com.wujie.springbootlayui.bus.entity.recordRank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PassRecordMapper extends BaseMapper<PassRecord>{
	
	public void createPassRecordTable(@Param("tName") String tName);

	public void insertPassRecord(PassRecord record);

	void deletePassRecordByUid(@Param("belong_id") Integer id);

	public void deleteTable(@Param("tName") String tName);

	public List<HourPass> selectByHour(@Param("tName") String tName, @Param("belong_id") Integer id,
                                       @Param("timeRange") Integer timeRange, @Param("gateId") Integer gateId);

	public List<recordRank> recordsRank(@Param("tName") String tName, @Param("begin") String begin, @Param("end") String end);
}
