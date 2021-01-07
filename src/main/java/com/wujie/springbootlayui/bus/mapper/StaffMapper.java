package com.wujie.springbootlayui.bus.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wujie.springbootlayui.bus.entity.Staff;

public interface StaffMapper extends BaseMapper<Staff>{

	List<Staff> getStaffsByDeptId(@Param("deptId") Integer deptId);

	String getStaffNameListByIds(@Param("ids") List<String> ids);

	List<Staff> getStaffsByBelongId(@Param("belongId") Integer belongId);
	
	List<Staff> getGateUnscheduled(@Param("belongId") Integer belongId);

	List<Staff> getAttendUnscheduled(@Param("belongId") Integer belongId);

	Staff getStaffById(@Param("id") Integer id);

	List<Staff> getStaffsByIds(@Param("ids") List<String> ids);

	void deleteStaffByUid(@Param("belongId") Integer id);

}
