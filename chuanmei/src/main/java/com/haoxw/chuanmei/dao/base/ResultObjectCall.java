package com.haoxw.chuanmei.dao.base;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.haoxw.chuanmei.bean.DbException;

/**
 * 处理返回数据
 * 
 * @author xuewuhao
 * 
 * @param <T>
 */
public interface ResultObjectCall<T extends Object> {
	public abstract T getResultObject(ResultSet rs) throws SQLException,
			DbException;
}