package com.wujie.springbootlayui.bus.entity;

public class PassRecordReq {
	
	private String deviceSn;
	
	private Integer personId;
	
	private String passTime;
	
	private String cardNum;
	
	private Integer passWay;
	
	private String temperature;
	
	private Integer passDirection;
	
	/**1-员工 2-访客*/
	private Integer identityType;

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}
	
	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getPassTime() {
		return passTime;
	}

	public void setPassTime(String passTime) {
		this.passTime = passTime;
	}
	
	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public Integer getPassWay() {
		return passWay;
	}

	public void setPassWay(Integer passWay) {
		this.passWay = passWay;
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

	public Integer getIdentityType() {
		return identityType;
	}

	public void setIdentityType(Integer identityType) {
		this.identityType = identityType;
	}
}
