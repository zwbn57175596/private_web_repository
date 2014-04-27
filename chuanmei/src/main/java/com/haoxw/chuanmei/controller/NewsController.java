package com.haoxw.chuanmei.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haoxw.chuanmei.bean.Constant;
import com.haoxw.chuanmei.dao.NewsDao;
import com.haoxw.chuanmei.model.News;
import com.haoxw.chuanmei.util.CookiesUtil;
import com.haoxw.chuanmei.util.RequestUtils;
import com.haoxw.chuanmei.util.ValidateUtil;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Resource
	private NewsDao newsDao;
	// 一页记录数
	private final static int limit = 10;

	/**
	 * 进入发布或者修改新闻
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/b_update")
	public String update(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if(StringUtils.isEmpty(code)){
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		News news = null;
		int newsId = RequestUtils.getInt(request, "id", 0);
		if (newsId == 0) {
			news = new News();
		} else {
			news = newsDao.getNewsById(newsId);
		}
		modelMap.addAttribute("title", "中国传媒大学-内容编辑");
		modelMap.addAttribute("seo_keywords", "中国传媒大学-内容编辑");
		modelMap.addAttribute("seo_desc", "中国传媒大学-内容编辑");
		modelMap.addAttribute("news", news);
		modelMap.addAttribute("newsType", Constant.NEWSTYPE);

		return "news/b_update";
	}

	
	/**
	 * 进入联系我们或者实验中心界面修改页
	 * @param id
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateContactOrJianjie/{id}")
	public String updateContactOrJianjie(@PathVariable int id,HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if(StringUtils.isEmpty(code)){
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		News news = null;
		if (id == 0) {
			news = new News();
		} else {
			news = newsDao.getNewsById(id);
			if(news==null){
				news = new News();
			}
			news.setId(id);
			news.setTypeNews(0);
		}
		if(id==1){
			modelMap.addAttribute("title", "中国传媒大学-联系我们编辑");
			modelMap.addAttribute("seo_keywords", "中国传媒大学-联系我们编辑");
			modelMap.addAttribute("seo_desc", "中国传媒大学-联系我们编辑");
			modelMap.addAttribute("navi", "联系我们编辑");
		}else if(id==2){
			modelMap.addAttribute("title", "中国传媒大学-实验中心编辑");
			modelMap.addAttribute("seo_keywords", "中国传媒大学-实验中心编辑");
			modelMap.addAttribute("seo_desc", "中国传媒大学-实验中心编辑");
			modelMap.addAttribute("navi", "实验中心编辑");
		}
		
		modelMap.addAttribute("news", news);
		modelMap.addAttribute("newsType", Constant.NEWSTYPE);

		return "news/updateContactOrJianjie";
	}
	
	/**
	 * 执行发布或者修改新闻
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doupdateContactOrJianjie")
	public String doupdateContactOrJianjie(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		News news = null;
		int newsId = RequestUtils.getInt(request, "id", 0);
		String title = RequestUtils.getString(request, "title", null);
		String content = RequestUtils.getString(request, "content", null);
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还未登录");
			return "tips";
		}
		if (!ValidateUtil.validParam(title, content)) {
			modelMap.addAttribute("info", "标题和内容不能为空");
			return "tips";
		}
		if (newsId == 0) {
			news = new News();
			news.setTitle(title);
		} else {
			news = newsDao.getNewsById(newsId);
			news.setTitle(title);
		}
		news.setAddUserId(code);
		news.setContent(content);
		boolean b = newsDao.saveOrUpdateNews(news);
		if (b) {
			modelMap.addAttribute("info", "编辑成功");
			return updateContactOrJianjie(newsId,request,modelMap);
		} else {
			modelMap.addAttribute("title", "中国传媒大学-系统提示");
			modelMap.addAttribute("seo_keywords", "中国传媒大学-系统提示");
			modelMap.addAttribute("seo_desc", "中国传媒大学-系统提示");
			modelMap.addAttribute("info", "提交失败");
			return "tips";
		}
	}
	
	/**
	 * 执行发布或者修改新闻
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doupdate")
	public String doupdate(HttpServletRequest request, ModelMap modelMap)
			throws Exception {
		News news = null;
		int newsId = RequestUtils.getInt(request, "id", 0);
		String title = RequestUtils.getString(request, "title", null);
		String type = RequestUtils.getString(request, "type", null);
		String content = RequestUtils.getString(request, "content", null);
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if (StringUtils.isEmpty(code)) {
			modelMap.addAttribute("info", "您还未登录");
			return "tips";
		}
		if (!ValidateUtil.validParam(title, type, content)) {
			modelMap.addAttribute("info", "标题和内容不能为空");
			return "tips";
		}
		if (newsId == 0) {
			news = new News();
			news.setTitle(title);
			news.setTypeNews(Integer.parseInt(type));
		} else {
			news = newsDao.getNewsById(newsId);
			news.setTitle(title);
			news.setTypeNews(Integer.parseInt(type));
		}
		news.setAddUserId(code);
		news.setContent(content);
		boolean b = newsDao.saveOrUpdateNews(news);
		if (b) {
			return blist(type, request, modelMap);
		} else {
			modelMap.addAttribute("title", "中国传媒大学-系统提示");
			modelMap.addAttribute("seo_keywords", "中国传媒大学-系统提示");
			modelMap.addAttribute("seo_desc", "中国传媒大学-系统提示");
			modelMap.addAttribute("info", "提交失败");
			return "tips";
		}
	}

	/**
	 * 后台新闻列表页
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/b_list/{type}")
	public String blist(@PathVariable String type, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		CookiesUtil cu = CookiesUtil.getInstance();
		String code = cu.getCookieValue(request);
		if(StringUtils.isEmpty(code)){
			modelMap.addAttribute("info", "您还没有登录！");
			return "tips";
		}
		if (StringUtils.isEmpty(Constant.NEWSTYPE.get(type))) {
			modelMap.addAttribute("info", "非法请求");
			return "tips";
		}
		int pageNo = RequestUtils.getInt(request, "pageNo", 1);
		int offset = (pageNo - 1) * limit;
		int count = newsDao.countNews(Integer.parseInt(type));
		int totalPageNo = (count + limit - 1) / limit;
		List<News> list = newsDao.findNewsList(Integer.parseInt(type), offset,
				limit);

		modelMap.addAttribute("title", "中国传媒大学-" + Constant.NEWSTYPE.get(type));
		modelMap.addAttribute("seo_keywords",
				"中国传媒大学-" + Constant.NEWSTYPE.get(type));
		modelMap.addAttribute("seo_desc",
				"中国传媒大学-" + Constant.NEWSTYPE.get(type));

		modelMap.addAttribute("navigateName", Constant.NEWSTYPE.get(type));
		modelMap.addAttribute("type", type);
		modelMap.addAttribute("pageNo", pageNo);
		modelMap.addAttribute("totalPageNo", totalPageNo);
		modelMap.addAttribute("count", count);
		modelMap.addAttribute("list", list);
		return "news/b_list";
	}

	/**
	 * 前台新闻列表页
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/f_list/{type}")
	public String flist(@PathVariable String type, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		if (StringUtils.isEmpty(Constant.NEWSTYPE.get(type))) {
			modelMap.addAttribute("info", "非法请求");
			return "tips";
		}
		int pageNo = RequestUtils.getInt(request, "pageNo", 1);
		int offset = (pageNo - 1) * limit;
		int count = newsDao.countNews(Integer.parseInt(type));
		int totalPageNo = (count + limit - 1) / limit;
		List<News> list = newsDao.findNewsList(Integer.parseInt(type), offset,
				limit);

		modelMap.addAttribute("title", "中国传媒大学-" + Constant.NEWSTYPE.get(type));
		modelMap.addAttribute("seo_keywords",
				"中国传媒大学-" + Constant.NEWSTYPE.get(type));
		modelMap.addAttribute("seo_desc",
				"中国传媒大学-" + Constant.NEWSTYPE.get(type));

		modelMap.addAttribute("navigateName", Constant.NEWSTYPE.get(type));
		modelMap.addAttribute("type", type);
		modelMap.addAttribute("pageNo", pageNo);
		modelMap.addAttribute("totalPageNo", totalPageNo);
		modelMap.addAttribute("count", count);
		modelMap.addAttribute("list", list);
		return "news/f_list";
	}

	/**
	 * 新闻页
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/show/{newId}")
	public String show(@PathVariable int newId, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		News news = newsDao.getNewsById(newId);
		if (news == null) {
			modelMap.addAttribute("info", "非法请求");
			return "tips";
		}
		modelMap.addAttribute("news", news);
		modelMap.addAttribute("navigateName",
				Constant.NEWSTYPE.get(String.valueOf(news.getTypeNews())));
		modelMap.addAttribute("title", "中国传媒大学-" + news.getTitle());
		modelMap.addAttribute("seo_keywords", "中国传媒大学-" + news.getTitle());
		modelMap.addAttribute("seo_desc", "中国传媒大学-" + news.getTitle());
		return "news/f_view";
	}

//	private String htmlspecialchars(String str) {
//		str = str.replaceAll("&", "&amp;");
//		str = str.replaceAll("<", "&lt;");
//		str = str.replaceAll(">", "&gt;");
//		str = str.replaceAll("\"", "&quot;");
//		return str;
//	}
}
