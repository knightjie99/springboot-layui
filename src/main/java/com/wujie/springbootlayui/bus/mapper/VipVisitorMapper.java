package com.wujie.springbootlayui.bus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wujie.springbootlayui.bus.entity.VipVisitor;

public interface VipVisitorMapper extends BaseMapper<VipVisitor> {

	List<VipVisitor> getValidVipVisitors(@Param("belongId") Integer belongId, @Param("gateId") Integer gateId);

	VipVisitor getVipVisitorById(@Param("id") Integer id);

	Integer getVipVisitorByIdNum(@Param("idNum") String idNum, @Param("belongId") Integer belongId);

	void deleteVipByUid(@Param("belongId") Integer id);
}
