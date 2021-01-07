package com.wujie.springbootlayui.bus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wujie.springbootlayui.bus.entity.Gate;

public interface GateMapper extends BaseMapper<Gate>{

	Integer getIdByDeviceSn(@Param("deviceSn") String deviceSn);

	Gate getGateById(@Param("id") Integer id);

	List<Gate> getAllOnlineGateByBelong(@Param("belongId") Integer belongId);

	List<Gate> getAllGatesByVerType(@Param("verType") Integer verType);

	void deleteGateByUid(@Param("belongId") Integer id);
}
