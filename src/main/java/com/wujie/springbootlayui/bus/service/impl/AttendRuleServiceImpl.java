package com.wujie.springbootlayui.bus.service.impl;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.bus.entity.AttendRule;
import com.wujie.springbootlayui.bus.mapper.AttendRuleMapper;
import com.wujie.springbootlayui.bus.service.IAttendRuleService;


@Service
@Transactional
public class AttendRuleServiceImpl extends ServiceImpl<AttendRuleMapper, AttendRule> implements IAttendRuleService {

	@Override
	public boolean save(AttendRule entity) {
		return super.save(entity);
	}

	@Override
	public boolean updateById(AttendRule entity) {
		return super.updateById(entity);
	}

	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	@Override
	public AttendRule getById(Serializable id) {
		return super.getById(id);
	}

	@Override
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		return super.removeByIds(idList);
	}

}
