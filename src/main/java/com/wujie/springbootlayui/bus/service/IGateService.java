package com.wujie.springbootlayui.bus.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wujie.springbootlayui.bus.entity.Gate;

public interface IGateService extends IService<Gate>{
	
	 public Integer getIdByDeviceSn(String deviceSn);
	 
	 public Gate getGateById(Integer id);
	 
	 /**
	  * 根据belongId查询所有在线闸机
	  * @param belongId
	  * @return
	  */
	 public List<Gate> getAllOnlineGateByBelong(Integer belongId);
	 
	 public List<Gate> getAllGatesByVerType(Integer verType);

}
