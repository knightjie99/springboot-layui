package com.wujie.springbootlayui.bus.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wujie.springbootlayui.bus.entity.GateAuthor;

public interface IGateAuthorService extends IService<GateAuthor>{
	
	public String getAllGateIds();
	
	public List<Integer> getGateIdByStaffId(Integer staffId);

	//通过闸机id获取闸机授权表的主键ID
	public Integer getIdByGateId(Integer gateId);
	
	public GateAuthor getGateAuthorById(Integer id);
}
