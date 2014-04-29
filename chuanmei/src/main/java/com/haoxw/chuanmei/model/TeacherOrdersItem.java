package com.haoxw.chuanmei.model;

import java.util.Date;
import java.util.List;

/**
 * 老师订单项
 * @author xuewuhao
 *
 */
public class TeacherOrdersItem implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String orderId;
	private int shebeiId;
	private Date cDate;
	//辅助显示用
	private String name;
	private String code;
	private List<ShebeiOrder> shebeiOrderList;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TeacherOrdersItem [id=" + id + ", orderId=" + orderId
				+ ", shebeiId=" + shebeiId + ", cDate=" + cDate + "]";
	}
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
	 * @return the shebeiId
	 */
	public int getShebeiId() {
		return shebeiId;
	}
	/**
	 * @param shebeiId the shebeiId to set
	 */
	public void setShebeiId(int shebeiId) {
		this.shebeiId = shebeiId;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
  public List<ShebeiOrder> getShebeiOrderList() {
    return shebeiOrderList;
  }
  public void setShebeiOrderList(List<ShebeiOrder> shebeiOrderList) {
    this.shebeiOrderList = shebeiOrderList;
  }
	
}
