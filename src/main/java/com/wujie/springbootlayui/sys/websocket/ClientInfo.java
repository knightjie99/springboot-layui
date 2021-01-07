package com.wujie.springbootlayui.sys.websocket;

import org.springframework.web.socket.WebSocketSession;

public class ClientInfo {
	
	private WebSocketSession session;
	
	private String addTime;
	
	private String deviceSn;

	public WebSocketSession getSession() {
		return session;
	}

	public void setSession(WebSocketSession session) {
		this.session = session;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}
}
