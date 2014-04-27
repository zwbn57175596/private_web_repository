package com.haoxw.chuanmei.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.haoxw.chuanmei.bean.DbException;
import com.haoxw.chuanmei.dao.base.DbOp;
import com.haoxw.chuanmei.dao.base.ResultObjectCall;
import com.haoxw.chuanmei.model.Shebei;

/**
 * 设备 dao
 * 
 * @author xuewuhao
 * 
 */
@Repository
public class ShebeiDao {
	private static final DbOp<Shebei> dbop = new DbOp<Shebei>();
	private boolean flag = false;

	/**
	 * 添加或者修改
	 * 
	 * @param shebei
	 * @return
	 */
	public boolean updateShebei(Shebei shebei) throws DbException {
		int result = -1;
		String sql = "";
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(shebei.getName());
		listParam.add(shebei.getImgUrl());
		listParam.add(shebei.getNum());
		listParam.add(shebei.getNowNum());
		listParam.add(shebei.getRemark());
		listParam.add(shebei.getCode());
		listParam.add(shebei.getType());
		if (shebei.getId() == 0) {
			sql = "insert into shebei set name=?,imgUrl=?,cDate=now(),num=?,nowNum=?,remark=?,code=?,type=?,state=0";
		} else {
			listParam.add(shebei.getState());
			listParam.add(shebei.getId());
			sql = "update shebei  set name=?,imgUrl=?,cDate=now(),num=?,nowNum=?,remark=?,code=?,type=?,state=? where id = ?";
		}
		result = dbop.update(1, sql, listParam);
		if (result > 0)
			flag = true;
		return flag;
	}

	/**
	 * 查找
	 * 
	 * @param id
	 * @return
	 */
	public Shebei findshebei(int id) throws DbException {
		List<Shebei> listShebei = null;
		String sql = "select * from shebei where id = " + id;
		listShebei = dbop.findList(1, sql, new ResultObjectCall<Shebei>() {
			@Override
			public Shebei getResultObject(ResultSet rs) throws SQLException {
				if (rs != null) {
					Shebei shebei = new Shebei();
					shebei.setId(rs.getInt(1));
					shebei.setName(rs.getString(2));
					shebei.setImgUrl(rs.getString(3));
					shebei.setNum(rs.getInt(4));
					shebei.setcDate(rs.getTimestamp(5));
					shebei.setNowNum(rs.getInt(6));
					shebei.setRemark(rs.getString(7));
					shebei.setCode(rs.getString(8));
					shebei.setType(rs.getInt(9));
					shebei.setState(rs.getInt(10));
					return shebei;
				}
				return null;
			}

		});
		return listShebei != null && listShebei.size() > 0 ? listShebei.get(0)
				: null;
	}

	/**
	 * 所有可用设备列表 
	 * 
	 * @return
	 */
	public List<Shebei> allShebeiList() throws DbException {
		List<Shebei> shebeiList = new ArrayList<Shebei>();
		shebeiList = dbop.findList(0,
				"select * from shebei where state=0 order by cDate desc",
				new ResultObjectCall<Shebei>() {
					@Override
					public Shebei getResultObject(ResultSet rs)
							throws SQLException {
						if (rs != null) {
							Shebei shebei = new Shebei();
							shebei.setId(rs.getInt(1));
							shebei.setName(rs.getString(2));
							shebei.setImgUrl(rs.getString(3));
							shebei.setNum(rs.getInt(4));
							shebei.setcDate(rs.getTimestamp(5));
							shebei.setNowNum(rs.getInt(6));
							shebei.setRemark(rs.getString(7));
							shebei.setCode(rs.getString(8));
							shebei.setType(rs.getInt(9));
							shebei.setState(rs.getInt(10));
							return shebei;
						}
						return null;
					}
				});

		return shebeiList;
	}
	/**
	 * 所有可用某类设备列表 
	 * 
	 * @return
	 */
	public List<Shebei> allShebeiListByType(int type) throws DbException {
		List<Shebei> shebeiList = new ArrayList<Shebei>();
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(type);
		shebeiList = dbop.findListParam(0,
				"select * from shebei where state=0 and type=? order by cDate desc",listParam,
				new ResultObjectCall<Shebei>() {
					@Override
					public Shebei getResultObject(ResultSet rs)
							throws SQLException {
						if (rs != null) {
							Shebei shebei = new Shebei();
							shebei.setId(rs.getInt(1));
							shebei.setName(rs.getString(2));
							shebei.setImgUrl(rs.getString(3));
							shebei.setNum(rs.getInt(4));
							shebei.setcDate(rs.getTimestamp(5));
							shebei.setNowNum(rs.getInt(6));
							shebei.setRemark(rs.getString(7));
							shebei.setCode(rs.getString(8));
							shebei.setType(rs.getInt(9));
							shebei.setState(rs.getInt(10));
							return shebei;
						}
						return null;
					}
				});

		return shebeiList;
	}
	/**
	 * 前台某类设备列表 
	 * 
	 * @return
	 */
	public List<Shebei> allShebeiListByTypeFront(int type) throws DbException {
		List<Shebei> shebeiList = new ArrayList<Shebei>();
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(type);
		shebeiList = dbop.findListParam(0,
				"select * from shebei where type=? order by cDate desc",listParam,
				new ResultObjectCall<Shebei>() {
					@Override
					public Shebei getResultObject(ResultSet rs)
							throws SQLException {
						if (rs != null) {
							Shebei shebei = new Shebei();
							shebei.setId(rs.getInt(1));
							shebei.setName(rs.getString(2));
							shebei.setImgUrl(rs.getString(3));
							shebei.setNum(rs.getInt(4));
							shebei.setcDate(rs.getTimestamp(5));
							shebei.setNowNum(rs.getInt(6));
							shebei.setRemark(rs.getString(7));
							shebei.setCode(rs.getString(8));
							shebei.setType(rs.getInt(9));
							shebei.setState(rs.getInt(10));
							return shebei;
						}
						return null;
					}
				});

		return shebeiList;
	}
}
