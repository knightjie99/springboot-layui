package com.wujie.springbootlayui.sys.vo;

import com.wujie.springbootlayui.sys.entity.User;

public class UserVo extends User {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer page=1;
	
    private Integer limit=10;

    /**
     * 验证码
     */
    private String code;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
    
}
