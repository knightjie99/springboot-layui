package com.wujie.springbootlayui.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.sys.common.ActiverUser;
import com.wujie.springbootlayui.sys.entity.Dept;
import com.wujie.springbootlayui.sys.mapper.DeptMapper;
import com.wujie.springbootlayui.sys.service.IDeptService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

	@Override
	public Dept getById(Serializable id) {
		return super.getById(id);
	}

	@Override
	public boolean update(Dept entity, Wrapper<Dept> updateWrapper) {
		return super.update(entity, updateWrapper);
	}

	@Override
	public boolean updateById(Dept entity) {
		return super.updateById(entity);
	}

	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	@Override
	public boolean save(Dept entity) {
		return super.save(entity);
	}

	@Override
	public Dept getDeptById(Integer id) {
		return this.getBaseMapper().getDeptById(id);
	}

	@Override
	public Integer loadDeptOrderNum(Integer belongId) {
		QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("ordernum");
		queryWrapper.eq("belong_user", belongId);
		List<Dept> list = this.getBaseMapper().selectList(queryWrapper);
		if (list.size() > 0) {
			return list.get(0).getOrdernum() + 1;
		} else {
			return 1;
		}
	}

	@Override
	public Dept getOtherDept(Integer belongId) {
		Integer lang=1;
		Subject subject = SecurityUtils.getSubject();
		ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
		 if(activerUser!=null){
			 lang = activerUser.getUser().getLoginLang();
		 }

		Dept other = new Dept();
		QueryWrapper<Dept> othersQuery = new QueryWrapper<>();
		othersQuery.eq("belong_user", belongId);
		othersQuery.eq("name", "其它");
		List<Dept> otherDepts = this.getBaseMapper().selectList(othersQuery);
		if (otherDepts.size() > 0) {
			other = otherDepts.get(0);
		} else {
			other.setName("其它");
			other.setPid(0);
			other.setAvailable(1);
			other.setBelongUser(belongId);
			other.setCreatetime(new Date());
			other.setOpen(1);
			// 获取排序码
			Integer orderNum = this.loadDeptOrderNum(belongId);
			other.setOrdernum(orderNum);
			this.save(other);
		}

		if(lang==2){
			other.setName("others");
		}

		return other;
	}
}
