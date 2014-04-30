package com.haoxw.chuanmei.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.haoxw.chuanmei.bean.DbException;
import com.haoxw.chuanmei.dao.base.DbOp;
import com.haoxw.chuanmei.dao.base.ResultObjectCall;
import com.haoxw.chuanmei.model.TeacherOrders;
import com.haoxw.chuanmei.util.Uuid;

/**
 * 老师订单dao
 * 
 * @author xuewuhao
 * 
 */
@Repository
public class TeacherOrdersDao {

	private static final DbOp<TeacherOrders> dbop = new DbOp<TeacherOrders>();

	/**
	 * 保存
	 * 
	 * @param teacherOrders
	 * @return
	 * @throws DbException
	 */
	public String saveTeacherOrders(TeacherOrders teacherOrders)
			throws DbException {
		String result = null;
		String addsql = " insert into teacher_orders(id,userId,studentIds,sDate,eDate,"
		    + "state,cDate,checkDate,checkUserId,jieshuDate,jieshuUserId,leaveWord,"
		    + "remark,subject,sbTypeId, className, stuNum, expType, workTime) values"
		    + "(?,?,?,?,?,0,now(),?,?,?,?,?,?,?,?,?,?,?,?) ";
		List<Object> listParam = new ArrayList<Object>();
		int rows = -1;
		String uuid = Uuid.getUUID();
		listParam.add(uuid);
		listParam.add(teacherOrders.getUserId());
		listParam.add(teacherOrders.getStudentIds());
		listParam.add(teacherOrders.getsDate());
		listParam.add(teacherOrders.geteDate());
		listParam.add(teacherOrders.getCheckDate());
		listParam.add(teacherOrders.getCheckUserId());
		listParam.add(teacherOrders.getJieshuDate());
		listParam.add(teacherOrders.getJieshuUserId());
		listParam.add(teacherOrders.getLeaveWord());
		listParam.add(teacherOrders.getRemark());
		listParam.add(teacherOrders.getSubject());
		listParam.add(teacherOrders.getSbTypeId());
		listParam.add(teacherOrders.getClaName());
		listParam.add(teacherOrders.getStuNum());
		listParam.add(teacherOrders.getExpType());
		listParam.add(teacherOrders.getWorkTime());
		rows = dbop.update(0, addsql, listParam);
		if (rows > 0)
			result = uuid;
		return result;

	}

	/**
	 * 修改
	 * 
	 * @param teacherOrders
	 * @return
	 * @throws DbException
	 */
	public boolean updateTeacherOrders(TeacherOrders teacherOrders)
			throws DbException {
		boolean result = false;
		String updatesql = "update teacher_orders set userId=?,studentIds=?,sDate=?,eDate = ?,"
		    + "state=?,checkDate=?,checkUserId=?,jieshuDate=?,jieshuUserId=?,leaveWord=?,"
		    + "remark=?,subject=?,sbTypeId=?, className=?, stuNum=?, expType=?, workTime=? where id=?";
		List<Object> listParam = new ArrayList<Object>();
		int rows = -1;
		listParam.add(teacherOrders.getUserId());
		listParam.add(teacherOrders.getStudentIds());
		listParam.add(teacherOrders.getsDate());
		listParam.add(teacherOrders.geteDate());
		listParam.add(teacherOrders.getState());
		listParam.add(teacherOrders.getCheckDate());
		listParam.add(teacherOrders.getCheckUserId());
		listParam.add(teacherOrders.getJieshuDate());
		listParam.add(teacherOrders.getJieshuUserId());
		listParam.add(teacherOrders.getLeaveWord());
		listParam.add(teacherOrders.getRemark());
		listParam.add(teacherOrders.getSubject());
		listParam.add(teacherOrders.getSbTypeId());
		listParam.add(teacherOrders.getClaName());
		listParam.add(teacherOrders.getStuNum());
		listParam.add(teacherOrders.getExpType());
		listParam.add(teacherOrders.getWorkTime());
		listParam.add(teacherOrders.getId());
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
	public TeacherOrders getTeacherOrdersById(String id) throws DbException {
		String sql = "select t.*,u.name from teacher_orders t,user u where t.id=? and t.userId=u.code";
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(id);
		List<TeacherOrders> listTeacherOrders = null;
		listTeacherOrders = dbop.findListParam(0l, sql, listParam,
				new ResultObjectCall<TeacherOrders>() {
					@Override
					public TeacherOrders getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							TeacherOrders teacherOrders = new TeacherOrders();
							teacherOrders.setId(rs.getString(1));
							teacherOrders.setUserId(rs.getString(2));
							teacherOrders.setStudentIds(rs.getString(3));
							teacherOrders.setsDate(rs.getTimestamp(4));
							teacherOrders.seteDate(rs.getTimestamp(5));
							teacherOrders.setState(rs.getInt(6));
							teacherOrders.setcDate(rs.getTimestamp(7));
							teacherOrders.setCheckDate(rs.getTimestamp(8));
							teacherOrders.setCheckUserId(rs.getString(9));
							teacherOrders.setJieshuDate(rs.getTimestamp(10));
							teacherOrders.setJieshuUserId(rs.getString(11));
							teacherOrders.setLeaveWord(rs.getString(12));
							teacherOrders.setRemark(rs.getString(13));
							teacherOrders.setSubject(rs.getString(14));
							teacherOrders.setSbTypeId(rs.getInt(15));
							teacherOrders.setClaName(rs.getString(16));
							teacherOrders.setStuNum(rs.getInt(17));
							teacherOrders.setExpType(rs.getString(18));
							teacherOrders.setWorkTime(rs.getInt(19));
							teacherOrders.setName(rs.getString(20));
							return teacherOrders;
						}
						return null;
					}

				});
		if (listTeacherOrders != null && listTeacherOrders.size() > 0) {
			return listTeacherOrders.get(0);
		}
		return null;
	}

	/**
	 * 统计用户数目
	 * 
	 * @param userId
	 * @return
	 */
	public int countTeacherOrders(String userId) {
		List<Object> listParam = new ArrayList<Object>();
		String sql = "select count(userId) from teacher_orders where userId = ?  ";
		listParam.add(userId);
		int rows = dbop.count(0, sql, listParam);
		return rows;
	}

	/**
	 * 查找用户列表
	 * 
	 * @param userId
	 * @param offset
	 * @param limit
	 * @return
	 * @throws DbException
	 */
	public List<TeacherOrders> findTeacherOrdersList(String userId, int offset,
			int limit) throws DbException {
		List<Object> listParam = new ArrayList<Object>();
		String sql = "select t.*,u.name from teacher_orders t,user u where t.userId = ? and t.userId=u.code  order by t.cDate desc limit ?,? ";
		listParam.add(userId);
		listParam.add(offset);
		listParam.add(limit);

		List<TeacherOrders> listTeacherOrders = null;
		listTeacherOrders = dbop.findListParam(0l, sql, listParam,
				new ResultObjectCall<TeacherOrders>() {
					@Override
					public TeacherOrders getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							TeacherOrders teacherOrders = new TeacherOrders();
							teacherOrders.setId(rs.getString(1));
							teacherOrders.setUserId(rs.getString(2));
							teacherOrders.setStudentIds(rs.getString(3));
							teacherOrders.setsDate(rs.getTimestamp(4));
							teacherOrders.seteDate(rs.getTimestamp(5));
							teacherOrders.setState(rs.getInt(6));
							teacherOrders.setcDate(rs.getTimestamp(7));
							teacherOrders.setCheckDate(rs.getTimestamp(8));
							teacherOrders.setCheckUserId(rs.getString(9));
							teacherOrders.setJieshuDate(rs.getTimestamp(10));
							teacherOrders.setJieshuUserId(rs.getString(11));
							teacherOrders.setLeaveWord(rs.getString(12));
							teacherOrders.setRemark(rs.getString(13));
							teacherOrders.setSubject(rs.getString(14));
							teacherOrders.setSbTypeId(rs.getInt(15));
              teacherOrders.setClaName(rs.getString(16));
              teacherOrders.setStuNum(rs.getInt(17));
              teacherOrders.setExpType(rs.getString(18));
              teacherOrders.setWorkTime(rs.getInt(19));
              teacherOrders.setName(rs.getString(20));
							return teacherOrders;
						}
						return null;
					}

				});
		return listTeacherOrders;
	}

	/**
	 *  统计某审核员可以审核记录数目
	 * @param sbTypeId
	 * @return
	 */
	public int countAllTeacherOrders(int sbTypeId) {
		List<Object> listParam = new ArrayList<Object>();
		String sql  = "select count(*) from teacher_orders where sbTypeId=? ";
		listParam.add(sbTypeId);
		int rows = dbop.count(0, sql, listParam);
		return rows;
	}

	/**
	 * 查找某审核员可以审核记录数目
	 * 
	 * @param userId
	 * @param offset
	 * @param limit
	 * @return
	 * @throws DbException
	 */
	public List<TeacherOrders> findAllTeacherOrdersList(int sbTypeId,int offset, int limit)
			throws DbException {
		List<Object> listParam = new ArrayList<Object>();
		String sql = "select t.*,u.name from teacher_orders t,user u where t.sbTypeId=? and  t.userId= u.code   order by t.cDate desc limit ?,? ";
		listParam.add(sbTypeId);
		listParam.add(offset);
		listParam.add(limit);

		List<TeacherOrders> listTeacherOrders = null;
		listTeacherOrders = dbop.findListParam(0l, sql, listParam,
				new ResultObjectCall<TeacherOrders>() {
					@Override
					public TeacherOrders getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							TeacherOrders teacherOrders = new TeacherOrders();
							teacherOrders.setId(rs.getString(1));
							teacherOrders.setUserId(rs.getString(2));
							teacherOrders.setStudentIds(rs.getString(3));
							teacherOrders.setsDate(rs.getTimestamp(4));
							teacherOrders.seteDate(rs.getTimestamp(5));
							teacherOrders.setState(rs.getInt(6));
							teacherOrders.setcDate(rs.getTimestamp(7));
							teacherOrders.setCheckDate(rs.getTimestamp(8));
							teacherOrders.setCheckUserId(rs.getString(9));
							teacherOrders.setJieshuDate(rs.getTimestamp(10));
							teacherOrders.setJieshuUserId(rs.getString(11));
							teacherOrders.setLeaveWord(rs.getString(12));
							teacherOrders.setRemark(rs.getString(13));
							teacherOrders.setSubject(rs.getString(14));
							teacherOrders.setSbTypeId(rs.getInt(15));
              teacherOrders.setClaName(rs.getString(16));
              teacherOrders.setStuNum(rs.getInt(17));
              teacherOrders.setExpType(rs.getString(18));
              teacherOrders.setWorkTime(rs.getInt(19));
              teacherOrders.setName(rs.getString(20));
							return teacherOrders;
						}
						return null;
					}

				});
		return listTeacherOrders;
	}
	
	
	/**
	 * 查找学生可借用订单列表
	 * @param userId
	 * @return
	 * @throws DbException
	 */
	public List<TeacherOrders> findStudentOrdersList(String studentId) throws DbException {
		List<Object> listParam = new ArrayList<Object>();
		String sql = "select t.*,u.name from teacher_orders t,user u where t.state=1 "
		    + " and find_in_set(?, t.studentIds) and t.userId= u.code "
		    // 目前一次仅能约一天的，所以已提交的用也不能受到限制。否则就只能约一天的了
		    // + " and t.id not in (select s.orderId from student_orders s where s.userId = ?) "
		    + " order by t.cDate desc ";
		listParam.add(studentId);
		// listParam.add(studentId);
		List<TeacherOrders> listTeacherOrders = null;
		listTeacherOrders = dbop.findListParam(0l, sql, listParam,
				new ResultObjectCall<TeacherOrders>() {
					@Override
					public TeacherOrders getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							TeacherOrders teacherOrders = new TeacherOrders();
							teacherOrders.setId(rs.getString(1));
							teacherOrders.setUserId(rs.getString(2));
							teacherOrders.setStudentIds(rs.getString(3));
							teacherOrders.setsDate(rs.getTimestamp(4));
							teacherOrders.seteDate(rs.getTimestamp(5));
							teacherOrders.setState(rs.getInt(6));
							teacherOrders.setcDate(rs.getTimestamp(7));
							teacherOrders.setCheckDate(rs.getTimestamp(8));
							teacherOrders.setCheckUserId(rs.getString(9));
							teacherOrders.setJieshuDate(rs.getTimestamp(10));
							teacherOrders.setJieshuUserId(rs.getString(11));
							teacherOrders.setLeaveWord(rs.getString(12));
							teacherOrders.setRemark(rs.getString(13));
							teacherOrders.setSubject(rs.getString(14));
							teacherOrders.setSbTypeId(rs.getInt(15));
              teacherOrders.setClaName(rs.getString(16));
              teacherOrders.setStuNum(rs.getInt(17));
              teacherOrders.setExpType(rs.getString(18));
              teacherOrders.setWorkTime(rs.getInt(19));
              teacherOrders.setName(rs.getString(20));
							return teacherOrders;
						}
						return null;
					}

				});
		return listTeacherOrders;
	}
}
