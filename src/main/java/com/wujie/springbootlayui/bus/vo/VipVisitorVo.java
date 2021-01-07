package com.wujie.springbootlayui.bus.vo;

import com.wujie.springbootlayui.bus.entity.VipVisitor;

public class VipVisitorVo extends VipVisitor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 分页参数，当前是第一页，每页10条数据
     */
    private Integer page=1;
    
    private Integer limit=10;

    /**
     * 批量删除访客，存放访客ID的数组
     */
    private Integer[] ids;

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

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}
}
