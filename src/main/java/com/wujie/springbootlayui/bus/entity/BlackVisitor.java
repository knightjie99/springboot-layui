package com.wujie.springbootlayui.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@TableName("bus_black_visitor")
public class BlackVisitor implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Size(min = 1, max = 20, message = "姓名长度不能超过20")
    private String name;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String phone;

    @Size(min = 0, max = 20, message = "人员组长度不能超过20")
    private String personGroup;

    @Size(min = 0, max = 11, message = "手机长度为11位")
    private String connectedPhone;
    /**
     * 导入的文件路径
     */
    @TableField(exist = false)
    private String mf;
    private String visitorPhoto;

    private String creatorName;

    private Integer belongId;

    @Size(min = 0, max = 100, message = "备注请控制在100字以内")
    private String remark;

    @Size(min = 0, max = 18, message = "身份证长度为18位")
    private String idNum;

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

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

    public String getMf() {
        return mf;
    }

    public void setMf(String mf) {
        this.mf = mf;
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

    public String getVisitorPhoto() {
        return visitorPhoto;
    }

    public void setVisitorPhoto(String visitorPhoto) {
        this.visitorPhoto = visitorPhoto;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BlackVisitor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", personGroup='" + personGroup + '\'' +
                ", connectedPhone='" + connectedPhone + '\'' +
                ", mf='" + mf + '\'' +
                ", visitorPhoto='" + visitorPhoto + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", belongId=" + belongId +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
