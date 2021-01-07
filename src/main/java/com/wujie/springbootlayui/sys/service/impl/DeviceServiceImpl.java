package com.wujie.springbootlayui.sys.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.sys.entity.Device;
import com.wujie.springbootlayui.sys.mapper.DeviceMapper;
import com.wujie.springbootlayui.sys.service.IDeviceService;

@Service
@Transactional
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

	@Override
	public Device getById(Serializable id) {
		return super.getById(id);
	}

	@Override
	public boolean update(Device entity, Wrapper<Device> updateWrapper) {
		return super.update(entity, updateWrapper);
	}

	@Override
	public boolean updateById(Device entity) {
		return super.updateById(entity);
	}

	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	@Override
	public boolean save(Device entity) {
		return super.save(entity);
	}

	@Override
	public Integer queryBelongIdByDeviceSn(String deviceSn) {
		return this.getBaseMapper().queryBelongIdByDeviceSn(deviceSn);
	}

	@Override
	public String selectSnGroup() {
		return this.getBaseMapper().selectSnGroup();
	}

	@Override
	public Device queryDeviceByDeviceSn(String deviceSn) {
		return this.getBaseMapper().queryDeviceByDeviceSn(deviceSn);
	}

	@Override
	public void resetBelongId(Integer belongId) {
		 this.getBaseMapper().resetBelongId(belongId);
	}
}
