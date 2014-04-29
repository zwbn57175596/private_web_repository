package com.haoxw.chuanmei.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.haoxw.chuanmei.bean.DbException;

/**
 * 简单db操作封装   sql中的表需要事先计算出来
 * @author xuewuhao
 *
 */
public class DbOp<T> {
	private static final Logger log = LoggerFactory.getLogger(DbOp.class);
	private static DbConn dbConn = DbConn.getInstance();

    /**
     * 不带参数的更新
     * @param key 分库key
     * @param sql
     * @return flag
     */
    public static int update(long key,String sql) {
        int rowCount = -1;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConn.getConnection(key);
            statement = connection.prepareStatement(sql);
            rowCount = statement.executeUpdate();
        } catch (SQLException e) {
        	log.error("",e);
        } finally {
        	dbConn.closeConnection(statement, connection);
        }
        return rowCount;
    }
    
    /**
     * 统计数目
     * @param key
     * @param sql
     * @param par
     * @return
     */
    public static int count(long key,String sql, List<Object> par) {
        int rowCount = -1;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConn.getConnection(key);
            statement = connection.prepareStatement(sql);
            if (par != null) {
				for (int i = 0; i < par.size(); i++) {
						statement.setObject(i + 1, par.get(i));
				}
			}
            resultSet = statement.executeQuery();
            if(resultSet.next()){
            	rowCount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
        	log.error("",e);
        } finally {
        	dbConn.closeConnection(resultSet,statement, connection);
        }
        return rowCount;
    }
 
    /**
     * 带参数的更新
     * 例：
     * try{
     * String sql="update table set field1 = ?,field2=? where id=?";
     * List<Object> par = new ArrayList<Object>();
     * par.add("field1 value");
     * par.add("field2 value");
     * par.add(1);
     *      DB.update(sql,par);//这里无需根据判断返回值来判定是否执行成功
     * }catch(Exception e){
     *      e.printStackTrace();
     * }
     * @param key 分库key
     * @param sql
     * @param par
     * @return rowCount
     */
    public static int update(long key,String sql, List<Object> par) {
 
        //执行更新后被影响的行的数量
        int rowCount = -1;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConn.getConnection(key);
            statement = connection.prepareStatement(sql);
			if (par != null) {
				for (int i = 0; i < par.size(); i++) {
						statement.setObject(i + 1, par.get(i));
				}
			}
            //将执行更新所影响的行数赋值给rouCount变量
            rowCount = statement.executeUpdate();
        } catch (SQLException e) {
        	log.error("",e);
        	e.printStackTrace();
        } finally {
        	dbConn.closeConnection(statement, connection);
        }
        //返回所影响的行数给控制端
        return rowCount;
    }
 
    /**********************************************************/

	/**
	 * 无参查询
	 * 
	 * @param key
	 * @param sql
	 * @param rsObjCall 回调处理db返回值
	 * @return List<T>
	 * @throws DbException 
	 */
	public List<T> findList(long key, String sql, ResultObjectCall<T> rsObjCall) throws DbException {
		List<T> list = new ArrayList<T>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = dbConn.getConnection(key);
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				list.add(rsObjCall.getResultObject(resultSet));
			}
		} catch (SQLException e) {
			log.error("", e);
		} finally {
			// 关闭数据库
			dbConn.closeConnection(resultSet,statement, connection);
		}
		return list;
	}

	/**
	 * 参数查询
	 * 
	 * @param 分库key
	 * @param sql
	 * @param par
	 * @param rsObjCall 回调处理db返回值
	 * @return List<T>
	 * @throws DbException 
	 */
	public List<T> findListParam(long key, String sql, List<Object> par, ResultObjectCall<T> rsObjCall) throws DbException {
		List<T> list = new ArrayList<T>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = dbConn.getConnection(key);
			statement = connection.prepareStatement(sql);
			if (par != null) {
				for (int i = 0; i < par.size(); i++) {
					statement.setObject(i + 1, par.get(i));
				}
			}
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				list.add(rsObjCall.getResultObject(resultSet));
			}
		} catch (SQLException e) {
			log.error("", e);
			e.printStackTrace();
		} finally {
			// 关闭数据库
			dbConn.closeConnection(resultSet,statement, connection);
		}
		return list;
	}
}