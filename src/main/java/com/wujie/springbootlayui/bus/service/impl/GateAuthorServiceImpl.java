package com.wujie.springbootlayui.bus.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.bus.entity.GateAuthor;
import com.wujie.springbootlayui.bus.mapper.GateAuthorMapper;
import com.wujie.springbootlayui.bus.service.IGateAuthorService;

@Service
@Transactional
public class GateAuthorServiceImpl extends ServiceImpl<GateAuthorMapper, GateAuthor> implements IGateAuthorService {

	@Override
	public boolean save(GateAuthor entity) {
		return super.save(entity);
	}

	@Override
	public boolean updateById(GateAuthor entity) {
		return super.updateById(entity);
	}

	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	@Override
	public GateAuthor getById(Serializable id) {
		return super.getById(id);
	}

	@Override
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		return super.removeByIds(idList);
	}

	@Override
	public String getAllGateIds() {
		return this.getBaseMapper().getAllGateIds();
	}

	@Override
	public List<Integer> getGateIdByStaffId(Integer staffId) {
		return this.getBaseMapper().getGateIdByStaffId(staffId);
	}

	@Override
	public Integer getIdByGateId(Integer gateId) {
		return this.getBaseMapper().getIdByGateId(gateId);
	}

	@Override
	public GateAuthor getGateAuthorById(Integer id) {
		return this.getBaseMapper().getGateAuthorById(id);
	}
}
