package com.haoxw.chuanmei.model;

import java.util.Date;

/**
 * 学生订单
 * @author xuewuhao
 *
 */
public class StudentOrders implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//订单id
	private String id;
	//学生id
	private String userId;
	//老师订单id
	private String orderId;
	private Date cDate;
	//-1不通过 0申请 1通过 2已领取 3已归还 4结束
	private int state;
	// check user id
	private String checkUserId;
	// jieshu user id
	private String jieshuUserId;
	private Date updateDate;
	private Date sDate;
	private Date eDate;
	//是否红色显示 0 默认非红色显示 1红色
	private int  isRed;
	//辅助显示用
	private String remark;
	private String name;
	private String title;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getsDate() {
		return sDate;
	}
	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}
	/**
	 * @return the eDate
	 */
	public Date geteDate() {
		return eDate;
	}
	/**
	 * @param eDate the eDate to set
	 */
	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}
	/**
	 * @return the checkUserId
	 */
	public String getCheckUserId() {
		return checkUserId;
	}
	/**
	 * @param checkUserId the checkUserId to set
	 */
	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
	}
	/**
	 * @return the jieshuUserId
	 */
	public String getJieshuUserId() {
		return jieshuUserId;
	}
	/**
	 * @param jieshuUserId the jieshuUserId to set
	 */
	public void setJieshuUserId(String jieshuUserId) {
		this.jieshuUserId = jieshuUserId;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	
	public int getIsRed() {
		return isRed;
	}
	public void setIsRed(int isRed) {
		this.isRed = isRed;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "StudentOrders [id=" + id + ", userId=" + userId + ", orderId="
				+ orderId + ", cDate=" + cDate + ", state=" + state
				+ ", checkUserId=" + checkUserId + ", jieshuUserId="
				+ jieshuUserId + ", updateDate=" + updateDate + ", sDate="
				+ sDate + ", eDate=" + eDate + ", isRed=" + isRed + ", remark="
				+ remark + ", name=" + name + ", title=" + title + "]";
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	
}
