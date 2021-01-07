package com.wujie.springbootlayui.sys.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.sys.entity.DeviceError;
import com.wujie.springbootlayui.sys.mapper.DeviceErrorMapper;
import com.wujie.springbootlayui.sys.service.IDeviceErrorService;

@Service
@Transactional
public class DeviceErrorServiceImpl extends ServiceImpl<DeviceErrorMapper,DeviceError> implements IDeviceErrorService{

	@Override
	public DeviceError getById(Serializable id) {
		return super.getById(id);
	}

	@Override
	public boolean update(DeviceError entity, Wrapper<DeviceError> updateWrapper) {
		return super.update(entity, updateWrapper);
	}

	@Override
	public boolean updateById(DeviceError entity) {
		return super.updateById(entity);
	}

	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	@Override
	public boolean save(DeviceError entity) {
		return super.save(entity);
	}
	
}
