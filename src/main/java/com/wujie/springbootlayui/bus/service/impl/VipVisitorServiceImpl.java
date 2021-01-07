package com.wujie.springbootlayui.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.bus.entity.VipVisitor;
import com.wujie.springbootlayui.bus.mapper.VipVisitorMapper;
import com.wujie.springbootlayui.bus.service.IVipVisitorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@Service
@Transactional
public class VipVisitorServiceImpl extends ServiceImpl<VipVisitorMapper, VipVisitor> implements IVipVisitorService {

    @Override
    public boolean save(VipVisitor entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(VipVisitor entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public VipVisitor getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

	@Override
	public List<VipVisitor> getValidVipVisitors(Integer belongId, Integer gateId) {
		return this.getBaseMapper().getValidVipVisitors(belongId,gateId);
	}

	@Override
	public VipVisitor getVipVisitorById(Integer id) {
		return this.getBaseMapper().getVipVisitorById(id);
	}

	@Override
	public Integer getVipVisitorByIdNum(String idNum, Integer belongId) {
		return  this.getBaseMapper().getVipVisitorByIdNum(idNum,belongId);
	}

}
