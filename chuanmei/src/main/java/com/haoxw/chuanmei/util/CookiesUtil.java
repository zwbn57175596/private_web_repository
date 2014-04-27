package com.haoxw.chuanmei.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.haoxw.chuanmei.bean.Constant;

/**
 * cookies操作
 * @author xuewuhao
 *
 */
public class CookiesUtil {
	private static CookiesUtil instance = null;

	private CookiesUtil() {

	}

	public static CookiesUtil getInstance() {
		if (instance == null)
			instance = new CookiesUtil();
		return instance;
	}

	private final static Logger logger = LoggerFactory
			.getLogger(CookiesUtil.class);

	/**
	 * 获取当前用户cookies值
	 * 
	 * @param cookies
	 * @return
	 */
	public  String getCookieValue(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String result = null;
		if (cookies != null && cookies.length > 0)
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(Constant.loginFlag)) {
					try {
						VerifyUtil verifyUtil = new VerifyUtil();
						result = verifyUtil.decrypt(cookie.getValue());
					} catch (Exception e) {
						logger.error("", e);
					}

					break;
				}
			}
		return result;
	}
	
	/**
	 * 添加登录用户email作为cookies
	 * 
	 * @param email
	 * @param response
	 * @return
	 */
	public Cookie addCookie(String code, HttpServletResponse response) {
		VerifyUtil verifyUtil;
		Cookie cookie = null;
		try {
			verifyUtil = new VerifyUtil();
			cookie = new Cookie(Constant.loginFlag, verifyUtil.encrypt(code
					+ ""));
			cookie.setPath("/");
			cookie.setMaxAge(60 * 60 * 24 * 1);// cookie保存1天
			response.addCookie(cookie);
			logger.info("add cookies code=" + code);
		} catch (Exception e) {
			logger.error("", e);
		}
		return cookie;
	}

	/**
	 * 退出 移除cookies
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean removeCookie(HttpServletRequest request,
			HttpServletResponse response) {
		boolean b = false;
		Cookie[] cookies = request.getCookies();
		// cookies不为空，则清除
		if (cookies != null) {
			try {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = new Cookie(cookies[i].getName(), null);
					cookie.setMaxAge(0);
					cookie.setPath("/");// 根据你创建cookie的路径进行填写
					response.addCookie(cookie);
				}
			} catch (Exception e) {
				logger.error("removeCookie", e);
			}
			b = true;
		}
		return b;
	}
}
