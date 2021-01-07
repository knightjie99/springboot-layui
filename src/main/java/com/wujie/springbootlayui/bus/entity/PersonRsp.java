package com.wujie.springbootlayui.bus.entity;

public class PersonRsp {
	
	private Integer personId;
	
	private String personName;
	
	private String phone;
	
	private String cardNum;
	
	private String deptName;
	
	private String idNum;
	
	/**1-男 2-女*/
	private Integer sex;
	
	private String personPhoto;
	
	/**1-员工 2-访客  3-VIP 4-黑名单*/
	private Integer identityType;
	
	/**当访客为VIP的访问周期 */
	private String cycleDate;
	
	/**员工提示语*/
	private String prompt;
	
	private String validTime;  
	
	private String invalidTime;
	
	private String createTime;
	
	private String updateTime;
	
	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public String getPersonPhoto() {
		return personPhoto;
	}

	public void setPersonPhoto(String personPhoto) {
		this.personPhoto = personPhoto;
	}

	public Integer getIdentityType() {
		return identityType;
	}

	public void setIdentityType(Integer identityType) {
		this.identityType = identityType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	public String getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(String invalidTime) {
		this.invalidTime = invalidTime;
	}
	
	public String getCycleDate() {
		return cycleDate;
	}

	public void setCycleDate(String cycleDate) {
		this.cycleDate = cycleDate;
	}

	@Override
	public String toString() {
		return "PersonRsp [personId=" + personId + ", personName=" + personName + ", phone=" + phone + ", cardNum="
				+ cardNum + ", deptName=" + deptName + ", idNum=" + idNum + ", sex=" + sex + ", personPhoto="
				+ personPhoto + ", identityType=" + identityType + ", cycleDate=" + cycleDate + ", validTime="
				+ validTime + ", invalidTime=" + invalidTime + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}

}
