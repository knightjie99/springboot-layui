package com.wujie.springbootlayui.bus.mapper;

import java.util.List;

import com.wujie.springbootlayui.bus.entity.BlackVisitor;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface BlackVisitorMapper extends BaseMapper<BlackVisitor> {

	List<BlackVisitor> getBlackVisitorByBelongId(@Param("belongId") Integer belongId);

	BlackVisitor getBlackVisitorById(@Param("id") Integer id);

	Integer getBlackVisitorByIdNum(@Param("idNum") String idNum, @Param("belongId") Integer belongId);

	void deleteBlackVisitorByUid(@Param("belongId") Integer id);
}
