package com.wujie.springbootlayui.sys.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.sys.entity.App;
import com.wujie.springbootlayui.sys.mapper.AppMapper;
import com.wujie.springbootlayui.sys.service.IAppService;


@Service
@Transactional
public class AppServiceImpl extends ServiceImpl<AppMapper, App> implements IAppService {
	
	@Override
	public App getById(Serializable id) {
		return super.getById(id);
	}

	@Override
	public boolean update(App entity, Wrapper<App> updateWrapper) {
		return super.update(entity, updateWrapper);
	}

	@Override
	public boolean updateById(App entity) {
		return super.updateById(entity);
	}

	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	@Override
	public boolean save(App entity) {
		return super.save(entity);
	}

	@Override
	public Integer getPreviousId(Integer id) {
		return this.getBaseMapper().getPreviousId(id);
	}

	@Override
	public App getAppById(Integer id) {
		return this.getBaseMapper().getAppById(id);
	}
}
