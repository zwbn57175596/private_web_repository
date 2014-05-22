package com.haoxw.chuanmei.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.haoxw.chuanmei.bean.DbException;
import com.haoxw.chuanmei.dao.base.DbOp;
import com.haoxw.chuanmei.dao.base.ResultObjectCall;
import com.haoxw.chuanmei.model.StudentGroup;

/**
 * 学生分组
 * @author zhaowei
 *
 */
@Repository
public class StudentGroupDao {
  private static final DbOp<StudentGroup> dbop = new DbOp<StudentGroup>();
  
  /**
   * 获取老师申请的分组信息
   * @param id 老师的订单ID
   * @return
   * @throws DbException
   */
  public List<StudentGroup> getStudentGroupsByTeacherOrdersId (String id) throws DbException {
    String sql = " select t.* from student_group t where t.teacherOrdersId = ? ";
    List<Object> lp = new ArrayList<Object>();
    lp.add(id);
    
    List<StudentGroup> r = null;
    r = dbop.findListParam(0l, sql, lp, new ResultObjectCall<StudentGroup>() {
      @Override
      public StudentGroup getResultObject(ResultSet rs) throws SQLException, DbException {
        if (rs != null) {
          StudentGroup g = new StudentGroup();
          g.setId(rs.getInt(1));
          g.setName(rs.getString(2));
          g.setTeacherOrdersId(rs.getString(3));
          g.setLeader(rs.getString(4));
          g.setMembers(rs.getString(5));
          return g;
        }
        return null;
      }
    });
    if (null != r && r.size() > 0) {
      return r;
    }
    return null;
  }
  
  
  /**
   * 保存学生分组
   * @param g
   * @return
   */
  @SuppressWarnings("static-access")
  public boolean insertOrUpdate (StudentGroup g) {
    String sql = "";
    List<Object> lp = new ArrayList<Object>();
    lp.add(g.getName());
    lp.add(g.getTeacherOrdersId());
    lp.add(g.getLeader());
    lp.add(g.getMembers());
    if (g.getId() == 0) {
      sql = " insert into student_group (name, teacherOrdersId, leader, members) "
          + " values (?,?,?,?)";
    } else {
      lp.add(g.getId());
      sql = " update student_group set name=?, teacherOrdersId=?, leader=?, "
          + " members=? where id=?";
    }
    int result = dbop.update(1, sql, lp);
    if (result > 0) {
      return true;
    }
    return false;
  }
}
