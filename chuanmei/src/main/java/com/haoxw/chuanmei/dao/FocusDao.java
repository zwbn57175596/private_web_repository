package com.haoxw.chuanmei.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.haoxw.chuanmei.bean.DbException;
import com.haoxw.chuanmei.dao.base.DbOp;
import com.haoxw.chuanmei.dao.base.ResultObjectCall;
import com.haoxw.chuanmei.model.Focus;

/**
 * 首页焦点图 dao
 * 
 * @author xuewuhao
 * 
 */
@Repository
public class FocusDao {
	private static final DbOp<Focus> dbop = new DbOp<Focus>();
	private boolean flag = false;

	/**
	 * 添加或者修改焦点图
	 * 
	 * @param focus
	 * @return
	 */
	public boolean updateFocus(Focus focus) throws DbException {
		int result = -1;
		String sql = "";
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(focus.getName());
		listParam.add(focus.getImgUrl());
		listParam.add(focus.getLinkUrl());
		listParam.add(focus.getOrderNum());
		if (focus.getId() == 0) {
			sql = "insert into focus set name=?,img_url=?,link_url=?,create_time=now(),order_num=?";
		} else {
			listParam.add(focus.getId());
			sql = "update focus  set name=?,img_url=?,link_url=?,create_time=now(),order_num=? where id = ?";
		}
		result = dbop.update(1, sql, listParam);
		if (result > 0)
			flag = true;
		return flag;
	}

	/**
	 * 查找焦点图
	 * 
	 * @param id
	 * @return
	 */
	public Focus findFocus(int id) throws DbException {
		List<Focus> listFocus = null;
		String sql = "select * from focus where id = " + id;
		listFocus = dbop.findList(1, sql, new ResultObjectCall<Focus>() {
			@Override
			public Focus getResultObject(ResultSet rs) throws SQLException {
				Focus focusTemp = new Focus();
				focusTemp.setId(rs.getInt(1));
				focusTemp.setName(rs.getString(2));
				focusTemp.setImgUrl(rs.getString(3));
				focusTemp.setLinkUrl(rs.getString(4));
				focusTemp.setCreateTime(rs.getTimestamp(5));
				focusTemp.setOrderNum(rs.getInt(6));
				return focusTemp;
			}

		});
		return listFocus != null && listFocus.size() > 0 ? listFocus.get(0)
				: null;
	}

	/**
	 * 焦点图列表
	 * 
	 * @return
	 */
	public List<Focus> allFocusList() throws DbException {
		List<Focus> focusList = new ArrayList<Focus>();
		focusList = dbop.findList(0,
				"select * from focus order by order_num desc",
				new ResultObjectCall<Focus>() {
					@Override
					public Focus getResultObject(ResultSet rs)
							throws SQLException {
						Focus focusTemp = new Focus();
						focusTemp.setId(rs.getInt(1));
						focusTemp.setName(rs.getString(2));
						focusTemp.setImgUrl(rs.getString(3));
						focusTemp.setLinkUrl(rs.getString(4));
						focusTemp.setCreateTime(rs.getTimestamp(5));
						focusTemp.setOrderNum(rs.getInt(6));
						return focusTemp;
					}
				});

		return focusList;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean delFocus(int id) throws DbException {
		String delsql = " delete from  focus where id = " + id;
		dbop.update(0, delsql);
		flag = true;
		return flag;
	}
}
