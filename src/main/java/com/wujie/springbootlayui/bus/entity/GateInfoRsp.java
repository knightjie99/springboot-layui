package com.wujie.springbootlayui.bus.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class GateInfoRsp {
	
	private Integer id;
	
	/**
	 * 闸机名称
	 */
	private String gateName;
	
	/**
	 * 在线状态(1-在线 2-离线)
	 */
	private Integer onlineStatus;
	
	private String installLocation;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date  activeTime;
	
	/**
	 * 闸机状态(1-已激活 2-未激活)
	 */
	private Integer gateStatus;
	
	private String currentVer;
	
	private String newestVer;
	
	/**
	 * 备注
	 */
	private String remark;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date  createTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date  updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGateName() {
		return gateName;
	}

	public void setGateName(String gateName) {
		this.gateName = gateName;
	}

	public Integer getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(Integer onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public String getInstallLocation() {
		return installLocation;
	}

	public void setInstallLocation(String installLocation) {
		this.installLocation = installLocation;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

	public Integer getGateStatus() {
		return gateStatus;
	}

	public void setGateStatus(Integer gateStatus) {
		this.gateStatus = gateStatus;
	}

	public String getCurrentVer() {
		return currentVer;
	}

	public void setCurrentVer(String currentVer) {
		this.currentVer = currentVer;
	}

	public String getNewestVer() {
		return newestVer;
	}

	public void setNewestVer(String newestVer) {
		this.newestVer = newestVer;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
