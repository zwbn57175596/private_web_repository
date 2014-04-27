package com.haoxw.chuanmei.model;

import java.util.Date;

/**
 * 角色实体类.
 * 
 * @author xuewuhao
 * 
 */
@SuppressWarnings("serial")
public class Role implements java.io.Serializable {

	// Fields

	private int roleId;
	private String roleName;
	private String roleDesc;
	private Date createTime;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName
				+ ", roleDesc=" + roleDesc + ", createTime=" + createTime + "]";
	}

}