package com.wujie.springbootlayui.bus.entity;

public class SelectHourParam {
    Integer timeRange;
    Integer gateId;

    public Integer getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(Integer timeRange) {
        this.timeRange = timeRange;
    }

    public Integer getGateId() {
        return gateId;
    }

    public void setGateId(Integer gateId) {
        this.gateId = gateId;
    }
}
