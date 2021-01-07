package com.wujie.springbootlayui.bus.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wujie.springbootlayui.bus.entity.Staff;

public interface IStaffService extends IService<Staff>{
	
	public Staff getStaffById(Integer id);
	/**
	 * 根据部门ID查询部门下的所有员工
	 */
	public List<Staff> getStaffsByDeptId(Integer deptId);
	
	public String  getStaffNameListByIds(List<String> ids);
	
	public List<Staff> getStaffsByBelongId(Integer belongId);
	
	public List<Staff> getGateUnscheduled(Integer belongId);

	public List<Staff> getAttendUnscheduled(Integer belongId);
	
	public List<Staff> getStaffsByIds(List<String> ids);
	
	/**
	 * 查询所有在职员工
	 */
	public List<Staff> getAllWorkStaffs();
}
