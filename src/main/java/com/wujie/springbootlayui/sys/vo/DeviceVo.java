package com.wujie.springbootlayui.sys.vo;

import com.wujie.springbootlayui.sys.entity.Device;

public class DeviceVo extends Device {
	
	private static final long serialVersionUID = 1L;

	private Integer page = 1;

	private Integer limit = 10;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
