package com.haoxw.chuanmei.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haoxw.chuanmei.dao.FocusDao;
import com.haoxw.chuanmei.dao.NewsDao;
import com.haoxw.chuanmei.dao.UserDao;
import com.haoxw.chuanmei.dao.UserRoleDao;
import com.haoxw.chuanmei.model.Focus;
import com.haoxw.chuanmei.model.News;
import com.haoxw.chuanmei.model.Role;
import com.haoxw.chuanmei.model.User;
import com.haoxw.chuanmei.util.CookiesUtil;
import com.haoxw.chuanmei.util.RequestUtils;
import com.haoxw.chuanmei.util.ValidateUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	private static Logger logger = LoggerFactory
			.getLogger(UserController.class);
	@Resource
	private UserDao userDao;
	@Resource
	private NewsDao newsDao;
	@Resource
	private FocusDao focusDao;
	@Resource
	private UserRoleDao userRoleDao;
	/**
	 * 登录
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		String code = RequestUtils.getString(request, "code", null);
		String pwd = RequestUtils.getString(request, "pwd", null);
		if (!ValidateUtil.validParam(code, pwd)) {
			modelMap.addAttribute("info", "用户名和密码都不能为空");
			return "tips";
		}
		User u = userDao.checkCodeLogin(code, pwd);
		if (u == null) {
			modelMap.addAttribute("info", "用户名或者密码错误");
			return "tips";
		} else {
			CookiesUtil cu = CookiesUtil.getInstance();
			cu.addCookie(code, response);
			modelMap.addAttribute("url", "/message/list");
			return "temp";
		}
	}

	/**
	 * 退出
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		boolean b = cu.removeCookie(request, response);
		if (b) {
			modelMap.addAttribute("url", "/user/index");
			return "temp";
		} else {
			modelMap.addAttribute("info", "您已经成功退出");
			return "tips";
		}

	}

	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/changepwd")
	public String changepwd(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (!StringUtils.isEmpty(code)) {
			User u = userDao.getUserByCode(code);
			modelMap.addAttribute("user", u);
			modelMap.addAttribute("title", "中国传媒大学-密码修改");
			modelMap.addAttribute("seo_keywords", "中国传媒大学-密码修改");
			modelMap.addAttribute("seo_desc", "中国传媒大学-密码修改" );
			return "user/changepwd";
		} else {
			modelMap.addAttribute("info", "您还未登录");
			return "tips";
		}

	}
	
	
	/**
	 * 执行修改密码
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/changedpwd")
	public String changedpwd(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (!StringUtils.isEmpty(code)) {
			String pwd = RequestUtils.getString(request, "pwd", null);
			if(!StringUtils.isEmpty(code)){
				User u = userDao.getUserByCode(code);
				u.setPwd(pwd);
				userDao.updateUser(u);
				modelMap.addAttribute("info", "成功修改");
				return "tips";
			}else{
				modelMap.addAttribute("info", "密码不能为空");
				return "user/changepwd";
			}
			
		} else {
			modelMap.addAttribute("info", "您还未登录");
			return "tips";
		}

	}
	
	/**
	 * 充值密码
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/resetpwd")
	public String resetpwd(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (!StringUtils.isEmpty(code)) {
			User u = userDao.getUserByCode(code);
			modelMap.addAttribute("user", u);
			modelMap.addAttribute("title", "中国传媒大学-重置密码");
			modelMap.addAttribute("seo_keywords", "中国传媒大学-重置密码");
			modelMap.addAttribute("seo_desc", "中国传媒大学-重置密码" );
			return "user/resetpwd";
		} else {
			modelMap.addAttribute("info", "您还未登录");
			return "tips";
		}

	}
	
	
	/**
	 * 执行重置密码
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doresetpwd")
	public String doresetpwd(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		String pwd = RequestUtils.getString(request, "pwd", null);
		String codeTmp = RequestUtils.getString(request, "code", null);
		if (StringUtils.isEmpty(pwd)||StringUtils.isEmpty(codeTmp)) {
			modelMap.addAttribute("info", "用户名密码均不能为空");
			return "tips";
		}
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (!StringUtils.isEmpty(code)) {
				User u = userDao.getUserByCode(codeTmp);
				if (u==null){
					modelMap.addAttribute("info", codeTmp+"不存在");
					return "tips";
				}
				u.setPwd(pwd);
				userDao.updateUser(u);
				modelMap.addAttribute("info", "成功重置");
				return "tips";
			
		} else {
			modelMap.addAttribute("info", "您还未登录");
			return "tips";
		}

	}
	/**
	 *用户信息
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/info")
	public String info(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (!StringUtils.isEmpty(code)) {
			User u = userDao.getUserByCode(code);
			Role r  = userRoleDao.findUserRoleByUserId(u.getCode());
			modelMap.addAttribute("user", u);
			modelMap.addAttribute("role", r);
			modelMap.addAttribute("title", "中国传媒大学-个人资料");
			modelMap.addAttribute("seo_keywords", "中国传媒大学-个人资料");
			modelMap.addAttribute("seo_desc", "中国传媒大学-个人资料" );
			return "user/info";
		} else {
			modelMap.addAttribute("info", "您还未登录");
			return "tips";
		}

	}
	/**
	 * 系统首页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/index")
	public String index(ModelMap modelMap) throws Exception {
		List<Focus> listFocus = focusDao.allFocusList();
		List<News> listNews1 = newsDao.findNewsList(1, 0, 5);
		List<News> listNews2 = newsDao.findNewsList(2, 0, 5);
		List<News> listNews3 = newsDao.findNewsList(3, 0, 5);
		modelMap.addAttribute("listFocus", listFocus);
		modelMap.addAttribute("listNews1", listNews1);
		modelMap.addAttribute("listNews2", listNews2);
		modelMap.addAttribute("listNews3", listNews3);
		return "index";

	}
	/**
	 * 联系我们
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/contact")
	public String contact(ModelMap modelMap) throws Exception {
		News news = newsDao.getNewsById(1);
		modelMap.addAttribute("news", news);
		return "contact";
	}
	/**
	 * 实验中心
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/syzx")
	public String syzx(ModelMap modelMap) throws Exception {
		News news = newsDao.getNewsById(2);
		modelMap.addAttribute("news", news);
		return "syzx";
	}
	
}
