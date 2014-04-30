package com.haoxw.chuanmei.controller2;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haoxw.chuanmei.bean.Constant;
import com.haoxw.chuanmei.dao.ShebeiDao;
import com.haoxw.chuanmei.dao.ShebeiTypeDao;
import com.haoxw.chuanmei.dao.StudentGroupDao;
import com.haoxw.chuanmei.dao.TeacherOrdersDao;
import com.haoxw.chuanmei.dao.TeacherOrdersItemDao;
import com.haoxw.chuanmei.model.Shebei;
import com.haoxw.chuanmei.model.ShebeiType;
import com.haoxw.chuanmei.model.StudentGroup;
import com.haoxw.chuanmei.model.TeacherOrders;
import com.haoxw.chuanmei.model.TeacherOrdersItem;
import com.haoxw.chuanmei.util.CookiesUtil;
import com.haoxw.chuanmei.util.DateUtil;
import com.haoxw.chuanmei.util.RequestUtils;
import com.haoxw.chuanmei.util.ValidateUtil;

@Controller
@RequestMapping("/teacherOrders2")
public class TeacherOrdersController2 {
	private static Logger logger = LoggerFactory
			.getLogger(TeacherOrdersController2.class);
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
	private StudentGroupDao studentGroupDao;
	private Map<String, String> SHEBEITYPE;
	/**
	 * 老师订单列表
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
		int pageNo = RequestUtils.getInt(request, "pageNo", 1);
		int offset = (pageNo - 1) * limit;
		int count = teacherOrdersDao.countTeacherOrders(code);
		int totalPageNo = (count + limit - 1) / limit;
		List<TeacherOrders> listTeacherOrders = teacherOrdersDao
				.findTeacherOrdersList(code, offset, limit);
		modelMap.addAttribute("title", "中国传媒大学-老师借用列表");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-老师借用列表");
		modelMap.addAttribute("seo_desc", "中国传媒大学-老师借用列表");
		modelMap.addAttribute("pageNo", pageNo);
		modelMap.addAttribute("totalPageNo", totalPageNo);
		modelMap.addAttribute("count", count);
		modelMap.addAttribute("list", listTeacherOrders);
		modelMap.addAttribute("teacherOrderState", Constant.TEACHERORDERSTATE);
		return "teacherorder2/list";
	}

	/**
	 * 选设备分类
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
		int sbtype = RequestUtils.getInt(request, "sbtype", 1);
		String id = RequestUtils.getString(request, "id", null);
		
		SHEBEITYPE = new TreeMap<String,String>();
		List<ShebeiType> listtype = shebeiTypeDao.allShebeiType();
		for(int i=0;i<listtype.size();i++){
			SHEBEITYPE.put(listtype.get(i).getId()+"", listtype.get(i).getName());
		}
		
    List<Shebei> listShebei = shebeiDao.allShebeiListByType(sbtype);
    
    List<TeacherOrdersItem> listTeacherOrdersItem = teacherOrdersItemDao
        .allTeacherOrdersItemByOrderId(id);
    for (int i = 0; i < listShebei.size(); i++) {
      for (int j = 0; j < listTeacherOrdersItem.size(); j++) {
        if (listShebei.get(i).getId() == listTeacherOrdersItem.get(j)
            .getShebeiId()) {
          listShebei.get(i).setCheck(1);
          break;
        }
      }
    }
    
    // 分组信息
    List<StudentGroup> group = studentGroupDao.getStudentGroupsByTeacherOrdersId(id);
    
    modelMap.addAttribute("groups", group);
		modelMap.addAttribute("type", SHEBEITYPE);
		modelMap.addAttribute("sbtype", sbtype);
		modelMap.addAttribute("listShebei", listShebei);
		modelMap.addAttribute("title", "中国传媒大学-老师借用");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-老师借用");
		modelMap.addAttribute("seo_desc", "中国传媒大学-老师借用");
		return "teacherorder2/in";
	}

	/**
	 * 进入下订单页
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/in2")
	public void in2(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap)
			throws Exception {
		int sbtype = RequestUtils.getInt(request, "sbtype", 1);
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还未登录");
			return;
		}
//		SHEBEITYPE = new TreeMap<String,String>();
//		List<ShebeiType> listtype = shebeiTypeDao.allShebeiType();
//		for(int i=0;i<listtype.size();i++){
//			SHEBEITYPE.put(listtype.get(i).getId()+"", listtype.get(i).getName());
//		}
		String id = RequestUtils.getString(request, "id", null);
		List<Shebei> listShebei = shebeiDao.allShebeiListByType(sbtype);
    List<TeacherOrdersItem> listTeacherOrdersItem = teacherOrdersItemDao
        .allTeacherOrdersItemByOrderId(id);
    for (int i = 0; i < listShebei.size(); i++) {
      for (int j = 0; j < listTeacherOrdersItem.size(); j++) {
        if (listShebei.get(i).getId() == listTeacherOrdersItem.get(j)
            .getShebeiId()) {
          listShebei.get(i).setCheck(1);
          break;
        }
      }
    }
		response.setContentType("application/json");
		try {
		  ObjectMapper mapper = new ObjectMapper();
		  mapper.writeValue(response.getWriter(), listShebei);
		} catch (IOException e) {
		  logger.error("", e);
		}
//		modelMap.addAttribute("listShebei", listShebei);
//		modelMap.addAttribute("type", SHEBEITYPE);
//		modelMap.addAttribute("sbtype", sbtype);
//		modelMap.addAttribute("title", "中国传媒大学-老师借用");
//		modelMap.addAttribute("seo_keywords", "中国传媒大学-老师借用");
//		modelMap.addAttribute("seo_desc", "中国传媒大学-老师借用");
		return ;
	}
	
	/**
	 * 进入下订单页
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/inupdate")
	public String inupdate(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		int sbtype = RequestUtils.getInt(request, "sbtype", 1);
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还未登录");
			return "tips";
		}
		String id = RequestUtils.getString(request, "id", null);
		TeacherOrders teacherOrders = teacherOrdersDao.getTeacherOrdersById(id);
		List<Shebei> listShebei = shebeiDao.allShebeiListByType(sbtype);
		List<TeacherOrdersItem> listTeacherOrdersItem = teacherOrdersItemDao
				.allTeacherOrdersItemByOrderId(id);
		for (int i = 0; i < listShebei.size(); i++) {
			for (int j = 0; j < listTeacherOrdersItem.size(); j++) {
				if (listShebei.get(i).getId() == listTeacherOrdersItem.get(j)
						.getShebeiId()) {
					listShebei.get(i).setCheck(1);
					break;
				}
			}
		}
		
		SHEBEITYPE = new TreeMap<String,String>();
		List<ShebeiType> listtype = shebeiTypeDao.allShebeiType();
		for(int i=0;i<listtype.size();i++){
			SHEBEITYPE.put(listtype.get(i).getId()+"", listtype.get(i).getName());
		}
		
		// 分组信息
    List<StudentGroup> group = studentGroupDao.getStudentGroupsByTeacherOrdersId(id);
    
    modelMap.addAttribute("groups", group);
    
		modelMap.addAttribute("teacherOrders", teacherOrders);
		modelMap.addAttribute("listShebei", listShebei);
		modelMap.addAttribute("type", SHEBEITYPE);
		modelMap.addAttribute("sbtype", sbtype);
		modelMap.addAttribute("title", "中国传媒大学-老师借用");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-老师借用");
		modelMap.addAttribute("seo_desc", "中国传媒大学-老师借用");
		return "teacherorder2/in";
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
		
		String sTime = RequestUtils.getString(request, "sTime", null);
		String eTime = RequestUtils.getString(request, "eTime", null);
		String studentIds = RequestUtils.getString(request, "studentIds", null);
		String subject = RequestUtils.getString(request, "subject", null);
		String leaveWord = RequestUtils.getString(request, "leaveWord", null);
		int sbtype = RequestUtils.getInt(request, "sbtype", 1);
		String id = RequestUtils.getString(request, "id", null);
		// extends edit by zhaowei
		String className = RequestUtils.getString(request, "className", null);
		int stuNum = RequestUtils.getInt(request, "stuNum", 0);
		String expType = RequestUtils.getString(request, "expType", null);
		int workTime = RequestUtils.getInt(request, "workTime", 1);
		
		String shebeis[] = request.getParameterValues("shebeis");
		if (!ValidateUtil.validParam(sTime, eTime, studentIds, subject,leaveWord, className, 
		    expType)
				|| shebeis==null||shebeis.length == 0) {
			modelMap.addAttribute("info", "最少选择一个设备并且时间,学号,标题,特殊说明均不能为空");
			return "tips";
		}
		// 新增
		String toUuid = null;
		if (StringUtils.isEmpty(id)) {
			TeacherOrders to = new TeacherOrders();
			to.setsDate(DateUtil.str2Date(sTime, "yyyy-MM-dd"));
			to.seteDate(DateUtil.str2Date(eTime, "yyyy-MM-dd"));
			to.setUserId(code);
			to.setLeaveWord(leaveWord);
			to.setState(0);
			to.setStudentIds(studentIds);
			to.setSubject(subject);
			to.setSbTypeId(sbtype);
			to.setClaName(className);
			to.setStuNum(stuNum);
			to.setExpType(expType);
			to.setWorkTime(workTime);
			String uuid = teacherOrdersDao.saveTeacherOrders(to);
			toUuid = uuid;
			if (!StringUtils.isEmpty(uuid)) {
				for (int i = 0; i < shebeis.length; i++) {
					TeacherOrdersItem ti = new TeacherOrdersItem();
					ti.setOrderId(uuid);
					ti.setShebeiId(Integer.parseInt(shebeis[i].toString()));
					teacherOrdersItemDao.saveOrUpdateTeacherOrdersItem(ti);
				}
			}
		} else {
			TeacherOrders to = teacherOrdersDao.getTeacherOrdersById(id);
			to.setsDate(DateUtil.str2Date(sTime, "yyyy-MM-dd"));
			to.seteDate(DateUtil.str2Date(eTime, "yyyy-MM-dd"));
			to.setUserId(code);
			to.setState(0);
			to.setLeaveWord(leaveWord);
			to.setStudentIds(studentIds);
			to.setSubject(subject);
			to.setSbTypeId(sbtype);
			to.setClaName(className);
      to.setStuNum(stuNum);
      to.setExpType(expType);
      to.setWorkTime(workTime);
			boolean b = teacherOrdersDao.updateTeacherOrders(to);
			if (b) {
				boolean b2 = teacherOrdersItemDao.delTeacherOrdersItemByOrderId(id);
				if(b2)
  				for (int i = 0; i < shebeis.length; i++) {
  					TeacherOrdersItem ti = new TeacherOrdersItem();
  					ti.setOrderId(id);
  					ti.setShebeiId(Integer.parseInt(shebeis[i].toString()));
  					teacherOrdersItemDao.saveOrUpdateTeacherOrdersItem(ti);
  				}
			}
		}
		
		//学生分组
    String groupNames[] = request.getParameterValues("group_names");
    String groupLeaders[] = request.getParameterValues("group_leaders");
    String groupMembers[] = request.getParameterValues("group_members");
    if (null != groupNames) {
      System.out.println(groupNames.length + " " + groupLeaders.length + " " + groupMembers.length);
      StudentGroup g;
      for (int i = 0; i < groupNames.length; i++) {
        g = new StudentGroup();
        g.setLeader(groupLeaders[i]);
        g.setMembers(groupMembers[i]);
        g.setName(groupNames[i]);
        g.setTeacherOrdersId(toUuid);
        studentGroupDao.insertOrUpdate(g);
      }
    }
    
		SHEBEITYPE = new TreeMap<String,String>();
		List<ShebeiType> listtype = shebeiTypeDao.allShebeiType();
		for(int i=0;i<listtype.size();i++){
			SHEBEITYPE.put(listtype.get(i).getId()+"", listtype.get(i).getName());
		}
		modelMap.addAttribute("type", SHEBEITYPE);
		modelMap.addAttribute("title", "中国传媒大学-老师下订单");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-老师下订单");
		modelMap.addAttribute("seo_desc", "中国传媒大学-老师下订单");
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
		List<TeacherOrdersItem> listTeacherOrdersItem = teacherOrdersItemDao
				.allTeacherOrdersItemByOrderId(id);
		
	 // 分组信息
    List<StudentGroup> group = studentGroupDao.getStudentGroupsByTeacherOrdersId(id);
    
    modelMap.addAttribute("groups", group);
    
		modelMap.addAttribute("teacherOrders", teacherOrders);
		modelMap.addAttribute("listShebei", listTeacherOrdersItem);
		modelMap.addAttribute("title", "中国传媒大学-查看借用信息页");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-查看借用信息页");
		modelMap.addAttribute("seo_desc", "中国传媒大学-查看借用信息页");
		return "teacherorder2/view";
	}
}
