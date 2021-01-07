package com.wujie.springbootlayui.bus.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wujie.springbootlayui.bus.entity.TempVisitor;

public interface ITempVisitorService extends IService<TempVisitor>{
	
	public TempVisitor getVisitorById(Integer id);
	
	public List<TempVisitor> getValidVisitors(Integer belongId, Integer gateId);
	
	public Integer  getValidVisitorByIdNum(String idNum, Integer belongId);

}
