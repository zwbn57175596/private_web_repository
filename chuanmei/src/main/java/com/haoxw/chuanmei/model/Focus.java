package com.haoxw.chuanmei.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 焦点图
 * 
 * @author xuewuhao
 * 
 */
@SuppressWarnings("serial")
public class Focus implements Serializable {
	protected int id;
	protected String name;
	protected String imgUrl;
	protected String linkUrl;
	protected Date createTime;
	protected int orderNum;

	public Focus() {
	}

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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public String toString() {
		return "Focus [id=" + id + ", name=" + name + ", imgUrl=" + imgUrl + ", linkUrl=" + linkUrl + ", createTime="
				+ createTime + ", orderNum=" + orderNum + "]";
	}

}
