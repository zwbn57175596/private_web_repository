package com.haoxw.chuanmei.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.haoxw.chuanmei.dao.ShebeiDao;
import com.haoxw.chuanmei.dao.ShebeiTypeDao;
import com.haoxw.chuanmei.model.Shebei;
import com.haoxw.chuanmei.model.ShebeiType;
import com.haoxw.chuanmei.util.CookiesUtil;
import com.haoxw.chuanmei.util.PhotoUtil;
import com.haoxw.chuanmei.util.RequestUtils;
import com.haoxw.chuanmei.util.ValidateUtil;

@Controller
@RequestMapping("/shebei")
public class ShebeiController {
	private static Logger logger = LoggerFactory
			.getLogger(ShebeiController.class);
	@Resource
	private ShebeiDao shebeiDao;
	@Resource
	private ShebeiTypeDao shebeiTypeDao;
	private Map<String, String> SHEBEITYPE;
	
	/**
	 * 前台 设备列表
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/flist/{typeId}")
	public String flist(@PathVariable int typeId,HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		List<Shebei> list = shebeiDao.allShebeiListByTypeFront(typeId);
		SHEBEITYPE = new TreeMap<String,String>();
		List<ShebeiType> listtype = shebeiTypeDao.allShebeiType();
		String  naviName = "";
		for(ShebeiType st:listtype){
			if(typeId==st.getId()){
				naviName = st.getName();
				break;
			}
		}
		
		modelMap.addAttribute("title", "中国传媒大学-设备列表-"+naviName);
		modelMap.addAttribute("seo_keywords", "中国传媒大学-设备列表-"+naviName);
		modelMap.addAttribute("seo_desc", "中国传媒大学-设备列表-"+naviName);
		modelMap.addAttribute("list", list);
		modelMap.addAttribute("naviName", naviName);
		modelMap.addAttribute("listtype", listtype);
		return "shebei/flist";
	}
	/**
	 * 前台 设备查看
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/show/{id}")
	public String show(@PathVariable int id,HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		Shebei shebei = shebeiDao.findshebei(id);
		if (shebei == null) {
			modelMap.addAttribute("info", "该设备不存在");
			return "tips";
		} else {
			modelMap.addAttribute("shebei", shebei);
			return "shebei/show";
		}
	}
	/**
	 * 设备列表 后台
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
		List<Shebei> list = shebeiDao.allShebeiList();
		SHEBEITYPE = new TreeMap<String,String>();
		List<ShebeiType> listtype = shebeiTypeDao.allShebeiType();
		for(int i=0;i<listtype.size();i++){
			SHEBEITYPE.put(listtype.get(i).getId()+"", listtype.get(i).getName());
		}
		modelMap.addAttribute("title", "中国传媒大学-设备列表");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-设备列表");
		modelMap.addAttribute("seo_desc", "中国传媒大学-设备列表");
		modelMap.addAttribute("list", list);
		modelMap.addAttribute("type", SHEBEITYPE);
		return "shebei/list";
	}

	/**
	 * 进入增加或者修改
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
		int id = RequestUtils.getInt(request, "id", 0);
		Shebei shebei = null;
		if (id == 0) {
			shebei = new Shebei();
		} else {
			shebei = shebeiDao.findshebei(id);
		}
		SHEBEITYPE = new TreeMap<String,String>();
		List<ShebeiType> listtype = shebeiTypeDao.allShebeiType();
		for(int i=0;i<listtype.size();i++){
			SHEBEITYPE.put(listtype.get(i).getId()+"", listtype.get(i).getName());
		}
		modelMap.addAttribute("title", "中国传媒大学-设备编辑");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-设备编辑");
		modelMap.addAttribute("seo_desc", "中国传媒大学-设备编辑");
		modelMap.addAttribute("type", SHEBEITYPE);
		modelMap.addAttribute("shebei", shebei);
		return "shebei/update";
	}

	/**
	 * 上传
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload")
	public String upload(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String codeUser = cu.getCookieValue(request);
		if (StringUtils.isEmpty(codeUser)) {
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		String name = RequestUtils.getString(request, "name", "");
		String code = RequestUtils.getString(request, "code", "");
		String type = RequestUtils.getString(request, "type", "");
		String remark = RequestUtils.getString(request, "remark", "");
		if (!ValidateUtil.validParam(name, code,remark)) {
			modelMap.addAttribute("info", "ID,名称,描述均不能为空");
			return "tips";
		}
		int id = RequestUtils.getInt(request, "id", 0);
		Shebei shebei = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		/** 页面控件的文件流 **/
		MultipartFile multipartFile = multipartRequest.getFile("fileUpload");
		if (!multipartFile.isEmpty()) {
			String photoType = ValidateUtil.getTypeByStream(multipartFile
					.getInputStream());
			if (!StringUtils.isEmpty(photoType)) {
				String realPath = request.getSession().getServletContext()
						.getRealPath("/resources/upload/");
				PhotoUtil pu = PhotoUtil.getInstance();
				String filename = pu.uploadImgProdByInputStream(
						multipartFile.getInputStream(), photoType, 400, 400,
						realPath);
				if (id == 0) {
					shebei = new Shebei();
					shebei.setCode(code);
					shebei.setcDate(new Date());
					shebei.setImgUrl(filename);
					shebei.setName(name);
					shebei.setNum(1);
					shebei.setNowNum(1);
					shebei.setType(Integer.parseInt(type));
					shebei.setRemark(remark);
				} else {
					shebei = shebeiDao.findshebei(id);
					shebei.setImgUrl(filename);
					shebei.setName(name);
					shebei.setCode(code);
					shebei.setNum(1);
					shebei.setType(Integer.parseInt(type));
					shebei.setRemark(remark);
				}
				shebeiDao.updateShebei(shebei);
			} else {
				modelMap.addAttribute("info", "只支持jpg png gif bmp格式图片");
				return "tips";
			}

		} else {
			if (id > 0) {
				shebei = shebeiDao.findshebei(id);
				shebei.setName(name);
				shebei.setCode(code);
				shebei.setNum(1);
				shebei.setType(Integer.parseInt(type));
				shebei.setRemark(remark);
				shebeiDao.updateShebei(shebei);
			} else {
				modelMap.addAttribute("info", "请选择图片");
				return "tips";
			}

		}
		return list(request, modelMap);
	}

	/**
	 * del
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
		int id = RequestUtils.getInt(request, "id", 0);
		Shebei shebei = shebeiDao.findshebei(id);
		if (shebei == null) {
			modelMap.addAttribute("info", "该设备不存在");
			return "tips";
		} else {
			shebei.setState(-1);
			shebeiDao.updateShebei(shebei);
			modelMap.addAttribute("info", "删除成功");
			return list(request, modelMap);
		}
	}

	/**
	 * 进入添加或者修改设备分类
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/inaddtype")
	public String inaddtype(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		int id = RequestUtils.getInt(request, "id", 0);
		ShebeiType shebeiType = shebeiTypeDao.getShebeiTypeById(id);
		if (shebeiType == null) {
			shebeiType = new ShebeiType();
		} else {
			modelMap.addAttribute("shebeiType", shebeiType);
		}
		modelMap.addAttribute("title", "中国传媒大学-设备分类编辑");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-设备分类编辑");
		modelMap.addAttribute("seo_desc", "中国传媒大学-设备分类编辑");
		return "shebei/addtype";
	}

	/**
	 * 进入添加或者修改设备分类
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doaddtype")
	public String doaddtype(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		int id = RequestUtils.getInt(request, "id", 0);
		String name = RequestUtils.getString(request, "name", null);
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		if (StringUtils.isEmpty(name)) {
			modelMap.addAttribute("info", "名称不能为空");
			return "tips";
		}
		ShebeiType shebeiType = null;
		if (id > 0) {
			shebeiType = shebeiTypeDao.getShebeiTypeById(id);
			shebeiType.setName(name);
			shebeiTypeDao.saveOrUpdateShebeiType(shebeiType);
			modelMap.addAttribute("info", "修改成功");
			return "tips";
		} else {
			shebeiType = new ShebeiType();
			shebeiType.setName(name);
			shebeiTypeDao.saveOrUpdateShebeiType(shebeiType);
			modelMap.addAttribute("info", "添加成功");
			return listtype(request, modelMap);
		}
	}

	/**
	 * 设备分类列表
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listtype")
	public String listtype(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		List<ShebeiType> listShebeiType = shebeiTypeDao.allShebeiType();
		modelMap.addAttribute("title", "中国传媒大学-设备分类列表");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-设备分类列表");
		modelMap.addAttribute("seo_desc", "中国传媒大学-设备分类列表");
		modelMap.addAttribute("listShebeiType", listShebeiType);
		return "shebei/listtype";
	}

	

	/**
	 * del
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deltype")
	public String deltype(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		int id = RequestUtils.getInt(request, "id", 0);
		ShebeiType shebeiType = shebeiTypeDao.getShebeiTypeById(id);
		if (shebeiType == null) {
			modelMap.addAttribute("info", "该设备分类不存在");
			return "tips";
		} else {
			shebeiType.setState(-1);
			shebeiTypeDao.saveOrUpdateShebeiType(shebeiType);
			modelMap.addAttribute("info", "删除成功");
			return list(request, modelMap);
		}
	}
}
