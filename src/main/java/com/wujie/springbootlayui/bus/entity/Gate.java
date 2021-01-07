package com.wujie.springbootlayui.bus.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.Size;


@TableName("bus_gate")
public class Gate implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@Size(min=0, max=20 ,message="序列号长度不能超过20")//0是为了批量导入不报错
	private String deviceSn;
	
	/**导入方式  1-批量导入 2-单卡导入*/
	@TableField(exist = false)
	private Integer inputWay;
	
	/**上传的文件路径*/
	@TableField(exist = false)
	private String mf;
	
	/**
	 * 闸机名称
	 */
	@Size(min=0, max=20 ,message="闸机名称不能超过20")  //0是为了批量导入不报错
	private String gateName;
	
	/**
	 * 在线状态(1-在线 2-离线)
	 */
	private Integer onlineStatus;

	@Size(min=0, max=20 ,message="安装地址长度不能超过20")
	private String installLocation;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date  activeTime;
	
	/**
	 * 闸机状态(1-已激活 2-未激活)
	 */
	private Integer gateStatus;
	
	private String currentVer;
	
	private Integer verType;
	
	private String newestVer;
	
	/**
	 * 人脸设置(on-开 off-关)
	 */
	private String faceSet;
	
	/**
	 * 红外线设置(on-开 off-关)
	 */
	private String infraredSet;
	
	/**
	 * 测温设置(on-开 off-关)
	 */
	private String temperatureSet;
	
	/**
	 * 所属用户的ID(关联sys_user表)
	 */
	private Integer belongId;
	
	/**
	 * 备注
	 */
	@Size(min = 0, max = 100, message = "备注长度不能超过100")
	private String remark;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date  createTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date  updateTime;
	
	/**
	 * 是否可用(0-已删除 1-可用)
	 */
	private Integer available;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}
	
	public Integer getInputWay() {
		return inputWay;
	}

	public void setInputWay(Integer inputWay) {
		this.inputWay = inputWay;
	}

	public Integer getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(Integer onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public String getInstallLocation() {
		return installLocation;
	}

	public void setInstallLocation(String installLocation) {
		this.installLocation = installLocation;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}
	
	public String getMf() {
		return mf;
	}

	public void setMf(String mf) {
		this.mf = mf;
	}

	public String getGateName() {
		return gateName;
	}

	public void setGateName(String gateName) {
		this.gateName = gateName;
	}

	public Integer getGateStatus() {
		return gateStatus;
	}

	public void setGateStatus(Integer gateStatus) {
		this.gateStatus = gateStatus;
	}

	public String getCurrentVer() {
		return currentVer;
	}

	public void setCurrentVer(String currentVer) {
		this.currentVer = currentVer;
	}
	
	public Integer getVerType() {
		return verType;
	}

	public void setVerType(Integer verType) {
		this.verType = verType;
	}

	public String getNewestVer() {
		return newestVer;
	}

	public void setNewestVer(String newestVer) {
		this.newestVer = newestVer;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getBelongId() {
		return belongId;
	}

	public void setBelongId(Integer belongId) {
		this.belongId = belongId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "Gate [id=" + id + ", deviceSn=" + deviceSn + ", inputWay=" + inputWay + ", mf=" + mf + ", gateName="
				+ gateName + ", onlineStatus=" + onlineStatus + ", installLocation=" + installLocation + ", activeTime="
				+ activeTime + ", gateStatus=" + gateStatus + ", currentVer=" + currentVer + ", verType=" + verType
				+ ", newestVer=" + newestVer + ", faceSet=" + faceSet + ", infraredSet=" + infraredSet
				+ ", temperatureSet=" + temperatureSet + ", belongId=" + belongId + ", remark=" + remark
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", available=" + available + "]";
	}
}
