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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haoxw.chuanmei.bean.Constant;
import com.haoxw.chuanmei.dao.ShebeiDao;
import com.haoxw.chuanmei.dao.ShebeiTypeDao;
import com.haoxw.chuanmei.dao.TeacherOrdersDao;
import com.haoxw.chuanmei.dao.TeacherOrdersItemDao;
import com.haoxw.chuanmei.dao.TypeUserDao;
import com.haoxw.chuanmei.dao.UserRoleDao;
import com.haoxw.chuanmei.model.Shebei;
import com.haoxw.chuanmei.model.TeacherOrders;
import com.haoxw.chuanmei.model.TeacherOrdersItem;
import com.haoxw.chuanmei.util.CookiesUtil;
import com.haoxw.chuanmei.util.RequestUtils;

@Controller
@RequestMapping("/teacherOrdersCheck2")
public class TeacherOrdersCheckController2 {
	private static Logger logger = LoggerFactory
			.getLogger(TeacherOrdersCheckController2.class);
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
	private UserRoleDao userRoleDao;
	@Resource
	private TypeUserDao typeUserDao;
	private Map<String, String> SHEBEITYPE;
	/**
	 * 列表
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
		int count = teacherOrdersDao.countAllTeacherOrders(sbTypeId);
		int totalPageNo = (count + limit - 1) / limit;
		List<TeacherOrders> listTeacherOrders = teacherOrdersDao
				.findAllTeacherOrdersList(sbTypeId,offset, limit);
		modelMap.addAttribute("title", "中国传媒大学-审核老师借用列表");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-审核老师借用列表");
		modelMap.addAttribute("seo_desc", "中国传媒大学-审核老师借用列表");
		modelMap.addAttribute("pageNo", pageNo);
		modelMap.addAttribute("totalPageNo", totalPageNo);
		modelMap.addAttribute("count", count);
		modelMap.addAttribute("list", listTeacherOrders);
		modelMap.addAttribute("teacherOrderState", Constant.TEACHERORDERSTATE);
		return "check/teacherList";
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
	public String inupdate(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还未登录");
			return "tips";
		}
		String id = RequestUtils.getString(request, "id", null);
		TeacherOrders teacherOrders = teacherOrdersDao.getTeacherOrdersById(id);
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
//		SHEBEITYPE = new TreeMap<String,String>();
//		List<ShebeiType> listtype = shebeiTypeDao.allShebeiType();
//		for(int i=0;i<listtype.size();i++){
//			SHEBEITYPE.put(listtype.get(i).getId()+"", listtype.get(i).getName());
//		}
		modelMap.addAttribute("teacherOrders", teacherOrders);
		modelMap.addAttribute("listShebei", listTeacherOrdersItem);
		modelMap.addAttribute("title", "中国传媒大学-审核老师借用详情页");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-审核老师借用详情页");
		modelMap.addAttribute("seo_desc", "中国传媒大学-审核老师借用详情页");
		modelMap.addAttribute("teacherOrderState", Constant.TEACHERORDERSTATE);
		return "check/checkTeacher";
	}

	/**
	 * 下订单
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
		int teacherOrderState = RequestUtils.getInt(request, "teacherOrderState", 0);
		String id = RequestUtils.getString(request, "id", null);
		String remark = RequestUtils.getString(request, "remark", "无");
		
		TeacherOrders to = teacherOrdersDao.getTeacherOrdersById(id);
		if(teacherOrderState==to.getState()){
			modelMap.addAttribute("info", "订单状态未改变,请修改订单状态再提交");
			return "tips";
		}
		to.setState(teacherOrderState);
		to.setCheckDate(new Date());
		to.setCheckUserId(code);
		to.setRemark(remark);
		boolean b = teacherOrdersDao.updateTeacherOrders(to);
		if (b) {
			if (teacherOrderState == 1) {
				// 将订单中设备置为被借用状态
				List<TeacherOrdersItem> listTeacherOrdersItem = teacherOrdersItemDao
						.allTeacherOrdersItemByOrderId(to.getId());
				for (TeacherOrdersItem ti : listTeacherOrdersItem) {
					Shebei shebei = shebeiDao.findshebei(ti.getShebeiId());
					shebei.setState(1);
					shebeiDao.updateShebei(shebei);
				}
			}
			if (teacherOrderState == 2) {
				// 将订单中设备置为可借用状态
				List<TeacherOrdersItem> listTeacherOrdersItem = teacherOrdersItemDao
						.allTeacherOrdersItemByOrderId(to.getId());
				for (TeacherOrdersItem ti : listTeacherOrdersItem) {
					Shebei shebei = shebeiDao.findshebei(ti.getShebeiId());
					shebei.setState(0);
					shebeiDao.updateShebei(shebei);
				}
			}
			modelMap.addAttribute("info", "状态修改成功");
		}else{
			modelMap.addAttribute("info", "状态修改失败");
		}
		return list(request, modelMap);
	}

	/**
	 * 查看订单信息页
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/show/{id}")
	public String show(@PathVariable String id, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		TeacherOrders teacherOrders = teacherOrdersDao.getTeacherOrdersById(id);
		if (teacherOrders == null) {
			modelMap.addAttribute("info", "非法请求");
			return "tips";
		}
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
//		SHEBEITYPE = new TreeMap<String,String>();
//		List<ShebeiType> listtype = shebeiTypeDao.allShebeiType();
//		for(int i=0;i<listtype.size();i++){
//			SHEBEITYPE.put(listtype.get(i).getId()+"", listtype.get(i).getName());
//		}
//		modelMap.addAttribute("type", SHEBEITYPE);
		modelMap.addAttribute("teacherOrders", teacherOrders);
		modelMap.addAttribute("listShebei", listTeacherOrdersItem);
		modelMap.addAttribute("title", "中国传媒大学-查看借用信息页");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-查看借用信息页");
		modelMap.addAttribute("seo_desc", "中国传媒大学-查看借用信息页");
		return "teacherorder/view";
	}
}
