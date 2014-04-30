package com.haoxw.chuanmei.model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class StudentGroup implements Serializable {
  // 主键
  private int id;
  // 关键老师课程订单的ID
  private String teacherOrdersId;
  // 小组名称
  private String name;
  // 组长一名
  private String leader;
  // 组员若干
  private String members;
  
  // 辅助页面用
  private User uleader;
  
  private List<User> membersList;
  
  
  public StudentGroup() {
    super();
  }
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getTeacherOrdersId() {
    return teacherOrdersId;
  }
  public void setTeacherOrdersId(String teacherOrdersId) {
    this.teacherOrdersId = teacherOrdersId;
  }
  public String getLeader() {
    return leader;
  }
  public void setLeader(String leader) {
    this.leader = leader;
  }
  public String getMembers() {
    return members;
  }
  public void setMembers(String members) {
    this.members = members;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User getUleader() {
    return uleader;
  }

  public void setUleader(User uleader) {
    this.uleader = uleader;
  }

  public List<User> getMembersList() {
    return membersList;
  }

  public void setMembersList(List<User> membersList) {
    this.membersList = membersList;
  }
  
  
  
  

}
