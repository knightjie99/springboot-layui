package com.wujie.springbootlayui.bus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wujie.springbootlayui.bus.entity.GateAuthor;

public interface GateAuthorMapper extends BaseMapper<GateAuthor>{

	 /**
     * 查询所有已授权的闸机ID
     * @return
     */
	public String getAllGateIds();

	public List<Integer> getGateIdByStaffId(@Param("staffId") Integer staffId);

	public Integer getIdByGateId(@Param("gateId") Integer gateId);

	public GateAuthor getGateAuthorById(@Param("id") Integer id);

	void deleteGateAuthorByUid(@Param("belongId") Integer id);
}
