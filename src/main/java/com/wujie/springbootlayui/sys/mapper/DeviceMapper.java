package com.wujie.springbootlayui.sys.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wujie.springbootlayui.sys.entity.Device;

public interface DeviceMapper extends BaseMapper<Device>{

	Integer queryBelongIdByDeviceSn(@Param("deviceSn") String deviceSn);
	void resetBelongId(@Param("belongId") Integer belongId);

	String  selectSnGroup();

	Device  queryDeviceByDeviceSn(@Param("deviceSn") String deviceSn);
}
