package com.haoxw.chuanmei.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haoxw.chuanmei.dao.MessageDao;
import com.haoxw.chuanmei.model.Message;
import com.haoxw.chuanmei.util.CookiesUtil;
import com.haoxw.chuanmei.util.RequestUtils;

@Controller
@RequestMapping("/message")
public class MessageController {
	private static Logger logger = LoggerFactory
			.getLogger(MessageController.class);
	// 一页记录数
	private final static int limit = 10;
	@Resource
	private MessageDao messageDao;

	/**
	 * 消息列表
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还未登录");
			return "tips";
		}
		int pageNo = RequestUtils.getInt(request, "pageNo", 1);
		int offset = (pageNo - 1) * limit;
		int count = messageDao.countMessage(code);
		int totalPageNo = (count + limit - 1) / limit;
		List<Message> listMessage = messageDao.findMessageList(code, offset,
				limit);
		modelMap.addAttribute("title", "中国传媒大学-消息");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-消息");
		modelMap.addAttribute("seo_desc", "中国传媒大学-消息");
		modelMap.addAttribute("pageNo", pageNo);
		modelMap.addAttribute("totalPageNo", totalPageNo);
		modelMap.addAttribute("count", count);
		modelMap.addAttribute("list", listMessage);

		return "message/list";
	}

	/**
	 * 查看消息页
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/show/{id}")
	public String show(@PathVariable int id, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if(StringUtils.isEmpty(code)){
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		Message message = messageDao.getMessageById(id);
		if(message.getState()==0){
			message.setState(1);
			messageDao.saveOrUpdateMessage(message);
		}
		if (message == null) {
			modelMap.addAttribute("info", "非法请求");
			return "tips";
		}
		modelMap.addAttribute("message", message);
		modelMap.addAttribute("title", "中国传媒大学-查看消息");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-查看消息");
		modelMap.addAttribute("seo_desc", "中国传媒大学-查看消息");
		return "message/view";
	}
}
