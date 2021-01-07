package com.wujie.springbootlayui.sys.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.sys.entity.Register;
import com.wujie.springbootlayui.sys.mapper.RegisterMapper;
import com.wujie.springbootlayui.sys.service.IRegisterService;

@Service
@Transactional
public class RegisterServiceImpl extends ServiceImpl<RegisterMapper, Register> implements IRegisterService {

	@Override
	public boolean save(Register entity) {
		return super.save(entity);
	}

	@Override
	public boolean updateById(Register entity) {
		return super.updateById(entity);
	}

	@Override
	public Register getById(Serializable id) {
		return super.getById(id);
	}
	

	@Override
	public Integer queryRegisterByPhone(String phone) {
		return this.getBaseMapper().queryRegisterByPhone(phone);
	}

}
