package com.haoxw.chuanmei.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.haoxw.chuanmei.bean.DbException;
import com.haoxw.chuanmei.dao.base.DbOp;
import com.haoxw.chuanmei.dao.base.ResultObjectCall;
import com.haoxw.chuanmei.model.ShebeiOrder;

/**
 * 设备订单
 * 
 * @author zhaowei
 * 
 */
@Repository
public class ShebeiOrderDao {
  private static final DbOp<ShebeiOrder> dbop = new DbOp<ShebeiOrder>();

  // private boolean flag = false;
  
  /**
   * 通过设备ID取设备被的预约记录（只计算已审批通过的和已领取的，取大于当天的数据）
   * @param id
   * @return List<ShebeiOrder>
   * @throws DbException
   */
  public List<ShebeiOrder> listShebeiOrderByShebeiId (int id) throws DbException {
    List<Object> lp = new ArrayList<Object>();
    String sql = " select t.*, u.name from shebei_order t left join student_orders o on t.studentOrdersId = o.id " +
        " left join user u on o.userId = u.code where t.shebeiId = ? and t.sDate >= ? and (o.state = 1 or o.state = 2) ";
    lp.add(id);
    Calendar c = Calendar.getInstance();
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);
    lp.add(c.getTime());
    
    List<ShebeiOrder> r = null;
    r = dbop.findListParam(0l, sql, lp, new ResultObjectCall<ShebeiOrder>() {
      @Override
      public ShebeiOrder getResultObject(ResultSet rs) throws SQLException, DbException {
        if (rs != null) {
          ShebeiOrder o = new ShebeiOrder();
          o.setId(rs.getInt("id"));
          o.setShebeiId(rs.getInt("shebeiId"));
          o.setStudentId(rs.getString("studentId"));
          o.setStudentOrdersId(rs.getString("studentOrdersId"));
          o.setsDate(rs.getTimestamp("sDate"));
          o.seteDate(rs.getTimestamp("eDate"));
          o.setStudentName(rs.getString("name"));
          // 取出预约的学生姓名，方便显示
          return o;
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
   * 通过学生ID取其借用列表
   * @param id
   * @return List<ShebeiOrder>
   * @throws DbException
   */
  public List<ShebeiOrder> listShebeiOrderByStudentId(String id) throws DbException {
    List<Object> lp = new ArrayList<Object>();
    String sql = " select t.* from shebei_order t where t.studentId = ? ";
    lp.add(id);

    List<ShebeiOrder> r = null;
    r = dbop.findListParam(0l, sql, lp, new ResultObjectCall<ShebeiOrder>() {
      @Override
      public ShebeiOrder getResultObject(ResultSet rs) throws SQLException, DbException {
        if (rs != null) {
          ShebeiOrder o = new ShebeiOrder();
          o.setId(rs.getInt("id"));
          o.setShebeiId(rs.getInt("shebeiId"));
          o.setStudentId(rs.getString("studentId"));
          o.setStudentOrdersId(rs.getString("studentOrdersId"));
          o.setsDate(rs.getTimestamp("sDate"));
          o.seteDate(rs.getTimestamp("eDate"));
          return o;
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
   * 插入一个设备实际订单
   * 
   * @param si
   *          设备ID
   * @param so
   *          学生订单ID
   * @param sd
   *          开始时间
   * @param ed
   *          归还时间
   * @return
   */
  public boolean saveShebeiOrder(ShebeiOrder o) {
    String sql = "insert into shebei_order(shebeiId, studentOrdersId, sDate, eDate, studentId) VALUES (?,?,?,?,?) ";
    List<Object> lp = new ArrayList<Object>();
    int rw = -1;
    lp.add(o.getShebeiId());
    lp.add(o.getStudentOrdersId());
    lp.add(o.getsDate());
    lp.add(o.geteDate());
    lp.add(o.getStudentId());
    rw = DbOp.update(0, sql, lp);
    if (rw > 0) {
      return true;
    }
    return false;
  }

}
