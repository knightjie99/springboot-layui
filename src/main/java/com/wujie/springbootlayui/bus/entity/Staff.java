package com.wujie.springbootlayui.bus.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@TableName("bus_staff")
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @Size(min = 1, max = 20, message = "姓名长度不能超过10")
    private String staffName;

    private Integer deptId;

    private Integer sex;

    /**
     * 员工状态(1-在职 2-离职)
     */
    private Integer status;

    @Size(min = 0, max = 18, message = "身份证长度为18位")
    private String idNum;

    @Size(min = 0, max = 11, message = "手机长度为11位")
    private String phone;

    @Pattern(regexp = "^(\\d{0}|\\d{7}|\\d{9})$", message = "卡号必须为7位或9位纯数字")
    private String cardNum;

    private String staffPhoto;

    private Integer belongId;

    @Size(min = 0, max = 100, message = "备注长度不能超过100")
    private String remark;

    @Size(min = 0, max = 20, message = "员工编号长度不能超过20")
    private String staffNum;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Size(min = 0, max = 30, message = "入职日期长度不能超过30")
    private String entryDate;
    @Size(min = 0, max = 10, message = "职位长度不能超过10")
    private String staffJob;

    @Size(min = 0, max = 30, message = "Email长度不能超过30")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 导入的文件路径
     */
    @TableField(exist = false)
    private String mf;

    /**
     * 客户名称
     */
    @TableField(exist = false)
    private String belongName;

    /**
     * 导入方式
     */
    @TableField(exist = false)
    private Integer inputWay;

    /**
     * 部门名称
     */
    @TableField(exist = false)
    private String deptName;

    /**
     * 是否可用(0-已删除 1-可用)
     */
    private Integer available;

    private Integer recogStatus;

    private String prompt;

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
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

    public Integer getRecogStatus() {
        return recogStatus;
    }

    public void setRecogStatus(Integer recogStatus) {
        this.recogStatus = recogStatus;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getStaffPhoto() {
        return staffPhoto;
    }

    public void setStaffPhoto(String staffPhoto) {
        this.staffPhoto = staffPhoto;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(String staffNum) {
        this.staffNum = staffNum;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getStaffJob() {
        return staffJob;
    }

    public void setStaffJob(String staffJob) {
        this.staffJob = staffJob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", staffName='" + staffName + '\'' +
                ", deptId=" + deptId +
                ", sex=" + sex +
                ", status=" + status +
                ", idNum='" + idNum + '\'' +
                ", phone='" + phone + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", staffPhoto='" + staffPhoto + '\'' +
                ", belongId=" + belongId +
                ", remark='" + remark + '\'' +
                ", staffNum='" + staffNum + '\'' +
                ", createTime=" + createTime +
                ", entryDate='" + entryDate + '\'' +
                ", staffJob='" + staffJob + '\'' +
                ", email='" + email + '\'' +
                ", updateTime=" + updateTime +
                ", mf='" + mf + '\'' +
                ", belongName='" + belongName + '\'' +
                ", inputWay=" + inputWay +
                ", deptName='" + deptName + '\'' +
                ", available=" + available +
                ", recogStatus=" + recogStatus +
                ", prompt='" + prompt + '\'' +
                '}';
    }
}
