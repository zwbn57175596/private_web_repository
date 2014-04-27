package com.haoxw.chuanmei.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haoxw.chuanmei.bean.Constant;
import com.haoxw.chuanmei.dao.ShebeiDao;
import com.haoxw.chuanmei.dao.ShebeiTypeDao;
import com.haoxw.chuanmei.dao.StudentOrdersDao;
import com.haoxw.chuanmei.dao.TeacherOrdersDao;
import com.haoxw.chuanmei.dao.TeacherOrdersItemDao;
import com.haoxw.chuanmei.model.Shebei;
import com.haoxw.chuanmei.model.ShebeiType;
import com.haoxw.chuanmei.model.StudentOrders;
import com.haoxw.chuanmei.model.TeacherOrders;
import com.haoxw.chuanmei.model.TeacherOrdersItem;
import com.haoxw.chuanmei.util.CookiesUtil;
import com.haoxw.chuanmei.util.DateUtil;
import com.haoxw.chuanmei.util.RequestUtils;
import com.haoxw.chuanmei.util.ValidateUtil;

@Controller
@RequestMapping("/studentOrders")
public class StudentOrdersController {
	private static Logger logger = LoggerFactory
			.getLogger(StudentOrdersController.class);
	// 一页记录数
	private final static int limit = 10;
	@Resource
	private TeacherOrdersDao teacherOrdersDao;
	@Resource
	private TeacherOrdersItemDao teacherOrdersItemDao;
	@Resource
	private ShebeiDao shebeiDao;
	@Resource
	private ShebeiTypeDao shebeiTypeDao;
	@Resource
	private StudentOrdersDao studentOrdersDao;
	private Map<String, String> SHEBEITYPE;

	/**
	 * 查找学生可借用订单列表
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
		List<TeacherOrders> listTeacherOrders = teacherOrdersDao
				.findStudentOrdersList(code);
		modelMap.addAttribute("title", "中国传媒大学-学生可借用订单列表");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-学生可借用订单列表");
		modelMap.addAttribute("seo_desc", "中国传媒大学-学生可借用订单列表");
		modelMap.addAttribute("list", listTeacherOrders);
		modelMap.addAttribute("teacherOrderState", Constant.TEACHERORDERSTATE);
		return "studentorder/list";
	}

	/**
	 * 学生已借用订单列表
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mylist")
	public String mylist(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还未登录");
			return "tips";
		}
		int pageNo = RequestUtils.getInt(request, "pageNo", 1);
		int offset = (pageNo - 1) * limit;
		int count = studentOrdersDao.countStudentOrders(code);
		int totalPageNo = (count + limit - 1) / limit;
		List<StudentOrders> listStudentOrders = studentOrdersDao
				.findStudentOrdersList(code, offset, limit);
		modelMap.addAttribute("title", "中国传媒大学-学生已借用订单列表");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-学生已借用订单列表");
		modelMap.addAttribute("seo_desc", "中国传媒大学-学生已借用订单列表");
		modelMap.addAttribute("pageNo", pageNo);
		modelMap.addAttribute("totalPageNo", totalPageNo);
		modelMap.addAttribute("count", count);
		modelMap.addAttribute("list", listStudentOrders);
		modelMap.addAttribute("studentOrderState", Constant.STUDENTORDERSTATE);
		return "studentorder/mylist";
	}

	/**
	 * 进入申请页
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
			modelMap.addAttribute("info", "您还未登录");
			return "tips";
		}
		String id = RequestUtils.getString(request, "id", null);
		String sid = RequestUtils.getString(request, "sid", null);
		TeacherOrders teacherOrders = teacherOrdersDao.getTeacherOrdersById(id);
		StudentOrders studentOrders = studentOrdersDao.getStudentOrdersById(sid);
//		List<Shebei> listShebeiCheck = new ArrayList<Shebei>();
//		List<Shebei> listShebei = shebeiDao.allShebeiList();
//		List<TeacherOrdersItem> listTeacherOrdersItem = teacherOrdersItemDao
//				.allTeacherOrdersItemByOrderId(id);
//		for (int i = 0; i < listShebei.size(); i++) {
//			for (int j = 0; j < listTeacherOrdersItem.size(); j++) {
//				if (listShebei.get(i).getId() == listTeacherOrdersItem.get(j)
//						.getShebeiId()) {
//					listShebei.get(i).setCheck(1);
//					listShebeiCheck.add(listShebei.get(i));
//					break;
//				}
//			}
//		}
		List<TeacherOrdersItem> listTeacherOrdersItem = teacherOrdersItemDao.allTeacherOrdersItemByOrderId(id);
		modelMap.addAttribute("teacherOrders", teacherOrders);
		modelMap.addAttribute("studentOrders", studentOrders);
		modelMap.addAttribute("listShebei", listTeacherOrdersItem);
		modelMap.addAttribute("studentOrderState", Constant.STUDENTORDERSTATE);
		modelMap.addAttribute("title", "中国传媒大学-学生借用");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-学生借用");
		modelMap.addAttribute("seo_desc", "中国传媒大学-学生借用");
		return "studentorder/in";
	}

	/**
	 * 查看申请页
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还未登录");
			return "tips";
		}
		String id = RequestUtils.getString(request, "id", null);
		String myid = RequestUtils.getString(request, "myid", null);
		TeacherOrders teacherOrders = teacherOrdersDao.getTeacherOrdersById(id);
		StudentOrders studentOrders = studentOrdersDao
				.getStudentOrdersById(myid);
//		List<Shebei> listShebeiCheck = new ArrayList<Shebei>();
//		List<Shebei> listShebei = shebeiDao.allShebeiList();
//		for (int i = 0; i < listShebei.size(); i++) {
//			for (int j = 0; j < listTeacherOrdersItem.size(); j++) {
//				if (listShebei.get(i).getId() == listTeacherOrdersItem.get(j)
//						.getShebeiId()) {
//					listShebei.get(i).setCheck(1);
//					listShebeiCheck.add(listShebei.get(i));
//					break;
//				}
//			}
//		}
//		SHEBEITYPE = new TreeMap<String, String>();
//		List<ShebeiType> listtype = shebeiTypeDao.allShebeiType();
//		for (int i = 0; i < listtype.size(); i++) {
//			SHEBEITYPE.put(listtype.get(i).getId() + "", listtype.get(i)
//					.getName());
//		}
		List<TeacherOrdersItem> listTeacherOrdersItem = teacherOrdersItemDao
				.allTeacherOrdersItemByOrderId(id);
		modelMap.addAttribute("teacherOrders", teacherOrders);
		modelMap.addAttribute("studentOrders", studentOrders);
		modelMap.addAttribute("listShebei", listTeacherOrdersItem);
		modelMap.addAttribute("studentOrderState", Constant.STUDENTORDERSTATE);
		modelMap.addAttribute("title", "中国传媒大学-学生借用");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-学生借用");
		modelMap.addAttribute("seo_desc", "中国传媒大学-学生借用");
		return "studentorder/view";
	}

	/**
	 * 下订单
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doIn")
	public String doIn(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还未登录");
			return "tips";
		}
		String id = RequestUtils.getString(request, "id", null);
		String sid = RequestUtils.getString(request, "sid", null);
		String remark = RequestUtils.getString(request, "remark", null);
		String sTime = RequestUtils.getString(request, "sTime", null);
		String eTime = RequestUtils.getString(request, "eTime", null);
		if (!ValidateUtil.validParam(sTime, eTime)) {
			modelMap.addAttribute("info", "开始时间和结束时间不能为null");
			return "tips";
		}

		Date s = DateUtil.str2Date(sTime, "yyyy-MM-dd HH:mm:ss");
		Date e = DateUtil.str2Date(eTime, "yyyy-MM-dd HH:mm:ss");

		TeacherOrders to = teacherOrdersDao.getTeacherOrdersById(id);
		if (s.after(to.getsDate()) && e.before(to.geteDate())) {
			// 新增
			if (!StringUtils.isEmpty(sid)) {
				StudentOrders so = studentOrdersDao.getStudentOrdersById(sid);
				so.setRemark(remark);
				so.setsDate(s);
				so.seteDate(e);
				so.setState(0);
				so.setUserId(code);
				studentOrdersDao.updateStudentOrders(so);
				modelMap.addAttribute("info", "修改申请已提交");
			}else{
				StudentOrders so = new StudentOrders();
				so.setOrderId(id);
				so.setRemark(remark);
				so.setsDate(s);
				so.seteDate(e);
				so.setUserId(code);
				studentOrdersDao.saveStudentOrders(so);
				modelMap.addAttribute("info", "申请已提交");
			}
		} else {
			modelMap.addAttribute("info", "开始时间和结束时间需要在老师借用时间之间");
			return "tips";
		}
		return mylist(request, modelMap);
	}

}
