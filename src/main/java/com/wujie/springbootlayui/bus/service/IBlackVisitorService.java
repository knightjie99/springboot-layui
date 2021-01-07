package com.wujie.springbootlayui.bus.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wujie.springbootlayui.bus.entity.BlackVisitor;

public interface IBlackVisitorService extends IService<BlackVisitor> {
	
	public List<BlackVisitor>  getBlackVisitorByBelongId(Integer belongId);

	public BlackVisitor getBlackVisitorById(Integer id);
	
	public Integer getBlackVisitorByIdNum(String idNum, Integer belongId);
}
