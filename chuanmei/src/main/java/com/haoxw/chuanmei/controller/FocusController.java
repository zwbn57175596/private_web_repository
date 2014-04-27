package com.haoxw.chuanmei.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.haoxw.chuanmei.dao.FocusDao;
import com.haoxw.chuanmei.model.Focus;
import com.haoxw.chuanmei.util.CookiesUtil;
import com.haoxw.chuanmei.util.PhotoUtil;
import com.haoxw.chuanmei.util.RequestUtils;
import com.haoxw.chuanmei.util.ValidateUtil;

@Controller
@RequestMapping("/focus")
public class FocusController {
	private static Logger logger = LoggerFactory.getLogger(FocusController.class);
	@Resource
	private FocusDao focusDao;
	
	/**
	 * 焦点图列表
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
		if(StringUtils.isEmpty(code)){
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		List<Focus> list = focusDao.allFocusList();
		modelMap.addAttribute("title", "中国传媒大学-焦点图");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-焦点图");
		modelMap.addAttribute("seo_desc", "中国传媒大学-焦点图");
		modelMap.addAttribute("list", list);
		return "focus/list";
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
		if(StringUtils.isEmpty(code)){
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		int id = RequestUtils.getInt(request, "id", 0);
		Focus focus =null;
		if(id==0){
			focus = new Focus(); 
		}else{
			focus = focusDao.findFocus(id);
		}
		modelMap.addAttribute("title", "中国传媒大学-焦点图编辑");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-焦点图编辑");
		modelMap.addAttribute("seo_desc", "中国传媒大学-焦点图编辑");
		modelMap.addAttribute("focus", focus);
		return "focus/update";
	}
	/**
	 * 焦点图上传
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
		String code = cu.getCookieValue(request);
		if(StringUtils.isEmpty(code)){
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		String name = RequestUtils.getString(request, "name", null);
		int id = RequestUtils.getInt(request, "id", 0);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		/** 页面控件的文件流 **/
		MultipartFile multipartFile = multipartRequest.getFile("fileUpload");
		if(!multipartFile.isEmpty()){
			String photoType = ValidateUtil.getTypeByStream(multipartFile.getInputStream());
			if(!StringUtils.isEmpty(photoType)){
				String realPath = request.getSession().getServletContext().getRealPath("/resources/upload/");
				PhotoUtil pu = PhotoUtil.getInstance();
				String filename = pu.uploadImgProdByInputStream(multipartFile.getInputStream(), photoType, 893, 262,realPath);
				Focus focus = new Focus();
				focus.setId(id);
				focus.setCreateTime(new Date());
				focus.setImgUrl(filename);
				focus.setLinkUrl("");
				focus.setName(name);
				focusDao.updateFocus(focus);
			}else{
				modelMap.addAttribute("info", "只支持jpg png gif bmp格式图片");
				return "tips";
			}
			
		}else{
			modelMap.addAttribute("info", "请选择图片");
			return "tips";
		}
		modelMap.addAttribute("title", "中国传媒大学-焦点图");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-焦点图");
		modelMap.addAttribute("seo_desc", "中国传媒大学-焦点图");
		return list(request,modelMap);
	}
	
}
