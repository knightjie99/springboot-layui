package com.wujie.springbootlayui.sys.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.Size;

@TableName("sys_app")
public class App implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	
	private String appVer;
	
	private String appName;
	
	private Double appSize;
	
	/**
	 * 闸机名称
	 */
	@TableField(exist = false)
	private String mf;
	
	private String appUrl;

	@Size(min = 0, max = 100, message = "备注长度不能超过100")
	private String updateLog;
	
	/**1-阿里云 2-云飞励天*/
	private Integer verType;
	
	/**1-未发布 2-已发布*/
	private Integer status;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppVer() {
		return appVer;
	}

	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Double getAppSize() {
		return appSize;
	}

	public void setAppSize(Double appSize) {
		this.appSize = appSize;
	}
	
	public Integer getVerType() {
		return verType;
	}

	public void setVerType(Integer verType) {
		this.verType = verType;
	}

	public String getUpdateLog() {
		return updateLog;
	}

	public void setUpdateLog(String updateLog) {
		this.updateLog = updateLog;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMf() {
		return mf;
	}

	public void setMf(String mf) {
		this.mf = mf;
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

	@Override
	public String toString() {
		return "App [id=" + id + ", appVer=" + appVer + ", appName=" + appName + ", appSize=" + appSize + ", mf=" + mf
				+ ", appUrl=" + appUrl + ", updateLog=" + updateLog + ", verType=" + verType + ", status=" + status
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}
