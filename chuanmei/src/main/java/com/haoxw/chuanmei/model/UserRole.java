package com.haoxw.chuanmei.model;

/**
 * 用户角色中间表
 * 
 * @author xuewuhao
 * 
 */
@SuppressWarnings("serial")
public class UserRole implements java.io.Serializable {

	private int urId;
	private String userId;
	private int roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getUrId() {
		return urId;
	}

	public void setUrId(int urId) {
		this.urId = urId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}