package com.haoxw.chuanmei.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.haoxw.chuanmei.bean.Constant;
import com.haoxw.chuanmei.dao.UserDao;
import com.haoxw.chuanmei.dao.UserRoleDao;
import com.haoxw.chuanmei.model.Role;
import com.haoxw.chuanmei.model.User;
import com.haoxw.chuanmei.util.CookiesUtil;
import com.haoxw.chuanmei.util.DateUtil;
import com.haoxw.chuanmei.util.ExcelUtil;
import com.haoxw.chuanmei.util.RequestUtils;
import com.haoxw.chuanmei.util.ValidateUtil;

@Controller
@RequestMapping("/excel")
public class ExcelController {
	private static Logger logger = LoggerFactory
			.getLogger(ExcelController.class);
	@Resource
	private UserDao userDao;
	@Resource
	private UserRoleDao userRoleDao;
	// 一页记录数
	private final static int limit = 10;

	/**
	 * 进入导入页面
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/in")
	public String in(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		modelMap.addAttribute("title", "中国传媒大学-数据导入");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-数据导入");
		modelMap.addAttribute("seo_desc", "中国传媒大学-数据导入");
		modelMap.addAttribute("exportType", Constant.EXPORTTYPE);
		return "excel/in";
	}

	/**
	 * 进入用户列表页面
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
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		String codeTmp = RequestUtils.getString(request, "code", null);
		String nameTmp = RequestUtils.getString(request, "name", null);
		String roleIdTmp = RequestUtils.getString(request, "roleId", null);
		String uclassTmp = RequestUtils.getString(request, "uclass", null);
		String flagTmp = RequestUtils.getString(request, "flag", null);
		modelMap.addAttribute("title", "中国传媒大学-用户管理");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-用户管理");
		modelMap.addAttribute("seo_desc", "中国传媒大学-用户管理");
		modelMap.addAttribute("exportType", Constant.EXPORTTYPE);
		List<User> list = null;
		int count = 0;
		// 不执行查询
		if (flagTmp == null) {
		} else {
			int pageNo = RequestUtils.getInt(request, "pageNo", 1);
			int offset = (pageNo - 1) * limit;
			count = userDao.countUser(codeTmp, nameTmp, roleIdTmp, uclassTmp);
			int totalPageNo = (count + limit - 1) / limit;
			list = userDao.searchUser(codeTmp, nameTmp, roleIdTmp, uclassTmp,
					offset, limit);
			modelMap.addAttribute("pageNo", pageNo);
			modelMap.addAttribute("totalPageNo", totalPageNo);
			modelMap.addAttribute("count", count);
			modelMap.addAttribute("list", list);
		}
		modelMap.addAttribute("code", codeTmp);
		modelMap.addAttribute("name", nameTmp);
		modelMap.addAttribute("roleId", roleIdTmp);
		modelMap.addAttribute("uclass", uclassTmp);
		return "excel/list";
	}

	/**
	 * 进入添加页面
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		String codeTmp = RequestUtils.getString(request, "code", null);
		if (codeTmp != null) {
			User u = this.userDao.getUserByCode(codeTmp);
			Role role = this.userRoleDao.findUserRoleByUserId(codeTmp);
			modelMap.addAttribute("user", u);
			modelMap.addAttribute("roleId", role.getRoleId());
		}
		modelMap.addAttribute("title", "中国传媒大学-增加用户");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-增加用户");
		modelMap.addAttribute("seo_desc", "中国传媒大学-增加用户");
		modelMap.addAttribute("exportType", Constant.EXPORTTYPE);
		modelMap.addAttribute("gender", Constant.GENDER);
		return "excel/add";
	}

	/**
	 * 执行添加或者修改
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		String codeTmp = RequestUtils.getString(request, "code", null);
		String name = RequestUtils.getString(request, "name", null);
		String pwd = RequestUtils.getString(request, "pwd", null);
		String gender = RequestUtils.getString(request, "gender", null);
		String uclass = RequestUtils.getString(request, "uclass", null);
		String roleId = RequestUtils.getString(request, "roleId", null);

		if (!ValidateUtil
				.validParam(codeTmp, name, pwd, gender, uclass, roleId)) {
			modelMap.addAttribute("info", "输入项均不能为空！");
			return "tips";
		}
		User u = userDao.checkUniqueCode(codeTmp);
		if (u == null) {
			u = new User();
			u.setCode(codeTmp);
			u.setName(name);
			u.setGender(Integer.parseInt(gender));
			u.setUclass(uclass);
			u.setPwd(pwd);
			boolean b = userDao.saveUser(u);
			if (b) {
				userRoleDao
						.setUserRoleByUser(Integer.parseInt(roleId), codeTmp);
				modelMap.addAttribute("info", "添加成功");
			} else {
				modelMap.addAttribute("info", "添加失败，请稍后再试");
				return "tips";
			}
		} else {
			u.setCode(codeTmp);
			u.setName(name);
			u.setGender(Integer.parseInt(gender));
			u.setUclass(uclass);
			u.setPwd(pwd);
			boolean b = userDao.updateUser(u);
			if (b) {
				userRoleDao
						.setUserRoleByUser(Integer.parseInt(roleId), codeTmp);
				modelMap.addAttribute("info", "修改成功");
			} else {
				modelMap.addAttribute("info", "修改失败，请稍后再试");
				return "tips";
			}
		}

		return "tips";
	}

	/**
	 * 逻辑删除
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/del")
	public String del(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		String codeTmp = RequestUtils.getString(request, "code", null);

		if (!ValidateUtil.validParam(codeTmp)) {
			modelMap.addAttribute("info", "用户Id不能为空！");
			return "tips";
		}
		User u = userDao.checkUniqueCode(codeTmp);
		if (u == null) {
			modelMap.addAttribute("info", "非法请求，该用户不存在");
			return "tips";
		} else {
			u.setStatus(1);
			boolean b = userDao.updateUser(u);
			if (b) {
				modelMap.addAttribute("info", "删除成功");
			} else {
				modelMap.addAttribute("info", "删除失败，请稍后再试");
				return "tips";
			}
		}

		return "tips";
	}

	/**
	 * 处理上传
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/importInfo")
	public String importInfo(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		int insertrows = 0;
		int updaterows = 0;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		/** 页面控件的文件流 **/
		MultipartFile multipartFile = multipartRequest.getFile("excelFile");
		if (!multipartFile.isEmpty()) {
			String[][] contents = ExcelUtil.getExcelContents(
					multipartFile.getInputStream(), null);
			if (contents != null && contents.length > 0) {
				for (int i = 0; i < contents.length; i++) {
					User u = userDao.checkUniqueCode(contents[i][0]);
					if (u == null) {
						insertrows++;
						u = new User();
						u.setCode(contents[i][0]);
						u.setName(contents[i][1]);
						u.setGender(Integer.parseInt(contents[i][2]));
						u.setUclass(contents[i][3]);
						u.setPwd("123456");
						userDao.saveUser(u);
						userRoleDao.setUserRoleByUser(
								Integer.parseInt(contents[i][4]),
								contents[i][0]);
					} else {
						updaterows++;
						u.setCode(contents[i][0]);
						u.setName(contents[i][1]);
						u.setGender(Integer.parseInt(contents[i][2]));
						u.setUclass(contents[i][3]);
						u.setPwd("123456");
						userDao.updateUser(u);
						userRoleDao.setUserRoleByUser(
								Integer.parseInt(contents[i][4]),
								contents[i][0]);
					}
				}
			}
			modelMap.addAttribute("info", "成功导入" + insertrows + "条，成功更新"
					+ updaterows + "条");
		} else {
			modelMap.addAttribute("info", "您还没有选择excel文件");
		}
		modelMap.addAttribute("title", "中国传媒大学-系统提示");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-系统提示");
		modelMap.addAttribute("seo_desc", "中国传媒大学-系统提示");
		return "tips";
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/export")
	public void export(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int roleId = RequestUtils.getInt(request, "roleId", 0);
		List<User> listUser = userDao.allUserByRole(roleId);
		List list = new ArrayList();
		for(User u:listUser){
			Object[] o = new Object[5];
			o[0]=u.getCode();
			o[1]=u.getName();
			o[2]= u.getGender();
			o[3]=u.getUclass();
			o[4]=roleId;
			list.add(o);
		}
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("content-disposition", "attachment;filename=" +DateUtil.date2Str(new Date())+ ".xls");
		ExcelUtil.writeExcel(response.getOutputStream(), list, new  String[]{"ID","NAME","GENDER","CLASS","ROLE"});
	}
}
