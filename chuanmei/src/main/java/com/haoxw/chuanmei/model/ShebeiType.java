package com.haoxw.chuanmei.model;

import java.util.Date;

/**
 * 设备分类实体
 * @author xuewuhao
 *
 */
public class ShebeiType implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  int id;
	private String name;
	private Date cDate;
	// 0 正常1删除
	private int state;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getcDate() {
		return cDate;
	}
	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "ShebeiType [id=" + id + ", name=" + name + ", cDate=" + cDate
				+ ", state=" + state + "]";
	}
	
}
