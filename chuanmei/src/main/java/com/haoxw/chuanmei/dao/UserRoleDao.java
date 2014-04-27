package com.haoxw.chuanmei.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.haoxw.chuanmei.bean.DbException;
import com.haoxw.chuanmei.dao.base.DbOp;
import com.haoxw.chuanmei.dao.base.ResultObjectCall;
import com.haoxw.chuanmei.model.Role;

/**
 * 用户角色dao
 * @author xuewuhao
 *
 */
@Repository
public class UserRoleDao {

	private static boolean result = false;
	private static final DbOp dbop = new DbOp();

	/**
	 * 查找用户角色
	 * 
	 * @param userId
	 * @return
	 * @throws DbException
	 */
	public Role findUserRoleByUserId(String userId) throws DbException {
		String sql = "select r.* from role r,user_role ur where   r.roleId = ur.roleId and  ur.userId = '"
				+ userId+"'";
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(userId);
		List<Role> listRole = null;
		listRole = dbop.findList(0l, sql, new ResultObjectCall<Role>() {
			@Override
			public Role getResultObject(ResultSet rs) throws SQLException,
					DbException {
				// TODO Auto-generated method stub
				if (rs != null) {
					Role role = new Role();
					role.setRoleId(rs.getInt(1));
					role.setCreateTime(rs.getTimestamp(2));
					role.setRoleDesc(rs.getString(3));
					role.setRoleName(rs.getString(4));
					return role;
				}
				return null;
			}

		});
		if (listRole != null && listRole.size() > 0) {
			return listRole.get(0);
		}
		return null;
	}

	/**
	 * set用户角色 先删除用户关联角色记录 再增加用户关联角色记录
	 * 
	 * @param userId
	 * @return
	 */
	public boolean setUserRoleByUser(int roleId, String userId) {
		String delsql = " delete from  user_role where userId = '" + userId+"'";
		int rows = dbop.update(0, delsql);
		if (rows > -1) {
			String insql = " insert into user_role (roleId,userId) values ("
					+ roleId + ",'" + userId + "')";
			int inrows = dbop.update(0, insql);
			if (inrows > 0) {
				result = true;
			}
		}
		return result;
	}
}
