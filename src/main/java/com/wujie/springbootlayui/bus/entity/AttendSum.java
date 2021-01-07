package com.wujie.springbootlayui.bus.entity;

import java.io.Serializable;

public class AttendSum implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String tName;
	
	private Integer staffId;
	
	private String  staffName;
	
	private Integer deptId;
	
	private String  deptName;
	
	/**迟到次数*/
	private Integer lateSum;
	
	/**早退次数*/
	private Integer earlySum;
	
	/**总加班时长*/
	private Double overtimeSum;
	
	/**应出勤天数*/
	private Double attendDay;
	
	/**实际出勤天数*/
	private Double  actualDay;
	
	/**休息天数*/
	private Double  restDay;
	
	/**累计工时*/
	private Double  workSum;

	
	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	
	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getLateSum() {
		return lateSum;
	}

	public void setLateSum(Integer lateSum) {
		this.lateSum = lateSum;
	}

	public Integer getEarlySum() {
		return earlySum;
	}

	public void setEarlySum(Integer earlySum) {
		this.earlySum = earlySum;
	}

	public Double getOvertimeSum() {
		return overtimeSum;
	}

	public void setOvertimeSum(Double overtimeSum) {
		this.overtimeSum = overtimeSum;
	}

	public Double getAttendDay() {
		return attendDay;
	}

	public void setAttendDay(Double attendDay) {
		this.attendDay = attendDay;
	}

	public Double getActualDay() {
		return actualDay;
	}

	public void setActualDay(Double actualDay) {
		this.actualDay = actualDay;
	}

	public Double getRestDay() {
		return restDay;
	}

	public void setRestDay(Double restDay) {
		this.restDay = restDay;
	}

	public Double getWorkSum() {
		return workSum;
	}

	public void setWorkSum(Double workSum) {
		this.workSum = workSum;
	}
}
