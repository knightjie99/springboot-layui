package com.wujie.springbootlayui.bus.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 人员排班实体类
 * @author wuj
 *
 */
@TableName("bus_attend_staff")
public class AttendStaff implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	
	/**班次ID*/
	private Integer ruleId;
	
	/**班次名称*/
	@TableField(exist = false)
	private String ruleName;
	
	/**员工ID集合*/
	private String  staffList;
	
	/**员工名集合*/
	@TableField(exist = false)
	private String  nameList;
	
	/**循环日期*/
	private String cycleDate;
	
	/**上班时间*/
	private String signTime;
	
	/**下班时间*/
	private String leaveTime;
	
	private Integer belongId;
	
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

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	
	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
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

	public String getCycleDate() {
		return cycleDate;
	}

	public void setCycleDate(String cycleDate) {
		this.cycleDate = cycleDate;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
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
		return "AttendStaff [id=" + id + ", ruleId=" + ruleId + ", ruleName=" + ruleName + ", staffList=" + staffList
				+ ", nameList=" + nameList + ", cycleDate=" + cycleDate + ", signTime=" + signTime + ", leaveTime="
				+ leaveTime + ", belongId=" + belongId + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
}
