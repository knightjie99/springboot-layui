package com.wujie.springbootlayui.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.bus.entity.BlackVisitor;
import com.wujie.springbootlayui.bus.mapper.BlackVisitorMapper;
import com.wujie.springbootlayui.bus.service.IBlackVisitorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@Service
@Transactional
public class BlackVisitorServiceImpl extends ServiceImpl<BlackVisitorMapper, BlackVisitor> implements IBlackVisitorService {

    @Override
    public boolean save(BlackVisitor entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(BlackVisitor entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public BlackVisitor getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

	@Override
	public List<BlackVisitor> getBlackVisitorByBelongId(Integer belongId) {
		return this.getBaseMapper().getBlackVisitorByBelongId(belongId);
	}

	@Override
	public BlackVisitor getBlackVisitorById(Integer id) {
		return this.getBaseMapper().getBlackVisitorById(id);
	}

	@Override
	public Integer getBlackVisitorByIdNum(String idNum, Integer belongId) {
		return this.getBaseMapper().getBlackVisitorByIdNum(idNum,belongId);
	}

}
