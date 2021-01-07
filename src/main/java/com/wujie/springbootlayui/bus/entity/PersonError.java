package com.wujie.springbootlayui.bus.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


@TableName("bus_person_error")
public class PersonError implements Serializable {
	
	 private static final long serialVersionUID=1L;

	 @TableId(value = "id", type = IdType.AUTO)
	 private Integer id;
	 
	 private Integer personId;
	 
	 /**人员类型 1-员工 2-访客*/
	 private Integer identityType;
	 
	 private String name;
	 
	 private String idNum;
	 
	 private String phone;
	 
	 private String personPhoto;
	 
	 private String errorMsg;
	 
	 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	 private Date createTime;
	 
	 private Integer belongId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getIdentityType() {
		return identityType;
	}

	public void setIdentityType(Integer identityType) {
		this.identityType = identityType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPersonPhoto() {
		return personPhoto;
	}

	public void setPersonPhoto(String personPhoto) {
		this.personPhoto = personPhoto;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getBelongId() {
		return belongId;
	}

	public void setBelongId(Integer belongId) {
		this.belongId = belongId;
	}

	@Override
	public String toString() {
		return "PersonError [id=" + id + ", personId=" + personId + ", identityType=" + identityType + ", name=" + name
				+ ", idNum=" + idNum + ", phone=" + phone + ", personPhoto=" + personPhoto + ", errorMsg=" + errorMsg
				+ ", createTime=" + createTime + ", belongId=" + belongId + "]";
	}

}
