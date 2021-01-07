package com.wujie.springbootlayui.sys.entity;

import java.io.Serializable;

public class DeskParam implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer attends = 0;
    private Integer lateSum = 0;
    private Integer earlySum = 0;
    private Integer gates = 0;
    private Integer onGate = 0;
    private Integer offGate = 0;
    private Integer records = 0;
    private Integer visitor = 0;
    private Integer highTemp = 0;
    private Integer noClock=0;

    public Integer getNoClock() {
        return noClock;
    }

    public void setNoClock(Integer noClock) {
        this.noClock = noClock;
    }

    public Integer getAttends() {
        return attends;
    }

    public void setAttends(Integer attends) {
        this.attends = attends;
    }

    public Integer getLateSum() {
        return lateSum;
    }

    public void setLateSum(Integer lateSum) {
        this.lateSum = lateSum;
    }

    public Integer getEarlySum() {
        return earlySum;
    }

    public void setEarlySum(Integer earlySum) {
        this.earlySum = earlySum;
    }

    public Integer getGates() {
        return gates;
    }

    public void setGates(Integer gates) {
        this.gates = gates;
    }

    public Integer getOnGate() {
        return onGate;
    }

    public void setOnGate(Integer onGate) {
        this.onGate = onGate;
    }

    public Integer getOffGate() {
        return offGate;
    }

    public void setOffGate(Integer offGate) {
        this.offGate = offGate;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public Integer getVisitor() {
        return visitor;
    }

    public void setVisitor(Integer visitor) {
        this.visitor = visitor;
    }

    public Integer getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(Integer highTemp) {
        this.highTemp = highTemp;
    }

    @Override
    public String toString() {
        return "DeskParam{" +
                "attends=" + attends +
                ", lateSum=" + lateSum +
                ", earlySum=" + earlySum +
                ", gates=" + gates +
                ", onGate=" + onGate +
                ", offGate=" + offGate +
                ", records=" + records +
                ", visitor=" + visitor +
                ", highTemp=" + highTemp +
                '}';
    }
}
