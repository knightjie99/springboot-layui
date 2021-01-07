package com.wujie.springbootlayui.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


@TableName("sys_dept")
public class Dept implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer pid;

	@Size(min = 1, max = 20, message = "部门名称长度不能大于20")
    private String name;

    /**
     * 是否展开，0不展开，1展开
     */
    private Integer open;

	@Size(min = 0, max = 100, message = "备注长度不能超过100")
    private String remark;

    private String address;

    /**
     * 是否可用，0不可用，1可用
     */
    private Integer available;

    /**
     * 排序码
     */
    private Integer ordernum;

    private Date createtime;

    private Integer belongUser;

	public Integer getBelongUser() {
		return belongUser;
	}

	public void setBelongUser(Integer belongUser) {
		this.belongUser = belongUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOpen() {
		return open;
	}

	public void setOpen(Integer open) {
		this.open = open;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Integer getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Dept [id=" + id + ", pid=" + pid + ", name=" + name + ", open=" + open + ", remark=" + remark
				+ ", address=" + address + ", available=" + available + ", ordernum=" + ordernum + ", createtime="
				+ createtime + "]";
	}
}
