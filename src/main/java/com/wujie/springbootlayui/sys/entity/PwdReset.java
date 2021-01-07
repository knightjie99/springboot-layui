package com.wujie.springbootlayui.sys.entity;

import java.io.Serializable;

public class PwdReset implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String logName;
    
    private String phone;
    
    private String code;
    
    private String pwd;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
