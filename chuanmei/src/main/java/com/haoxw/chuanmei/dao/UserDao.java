package com.haoxw.chuanmei.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.haoxw.chuanmei.bean.DbException;
import com.haoxw.chuanmei.dao.base.DbOp;
import com.haoxw.chuanmei.dao.base.ResultObjectCall;
import com.haoxw.chuanmei.model.User;

/**
 * 用户管理dao
 * 
 * @author xuewuhao
 * 
 */
@Repository
public class UserDao {

	private static final DbOp<User> dbop = new DbOp<User>();
	private static boolean result = false;

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean saveUser(User user) throws DbException {
		String addsql = " insert into user(createTime,createUserId,name,pwd,status,gender,code,uclass) values(now(),?,?,?,?,?,?,?) ";
		List<Object> listParam = new ArrayList<Object>();
		int rows = -1;
		listParam.add(user.getCreateUserId());
		listParam.add(user.getName());
		listParam.add(user.getPwd());
		listParam.add(user.getStatus());
		listParam.add(user.getGender());
		listParam.add(user.getCode());
		listParam.add(user.getUclass());
		rows = dbop.update(0, addsql, listParam);
		if (rows > 0)
			result = true;
		return result;
	}

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user) throws DbException {
		String updatesql = "update  user set createTime=now(),createUserId=?,name=?,pwd=?,status=?,gender=?,uclass=? where code=?";
		List<Object> listParam = new ArrayList<Object>();
		int rows = -1;
		listParam.add(user.getCreateUserId());
		listParam.add(user.getName());
		listParam.add(user.getPwd());
		listParam.add(user.getStatus());
		listParam.add(user.getGender());
		listParam.add(user.getUclass());
		listParam.add(user.getCode());
		rows = dbop.update(0, updatesql, listParam);
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
	public User getUserByCode(String code) throws DbException {
		String sql = "select * from user where status=0 and  code=?";
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(code);
		List<User> listUser = null;
		listUser = dbop.findListParam(0l, sql, listParam,
				new ResultObjectCall<User>() {
					@Override
					public User getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							User user = new User();
							user.setCreateTime(rs.getTimestamp(1));
							user.setCreateUserId(rs.getInt(2));
							user.setName(rs.getString(3));
							user.setPwd(rs.getString(4));
							user.setStatus(rs.getInt(5));
							user.setGender(rs.getInt(6));
							user.setCode(rs.getString(7));
							user.setUclass(rs.getString(8));
							return user;
						}
						return null;
					}

				});
		if (listUser != null && listUser.size() > 0) {
			return listUser.get(0);
		}
		return null;
	}

	/**
	 * 登录验证
	 * 
	 * @param name
	 * @param pwd
	 * @return
	 * @throws DbException
	 */
	public User checkCodeLogin(String code, String pwd) throws DbException {
		String sql = "select * from user where status=0 and code=? and pwd = ?";
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(code);
		listParam.add(pwd);
		List<User> listUser = null;
		listUser = dbop.findListParam(0l, sql, listParam,
				new ResultObjectCall<User>() {
					@Override
					public User getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							User user = new User();
							user.setCreateTime(rs.getTimestamp(1));
							user.setCreateUserId(rs.getInt(2));
							user.setName(rs.getString(3));
							user.setPwd(rs.getString(4));
							user.setStatus(rs.getInt(5));
							user.setGender(rs.getInt(6));
							user.setCode(rs.getString(7));
							user.setUclass(rs.getString(8));
							return user;
						}
						return null;
					}

				});
		if (listUser != null && listUser.size() > 0) {
			return listUser.get(0);
		}
		return null;
	}

	/**
	 * 查找某种角色用户列表
	 * 
	 * @return
	 * @throws DbException
	 */
	public List<User> allUserByRole(int roleId) throws DbException {
		String sql = "select u.* from user u,user_role ur where u.status=0 and ur.roleId=? and u.code = ur.userId   ";
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(roleId);
		List<User> listUser = dbop.findListParam(0l, sql, listParam,
				new ResultObjectCall<User>() {
					@Override
					public User getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							User user = new User();
							user.setCreateTime(rs.getTimestamp(1));
							user.setCreateUserId(rs.getInt(2));
							user.setName(rs.getString(3));
							user.setPwd(rs.getString(4));
							user.setStatus(rs.getInt(5));
							user.setGender(rs.getInt(6));
							user.setCode(rs.getString(7));
							user.setUclass(rs.getString(8));
							return user;
						}
						return null;
					}

				});
		return listUser;
	}


	/**
	 * 查找用户列表
	 * 
	 * @return
	 * @throws DbException
	 */
	public List<User> allUser() throws DbException {
		String sql = "select * from user where status=0  ";
		List<User> listUser = null;
		listUser = dbop.findList(0l, sql, new ResultObjectCall<User>() {
			@Override
			public User getResultObject(ResultSet rs) throws SQLException,
					DbException {
				// TODO Auto-generated method stub
				if (rs != null) {
					User user = new User();
					user.setCreateTime(rs.getTimestamp(1));
					user.setCreateUserId(rs.getInt(2));
					user.setName(rs.getString(3));
					user.setPwd(rs.getString(4));
					user.setStatus(rs.getInt(5));
					user.setGender(rs.getInt(6));
					user.setCode(rs.getString(7));
					user.setUclass(rs.getString(8));
					return user;
				}
				return null;
			}

		});
		return listUser;
	}

	/**
	 * code唯一验证
	 * 
	 * @param code
	 * @return
	 * @throws DbException
	 */
	public User checkUniqueCode(String code) throws DbException {
		String sql = "select * from user where code=? ";
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(code);
		List<User> listUser = null;
		listUser = dbop.findListParam(0l, sql, listParam,
				new ResultObjectCall<User>() {
					@Override
					public User getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							User user = new User();
							user.setCreateTime(rs.getTimestamp(1));
							user.setCreateUserId(rs.getInt(2));
							user.setName(rs.getString(3));
							user.setPwd(rs.getString(4));
							user.setStatus(rs.getInt(5));
							user.setGender(rs.getInt(6));
							user.setCode(rs.getString(7));
							user.setUclass(rs.getString(8));
							return user;
						}
						return null;
					}

				});
		if (listUser != null && listUser.size() > 0) {
			return listUser.get(0);
		}
		return null;
	}

	/**
	 * 统计新闻条目
	 * 
	 * @param type
	 *            -1代表统计全部
	 * @return
	 */
	public int countUser(String code, String name, String roleId, String uclass) {
		String sql = null;
		List<Object> listParam = new ArrayList<Object>();

		if (StringUtils.isEmpty(roleId)) {
			sql = "select count(*) from user where  status=0  ";
			if (!StringUtils.isEmpty(code)) {
				sql = sql + " and code = ?";
				listParam.add(code);
			}
			if (!StringUtils.isEmpty(name)) {
				sql = sql + " and name like ? ";
				listParam.add(name);
			}
			if (!StringUtils.isEmpty(uclass)) {
				sql = sql + " and uclass = ? ";
				listParam.add(uclass);
			}
		} else {
			listParam.add(roleId);
			sql = "select count(*) from user u,user_role ur where ur.roleId=? and u.code=ur.userId and u.status=0 ";
			if (!StringUtils.isEmpty(code)) {
				sql = sql + " and u.code = ?";
				listParam.add(code);
			}
			if (!StringUtils.isEmpty(name)) {
				sql = sql + " and u.name like ? ";
				listParam.add("%" + name + "%");
			}
			if (!StringUtils.isEmpty(uclass)) {
				sql = sql + " and u.uclass = ? ";
				listParam.add(uclass);
			}
		}
		int rows = dbop.count(0, sql, listParam);
		return rows;
	}

	/**
	 * 查找用户列表
	 * 
	 * @param code
	 * @param name
	 * @param roleId
	 * @param uclass
	 * @return
	 * @throws DbException
	 */
	public List<User> searchUser(String code, String name, String roleId,
			String uclass, int offset, int limit) throws DbException {
		List<User> listUser = null;
		String sql = null;
		List<Object> listParam = new ArrayList<Object>();

		if (StringUtils.isEmpty(roleId)) {
			sql = "select * from user where status=0  ";
			if (!StringUtils.isEmpty(code)) {
				sql = sql + " and code = ?";
				listParam.add(code);
			}
			if (!StringUtils.isEmpty(name)) {
				sql = sql + " and name like ? ";
				listParam.add(name);
			}
			if (!StringUtils.isEmpty(uclass)) {
				sql = sql + " and uclass = ? ";
				listParam.add(uclass);
			}
			sql = sql + " limit ?,?";
			listParam.add(offset);
			listParam.add(limit);
			listUser = dbop.findList(0l, sql, new ResultObjectCall<User>() {
				@Override
				public User getResultObject(ResultSet rs) throws SQLException,
						DbException {
					// TODO Auto-generated method stub
					if (rs != null) {
						User user = new User();
						user.setCreateTime(rs.getTimestamp(1));
						user.setCreateUserId(rs.getInt(2));
						user.setName(rs.getString(3));
						user.setPwd(rs.getString(4));
						user.setStatus(rs.getInt(5));
						user.setGender(rs.getInt(6));
						user.setCode(rs.getString(7));
						user.setUclass(rs.getString(8));
						return user;
					}
					return null;
				}

			});
		} else {
			listParam.add(roleId);
			sql = "select u.*,ur.roleId from user u,user_role ur where ur.roleId=? and u.code=ur.userId and u.status=0 ";
			if (!StringUtils.isEmpty(code)) {
				sql = sql + " and u.code = ?";
				listParam.add(code);
			}
			if (!StringUtils.isEmpty(name)) {
				sql = sql + " and u.name like ? ";
				listParam.add("%" + name + "%");
			}
			if (!StringUtils.isEmpty(uclass)) {
				sql = sql + " and u.uclass = ? ";
				listParam.add(uclass);
			}
			sql = sql + " limit ?,?";
			listParam.add(offset);
			listParam.add(limit);
			listUser = dbop.findListParam(0l, sql, listParam,
					new ResultObjectCall<User>() {
						@Override
						public User getResultObject(ResultSet rs)
								throws SQLException, DbException {
							// TODO Auto-generated method stub
							if (rs != null) {
								User user = new User();
								user.setCreateTime(rs.getTimestamp(1));
								user.setCreateUserId(rs.getInt(2));
								user.setName(rs.getString(3));
								user.setPwd(rs.getString(4));
								user.setStatus(rs.getInt(5));
								user.setGender(rs.getInt(6));
								user.setCode(rs.getString(7));
								user.setUclass(rs.getString(8));
								return user;
							}
							return null;
						}

					});
		}

		return listUser;
	}
}
