package com.haoxw.chuanmei.model;

import java.util.Date;

/**
 * 功能实体类
 * 
 * @author xuewuhao
 * 
 */
@SuppressWarnings("serial")
public class Func implements java.io.Serializable {

	// Fields

	private int funcId;
	private String funcName;
	private String url;
	private String funcDesc;
	private int parentId;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Func() {
	}

	/** minimal constructor */
	public Func(String funcName, String url, int parentId, Date createTime) {
		this.funcName = funcName;
		this.url = url;
		this.parentId = parentId;
		this.createTime = createTime;
	}

	/** full constructor */
	public Func(String funcName, String url, String funcDesc, int parentId,
			Date createTime) {
		this.funcName = funcName;
		this.url = url;
		this.funcDesc = funcDesc;
		this.parentId = parentId;
		this.createTime = createTime;
	}

	public int getFuncId() {
		return funcId;
	}

	public void setFuncId(int funcId) {
		this.funcId = funcId;
	}

	public String getFuncName() {
		return this.funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFuncDesc() {
		return this.funcDesc;
	}

	public void setFuncDesc(String funcDesc) {
		this.funcDesc = funcDesc;
	}

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Func [funcId=" + funcId + ", funcName=" + funcName + ", url="
				+ url + ", funcDesc=" + funcDesc + ", parentId=" + parentId
				+ ", createTime=" + createTime + "]";
	}

}