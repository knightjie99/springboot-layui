package com.wujie.springbootlayui.bus.entity;

public class GateReq {
	
	private String deviceSn;
	
    private String faceSet;
	
	private String infraredSet;
	
	private String temperatureSet;

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}

	public String getFaceSet() {
		return faceSet;
	}

	public void setFaceSet(String faceSet) {
		this.faceSet = faceSet;
	}

	public String getInfraredSet() {
		return infraredSet;
	}

	public void setInfraredSet(String infraredSet) {
		this.infraredSet = infraredSet;
	}

	public String getTemperatureSet() {
		return temperatureSet;
	}

	public void setTemperatureSet(String temperatureSet) {
		this.temperatureSet = temperatureSet;
	}
	

}
