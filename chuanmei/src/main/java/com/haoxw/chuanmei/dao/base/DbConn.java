package com.haoxw.chuanmei.dao.base;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据源
 * 
 * @author xuewuhao
 * 
 */
public class DbConn {

	private static final Logger log = LoggerFactory.getLogger(DbConn.class);
	private static DbConn instance = null;

	private DbConn() {
	}

	public static DbConn getInstance() {
		if (instance == null) {
			instance = new DbConn();
			initpool();
		}
		return instance;
	}

	/**
	 * 数据源list
	 */
	public static List<ComboPooledDataSource> datesourceList = new ArrayList<ComboPooledDataSource>();

	/**
	 * 初始化连接池
	 */
	public static void initpool() {
		// 目前写死，之后改为从配置文件或者配置中心取值，多个之间用”,“号隔开
		String dateSourcesString = "jdbc:mysql://localhost:3306/chuanmei?characterEncoding=utf-8|root|root";
		String dateSourcesArr[] = dateSourcesString.split(",");
		for (String str : dateSourcesArr) {
			String dateSource[] = str.split("[|]");
			ComboPooledDataSource cpds = new ComboPooledDataSource(true);
			cpds.setJdbcUrl(dateSource[0]);
			try {
				cpds.setDriverClass("com.mysql.jdbc.Driver");
			} catch (PropertyVetoException e) {
				log.error("", e);
			}
			cpds.setUser(dateSource[1]);
			cpds.setPassword(dateSource[2]);
			cpds.setMaxPoolSize(50);
			cpds.setMinPoolSize(10);
			cpds.setAcquireIncrement(5);
			cpds.setInitialPoolSize(5);
			cpds.setMaxIdleTime(50);
			datesourceList.add(cpds);
		}
	}

	/**
	 * hash取模 返回不同的数据源
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public ComboPooledDataSource getComboPooledDataSource(long key) throws Exception {
		ComboPooledDataSource cpds = null;

		int listSize = datesourceList.size();
		if (listSize <= 0) {
			throw new RuntimeException();
		}
		int result = (int) key % listSize;
		cpds = datesourceList.get(result);
		//System.out.println("活动连接数" + cpds.getNumBusyConnections() + "");
		return cpds;
	}

	/**
	 * hash取模 获得不同数据库连接
	 */
	public synchronized Connection getConnection(long key) {
		Connection conn = null;
		try {
			conn = getComboPooledDataSource(key).getConnection();
		} catch (Exception e) {
			log.error("", e);
		}
		return conn;
	}

	/**
	 * 关闭执行查询操作的连接
	 */
	public void closeConnection(ResultSet resultSet, Statement statement, Connection connection) {
		try {
			if (resultSet != null) {
				resultSet.close();
				resultSet = null;
			}
			if (statement != null) {
				statement.close();
				statement = null;
			}
			if (connection != null) {
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			log.error("", e);
		}
	}

	/**
	 * 关闭执行更新操作的连接
	 */
	public void closeConnection(Statement statement, Connection connection) {
		try {
			if (statement != null) {
				statement.close();
				statement = null;
			}
			if (connection != null) {
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			log.error("", e);
		}
	}
}