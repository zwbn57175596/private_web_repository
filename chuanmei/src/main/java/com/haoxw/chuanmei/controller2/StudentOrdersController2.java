package com.haoxw.chuanmei.controller2;

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
import com.haoxw.chuanmei.dao.ShebeiOrderDao;
import com.haoxw.chuanmei.dao.ShebeiTypeDao;
import com.haoxw.chuanmei.dao.StudentOrdersDao;
import com.haoxw.chuanmei.dao.TeacherOrdersDao;
import com.haoxw.chuanmei.dao.TeacherOrdersItemDao;
import com.haoxw.chuanmei.model.ShebeiOrder;
import com.haoxw.chuanmei.model.StudentOrders;
import com.haoxw.chuanmei.model.TeacherOrders;
import com.haoxw.chuanmei.model.TeacherOrdersItem;
import com.haoxw.chuanmei.util.CookiesUtil;
import com.haoxw.chuanmei.util.DateUtil;
import com.haoxw.chuanmei.util.ParamUtils;
import com.haoxw.chuanmei.util.RequestUtils;

@Controller
@RequestMapping("/studentOrders2")
public class StudentOrdersController2 {
	@SuppressWarnings("unused")
  private static Logger logger = LoggerFactory
			.getLogger(StudentOrdersController2.class);
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
	@Resource
	private ShebeiOrderDao shebeiOrderDao;
	@SuppressWarnings("unused")
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
		return "studentorder2/list";
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
		return "studentorder2/mylist";
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
		if (null != studentOrders) {
		  studentOrders.setShebeiOrders(shebeiOrderDao.listShebeiOrderByStudentOrdersId(studentOrders.getId()));
		}
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
		for (TeacherOrdersItem i : listTeacherOrdersItem) {
      i.setShebeiOrderList(shebeiOrderDao.listShebeiOrderByShebeiId(i.getShebeiId()));
    }
		
		modelMap.addAttribute("teacherOrders", teacherOrders);
		modelMap.addAttribute("studentOrders", studentOrders);
		modelMap.addAttribute("listShebei", listTeacherOrdersItem);
		modelMap.addAttribute("studentOrderState", Constant.STUDENTORDERSTATE);
		modelMap.addAttribute("title", "中国传媒大学-学生借用");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-学生借用");
		modelMap.addAttribute("seo_desc", "中国传媒大学-学生借用");
		return "studentorder2/in";
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
//		List<TeacherOrdersItem> listTeacherOrdersItem = teacherOrdersItemDao
//				.allTeacherOrdersItemByOrderId(id);
		List<ShebeiOrder> shebeiOrderList = shebeiOrderDao.listShebeiOrderByStudentOrdersId(studentOrders.getId());
		modelMap.addAttribute("teacherOrders", teacherOrders);
		modelMap.addAttribute("studentOrders", studentOrders);
		modelMap.addAttribute("listShebei", shebeiOrderList);
		modelMap.addAttribute("studentOrderState", Constant.STUDENTORDERSTATE);
		modelMap.addAttribute("title", "中国传媒大学-学生借用");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-学生借用");
		modelMap.addAttribute("seo_desc", "中国传媒大学-学生借用");
		return "studentorder2/view";
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
//		String sTime = RequestUtils.getString(request, "sTime", null);
//		String eTime = RequestUtils.getString(request, "eTime", null);

//		Date s = DateUtil.str2Date(sTime, "yyyy-MM-dd");
//		Date e = DateUtil.str2Date(eTime, "yyyy-MM-dd");
		String shebeis[] = request.getParameterValues("shebeis");
		String sTimes[] = request.getParameterValues("sTime");

    if (null == sTimes || null == shebeis) {
      modelMap.addAttribute("info", "请至少提交一个设备的预约");
      return "tips";
    }
    
		// TeacherOrders to = teacherOrdersDao.getTeacherOrdersById(id);
		
		
//		if (s.compareTo(to.getsDate()) >= 0 && e.compareTo(to.geteDate()) <= 0) {
		// 修改
		if (!StringUtils.isEmpty(sid)) {
			StudentOrders so = studentOrdersDao.getStudentOrdersById(sid);
			so.setRemark(remark);
//				so.setsDate(s);
//				so.seteDate(e);
			so.setState(0);
			so.setUserId(code);
			studentOrdersDao.updateStudentOrders(so);
			modelMap.addAttribute("info", "修改申请已提交");
		} else { // 新增
			StudentOrders so = new StudentOrders();
			so.setOrderId(id);
			so.setRemark(remark);
//				so.setsDate(s);
//				so.seteDate(e);
			so.setUserId(code);
			sid = studentOrdersDao.saveStudentOrders(so);
			modelMap.addAttribute("info", "申请已提交");
		}
//		} else {
//			modelMap.addAttribute("info", "开始时间和结束时间需要在老师借用时间之间");
//			return "tips";
//		}
		// 新增sheibeiorders
		ShebeiOrder o = null;
		for (int i = 0; i < shebeis.length; i++) {
      o = new ShebeiOrder();
      o.setShebeiId(ParamUtils.getInt(shebeis[i], 0));
      o.setStudentOrdersId(sid);
      o.setsDate(DateUtil.str2Date(sTimes[i], "yyyy-MM-dd"));
      // o.seteDate(DateUtil.str2Date(eTimes[i], "yyyy-MM-dd"));
      shebeiOrderDao.saveShebeiOrder(o);
    }
		// TODO 修改sheibeiorders
		return mylist(request, modelMap);
	}

}
