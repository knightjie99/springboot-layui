package com.wujie.springbootlayui.bus.vo;

import com.wujie.springbootlayui.bus.entity.AttendStaff;

public class AttendStaffVo extends AttendStaff {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 分页参数，当前是第一页，每页10条数据
	 */
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
