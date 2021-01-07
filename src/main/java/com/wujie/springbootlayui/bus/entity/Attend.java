package com.wujie.springbootlayui.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 考勤表
 * 
 * @author wuj
 *
 */
@TableName("bus_attend")
public class Attend implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableField(exist = false)
	private String tName;

	@TableId(value = "id", type = IdType.INPUT)
	private Integer id;

	private Integer staffId;

	private String staffName;

	private Integer deptId;

	/**
	 * 部门名称
	 */
	@TableField(exist = false)
	private String deptName;

	/** 考勤日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date attendDate;

	/** 上班状态 */
	private Integer onStatus;

	/** 下班状态 */
	private Integer offStatus;

	/** 当前工时 */
	private Double attendHour;

	/**上班时间 */
	@DateTimeFormat(pattern = "HH:mm")
	private Date onTime;

	/**上班照片*/
	private String onPhoto;

	/**上班闸机*/
	private Integer onGate;

	/**
	 * 签到闸机名称
	 */
	@TableField(exist = false)
	private String onGateName;

	/** 下班时间 */
	@DateTimeFormat(pattern = "HH:mm")
	private Date offTime;

	/**下班照片*/
	private String offPhoto;

	/**下班闸机*/
	private Integer offGate;

	/**
	 * 签退闸机名称
	 */
	@TableField(exist = false)
	private String offGateName;

	private Integer belongId;

	private Double overtimeHours=0d;

	private Integer validAttend=0;

	public Double getOvertimeHours() {
		return overtimeHours;
	}

	public void setOvertimeHours(Double overtimeHours) {
		this.overtimeHours = overtimeHours;
	}

	public Integer getValidAttend() {
		return validAttend;
	}

	public void setValidAttend(Integer validAttend) {
		this.validAttend = validAttend;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Date getAttendDate() {
		return attendDate;
	}

	public void setAttendDate(Date attendDate) {
		this.attendDate = attendDate;
	}

	public Integer getOnStatus() {
		return onStatus;
	}

	public void setOnStatus(Integer onStatus) {
		this.onStatus = onStatus;
	}

	public Integer getOffStatus() {
		return offStatus;
	}

	public void setOffStatus(Integer offStatus) {
		this.offStatus = offStatus;
	}

	public Double getAttendHour() {
		return attendHour;
	}

	public void setAttendHour(Double attendHour) {
		this.attendHour = attendHour;
	}

	public Date getOnTime() {
		return onTime;
	}

	public void setOnTime(Date onTime) {
		this.onTime = onTime;
	}

	public String getOnPhoto() {
		return onPhoto;
	}

	public void setOnPhoto(String onPhoto) {
		this.onPhoto = onPhoto;
	}

	public Integer getOnGate() {
		return onGate;
	}

	public void setOnGate(Integer onGate) {
		this.onGate = onGate;
	}

	public String getOnGateName() {
		return onGateName;
	}

	public void setOnGateName(String onGateName) {
		this.onGateName = onGateName;
	}

	public Date getOffTime() {
		return offTime;
	}

	public void setOffTime(Date offTime) {
		this.offTime = offTime;
	}

	public String getOffPhoto() {
		return offPhoto;
	}

	public void setOffPhoto(String offPhoto) {
		this.offPhoto = offPhoto;
	}

	public Integer getOffGate() {
		return offGate;
	}

	public void setOffGate(Integer offGate) {
		this.offGate = offGate;
	}

	public String getOffGateName() {
		return offGateName;
	}

	public void setOffGateName(String offGateName) {
		this.offGateName = offGateName;
	}

	public Integer getBelongId() {
		return belongId;
	}

	public void setBelongId(Integer belongId) {
		this.belongId = belongId;
	}

	@Override
	public String toString() {
		return "Attend{" + "tName='" + tName + '\'' + ", id=" + id + ", staffId=" + staffId + ", staffName='"
				+ staffName + '\'' + ", deptId=" + deptId + ", deptName='" + deptName + '\'' + ", attendDate="
				+ attendDate + ", onStatus=" + onStatus + ", offStatus=" + offStatus + ", attendHour=" + attendHour
				+ ", onTime=" + onTime + ", onPhoto='" + onPhoto + '\'' + ", onGate=" + onGate + ", onGateName='"
				+ onGateName + '\'' + ", offTime=" + offTime + ", offPhoto='" + offPhoto + '\'' + ", offGate=" + offGate
				+ ", offGateName='" + offGateName + '\'' + ", belongId=" + belongId + '}';
	}
}
