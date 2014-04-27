package com.haoxw.chuanmei.model;

import java.util.Date;

/**
 * 新闻
 * @author xuewuhao
 *
 */
@SuppressWarnings("serial")
public class News implements java.io.Serializable{

	private int id;
	private int typeNews;
	private String title;
	private String content;
	private String addUserId;
	private Date createTime;
	private Date upTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTypeNews() {
		return typeNews;
	}
	public void setTypeNews(int typeNews) {
		this.typeNews = typeNews;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAddUserId() {
		return addUserId;
	}
	public void setAddUserId(String addUserId) {
		this.addUserId = addUserId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpTime() {
		return upTime;
	}
	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "News [id=" + id + ", typeNews=" + typeNews + ", title=" + title
				+ ", content=" + content + ", addUserId=" + addUserId
				+ ", createTime=" + createTime + ", upTime=" + upTime + "]";
	}
	
}
