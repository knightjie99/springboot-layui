package com.wujie.springbootlayui.sys.entity;

public class UserRsp {
	
	private String name;
	
	private String photo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "UserRsp [name=" + name + ", photo=" + photo + "]";
	}
}
