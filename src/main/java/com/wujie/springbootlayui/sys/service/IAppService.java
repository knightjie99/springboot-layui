package com.wujie.springbootlayui.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wujie.springbootlayui.sys.entity.App;

public interface IAppService extends IService<App>{

	public Integer getPreviousId(Integer id);
	public App getAppById(Integer id);
}
