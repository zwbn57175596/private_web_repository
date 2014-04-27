package com.haoxw.chuanmei.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.haoxw.chuanmei.bean.DbException;
import com.haoxw.chuanmei.dao.base.DbOp;
import com.haoxw.chuanmei.dao.base.ResultObjectCall;
import com.haoxw.chuanmei.model.TeacherOrdersItem;

/**
 * 老师订单项dao
 * 
 * @author xuewuhao
 * 
 */
@Repository
public class TeacherOrdersItemDao {
	private static final DbOp<TeacherOrdersItem> dbop = new DbOp<TeacherOrdersItem>();
	private static boolean result = false;

	/**
	 * 添加或者修改
	 * 
	 * @param teacherOrdersItem
	 * @return
	 */
	public boolean saveOrUpdateTeacherOrdersItem(
			TeacherOrdersItem teacherOrdersItem) throws DbException {
		String addsql = " insert into teacher_order_item(orderId,shebeiId,cDate) values(?,?,now()) ";
		String updatesql = "update teacher_order_item set orderId=?,shebeiId=?,cDate=now() where id=?";
		int rows = -1;
		List<Object> listParam = new ArrayList<Object>();
		if (teacherOrdersItem.getId() > 0) {
			listParam.add(teacherOrdersItem.getOrderId());
			listParam.add(teacherOrdersItem.getShebeiId());
			listParam.add(teacherOrdersItem.getId());
			rows = dbop.update(0, updatesql, listParam);
		} else {
			listParam.add(teacherOrdersItem.getOrderId());
			listParam.add(teacherOrdersItem.getShebeiId());
			rows = dbop.update(0, addsql, listParam);
		}
		if (rows > 0)
			result = true;
		return result;
	}

	/**
	 * 查找单条
	 * 
	 * @param id
	 * @return
	 * @throws DbException
	 */
	public TeacherOrdersItem getTeacherOrdersItemById(String id)
			throws DbException {
		String sql = "select * from teacher_order_item where id=?";
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(id);
		List<TeacherOrdersItem> listTeacherOrdersItem = null;
		listTeacherOrdersItem = dbop.findListParam(0l, sql, listParam,
				new ResultObjectCall<TeacherOrdersItem>() {
					@Override
					public TeacherOrdersItem getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							TeacherOrdersItem teacherOrdersItem = new TeacherOrdersItem();
							teacherOrdersItem.setId(rs.getInt(1));
							teacherOrdersItem.setOrderId(rs.getString(2));
							teacherOrdersItem.setShebeiId(rs.getInt(3));
							teacherOrdersItem.setcDate(rs.getTimestamp(4));
							teacherOrdersItem.setName(rs.getString(5));
							teacherOrdersItem.setCode(rs.getString(6));
							return teacherOrdersItem;
						}
						return null;
					}

				});
		if (listTeacherOrdersItem != null && listTeacherOrdersItem.size() > 0) {
			return listTeacherOrdersItem.get(0);
		}
		return null;
	}

	/**
	 * 查找列表
	 * 
	 * @return
	 * @throws DbException
	 */
	public List<TeacherOrdersItem> allTeacherOrdersItemByOrderId(String orderId)
			throws DbException {
		String sql = "select t.*,s.name,s.code from teacher_order_item t,shebei s  where t.orderId= ? and t.shebeiId = s.id";
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(orderId);
		List<TeacherOrdersItem> listTeacherOrdersItem = dbop.findListParam(0l,
				sql, listParam, new ResultObjectCall<TeacherOrdersItem>() {
					@Override
					public TeacherOrdersItem getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							TeacherOrdersItem teacherOrdersItem = new TeacherOrdersItem();
							teacherOrdersItem.setId(rs.getInt(1));
							teacherOrdersItem.setOrderId(rs.getString(2));
							teacherOrdersItem.setShebeiId(rs.getInt(3));
							teacherOrdersItem.setcDate(rs.getTimestamp(4));
							teacherOrdersItem.setName(rs.getString(5));
							teacherOrdersItem.setCode(rs.getString(6));
							return teacherOrdersItem;
						}
						return null;
					}

				});
		return listTeacherOrdersItem;
	}

	/**
	 * 删除订单关联订单项
	 * 
	 * @param TeacherOrdersItem
	 * @return
	 */
	public boolean delTeacherOrdersItemByOrderId(String orderId) throws DbException {
		String delsql = " delete from  teacher_order_item where orderId = '"+orderId+"'";
		dbop.update(0, delsql);
		result = true;
		return result;
	}
}
