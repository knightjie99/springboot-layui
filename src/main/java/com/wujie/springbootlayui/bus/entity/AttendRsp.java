package com.wujie.springbootlayui.bus.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AttendRsp {

    private Integer id;

    private String staffName;

    private String staffPhoto;

    private String deptName;

    //	private Integer status;
    private Integer onStatus;
    private Integer offStatus;

    @DateTimeFormat(pattern = "HH:mm")
    private Date onTime;

    private String onGateName;

    @DateTimeFormat(pattern = "HH:mm")
    private Date offTime;

    private String offGateName;

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

    public String getStaffPhoto() {
        return staffPhoto;
    }

    public void setStaffPhoto(String staffPhoto) {
        this.staffPhoto = staffPhoto;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public String getOnGateName() {
        return onGateName;
    }

    public void setOnGateName(String onGateName) {
        this.onGateName = onGateName;
    }

    public String getOffGateName() {
        return offGateName;
    }

    public void setOffGateName(String offGateName) {
        this.offGateName = offGateName;
    }

    public Date getOnTime() {
        return onTime;
    }

    public void setOnTime(Date onTime) {
        this.onTime = onTime;
    }

    public Date getOffTime() {
        return offTime;
    }

    public void setOffTime(Date offTime) {
        this.offTime = offTime;
    }
}
