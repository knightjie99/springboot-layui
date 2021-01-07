package com.wujie.springbootlayui.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wujie.springbootlayui.sys.entity.Register;

public interface IRegisterService extends IService<Register>{
	
	 /**
     * 根据手机号查询是否已注册
     * @param phone
     * @return
     */
	Integer queryRegisterByPhone(String phone);
}
