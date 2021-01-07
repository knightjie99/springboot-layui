package com.wujie.springbootlayui.sys.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wujie.springbootlayui.sys.entity.App;

public interface AppMapper extends BaseMapper<App>{

	Integer getPreviousId(@Param("id") Integer id);
	App getAppById(@Param("id") Integer id);
}
