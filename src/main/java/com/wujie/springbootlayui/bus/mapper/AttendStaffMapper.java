package com.wujie.springbootlayui.bus.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wujie.springbootlayui.bus.entity.AttendStaff;

public interface AttendStaffMapper extends BaseMapper<AttendStaff>{

	Integer getRuleIdByStaffId(@Param("staffId") Integer staffId);

	AttendStaff getAttendStaffByStaffId(@Param("staffId") Integer staffId);

	void deleteAttendStaffByUid(@Param("belong_id") Integer id);

}
