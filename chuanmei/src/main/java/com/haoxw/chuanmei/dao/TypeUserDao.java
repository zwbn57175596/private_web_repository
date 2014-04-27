package com.haoxw.chuanmei.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.haoxw.chuanmei.bean.DbException;
import com.haoxw.chuanmei.dao.base.DbOp;
import com.haoxw.chuanmei.dao.base.ResultObjectCall;

/**
 * 设备分类和审核员dao
 * 
 * @author xuewuhao
 * 
 */
@Repository
public class TypeUserDao {

	private static boolean result = false;
	private static final DbOp dbop = new DbOp();

	/**
	 * 根据用户id查找设备分类id
	 * 
	 * @param userId
	 * @return  -1代表异常
	 * @throws DbException
	 */
	public int findSbTypeIdByUserId(String userId) throws DbException {
		String sql = "select sbTypeId from type_user  where   userId =?";
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(userId);
		List<Integer> listInteger = dbop.findListParam(0l, sql,listParam,
				new ResultObjectCall<Integer>() {
					@Override
					public Integer getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							return rs.getInt(1);
						}
						return -1;
					}

				});
		if (listInteger != null && listInteger.size() > 0) {
			return listInteger.get(0);
		}
		return -1;
	}

	/**
	 * 根据设备分类id查找用户id
	 * 
	 * @param id
	 * @return
	 * @throws DbException
	 */
	public String findUserIdBySbTypeId(int id) throws DbException {
		String sql = "select userId from type_user  where   sbTypeId =?";
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(id);
		List<String> listString = dbop.findListParam(0l, sql,listParam,
				new ResultObjectCall<String>() {
					@Override
					public String getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							return rs.getString(1);
						}
						return null;
					}

				});
		if (listString != null && listString.size() > 0) {
			return listString.get(0);
		}
		return null;
	}

	/**
	 * 设置设备分类对应审核人员
	 * 
	 * @param userId
	 * @return
	 */
	public boolean setSbTypeUserId(int sbTypeId, String userId) {
		List<Object> listParam = new ArrayList<Object>();
		String delsql = " delete from  type_user where sbTypeId = ?";
		listParam.add(sbTypeId);
		int rows = dbop.update(0, delsql,listParam);
		if (rows > -1) {
			String insql = " insert into type_user (sbTypeId,userId) values (?,?)";
			listParam.add(userId);
			int inrows = dbop.update(0, insql,listParam);
			if (inrows > 0) {
				result = true;
			}
		}
		return result;
	}
}
