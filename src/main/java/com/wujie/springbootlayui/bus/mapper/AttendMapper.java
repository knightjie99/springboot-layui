package com.wujie.springbootlayui.bus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.bus.entity.Attend;
import com.wujie.springbootlayui.bus.entity.AttendSum;
import com.wujie.springbootlayui.bus.vo.AttendSumVo;

public interface AttendMapper extends BaseMapper<Attend>{

	AttendSum getAttendSumByStaffId(@Param("vo") AttendSumVo vo);

	List<Attend> loadAllAttendByStaffId(@Param("staffId") Integer staffId, @Param("tName") String tName);

	public void createAttendTable(@Param("tName") String tName);

	public void insertAttend(Attend attend);

	public void deleteTable(@Param("tName") String tName);

	List<AttendSum> getAttendSumPageList(Page<AttendSum> page, AttendSumVo reqVo);

}
