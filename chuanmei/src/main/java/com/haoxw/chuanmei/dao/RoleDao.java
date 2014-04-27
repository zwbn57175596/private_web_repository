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
import com.haoxw.chuanmei.model.Role;
/**
 * 角色dao
 * @author xuewuhao
 *
 */
@Repository
public class RoleDao {
	private static final DbOp<Role> dbop = new DbOp<Role>();
	private static boolean result = false;

	/**
	 * 添加或者修改角色
	 * 
	 * @param user
	 * @return
	 */
	public boolean saveOrUpdateRole(Role role) throws DbException {
		String addsql = " insert into role(createTime,roleDesc,roleName) values(?,?,?) ";
		String updatesql = "update role set createTime=?,roleDesc=?,roleName=? where roleId=?";
		List<Object> listParam = new ArrayList<Object>();
		int rows = -1;
		if (role.getRoleId() > 0) {
			listParam.add(role.getCreateTime());
			listParam.add(role.getRoleDesc());
			listParam.add(role.getRoleName());
			listParam.add(role.getRoleId());
			rows = dbop.update(0, updatesql, listParam);
		} else {
			listParam.add(role.getCreateTime());
			listParam.add(role.getRoleDesc());
			listParam.add(role.getRoleName());
			
			rows = dbop.update(0, addsql, listParam);
		}
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
	public Role getRoleById(int id) throws DbException {
		String sql = "select * from role where roleId=?";
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(id);
		List<Role> listRole = null;
		listRole = dbop.findListParam(0l, sql, listParam,
				new ResultObjectCall<Role>() {
					@Override
					public Role getResultObject(ResultSet rs)
							throws SQLException, DbException {
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
	 * 查找角色列表
	 * 
	 * @return
	 * @throws DbException
	 */
	public List<Role> allRole() throws DbException {
		String sql = "select * from role ";
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
		return listRole;
	}
	
	public static void main(String args[]) throws DbException{
		RoleDao  rd = new RoleDao();
		Role r = new Role();
		r.setCreateTime(new Date());
		r.setRoleName("审核员");
		r.setRoleDesc("审核员");
		rd.saveOrUpdateRole(r);
	}

}
