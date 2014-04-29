package com.haoxw.chuanmei.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.haoxw.chuanmei.bean.DbException;
import com.haoxw.chuanmei.dao.base.DbOp;
import com.haoxw.chuanmei.dao.base.ResultObjectCall;
import com.haoxw.chuanmei.model.StudentOrders;
import com.haoxw.chuanmei.model.TeacherOrders;
import com.haoxw.chuanmei.util.DateUtil;
import com.haoxw.chuanmei.util.Uuid;

/**
 * 学生订单dao
 * 
 * @author xuewuhao
 * 
 */
@Repository
public class StudentOrdersDao {

	private static final DbOp<StudentOrders> dbop = new DbOp<StudentOrders>();
	private static boolean result = false;

	/**
	 * 保存
	 * 
	 * @param studentOrders
	 * @return
	 * @throws DbException
	 */
	public String saveStudentOrders(StudentOrders studentOrders)
			throws DbException {
		String orderid = Uuid.getUUID();
		String addsql = " insert into student_orders(id,userId,orderId,cDate,state,checkUserId,jieshuUserId,updateDate,remark,sDate,eDate,isRed) values(?,?,?,now(),0,?,?,now(),?,?,?,?) ";
		List<Object> listParam = new ArrayList<Object>();
		int rows = -1;
		listParam.add(orderid);
		listParam.add(studentOrders.getUserId());
		listParam.add(studentOrders.getOrderId());
		listParam.add(studentOrders.getCheckUserId());
		listParam.add(studentOrders.getJieshuUserId());
		listParam.add(studentOrders.getRemark());
		listParam.add(studentOrders.getsDate());
		listParam.add(studentOrders.geteDate());
		listParam.add(0);
		rows = dbop.update(0, addsql, listParam);
		if (rows > 0) {
			return orderid;
		} else {
			return null;
		}

	}

	/**
	 * 修改
	 * 
	 * @param studentOrders
	 * @return
	 * @throws DbException
	 */
	public boolean updateStudentOrders(StudentOrders studentOrders)
			throws DbException {
		String updatesql = "update student_orders set userId=?,orderId=?,state=?,checkUserId=?,jieshuUserId = ?,updateDate=now(),remark=?,sDate=?,eDate=?,isRed=?  where id=?";
		List<Object> listParam = new ArrayList<Object>();
		int rows = -1;
		listParam.add(studentOrders.getUserId());
		listParam.add(studentOrders.getOrderId());
		listParam.add(studentOrders.getState());
		listParam.add(studentOrders.getCheckUserId());
		listParam.add(studentOrders.getJieshuUserId());
		listParam.add(studentOrders.getRemark());
		listParam.add(studentOrders.getsDate());
		listParam.add(studentOrders.geteDate());
		listParam.add(studentOrders.getIsRed());
		listParam.add(studentOrders.getId());
		rows = dbop.update(0, updatesql, listParam);
		if (rows > 0)
			result = true;
		return result;

	}

	/**
	 * 查找单条记录
	 * 
	 * @param id
	 * @return
	 * @throws DbException
	 */
	public StudentOrders getStudentOrdersById(String id) throws DbException {
		String sql = "select s.*,u.name from student_orders s,user u where s.id=? and s.userId=u.code";
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(id);
		List<StudentOrders> listStudentOrders = dbop.findListParam(0l, sql,
				listParam, new ResultObjectCall<StudentOrders>() {
					@Override
					public StudentOrders getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							StudentOrders studentOrders = new StudentOrders();
							studentOrders.setId(rs.getString(1));
							studentOrders.setUserId(rs.getString(2));
							studentOrders.setOrderId(rs.getString(3));
							studentOrders.setcDate(rs.getTimestamp(4));
							studentOrders.setState(rs.getInt(5));
							studentOrders.setCheckUserId(rs.getString(6));
							studentOrders.setJieshuUserId(rs.getString(7));
							studentOrders.setUpdateDate(rs.getTimestamp(8));
							studentOrders.setRemark(rs.getString(9));
							studentOrders.setsDate(rs.getTimestamp(10));
							studentOrders.seteDate(rs.getTimestamp(11));
							studentOrders.setIsRed(rs.getInt(12));
							studentOrders.setName(rs.getString(13));
							return studentOrders;
						}
						return null;
					}

				});
		if (listStudentOrders != null && listStudentOrders.size() > 0) {
			return listStudentOrders.get(0);
		}
		return null;
	}

	/**
	 * 统计学生订单数目
	 * 
	 * @param userId
	 * @return
	 */
	public int countStudentOrders(String userId) {
		List<Object> listParam = new ArrayList<Object>();
		String sql = null;
		sql = "select count(*) from student_orders where userId = ?  ";
		listParam.add(userId);
		int rows = dbop.count(0, sql, listParam);
		return rows;
	}

	/**
	 * 查找学生订单列表
	 * 
	 * @param userId
	 * @param offset
	 * @param limit
	 * @return
	 * @throws DbException
	 */
	public List<StudentOrders> findStudentOrdersList(String userId, int offset, int limit)
			throws DbException {
		List<Object> listParam = new ArrayList<Object>();
		String sql  = "select s.*,u.name,t.subject from student_orders s,user u,teacher_orders t where s.userId = ? and s.userId=u.code and s.orderId=t.id   order by s.cDate desc limit ?,? ";
		listParam.add(userId);
		listParam.add(offset);
		listParam.add(limit);

		List<StudentOrders> listStudentOrders  = dbop.findListParam(0l, sql, listParam,
				new ResultObjectCall<StudentOrders>() {
					@Override
					public StudentOrders getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							StudentOrders studentOrders = new StudentOrders();
							studentOrders.setId(rs.getString(1));
							studentOrders.setUserId(rs.getString(2));
							studentOrders.setOrderId(rs.getString(3));
							studentOrders.setcDate(rs.getTimestamp(4));
							studentOrders.setState(rs.getInt(5));
							studentOrders.setCheckUserId(rs.getString(6));
							studentOrders.setJieshuUserId(rs.getString(7));
							studentOrders.setUpdateDate(rs.getTimestamp(8));
							studentOrders.setRemark(rs.getString(9));
							studentOrders.setsDate(rs.getTimestamp(10));
							studentOrders.seteDate(rs.getTimestamp(11));
							studentOrders.setIsRed(rs.getInt(12));
							studentOrders.setName(rs.getString(13));
							studentOrders.setTitle(rs.getString(14));
							return studentOrders;
						}
						return null;
					}

				});
		return listStudentOrders;
	}

	
	/**
	 * 统计all订单数目
	 * 
	 * @return
	 */
	public int countAllStudentOrders() {
		String sql = null;
		sql = "select count(*) from student_orders";
		int rows = dbop.count(0, sql, null);
		return rows;
	}

	/**
	 * 查找all订单列表
	 * @param offset
	 * @param limit
	 * @return
	 * @throws DbException
	 */
	public List<StudentOrders> findAllStudentOrdersList(int offset, int limit)
			throws DbException {
		List<Object> listParam = new ArrayList<Object>();
		String sql = null;
		sql = "select s.*,u.name,t.subject from student_orders s,user u,teacher_orders t where s.userId=u.code and s.orderId=t.id   order by s.cDate desc limit ?,? ";
		listParam.add(offset);
		listParam.add(limit);

		List<StudentOrders> listStudentOrders  = dbop.findListParam(0l, sql, listParam,
				new ResultObjectCall<StudentOrders>() {
					@Override
					public StudentOrders getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							StudentOrders studentOrders = new StudentOrders();
							studentOrders.setId(rs.getString(1));
							studentOrders.setUserId(rs.getString(2));
							studentOrders.setOrderId(rs.getString(3));
							studentOrders.setcDate(rs.getTimestamp(4));
							studentOrders.setState(rs.getInt(5));
							studentOrders.setCheckUserId(rs.getString(6));
							studentOrders.setJieshuUserId(rs.getString(7));
							studentOrders.setUpdateDate(rs.getTimestamp(8));
							studentOrders.setRemark(rs.getString(9));
							studentOrders.setsDate(rs.getTimestamp(10));
							studentOrders.seteDate(rs.getTimestamp(11));
							studentOrders.setIsRed(rs.getInt(12));
							studentOrders.setName(rs.getString(13));
							studentOrders.setTitle(rs.getString(14));
							return studentOrders;
						}
						return null;
					}

				});
		return listStudentOrders;
	}

	
	/**
	 * 查找需要发信息提醒还设备的订单列表
	 * @return
	 * @throws DbException
	 */
	public List<StudentOrders> findNoticeStudentOrdersList()
			throws DbException {
		Date now = new Date();
		List<Object> listParam = new ArrayList<Object>();
		String sql = null;
		sql = "select  s.*,u.name,t.subject from student_orders s,teacher_orders t,user u where s.userId=u.code and s.orderId = t.id and s.state=2 and s.eDate<='"+DateUtil.date2Str(now, "yyyy-MM-dd HH:mm:ss")+"'   order by s.cDate desc ";

		List<StudentOrders> listStudentOrders  = dbop.findListParam(0l, sql, listParam,
				new ResultObjectCall<StudentOrders>() {
					@Override
					public StudentOrders getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							StudentOrders studentOrders = new StudentOrders();
							studentOrders.setId(rs.getString(1));
							studentOrders.setUserId(rs.getString(2));
							studentOrders.setOrderId(rs.getString(3));
							studentOrders.setcDate(rs.getTimestamp(4));
							studentOrders.setState(rs.getInt(5));
							studentOrders.setCheckUserId(rs.getString(6));
							studentOrders.setJieshuUserId(rs.getString(7));
							studentOrders.setUpdateDate(rs.getTimestamp(8));
							studentOrders.setRemark(rs.getString(9));
							studentOrders.setsDate(rs.getTimestamp(10));
							studentOrders.seteDate(rs.getTimestamp(11));
							studentOrders.setIsRed(rs.getInt(12));
							studentOrders.setName(rs.getString(13));
							studentOrders.setTitle(rs.getString(14));
							return studentOrders;
						}
						return null;
					}

				});
		return listStudentOrders;
	}

	
	
	/**
	 * 统计某审核员应该审核的订单数目
	 * 
	 * @return
	 */
	public int countAllStudentOrders(int sbTypeId) {
		String sql = null;
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(sbTypeId);
		sql = "select count(*) from student_orders  s , teacher_orders t where t.sbTypeId=? and s.orderId =t.id ";
		int rows = dbop.count(0, sql, listParam);
		return rows;
	}

	/**
	 * 查找某审核员应该审核的订单列表
	 * @param offset
	 * @param limit
	 * @return
	 * @throws DbException
	 */
	public List<StudentOrders> findAllStudentOrdersList(int sbTypeId,int offset, int limit)
			throws DbException {
		List<Object> listParam = new ArrayList<Object>();
		String sql = null;
		sql = " select s.*,u.name,t.subject,t.sDate,t.eDate from student_orders s,user u,teacher_orders t "
		    + " where t.sbTypeId=? and s.userId=u.code and s.orderId=t.id "
		    + " order by s.cDate desc limit ?,? ";
		listParam.add(sbTypeId);
		listParam.add(offset);
		listParam.add(limit);

		List<StudentOrders> listStudentOrders  = dbop.findListParam(0l, sql, listParam,
				new ResultObjectCall<StudentOrders>() {
					@Override
					public StudentOrders getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							StudentOrders studentOrders = new StudentOrders();
							studentOrders.setId(rs.getString(1));
							studentOrders.setUserId(rs.getString(2));
							studentOrders.setOrderId(rs.getString(3));
							studentOrders.setcDate(rs.getTimestamp(4));
							studentOrders.setState(rs.getInt(5));
							studentOrders.setCheckUserId(rs.getString(6));
							studentOrders.setJieshuUserId(rs.getString(7));
							studentOrders.setUpdateDate(rs.getTimestamp(8));
							studentOrders.setRemark(rs.getString(9));
							studentOrders.setsDate(rs.getTimestamp(10));
							studentOrders.seteDate(rs.getTimestamp(11));
							studentOrders.setIsRed(rs.getInt(12));
							studentOrders.setName(rs.getString(13));
							// studentOrders.setTitle(rs.getString(14));
							TeacherOrders t = new TeacherOrders();
							t.setSubject(rs.getString(14));
							t.setsDate(rs.getTimestamp(15));
							t.seteDate(rs.getTimestamp(16));
							studentOrders.setTeacherOrders(t);
							return studentOrders;
						}
						return null;
					}

				});
		return listStudentOrders;
	}
	
	public static void main(String arg[]) throws DbException{
		StudentOrdersDao sd = new StudentOrdersDao();
		sd.findNoticeStudentOrdersList();
	}
}
