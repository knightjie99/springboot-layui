package com.wujie.springbootlayui.sys.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wujie.springbootlayui.sys.entity.Dept;


public interface DeptMapper extends BaseMapper<Dept> {
	
	Dept getDeptById(@Param("id") Integer id);

	void deleteDeptByUid(@Param("belongId") Integer id);
}
