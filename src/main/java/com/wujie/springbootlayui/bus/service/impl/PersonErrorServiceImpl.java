package com.wujie.springbootlayui.bus.service.impl;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.bus.entity.PersonError;
import com.wujie.springbootlayui.bus.mapper.PersonErrorMapper;
import com.wujie.springbootlayui.bus.service.IPersonErrorService;

@Service
public class PersonErrorServiceImpl extends ServiceImpl<PersonErrorMapper,PersonError> implements IPersonErrorService{
	
	@Override
	public boolean save(PersonError record) {
		return super.save(record);
	}

	@Override
	public boolean updateById(PersonError record) {
		return super.updateById(record);
	}

	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	@Override
	public PersonError getById(Serializable id) {
		return super.getById(id);
	}

	@Override
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		return super.removeByIds(idList);
	}
}
