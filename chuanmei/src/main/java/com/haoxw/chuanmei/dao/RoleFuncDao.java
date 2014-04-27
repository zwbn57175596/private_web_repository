package com.haoxw.chuanmei.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.haoxw.chuanmei.bean.DbException;
import com.haoxw.chuanmei.dao.base.DbOp;
import com.haoxw.chuanmei.dao.base.ResultObjectCall;
import com.haoxw.chuanmei.model.Func;
/**
 * 角色功能dao
 * @author xuewuhao
 *
 */
@Repository
public class RoleFuncDao {

	private static boolean result = false;
	private static final DbOp dbop = new DbOp();

	/**
	 * 查找角色功能列表
	 * 
	 * @param roleId
	 * @return
	 * @throws DbException
	 */
	public List<Func> fingRoleFuncsByRoleId(int roleId) throws DbException {
		String sql = "select f.* from func f,role_func rf where rf.funcId=f.funcId and    rf.roleId = "
				+ roleId;
		List<Func> listFunc = dbop.findList(0l, sql,
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
		return listFunc;
	}

	/**
	 * set角色功能列表 先删掉角色功能列表 再insert功能列表
	 * 
	 * @param roleId
	 * @param list
	 *            funcId的list
	 * @return
	 */
	public boolean setRoleFuncs(int roleId, List<Integer> list) {

		String delsql = " delete from  role_func where roleId = " + roleId;
		int rows = dbop.update(0, delsql);
		if (rows > -1) {
			for (int i = 0; i < list.size(); i++) {
				String insql = " insert into role_func (roleId,funcId) values ("
						+ roleId + "," + list.get(i) + ")";
				dbop.update(0, insql);
			}
			result = true;
		}
		return result;

	}
}
