package com.wujie.springbootlayui.bus.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.bus.entity.Staff;
import com.wujie.springbootlayui.bus.mapper.StaffMapper;
import com.wujie.springbootlayui.bus.service.IStaffService;

@Service
@Transactional
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements IStaffService {

	@Override
	public boolean save(Staff entity) {
		return super.save(entity);
	}

	@Override
	public boolean updateById(Staff entity) {
		return super.updateById(entity);
	}

	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	@Override
	public Staff getById(Serializable id) {
		return super.getById(id);
	}

	@Override
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		return super.removeByIds(idList);
	}

	@Override
	public List<Staff> getStaffsByDeptId(Integer deptId) {
		return this.getBaseMapper().getStaffsByDeptId(deptId);
	}

	@Override
	public String getStaffNameListByIds(List<String> ids) {
		return this.getBaseMapper().getStaffNameListByIds(ids);
	}

	@Override
	public List<Staff> getStaffsByBelongId(Integer belongId) {
		return this.getBaseMapper().getStaffsByBelongId(belongId);
	}

	@Override
	public List<Staff> getGateUnscheduled(Integer belongId) {
		return this.getBaseMapper().getGateUnscheduled(belongId);
	}

	@Override
	public List<Staff> getAttendUnscheduled(Integer belongId) {
		return this.getBaseMapper().getAttendUnscheduled(belongId);
	}

	@Override
	public Staff getStaffById(Integer id) {
		return this.getBaseMapper().getStaffById(id);
	}

	@Override
	public List<Staff> getStaffsByIds(List<String> ids) {
		return this.getBaseMapper().getStaffsByIds(ids);
	}

	@Override
	public List<Staff> getAllWorkStaffs() {
		// 获取员工表
		QueryWrapper<Staff> queryStaff = new QueryWrapper<Staff>();
		// 在职员工
		queryStaff.eq("status", 1);
		queryStaff.eq("available", 1);
		List<Staff> staffs = this.getBaseMapper().selectList(queryStaff);
		return staffs;
	}

}
