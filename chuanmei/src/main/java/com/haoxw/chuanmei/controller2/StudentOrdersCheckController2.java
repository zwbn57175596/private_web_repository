package com.haoxw.chuanmei.controller2;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.haoxw.chuanmei.dao.TypeUserDao;
import com.haoxw.chuanmei.dao.UserRoleDao;
import com.haoxw.chuanmei.model.StudentOrders;
import com.haoxw.chuanmei.model.TeacherOrders;
import com.haoxw.chuanmei.model.TeacherOrdersItem;
import com.haoxw.chuanmei.util.CookiesUtil;
import com.haoxw.chuanmei.util.RequestUtils;

@Controller
@RequestMapping("/studentOrdersCheck2")
public class StudentOrdersCheckController2 {
	private static Logger logger = LoggerFactory
			.getLogger(StudentOrdersCheckController2.class);
	// 一页记录数
	private final static int limit = 10;
	@Resource
	private TeacherOrdersDao teacherOrdersDao;
	@Resource
	private StudentOrdersDao studentOrdersDao;
	@Resource
	private UserRoleDao userRoleDao;
	@Resource
	private TeacherOrdersItemDao teacherOrdersItemDao;
	@Resource
	private ShebeiDao shebeiDao;
	@Resource
	private ShebeiTypeDao shebeiTypeDao;
	@Resource
	private TypeUserDao typeUserDao;
	private Map<String, String> SHEBEITYPE;

	/**
	 * 待审核学生订单列表
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
		int roleId = userRoleDao.findUserRoleByUserId(code).getRoleId();
		if(roleId!=4){
			modelMap.addAttribute("info", "只有审核员才有该权限");
			return "tips";
		}
		int sbTypeId = typeUserDao.findSbTypeIdByUserId(code);
		int pageNo = RequestUtils.getInt(request, "pageNo", 1);
		int offset = (pageNo - 1) * limit;
		int count = studentOrdersDao.countAllStudentOrders(sbTypeId);
		int totalPageNo = (count + limit - 1) / limit;
		List<StudentOrders> listStudentOrders = studentOrdersDao
				.findAllStudentOrdersList(sbTypeId,offset, limit);
		modelMap.addAttribute("title", "中国传媒大学-审核学生借用列表");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-审核学生借用列表");
		modelMap.addAttribute("seo_desc", "中国传媒大学-审核学生借用列表");
		modelMap.addAttribute("pageNo", pageNo);
		modelMap.addAttribute("totalPageNo", totalPageNo);
		modelMap.addAttribute("count", count);
		modelMap.addAttribute("list", listStudentOrders);
		modelMap.addAttribute("studentOrderState", Constant.STUDENTORDERSTATE);
		return "check/studentList";
	}

	/**
	 * 进入审核页面
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/incheck")
	public String incheck(HttpServletRequest request, ModelMap modelMap)
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
		List<TeacherOrdersItem> listTeacherOrdersItem = teacherOrdersItemDao
				.allTeacherOrdersItemByOrderId(id);
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
		modelMap.addAttribute("teacherOrders", teacherOrders);
		modelMap.addAttribute("studentOrders", studentOrders);
		modelMap.addAttribute("listShebei", listTeacherOrdersItem);
		modelMap.addAttribute("title", "中国传媒大学-审核老师借用详情页");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-审核老师借用详情页");
		modelMap.addAttribute("seo_desc", "中国传媒大学-审核老师借用详情页");
		modelMap.addAttribute("studentOrderState", Constant.STUDENTORDERSTATE);
		return "check/checkStudent";
	}

	/**
	 * 审核
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doCheck")
	public String doCheck(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还未登录");
			return "tips";
		}
		int studentOrderState = RequestUtils.getInt(request,
				"studentOrderState", 0);
		String id = RequestUtils.getString(request, "id", null);
		String remark = RequestUtils.getString(request, "remark", "无");

		StudentOrders so = studentOrdersDao.getStudentOrdersById(id);
		if (studentOrderState == so.getState()) {
			modelMap.addAttribute("info", "订单状态未改变,请修改订单状态再提交");
			return "tips";
		}
		so.setState(studentOrderState);
		//如果置为已归还 则不红色显示
		if (studentOrderState == 3) {
			so.setIsRed(0);
		}
		so.setCheckUserId(code);
		so.setRemark(remark);
		so.setUpdateDate(new Date());
		boolean b = studentOrdersDao.updateStudentOrders(so);
		if (b) {
//			if (studentOrderState == 2) {
//				// 将订单中设备置为被借用状态
//				List<TeacherOrdersItem> listTeacherOrdersItem = teacherOrdersItemDao
//						.allTeacherOrdersItemByOrderId(so.getOrderId());
//				for (TeacherOrdersItem ti : listTeacherOrdersItem) {
//					Shebei shebei = shebeiDao.findshebei(ti.getShebeiId());
//					shebei.setState(1);
//					shebeiDao.updateShebei(shebei);
//				}
//			}
//			if (studentOrderState == 3) {
//				// 将订单中设备置为可借用状态
//				List<TeacherOrdersItem> listTeacherOrdersItem = teacherOrdersItemDao
//						.allTeacherOrdersItemByOrderId(so.getOrderId());
//				for (TeacherOrdersItem ti : listTeacherOrdersItem) {
//					Shebei shebei = shebeiDao.findshebei(ti.getShebeiId());
//					shebei.setState(0);
//					shebeiDao.updateShebei(shebei);
//				}
//			}
			modelMap.addAttribute("info", "状态修改成功");
		} else {
			modelMap.addAttribute("info", "状态修改失败");
		}
		return list(request, modelMap);
	}

}
