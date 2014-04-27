package com.haoxw.chuanmei.model;

/**
 * 设备分类与审核用户对应关系中间表
 * 
 * @author xuewuhao
 * 
 */
@SuppressWarnings("serial")
public class TypeUser implements java.io.Serializable {

	private int id;
	private String userId;
	private int sbTypeId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getSbTypeId() {
		return sbTypeId;
	}
	public void setSbTypeId(int sbTypeId) {
		this.sbTypeId = sbTypeId;
	}
	@Override
	public String toString() {
		return "TypeUser [id=" + id + ", userId=" + userId + ", sbTypeId="
				+ sbTypeId + "]";
	}

}