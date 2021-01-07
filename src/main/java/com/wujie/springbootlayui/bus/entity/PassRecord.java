package com.wujie.springbootlayui.bus.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("bus_pass_record")
public class PassRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.INPUT)
	private Integer id;
	
	/**通行记录表表名*/
	@TableField(exist = false)
	private String tName;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date passTime;

	private String name;

	/** 1-男 2-女 */
	private Integer sex;

	private String idNum;

	private String phone;

	private String cardNum;

	private String facePhoto;

	private Integer gateId;

	/**闸机名称*/
	@TableField(exist = false)
	private String gateName;
	
	/**1-人脸识别 2-刷卡*/
	private Integer passWay;
	
	/**1-员工 2-访客*/
	private Integer identityType;
	
	/**温度*/
	private String temperature;
	
	/**1-进入 2-离开*/
	private Integer passDirection;
	
	private Integer belongId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public Date getPassTime() {
		return passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
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

	public String getFacePhoto() {
		return facePhoto;
	}

	public void setFacePhoto(String facePhoto) {
		this.facePhoto = facePhoto;
	}

	public Integer getGateId() {
		return gateId;
	}

	public void setGateId(Integer gateId) {
		this.gateId = gateId;
	}

	public String getGateName() {
		return gateName;
	}

	public void setGateName(String gateName) {
		this.gateName = gateName;
	}

	public Integer getPassWay() {
		return passWay;
	}

	public void setPassWay(Integer passWay) {
		this.passWay = passWay;
	}

	public Integer getIdentityType() {
		return identityType;
	}

	public void setIdentityType(Integer identityType) {
		this.identityType = identityType;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public Integer getPassDirection() {
		return passDirection;
	}

	public void setPassDirection(Integer passDirection) {
		this.passDirection = passDirection;
	}
	
	public Integer getBelongId() {
		return belongId;
	}

	public void setBelongId(Integer belongId) {
		this.belongId = belongId;
	}

	@Override
	public String toString() {
		return "PassRecord [id=" + id + ", tName=" + tName + ", passTime=" + passTime + ", name=" + name + ", sex="
				+ sex + ", idNum=" + idNum + ", phone=" + phone + ", cardNum=" + cardNum + ", facePhoto=" + facePhoto
				+ ", gateId=" + gateId + ", gateName=" + gateName + ", passWay=" + passWay + ", identityType="
				+ identityType + ", temperature=" + temperature + ", passDirection=" + passDirection + ", belongId="
				+ belongId + "]";
	}
}
