package com.wujie.springbootlayui.bus.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("bus_attend_rule")
public class AttendRule implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/** 班次名称 */
	@Size(min=1, max=20 ,message="班次名称不合法,请重新输入....")
	private String ruleName;

	/** 上班时间 */
	private String signTime;

	/** 下班时间 */
	private String leaveTime;

	/** 开始签到时间 */
	private String startSign;

	/** 结束签到时间 */
	private String endSign;

	/** 开始签退 */
	private String startLeave;

	/** 结束签退 */
	private String endLeave;
	
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

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
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

	public String getStartSign() {
		return startSign;
	}

	public void setStartSign(String startSign) {
		this.startSign = startSign;
	}

	public String getEndSign() {
		return endSign;
	}

	public void setEndSign(String endSign) {
		this.endSign = endSign;
	}

	public String getStartLeave() {
		return startLeave;
	}

	public void setStartLeave(String startLeave) {
		this.startLeave = startLeave;
	}

	public String getEndLeave() {
		return endLeave;
	}

	public void setEndLeave(String endLeave) {
		this.endLeave = endLeave;
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
		return "AttendRule [id=" + id + ", ruleName=" + ruleName + ", signTime=" + signTime + ", leaveTime=" + leaveTime
				+ ", startSign=" + startSign + ", endSign=" + endSign + ", startLeave=" + startLeave + ", endLeave="
				+ endLeave + ", belongId=" + belongId + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
}
