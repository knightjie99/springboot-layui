package com.wujie.springbootlayui.sys.entity;

public class AppRsp {
	
	private String apkVer;
	
	private String downloadPath;
	
	private Integer verType;
	
	public Integer getVerType() {
		return verType;
	}

	public void setVerType(Integer verType) {
		this.verType = verType;
	}

	public String getApkVer() {
		return apkVer;
	}

	public void setApkVer(String apkVer) {
		this.apkVer = apkVer;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	@Override
	public String toString() {
		return "AppRsp [apkVer=" + apkVer + ", downloadPath=" + downloadPath + ", verType=" + verType + "]";
	}
}
