package com.haoxw.chuanmei.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备订单，用于识别设备资源的占用状态
 * @author zhaowei
 *
 */
@SuppressWarnings("serial")
public class ShebeiOrder implements Serializable {
  // 主键
  protected int id;
  // 设备ID
  protected int shebeiId;
  // 关联学生订单的ID
  protected String studentOrdersId;
  // 此设备开始占用时间
  protected Date sDate;
  // 此设备归还时间
  protected Date eDate;
  // 学生ID
  protected String studentId;
  
  //辅助显示用
  protected String studentName;
  
  protected Shebei shebei;
  
  public ShebeiOrder() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getShebeiId() {
    return shebeiId;
  }

  public void setShebeiId(int shebeiId) {
    this.shebeiId = shebeiId;
  }

  public String getStudentOrdersId() {
    return studentOrdersId;
  }

  public void setStudentOrdersId(String studentOrdersId) {
    this.studentOrdersId = studentOrdersId;
  }

  public Date getsDate() {
    return sDate;
  }

  public void setsDate(Date sDate) {
    this.sDate = sDate;
  }

  public Date geteDate() {
    return eDate;
  }

  public void seteDate(Date eDate) {
    this.eDate = eDate;
  }

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public Shebei getShebei() {
    return shebei;
  }

  public void setShebei(Shebei shebei) {
    this.shebei = shebei;
  }
}
