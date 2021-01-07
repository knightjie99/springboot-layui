package com.wujie.springbootlayui.bus.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.wujie.springbootlayui.bus.mapper.TempVisitorMapper;
import com.wujie.springbootlayui.bus.service.ITempVisitorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.bus.entity.TempVisitor;

@Service
@Transactional
public class TempVisitorServiceImpl extends ServiceImpl<TempVisitorMapper,TempVisitor> implements ITempVisitorService {
	
	@Override
	public boolean save(TempVisitor entity) {
		return super.save(entity);
	}

	@Override
	public boolean updateById(TempVisitor entity) {
		return super.updateById(entity);
	}

	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	@Override
	public TempVisitor getById(Serializable id) {
		return super.getById(id);
	}

	@Override
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		return super.removeByIds(idList);
	}

	@Override
	public List<TempVisitor> getValidVisitors(Integer belongId,Integer gateId) {
		return this.getBaseMapper().getValidVisitors(belongId,gateId);
	}

	@Override
	public TempVisitor getVisitorById(Integer id) {
		return this.getBaseMapper().getVisitorById(id);
	}

	@Override
	public Integer getValidVisitorByIdNum(String idNum, Integer belongId) {
		return this.getBaseMapper().getValidVisitorByIdNum(idNum,belongId);
	}
}
