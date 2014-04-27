package com.haoxw.chuanmei.intercepter;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.haoxw.chuanmei.bean.Constant;
import com.haoxw.chuanmei.dao.RoleFuncDao;
import com.haoxw.chuanmei.dao.UserRoleDao;
import com.haoxw.chuanmei.model.Func;
import com.haoxw.chuanmei.model.Role;
import com.haoxw.chuanmei.util.CookiesUtil;

public class CommonInterceptor extends HandlerInterceptorAdapter {
	private final Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);

	@Resource
	private UserRoleDao userRoleDao;
	@Resource
	private RoleFuncDao roleFuncdao;
	/**
	 * 方法处理后执行
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	/**
	 * 方法处理中
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		String os = System.getProperty("os.name");
		if (modelAndView == null) {
			modelAndView = new ModelAndView();
		}
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (code != null) {
			Role r = userRoleDao.findUserRoleByUserId(code);
			List<Func> listFuncs = roleFuncdao.fingRoleFuncsByRoleId(r.getRoleId());
			modelAndView.addObject(Constant.loginFlag, code);
			modelAndView.addObject("myFuncs", listFuncs);
		}

		if (os.startsWith("Windows"))
			modelAndView.addObject("url_prefix", "http://localhost:8000");
		else
			modelAndView.addObject("url_prefix", "http://202.205.18.48");
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 方法执行前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		return super.preHandle(request, response, handler);
	}

}
