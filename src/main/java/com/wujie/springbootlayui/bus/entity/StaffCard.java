package com.wujie.springbootlayui.bus.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.Size;


@TableName("bus_staff_card")
public class StaffCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Size(min = 0, max = 20, message = "卡号长度不能超过20")
    private String cardNum;

    private Integer cardBinder;

    /**
     * 卡片绑定者名
     */
    @TableField(exist = false)
    private String binderName;

    private Integer cardAuthor;

    /**
     * 发卡者名
     */
    @TableField(exist = false)
    private String authorName;

    private Integer belongId;

    @Size(min = 0, max = 100, message = "备注请控制在100字以内")
    private String remark;

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

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Integer getCardBinder() {
        return cardBinder;
    }

    public void setCardBinder(Integer cardBinder) {
        this.cardBinder = cardBinder;
    }

    public String getBinderName() {
        return binderName;
    }

    public void setBinderName(String binderName) {
        this.binderName = binderName;
    }

    public Integer getCardAuthor() {
        return cardAuthor;
    }

    public void setCardAuthor(Integer cardAuthor) {
        this.cardAuthor = cardAuthor;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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
        return "StaffCard [id=" + id + ", cardNum=" + cardNum + ", cardBinder=" + cardBinder + ", binderName="
                + binderName + ", cardAuthor=" + cardAuthor + ", authorName=" + authorName + ", belongId=" + belongId
                + ", remark=" + remark + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
    }

}
