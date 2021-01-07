package com.wujie.springbootlayui.sys.common;


import com.wujie.springbootlayui.sys.entity.User;
import java.util.List;

public class ActiverUser {

    private User user;

    private List<String> roles;

    private List<String> permission;

    private Integer loginRole;

	public ActiverUser() {
		
	}

	public ActiverUser(User user, List<String> roles, List<String> permission) {
		super();
		this.user = user;
		this.roles = roles;
		this.permission = permission;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getPermission() {
		return permission;
	}

	public void setPermission(List<String> permission) {
		this.permission = permission;
	}

	public Integer getLoginRole() {
		return loginRole;
	}

	public void setLoginRole(Integer loginRole) {
		this.loginRole = loginRole;
	}
}
