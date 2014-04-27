package com.haoxw.chuanmei.model;

import java.util.Date;

/**
 * 用户实体
 * 
 * @author xuewuhao
 * 
 */
@SuppressWarnings("serial")
public class User implements java.io.Serializable {

	// Fields
	//班级  学生属性 其他默认为“”
	private String uclass;
	private String name;
	private String pwd;
	//0有效 1无效
	private int status;
	private Date createTime;
	private int createUserId;
	//0男 1女
	private int gender;
	//id
	private String code;

	//辅助显示
	private int roleId;
	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}

	@Override
	public String toString() {
		return "User [uclass=" + uclass + ", name=" + name + ", pwd=" + pwd
				+ ", status=" + status + ", createTime=" + createTime
				+ ", createUserId=" + createUserId + ", gender=" + gender
				+ ", code=" + code + ", roleId=" + roleId + "]";
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}

	/**
	 * @return the uclass
	 */
	public String getUclass() {
		return uclass;
	}

	/**
	 * @param uclass the uclass to set
	 */
	public void setUclass(String uclass) {
		this.uclass = uclass;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}