package com.wujie.springbootlayui.sys.mapper;

import com.wujie.springbootlayui.sys.entity.DeskParam;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wujie.springbootlayui.sys.entity.User;

public interface UserMapper extends BaseMapper<User> {

	Integer queryUserByLoginName(@Param("loginName") String loginName);
	
	DeskParam getDesk(@Param("belongId") Integer belongId, @Param("time") String time);

	Integer queryUserByPhone(@Param("phone") String phone);

	Integer insertUser(User user);
}
