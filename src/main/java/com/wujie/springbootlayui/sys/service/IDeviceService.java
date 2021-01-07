package com.wujie.springbootlayui.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wujie.springbootlayui.sys.entity.Device;

public interface IDeviceService extends IService<Device>{
	
	Integer queryBelongIdByDeviceSn(String deviceSn);
	
	public String selectSnGroup();
	
	public Device queryDeviceByDeviceSn(String deviceSn);

	void resetBelongId(Integer belongId);
}
