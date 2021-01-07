package com.wujie.springbootlayui.bus.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.bus.entity.Gate;
import com.wujie.springbootlayui.bus.mapper.GateMapper;
import com.wujie.springbootlayui.bus.service.IGateService;


@Service
@Transactional
public class GateServiceImpl extends ServiceImpl<GateMapper,Gate> implements IGateService{

	@Override
	public boolean save(Gate entity) {
		return super.save(entity);
	}

	@Override
	public boolean updateById(Gate entity) {
		return super.updateById(entity);
	}

	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	@Override
	public Gate getById(Serializable id) {
		return super.getById(id);
	}

	@Override
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		return super.removeByIds(idList);
	}

	@Override
	public Integer getIdByDeviceSn(String deviceSn) {
		return this.getBaseMapper().getIdByDeviceSn(deviceSn);
	}

	@Override
	public Gate getGateById(Integer id) {
		return this.getBaseMapper().getGateById(id);
	}

	@Override
	public List<Gate> getAllOnlineGateByBelong(Integer belongId) {
		return this.getBaseMapper().getAllOnlineGateByBelong(belongId);
	}

	@Override
	public List<Gate> getAllGatesByVerType(Integer verType) {
		return this.getBaseMapper().getAllGatesByVerType(verType);
	}

}
