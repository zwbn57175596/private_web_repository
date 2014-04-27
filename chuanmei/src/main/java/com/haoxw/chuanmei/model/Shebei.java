package com.haoxw.chuanmei.model;

import java.util.Date;

/**
 * 设备实体
 * @author xuewuhao
 *
 */
public class Shebei implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  int id;
	private String name;
	private String imgUrl;
	private Date cDate;
	private int num;
	private int nowNum;
	private String remark;
	private String code;
	private  int type;
	// 0 可用 1被借用 -1删除
	private int state;
	//辅助显示用  >1表示选中
	private  int check;
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the check
	 */
	public int getCheck() {
		return check;
	}

	/**
	 * @param check the check to set
	 */
	public void setCheck(int check) {
		this.check = check;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the nowNum
	 */
	public int getNowNum() {
		return nowNum;
	}

	/**
	 * @param nowNum the nowNum to set
	 */
	public void setNowNum(int nowNum) {
		this.nowNum = nowNum;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * @param imgUrl the imgUrl to set
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/**
	 * @return the cDate
	 */
	public Date getcDate() {
		return cDate;
	}

	/**
	 * @param cDate the cDate to set
	 */
	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Shebei [id=" + id + ", name=" + name + ", imgUrl=" + imgUrl
				+ ", cDate=" + cDate + ", num=" + num + ", nowNum=" + nowNum
				+ ", remark=" + remark + ", code=" + code + ", type=" + type
				+ ", state=" + state + ", check=" + check + "]";
	}
}
