package com.haoxw.chuanmei.task;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.haoxw.chuanmei.bean.DbException;
import com.haoxw.chuanmei.dao.MessageDao;
import com.haoxw.chuanmei.dao.StudentOrdersDao;
import com.haoxw.chuanmei.model.Message;
import com.haoxw.chuanmei.model.StudentOrders;
import com.haoxw.chuanmei.util.DateUtil;

/**
 * 订单在结束前3天还没归还需要发消息提醒学生
 * 
 * @author xuewuhao
 * 
 */
@Component
public class NoticeTask {
	private static final Logger logger = LoggerFactory
			.getLogger(NoticeTask.class);

	@Resource
	private MessageDao messageDao;
	@Resource
	private StudentOrdersDao studentOrdersDao;

	/**
	 * 执行方法
	 */
	public void notice() {
		try {
			List<StudentOrders> listStudentOrders = studentOrdersDao
					.findNoticeStudentOrdersList();

			for (StudentOrders so : listStudentOrders) {
				//将该订单置为红色告警状态
				so.setIsRed(1);
				studentOrdersDao.updateStudentOrders(so);
				//发提醒通知
				Message m = new Message();
				m.setOrderId(so.getOrderId());
				m.setUserId(so.getUserId());
				m.setTitle("订单号" + so.getId() + "归还提醒");
				m.setContent("老师借用设备订单ID<a href=\"/studentOrders/view?id="
						+ so.getOrderId()
						+ "&myid="
						+ so.getId()
						+ "\">"
						+ so.getOrderId()
						+ "</a>将于"
						+ DateUtil.date2Str(so.geteDate(),
								"yyyy-MM-dd HH:mm:ss") + " 到期，请尽快归还");
				boolean b = messageDao.saveOrUpdateMessage(m);
				logger.info(m.toString() + "###result=" + b);
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			logger.error("", e);
		}
	}
}
