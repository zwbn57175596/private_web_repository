package com.haoxw.chuanmei.model;

import java.util.Date;

/**
 * 老师订单表
 * 
 * @author xuewuhao
 * 
 */
public class TeacherOrders implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	// 下订单用户id
	private String userId;
	// 可以领取设备学生id中间用#隔开
	private String studentIds;
	// start time
	private Date sDate;
	// end time
	private Date eDate;
	// -1不通过 0申请 1通过 2结束
	private int state;
	// create time
	private Date cDate;
	// check time
	private Date checkDate;
	// check user id
	private String checkUserId;

	// jieshu order time
	private Date jieshuDate;
	// jieshu order user id
	private String jieshuUserId;

	private String leaveWord;
	// 备注
	private String remark;
	// 课程名称
	private String subject;
	//设备分类id
	private  int sbTypeId; 
	//辅助显示用
	private String name;
	
	// edit by zhaowei 2012-04-17
	// 班级名称
	private String claName;
	// 班级人数
	private int stuNum;
	// 试验形式
	private String expType;
	// 作业长度
	private int workTime;
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSbTypeId() {
		return sbTypeId;
	}

	public void setSbTypeId(int sbTypeId) {
		this.sbTypeId = sbTypeId;
	}

	@Override
	public String toString() {
		return "TeacherOrders [id=" + id + ", userId=" + userId
				+ ", studentIds=" + studentIds + ", sDate=" + sDate
				+ ", eDate=" + eDate + ", state=" + state + ", cDate=" + cDate
				+ ", checkDate=" + checkDate + ", checkUserId=" + checkUserId
				+ ", jieshuDate=" + jieshuDate + ", jieshuUserId="
				+ jieshuUserId + ", leaveWord=" + leaveWord + ", remark="
				+ remark + ", subject=" + subject + ", sbTypeId=" + sbTypeId
				+ ", name=" + name + ", className=" + claName +", stuNum=" + stuNum +", expType=" + expType +", workTime=" + workTime +"天]";
	}

	/**
	 * @return the studentIds
	 */
	public String getStudentIds() {
		return studentIds;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @param studentIds
	 *            the studentIds to set
	 */
	public void setStudentIds(String studentIds) {
		this.studentIds = studentIds;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


	/**
	 * @return the sDate
	 */
	public Date getsDate() {
		return sDate;
	}

	/**
	 * @param sDate
	 *            the sDate to set
	 */
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
	 * @param eDate
	 *            the eDate to set
	 */
	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @return the cDate
	 */
	public Date getcDate() {
		return cDate;
	}

	/**
	 * @param cDate
	 *            the cDate to set
	 */
	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	/**
	 * @return the checkDate
	 */
	public Date getCheckDate() {
		return checkDate;
	}

	/**
	 * @param checkDate
	 *            the checkDate to set
	 */
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	/**
	 * @return the checkUserId
	 */
	public String getCheckUserId() {
		return checkUserId;
	}

	/**
	 * @param checkUserId
	 *            the checkUserId to set
	 */
	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
	}

	/**
	 * @return the jieshuDate
	 */
	public Date getJieshuDate() {
		return jieshuDate;
	}

	/**
	 * @param jieshuDate
	 *            the jieshuDate to set
	 */
	public void setJieshuDate(Date jieshuDate) {
		this.jieshuDate = jieshuDate;
	}

	/**
	 * @return the jieshuUserId
	 */
	public String getJieshuUserId() {
		return jieshuUserId;
	}

	/**
	 * @param jieshuUserId
	 *            the jieshuUserId to set
	 */
	public void setJieshuUserId(String jieshuUserId) {
		this.jieshuUserId = jieshuUserId;
	}

	/**
	 * @return the leaveWord
	 */
	public String getLeaveWord() {
		return leaveWord;
	}

	/**
	 * @param leaveWord
	 *            the leaveWord to set
	 */
	public void setLeaveWord(String leaveWord) {
		this.leaveWord = leaveWord;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

  public String getClaName() {
    return claName;
  }

  public void setClaName(String className) {
    this.claName = className;
  }

  public int getStuNum() {
    return stuNum;
  }

  public void setStuNum(int stuNum) {
    this.stuNum = stuNum;
  }

  public String getExpType() {
    return expType;
  }

  public void setExpType(String expType) {
    this.expType = expType;
  }

  public int getWorkTime() {
    return workTime;
  }

  public void setWorkTime(int workTime) {
    this.workTime = workTime;
  }

}
