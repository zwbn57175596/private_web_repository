package com.haoxw.chuanmei.controller;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.haoxw.chuanmei.dao.FuncDao;
import com.haoxw.chuanmei.dao.RoleDao;
import com.haoxw.chuanmei.dao.RoleFuncDao;
import com.haoxw.chuanmei.model.Func;
import com.haoxw.chuanmei.model.Role;
import com.haoxw.chuanmei.util.CookiesUtil;
import com.haoxw.chuanmei.util.RequestUtils;

@Controller
@RequestMapping("/authority")
public class AuthorityController {
	private static Logger logger = LoggerFactory.getLogger(AuthorityController.class);
	@Resource
	private FuncDao funcDao;
	@Resource
	private RoleDao roleDao;
	@Resource
	private RoleFuncDao roleFuncDao;
	/**
	 * 进入角色功能列表
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rolefunclist")
	public String rolefunclist(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if(StringUtils.isEmpty(code)){
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		int roleId = RequestUtils.getInt(request, "roleId", 0);
		List<Func> listFuncs = funcDao.allFunc();
		List<Func> roleListFuncs = roleFuncDao.fingRoleFuncsByRoleId(roleId);
		for(int i=0;i<listFuncs.size();i++){
			for(int j=0;j<roleListFuncs.size();j++){
				if(listFuncs.get(i).getFuncId()==roleListFuncs.get(j).getFuncId()){
					listFuncs.get(i).setParentId(listFuncs.get(i).getFuncId());
					break;
				}
			}
		}
		modelMap.addAttribute("title", "中国传媒大学-功能编辑");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-功能编辑");
		modelMap.addAttribute("seo_desc", "中国传媒大学-功能编辑");
		modelMap.addAttribute("roleId", roleId);
		modelMap.addAttribute("listFuncs", listFuncs);
		modelMap.addAttribute("roleListFuncs", roleListFuncs);
		return "user/funclist";
	}

	/**
	 * 进入增加或者修改
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/setrolefunclist")
	public String setrolefunclist(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if(StringUtils.isEmpty(code)){
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		int roleId = RequestUtils.getInt(request, "roleId", 0);
		String funcparam[] = request.getParameterValues("funcparam");
		if(funcparam.length==0){
			modelMap.addAttribute("info","请最少选一个功能" );
			return "tips";
		}
		List<Integer> listfunc = new ArrayList<Integer>();
		for(int i=0;i<funcparam.length;i++){  
			listfunc.add(Integer.parseInt(funcparam[i]));
        }  
		boolean b = roleFuncDao.setRoleFuncs(roleId,listfunc);
		if(b){
			return role(request,modelMap);
		}else{
			modelMap.addAttribute("info","设置失败，请稍后再试" );
			return "tips";
		}
	}
	/**
	 * 角色列表
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/role")
	public String role(HttpServletRequest request, ModelMap modelMap) throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (!StringUtils.isEmpty(code)) {
			List<Role> listRole = roleDao.allRole();
			modelMap.addAttribute("listRole", listRole);
			modelMap.addAttribute("title", "中国传媒大学-权限管理");
			modelMap.addAttribute("seo_keywords", "中国传媒大学-权限管理");
			modelMap.addAttribute("seo_desc", "中国传媒大学-权限管理");
			return "user/role";
		} else {
			modelMap.addAttribute("info", "您还未登录");
			return "tips";
		}

	}
	
}
