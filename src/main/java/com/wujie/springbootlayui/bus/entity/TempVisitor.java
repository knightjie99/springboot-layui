package com.wujie.springbootlayui.bus.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.Size;

@TableName("bus_temp_visitor")
public class TempVisitor implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Size(min = 1, max = 20, message = "名称长度不能大于20")
    private String visitorName;
    /**
     * 导入的文件路径
     */
    @TableField(exist = false)
    private String mf;


    private Integer sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date visitTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date leaveTime;

    @Size(min = 0, max = 18, message = "身份证长度为18位")
    private String idNum;

    @Size(min = 0, max = 11, message = "手机长度为11位")
    private String phone;

    /**
     * 闸机id集合
     */
    private String gateIds;

    /**
     * 闸机名称
     */
    @TableField(exist = false)
    private String gateName;

    private String visitorPhoto;

    //状态(1-生效中 2-已失效)
    private Integer status;

    private String creatorName;

    private Integer belongId;

    @Size(min = 0, max = 100, message = "备注请控制在100字以内")
    private String remark;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Integer recogStatus;

    public Integer getRecogStatus() {
        return recogStatus;
    }

    public void setRecogStatus(Integer recogStatus) {
        this.recogStatus = recogStatus;
    }

    public Integer getId() {
        return id;
    }

    public String getMf() {
        return mf;
    }

    public void setMf(String mf) {
        this.mf = mf;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGateIds() {
        return gateIds;
    }

    public void setGateIds(String gateIds) {
        this.gateIds = gateIds;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBelongId() {
        return belongId;
    }

    public void setBelongId(Integer belongId) {
        this.belongId = belongId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return "Visitor [id=" + id + ", visitorName=" + visitorName + ", sex=" + sex + ", visitTime=" + visitTime
                + ", leaveTime=" + leaveTime + ", idNum=" + idNum + ", phone=" + phone + ", gateIds=" + gateIds
                + ", gateName=" + gateName + ", visitorPhoto=" + visitorPhoto + ", status=" + status + ", creatorName="
                + creatorName + ", belongId=" + belongId + ", remark=" + remark + ", createTime=" + createTime
                + ", updateTime=" + updateTime + ", recogStatus=" + recogStatus + "]";
    }

}
