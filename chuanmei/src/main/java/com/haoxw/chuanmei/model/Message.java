package com.haoxw.chuanmei.model;

import java.util.Date;

/**
 * 提示信息
 * @author xuewuhao
 *
 */
public class Message implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  int id;
	//接受者id
	private  String userId;
	//发送者id  0代表系统发送 
	private  String sendUserId;
	//订单id
	private String orderId;
	//标题
	private String title;
	//内容
	private String content;
	//入库时间
	private Date cDate;
	//状态  0未读 1已读
	private int state;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * @return the state
	 */
	public int getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}
	
	/**
	 * @return the sendUserId
	 */
	public String getSendUserId() {
		return sendUserId;
	}
	/**
	 * @param sendUserId the sendUserId to set
	 */
	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Message [id=" + id + ", userId=" + userId + ", sendUserId="
				+ sendUserId + ", orderId=" + orderId + ", title=" + title
				+ ", content=" + content + ", cDate=" + cDate + ", state="
				+ state + "]";
	}
	
	
}
