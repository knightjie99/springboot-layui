package com.wujie.springbootlayui.bus.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.wujie.springbootlayui.bus.entity.AttendSum;

public class AttendSumVo extends AttendSum{
	
	private static final long serialVersionUID = 1L;

	private Integer page = 1;

	private Integer limit = 10;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;

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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
