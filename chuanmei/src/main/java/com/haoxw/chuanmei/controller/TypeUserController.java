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

import com.haoxw.chuanmei.dao.ShebeiTypeDao;
import com.haoxw.chuanmei.dao.TypeUserDao;
import com.haoxw.chuanmei.dao.UserDao;
import com.haoxw.chuanmei.model.ShebeiType;
import com.haoxw.chuanmei.model.User;
import com.haoxw.chuanmei.util.CookiesUtil;
import com.haoxw.chuanmei.util.RequestUtils;

/**
 * 设置设备分类和审核员对应关系
 * 
 * @author xuewuhao
 * 
 */
@Controller
@RequestMapping("/typeuser")
public class TypeUserController {
	private static Logger logger = LoggerFactory
			.getLogger(TypeUserController.class);
	@Resource
	private TypeUserDao typeUserDao;
	@Resource
	private UserDao userDao;
	@Resource
	private ShebeiTypeDao shebeiTypeDao;

	/**
	 * 进入设置页面
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/inset")
	public String inset(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还未登录");
			return "tips";
		}
		// List<User> listUser = userDao.allCheckUser();
		List<ShebeiType> listShebeiType = shebeiTypeDao.allShebeiType();
		modelMap.addAttribute("title", "中国传媒大学-审核设置");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-审核设置");
		modelMap.addAttribute("seo_desc", "中国传媒大学-审核设置");
		modelMap.addAttribute("listShebeiType", listShebeiType);

		return "typeuser/inset";
	}

	/**
	 * 进入设置
	 * 
	 * @param sbid
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/set/{sbid}")
	public String set(@PathVariable int sbid, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		String userId = typeUserDao.findUserIdBySbTypeId(sbid);
		List<User> listUser = userDao.allUserByRole(4);
		modelMap.addAttribute("sbid", sbid);
		modelMap.addAttribute("userId", userId);
		modelMap.addAttribute("listUser", listUser);
		modelMap.addAttribute("title", "中国传媒大学-审核设置");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-审核设置");
		modelMap.addAttribute("seo_desc", "中国传媒大学-审核设置");
		return "typeuser/set";
	}

	/**
	 * 执行设置
	 * 
	 * @param sbid
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doset")
	public String doset(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		int sbId = RequestUtils.getInt(request, "sbId", 1);
		String userId = RequestUtils.getString(request, "userId", null);
		boolean b = typeUserDao.setSbTypeUserId(sbId, userId);
		if (b) {
			modelMap.addAttribute("info", "设置成功");
		} else {
			modelMap.addAttribute("info", "设置失败");
		}
		return inset(request, modelMap);
	}
}
