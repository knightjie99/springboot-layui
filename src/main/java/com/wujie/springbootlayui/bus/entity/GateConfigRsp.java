package com.wujie.springbootlayui.bus.entity;

public class GateConfigRsp {
	
	private Integer id;
	
	private String gateName;
	
	private String faceSet;
	
	private String infraredSet;
	
	private String temperatureSet;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGateName() {
		return gateName;
	}

	public void setGateName(String gateName) {
		this.gateName = gateName;
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
