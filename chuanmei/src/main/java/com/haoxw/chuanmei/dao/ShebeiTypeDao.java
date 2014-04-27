package com.haoxw.chuanmei.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.haoxw.chuanmei.bean.DbException;
import com.haoxw.chuanmei.dao.base.DbOp;
import com.haoxw.chuanmei.dao.base.ResultObjectCall;
import com.haoxw.chuanmei.model.ShebeiType;

/**
 * 设备类型dao
 * 
 * @author xuewuhao
 * 
 */
@Repository
public class ShebeiTypeDao {
	private static final DbOp<ShebeiType> dbop = new DbOp<ShebeiType>();
	private static boolean result = false;

	/**
	 * 添加或者修改
	 * 
	 * @param shebeiType
	 * @return
	 */
	public boolean saveOrUpdateShebeiType(ShebeiType shebeiType)
			throws DbException {
		String addsql = " insert into shebei_type(name,cDate,state) values(?,now(),?) ";
		String updatesql = "update shebei_type set cDate=now(),name=?,state=? where id=?";
		int rows = -1;
		List<Object> listParam = new ArrayList<Object>();
		if (shebeiType.getId() > 0) {
			listParam.add(shebeiType.getName());
			listParam.add(shebeiType.getState());
			listParam.add(shebeiType.getId());
			rows = dbop.update(0, updatesql, listParam);
		} else {
			listParam.add(shebeiType.getName());
			listParam.add(shebeiType.getState());
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
	public ShebeiType getShebeiTypeById(int id) throws DbException {
		String sql = "select * from shebei_type where id=?";
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(id);
		List<ShebeiType> listShebeiType = dbop.findListParam(0l, sql,
				listParam, new ResultObjectCall<ShebeiType>() {
					@Override
					public ShebeiType getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							ShebeiType st = new ShebeiType();
							st.setId(rs.getInt(1));
							st.setName(rs.getString(2));
							st.setcDate(rs.getTimestamp(3));
							st.setState(rs.getInt(4));
							return st;
						}
						return null;
					}

				});
		if (listShebeiType != null && listShebeiType.size() > 0) {
			return listShebeiType.get(0);
		}
		return null;
	}

	/**
	 * 查找列表
	 * 
	 * @return
	 * @throws DbException
	 */
	public List<ShebeiType> allShebeiType() throws DbException {
		String sql = "select * from shebei_type where state = 0 ";
		List<ShebeiType> listShebeiType = dbop.findList(0l, sql,
				new ResultObjectCall<ShebeiType>() {
					@Override
					public ShebeiType getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							ShebeiType st = new ShebeiType();
							st.setId(rs.getInt(1));
							st.setName(rs.getString(2));
							st.setcDate(rs.getTimestamp(3));
							st.setState(rs.getInt(4));
							return st;
						}
						return null;
					}

				});
		return listShebeiType;
	}

}
