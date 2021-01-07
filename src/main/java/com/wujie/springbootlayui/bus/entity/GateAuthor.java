package com.wujie.springbootlayui.bus.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("bus_gate_author")
public class GateAuthor implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	
	private Integer gateId;
	
	/**闸机名称*/
	@TableField(exist = false)
	private String gateName;
	
	/**员工ID集合*/
	private String staffList;
	
	/**员工名集合*/
	@TableField(exist = false)
	private String  nameList;
	
	/**授权时间*/
	private String  authorTime;
	
	private Integer belongId;
	
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

	public Integer getGateId() {
		return gateId;
	}

	public void setGateId(Integer gateId) {
		this.gateId = gateId;
	}

	public String getGateName() {
		return gateName;
	}

	public void setGateName(String gateName) {
		this.gateName = gateName;
	}
	
	public String getStaffList() {
		return staffList;
	}

	public void setStaffList(String staffList) {
		this.staffList = staffList;
	}
	
	public String getNameList() {
		return nameList;
	}

	public void setNameList(String nameList) {
		this.nameList = nameList;
	}

	public String getAuthorTime() {
		return authorTime;
	}

	public void setAuthorTime(String authorTime) {
		this.authorTime = authorTime;
	}
	
	public Integer getBelongId() {
		return belongId;
	}

	public void setBelongId(Integer belongId) {
		this.belongId = belongId;
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
		return "GateAuthor [id=" + id + ", gateId=" + gateId + ", gateName=" + gateName + ", staffList=" + staffList
				+ ", nameList=" + nameList + ", authorTime=" + authorTime + ", belongId=" + belongId + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
}
