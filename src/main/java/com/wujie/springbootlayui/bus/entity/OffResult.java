package com.wujie.springbootlayui.bus.entity;

public class OffResult {
    Integer result;
    Double OverTime=0d;

    public Double getOverTime() {
        return OverTime;
    }

    public void setOverTime(Double overTime) {
        OverTime = overTime;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
