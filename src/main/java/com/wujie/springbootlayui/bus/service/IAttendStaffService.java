package com.wujie.springbootlayui.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wujie.springbootlayui.bus.entity.AttendStaff;

public interface IAttendStaffService extends IService<AttendStaff>{
	
	public Integer getRuleIdByStaffId(Integer staffId);

	public AttendStaff getAttendStaffByStaffId(Integer staffId);
	
}
