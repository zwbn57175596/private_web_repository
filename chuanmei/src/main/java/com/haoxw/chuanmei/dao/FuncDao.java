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
import com.haoxw.chuanmei.model.Func;
/**
 * 功能dao
 * @author xuewuhao
 *
 */
@Repository
public class FuncDao {
	private static final DbOp<Func> dbop = new DbOp<Func>();
	private static boolean result = false;

	/**
	 * 添加或者修改功能
	 * 
	 * @param func
	 * @return
	 */
	public boolean saveOrUpdateFunc(Func func) throws DbException {
		String addsql = " insert into func(createTime,funcDesc,funcName,parentId,url) values(?,?,?,?,?) ";
		String updatesql = "update func set createTime=?,funcDesc=?,funcName=?,parentId=?,url=? where funcId=?";
		int rows = -1;
		List<Object> listParam = new ArrayList<Object>();
		if (func.getFuncId() > 0) {
			listParam.add(func.getCreateTime());
			listParam.add(func.getFuncDesc() == null ? null : func
					.getFuncDesc());
			listParam.add(func.getFuncName());
			listParam.add(func.getParentId());
			listParam.add(func.getUrl());
			listParam.add(func.getFuncId());
			rows = dbop.update(0, updatesql, listParam);
		} else {
			listParam.add(func.getCreateTime());
			listParam.add(func.getFuncDesc() == null ? null : func
					.getFuncDesc());
			listParam.add(func.getFuncName());
			listParam.add(func.getParentId());
			listParam.add(func.getUrl());
			rows = dbop.update(0, addsql, listParam);
		}
		if (rows > 0)
			result = true;
		return result;
	}

	/**
	 * 查找单条功能
	 * 
	 * @param funcId
	 * @return
	 * @throws DbException
	 */
	public Func getFuncById(int funcId) throws DbException {
		String sql = "select * from func where funcId=?";
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(funcId);
		List<Func> listFunc = null;
		listFunc = dbop.findListParam(0l, sql, listParam,
				new ResultObjectCall<Func>() {
					@Override
					public Func getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							Func func = new Func();
							func.setCreateTime(rs.getTimestamp(2));
							func.setFuncDesc(rs.getString(3));
							func.setFuncName(rs.getString(4));
							func.setParentId(rs.getInt(5));
							func.setUrl(rs.getString(6));
							func.setFuncId(rs.getInt(1));
							return func;
						}
						return null;
					}

				});
		if (listFunc != null && listFunc.size() > 0) {
			return listFunc.get(0);
		}
		return null;
	}

	/**
	 * 查找功能列表
	 * 
	 * @return
	 * @throws DbException
	 */
	public List<Func> allFunc() throws DbException {
		String sql = "select * from func ";
		List<Func> listFunc = null;
		listFunc = dbop.findList(0l, sql, new ResultObjectCall<Func>() {
			@Override
			public Func getResultObject(ResultSet rs) throws SQLException,
					DbException {
				// TODO Auto-generated method stub
				if (rs != null) {
					Func func = new Func();
					func.setCreateTime(rs.getTimestamp(2));
					func.setFuncDesc(rs.getString(3));
					func.setFuncName(rs.getString(4));
					func.setParentId(rs.getInt(5));
					func.setUrl(rs.getString(6));
					func.setFuncId(rs.getInt(1));
					return func;
				}
				return null;
			}

		});
		return listFunc;
	}

	/**
	 * 删除功能
	 * 
	 * @param func
	 * @return
	 */
	public boolean delFunc(int funcId) throws DbException {
		String delsql = " delete from  func where funcId = " + funcId;
		String delrolefuncsql = " delete from  role_func where funcId = " + funcId;
		dbop.update(0, delrolefuncsql);
		dbop.update(0, delsql);
		result = true;
		return result;
	}
	
	public static void main(String args[]) throws DbException{
		FuncDao funcDao = new FuncDao();
		Func f = new Func();
		f.setCreateTime(new Date());
		f.setFuncDesc("重置密码功能");
		f.setFuncName("重置密码");
		f.setUrl("/user/resetpwd");
		funcDao.saveOrUpdateFunc(f);
	}
}
