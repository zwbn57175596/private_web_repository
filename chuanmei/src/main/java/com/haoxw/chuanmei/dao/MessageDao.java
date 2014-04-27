package com.haoxw.chuanmei.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.haoxw.chuanmei.bean.DbException;
import com.haoxw.chuanmei.dao.base.DbOp;
import com.haoxw.chuanmei.dao.base.ResultObjectCall;
import com.haoxw.chuanmei.model.Message;

/**
 * 新闻dao
 * 
 * @author xuewuhao
 * 
 */
@Repository
public class MessageDao {

	private static final DbOp<Message> dbop = new DbOp<Message>();
	private static boolean result = false;

	/**
	 * 保存或者修改
	 * 
	 * @param message
	 * @return
	 * @throws DbException
	 */
	public boolean saveOrUpdateMessage(Message message) throws DbException {

		String addsql = " insert into message(userId,sendUserId,orderId,title,content,cDate,state) values(?,?,?,?,?,now(),0) ";
		String updatesql = "update message set userId=?,sendUserId=?,orderId=?,title=?,content = ?,state=?  where id=?";
		List<Object> listParam = new ArrayList<Object>();
		int rows = -1;
		if (message.getId() > 0) {
			listParam.add(message.getUserId());
			listParam.add(message.getSendUserId());
			listParam.add(message.getOrderId());
			listParam.add(message.getTitle());
			listParam.add(message.getContent());
			listParam.add(message.getState());
			listParam.add(message.getId());
			rows = dbop.update(0, updatesql, listParam);
		} else {
			listParam.add(message.getUserId());
			listParam.add(message.getSendUserId());
			listParam.add(message.getOrderId());
			listParam.add(message.getTitle());
			listParam.add(message.getContent());
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
	public Message getMessageById(int id) throws DbException {
		String sql = "select * from message where id=?";
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(id);
		List<Message> listMessage = null;
		listMessage = dbop.findListParam(0l, sql, listParam,
				new ResultObjectCall<Message>() {
					@Override
					public Message getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							Message message = new Message();
							message.setId(rs.getInt(1));
							message.setUserId(rs.getString(2));
							message.setSendUserId(rs.getString(3));
							message.setOrderId(rs.getString(4));
							message.setTitle(rs.getString(5));
							message.setContent(rs.getString(6));
							message.setcDate(rs.getTimestamp(7));
							message.setState(rs.getInt(8));
							return message;
						}
						return null;
					}

				});
		if (listMessage != null && listMessage.size() > 0) {
			return listMessage.get(0);
		}
		return null;
	}

	/**
	 * 统计用户收到消息条目
	 * 
	 * @param userId
	 * @return
	 */
	public int countMessage(String userId) {
		List<Object> listParam = new ArrayList<Object>();
		String sql = null;
		sql = "select count(id) from message where userId = ?  ";
		listParam.add(userId);
		int rows = dbop.count(0, sql, listParam);
		return rows;
	}

	/**
	 * 查找消息列表
	 * 
	 * @param userId
	 * @param offset
	 * @param limit
	 * @return
	 * @throws DbException
	 */
	public List<Message> findMessageList(String userId, int offset, int limit)
			throws DbException {
		List<Object> listParam = new ArrayList<Object>();
		String sql = null;
		sql = "select * from message where userId = ?  order by cDate desc limit ?,? ";
		listParam.add(userId);
		listParam.add(offset);
		listParam.add(limit);

		List<Message> listMessage = null;
		listMessage = dbop.findListParam(0l, sql, listParam,
				new ResultObjectCall<Message>() {
					@Override
					public Message getResultObject(ResultSet rs)
							throws SQLException, DbException {
						// TODO Auto-generated method stub
						if (rs != null) {
							Message message = new Message();
							message.setId(rs.getInt(1));
							message.setUserId(rs.getString(2));
							message.setSendUserId(rs.getString(3));
							message.setOrderId(rs.getString(4));
							message.setTitle(rs.getString(5));
							message.setContent(rs.getString(6));
							message.setcDate(rs.getTimestamp(7));
							message.setState(rs.getInt(8));
							return message;
						}
						return null;
					}

				});
		return listMessage;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean delMessage(int id) throws DbException {
		String delsql = " delete from  message where id = " + id;
		dbop.update(0, delsql);
		result = true;
		return result;
	}
}
