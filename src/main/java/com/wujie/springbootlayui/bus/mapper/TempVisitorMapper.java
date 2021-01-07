package com.wujie.springbootlayui.bus.mapper;

import java.util.List;

import com.wujie.springbootlayui.bus.entity.TempVisitor;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface TempVisitorMapper extends BaseMapper<TempVisitor>{

	List<TempVisitor> getValidVisitors(@Param("belongId") Integer belongId, @Param("gateId") Integer gateId);

	TempVisitor getVisitorById(@Param("id") Integer id);

	Integer getValidVisitorByIdNum(@Param("idNum") String idNum, @Param("belongId") Integer belongId);

	void deleteTempVisitorByUid(@Param("belongId") Integer id);
}
