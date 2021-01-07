package com.wujie.springbootlayui.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@TableName("bus_vip_visitor")
public class VipVisitor implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Size(min = 1, max = 20, message = "名称长度不能大于20")
    private String visitorName;


    private String visitTime;

    private String leaveTime;

    @Size(min = 0, max = 11, message = "手机长度为11位")
    private String phone;

    @Size(min = 0, max = 50, message = "人员组长度不能超过50")
    private String personGroup;

    @Size(min = 0, max = 11, message = "关联手机长度为11位")
    private String connectedPhone;
    /**
     * 闸机设备id
     */
    private String gateIds;
    /**
     * 导入的文件路径
     */
    @TableField(exist = false)
    private String mf;

    /**
     * 闸机名称
     */
    @TableField(exist = false)
    private String gateName;

    private String visitorPhoto;


    private String creatorName;

    private Integer belongId;

    @Size(min = 0, max = 100, message = "备注请控制在100字以内")
    private String remark;

    private String cycleDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Size(min = 0, max = 18, message = "身份证长度为18位")
    private String idNum;

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getCycleDate() {
        return cycleDate;
    }


    public void setCycleDate(String cycleDate) {
        this.cycleDate = cycleDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMf() {
        return mf;
    }

    public void setMf(String mf) {
        this.mf = mf;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPersonGroup() {
        return personGroup;
    }

    public void setPersonGroup(String personGroup) {
        this.personGroup = personGroup;
    }

    public String getConnectedPhone() {
        return connectedPhone;
    }

    public void setConnectedPhone(String connectedPhone) {
        this.connectedPhone = connectedPhone;
    }


    public String getGateName() {
        return gateName;
    }

    public void setGateName(String gateName) {
        this.gateName = gateName;
    }

    public String getVisitorPhoto() {
        return visitorPhoto;
    }

    public void setVisitorPhoto(String visitorPhoto) {
        this.visitorPhoto = visitorPhoto;
    }

    public String getGateIds() {
        return gateIds;
    }

    public void setGateIds(String gateIds) {
        this.gateIds = gateIds;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Integer getBelongId() {
        return belongId;
    }

    public void setBelongId(Integer belongId) {
        this.belongId = belongId;
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

    @Override
    public String toString() {
        return "VipVisitor{" +
                "id=" + id +
                ", visitorName='" + visitorName + '\'' +
                ", visitTime=" + visitTime +
                ", leaveTime=" + leaveTime +
                ", phone='" + phone + '\'' +
                ", group='" + personGroup + '\'' +
                ", connectedPhone='" + connectedPhone + '\'' +
                ", gateIds=" + gateIds +
                ", gateName='" + gateName + '\'' +
                ", visitorPhoto='" + visitorPhoto + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", belongId=" + belongId +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
