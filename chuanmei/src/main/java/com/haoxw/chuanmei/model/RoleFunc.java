package com.haoxw.chuanmei.model;


/**
 * 角色 功能中间表
 * 
 * @author xuewuhao
 * 
 */
@SuppressWarnings("serial")
public class RoleFunc implements java.io.Serializable {

	private int rfId;
	private int roleId;
	private int funcId;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getFuncId() {
		return funcId;
	}

	public void setFuncId(int funcId) {
		this.funcId = funcId;
	}

	public int getRfId() {
		return rfId;
	}

	public void setRfId(int rfId) {
		this.rfId = rfId;
	}

}