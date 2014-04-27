package com.haoxw.chuanmei.dao;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.haoxw.chuanmei.bean.DbException;
import com.haoxw.chuanmei.dao.base.DbOp;
import com.haoxw.chuanmei.dao.base.ResultObjectCall;
import com.haoxw.chuanmei.model.News;

/**
 * 新闻dao
 * 
 * @author xuewuhao
 * 
 */
@Repository
public class NewsDao {

	private static final DbOp<News> dbop = new DbOp<News>();
	private static boolean result = false;

	/**
	 * 保存或者修改新闻
	 * 
	 * @param news
	 * @return
	 * @throws DbException
	 */
	public boolean saveOrUpdateNews(News news) throws DbException {

		String addsql = " insert into news(typeNews,title,content,addUserId,createTime,upTime) values(?,?,?,?,now(),now()) ";
		String updatesql = "update news set typeNews=?,title=?,content=?,addUserId=?,upTime = now()  where id=?";
		List<Object> listParam = new ArrayList<Object>();
		int rows = -1;
		if (news.getId() > 0) {
			listParam.add(news.getTypeNews());
			listParam.add(news.getTitle());
			listParam.add(news.getContent());
			listParam.add(news.getAddUserId());
			listParam.add(news.getId());
			rows = dbop.update(0, updatesql, listParam);
		} else {
			listParam.add(news.getTypeNews());
			listParam.add(news.getTitle());
			listParam.add(news.getContent());
			listParam.add(news.getAddUserId());
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
	public News getNewsById(int id) throws DbException {
		String sql = "select * from news where id=?";
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(id);
		List<News> listNews = null;
		listNews = dbop.findListParam(0l, sql, listParam,
				new ResultObjectCall<News>() {
					@Override
					public News getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							News news = new News();
							news.setId(rs.getInt(1));
							news.setTypeNews(rs.getInt(2));
							news.setTitle(rs.getString(3));
							try {
								news.setContent(new String(rs.getBytes(4),"utf-8"));
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							news.setAddUserId(rs.getString(5));
							news.setCreateTime(rs.getTimestamp(6));
							news.setUpTime(rs.getTimestamp(7));
							return news;
						}
						return null;
					}

				});
		if (listNews != null && listNews.size() > 0) {
			return listNews.get(0);
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
	public int countNews(int type) {
		List<Object> listParam = new ArrayList<Object>();
		String sql = null;
		if (type == -1) {
			sql = "select count(id) from news   ";
		} else {
			sql = "select count(id) from news where typeNews = ?  ";
			listParam.add(type);
		}
		int rows = dbop.count(0, sql, listParam);
		return rows;
	}
	/**
	 * 查找新闻列表
	 * 
	 * @param type
	 *            -1代表查找全部
	 * @param offset
	 * @param limit
	 * @return
	 * @throws DbException
	 */
	public List<News> findNewsList(int type, int offset, int limit)
			throws DbException {
		List<Object> listParam = new ArrayList<Object>();
		String sql = null;
		if (type == -1) {
			sql = "select * from news  order by createTime desc limit ?,? ";
			listParam.add(offset);
			listParam.add(limit);
		} else {
			sql = "select * from news where typeNews = ?  order by createTime desc limit ?,? ";
			listParam.add(type);
			listParam.add(offset);
			listParam.add(limit);
		}

		List<News> listNews = null;
		listNews = dbop.findListParam(0l, sql, listParam,
				new ResultObjectCall<News>() {
					@Override
					public News getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							News news = new News();
							news.setId(rs.getInt(1));
							news.setTypeNews(rs.getInt(2));
							news.setTitle(rs.getString(3));
							news.setContent(new String(rs.getBytes(4)));
							news.setAddUserId(rs.getString(5));
							news.setCreateTime(rs.getTimestamp(6));
							news.setUpTime(rs.getTimestamp(7));
							return news;
						}
						return null;
					}

				});
		return listNews;
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean delNews(int id) throws DbException {
		String delsql = " delete from  news where id = " + id;
		dbop.update(0, delsql);
		result = true;
		return result;
	}
}
