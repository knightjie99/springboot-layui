package com.wujie.springbootlayui.sys.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wujie.springbootlayui.sys.entity.Register;

public interface RegisterMapper extends BaseMapper<Register>{
	 /**
     * 根据手机号查询是否已注册
     * @param phone
     * @return
     */
	Integer queryRegisterByPhone(@Param("phone") String phone);

}
