package com.wujie.springbootlayui.sys.common;

public class PageResult{
	
	private Integer code;
	
	private String msg;
	
    private Object data;
	
	private long totalCount;
	
	private long totalPage;
	
	public PageResult() {
		
	}
	
	public PageResult(long totalCount ,long totalPage ,Object data) {
		  this.totalCount = totalCount;
		  this.totalPage = totalPage;
		  this.data = data;
		  this.code = 200;
		  this.msg = "操作成功!";
	}
	
	

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	} 
	
	
	
	
}
