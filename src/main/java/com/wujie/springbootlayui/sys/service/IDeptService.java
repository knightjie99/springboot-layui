package com.wujie.springbootlayui.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wujie.springbootlayui.sys.entity.Dept;

public interface IDeptService extends IService<Dept> {
	
	public Dept getDeptById(Integer id);
	
	public Integer loadDeptOrderNum(Integer belongId);
	
	public Dept getOtherDept(Integer belongId);

}
