package com.wujie.springbootlayui.bus.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wujie.springbootlayui.bus.entity.VipVisitor;

public interface IVipVisitorService  extends IService<VipVisitor> {
	
	public List<VipVisitor> getValidVipVisitors(Integer belongId, Integer gateId);
	
	public VipVisitor getVipVisitorById(Integer id);
	
	public Integer  getVipVisitorByIdNum(String idNum, Integer belongId);

}
