package com.wujie.springbootlayui.bus.service.impl;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.bus.entity.StaffCard;
import com.wujie.springbootlayui.bus.mapper.StaffCardMapper;
import com.wujie.springbootlayui.bus.service.IStaffCardService;

@Service
@Transactional
public class StaffCardServiceImpl extends ServiceImpl<StaffCardMapper, StaffCard> implements IStaffCardService {
	
	@Override
	public boolean save(StaffCard entity) {
		return super.save(entity);
	}

	@Override
	public boolean updateById(StaffCard entity) {
		return super.updateById(entity);
	}

	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	@Override
	public StaffCard getById(Serializable id) {
		return super.getById(id);
	}

	@Override
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		return super.removeByIds(idList);
	}

	@Override
	public String getBinded(Integer belongId) {
		return this.baseMapper.getBinded(belongId);
	}
}
