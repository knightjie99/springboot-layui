package com.wujie.springbootlayui.bus.service.impl;

import java.io.Serializable;
import java.util.Collection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.bus.entity.AttendStaff;
import com.wujie.springbootlayui.bus.mapper.AttendStaffMapper;
import com.wujie.springbootlayui.bus.service.IAttendStaffService;

@Service
@Transactional
public class AttendStaffServiceImpl extends ServiceImpl<AttendStaffMapper,AttendStaff> implements IAttendStaffService{

	@Override
	public boolean save(AttendStaff entity) {
		return super.save(entity);
	}

	@Override
	public boolean updateById(AttendStaff entity) {
		return super.updateById(entity);
	}

	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	@Override
	public AttendStaff getById(Serializable id) {
		return super.getById(id);
	}

	@Override
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		return super.removeByIds(idList);
	}

	@Override
	public Integer getRuleIdByStaffId(Integer staffId) {
		return this.getBaseMapper().getRuleIdByStaffId(staffId);
	}

	@Override
	public AttendStaff getAttendStaffByStaffId(Integer staffId) {
		return this.getBaseMapper().getAttendStaffByStaffId(staffId);
	}

}
