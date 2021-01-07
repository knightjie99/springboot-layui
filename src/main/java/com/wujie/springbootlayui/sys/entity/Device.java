package com.wujie.springbootlayui.sys.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("sys_device")
public class Device implements Serializable {
	
    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    private String deviceSn;
    
    private Integer belongId;
    
    private String cpuId;
    
    /**导入的文件路径*/
    @TableField(exist = false)
    private String mf;
    
    /**
	 * 客户名称
	 */
	@TableField(exist = false)
	private String belongName;
	
	/**
	 *导入方式
	 */
	@TableField(exist = false)
	private Integer inputWay;
	
	/**1-在线 2-离线*/
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

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}
	
	public Integer getBelongId() {
		return belongId;
	}

	public void setBelongId(Integer belongId) {
		this.belongId = belongId;
	}

	public String getMf() {
		return mf;
	}

	public void setMf(String mf) {
		this.mf = mf;
	}

	public String getBelongName() {
		return belongName;
	}

	public void setBelongName(String belongName) {
		this.belongName = belongName;
	}

	public Integer getInputWay() {
		return inputWay;
	}

	public void setInputWay(Integer inputWay) {
		this.inputWay = inputWay;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getCpuId() {
		return cpuId;
	}

	public void setCpuId(String cpuId) {
		this.cpuId = cpuId;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", deviceSn=" + deviceSn + ", belongId=" + belongId + ", cpuId=" + cpuId + ", mf="
				+ mf + ", belongName=" + belongName + ", inputWay=" + inputWay + ", status=" + status + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
}
