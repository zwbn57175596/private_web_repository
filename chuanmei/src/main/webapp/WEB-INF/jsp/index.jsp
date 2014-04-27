<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中国传媒大学</title>
<meta name="keywords" content="中国传媒大学">
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
<link rel="stylesheet" type="text/css" href="/resources/css/focus.css">
<script type="text/javascript" src="/resources/js/jquery-1.5.min.js" charset="utf-8"></script>
<script type="text/javascript" src="/resources/js/focus.js" charset="utf-8"></script>
</head>
<body>
<div class="container">
	<jsp:include page="include/head.jsp" flush="true"></jsp:include>
	<jsp:include page="include/focus.jsp"></jsp:include>
	
	
	<div style="clear: both"></div>
	<div class="index_content">
	  	<div class="left"><img src="/resources/images/index_content1.png">
        	<h2>实验中心</h2><span class="more"><a href="/news/f_list/1">更多</a></span>
            <ul>
            	<c:forEach items="${listNews1}" var="row">
													<li><a href="/news/show/${row.id}" target="_blank"
															title="${row.title}" class="c6551">${fn:substring(row.title,
																0, 15)} </a></li>
												</c:forEach>
            </ul>
        </div>
    <div class="right">
        	<h2>设备动态</h2><span class="more"><a href="/news/f_list/2">更多</a></span>
            <ul>
            	<c:forEach items="${listNews2}" var="row">
													<li><a href="/news/show/${row.id}" target="_blank"
															title="${row.title}" class="c6551">${fn:substring(row.title,
																0, 15)} </a></li>
												</c:forEach>
            </ul>
        </div>
    <div class="right"><h2>维护知识</h2><span class="more"><a href="/news/f_list/3">更多</a></span>
        	<ul>
            	<c:forEach items="${listNews3}" var="row">
													<li><a href="/news/show/${row.id}" target="_blank"
															title="${row.title}" class="c6551">${fn:substring(row.title,
																0, 15)} </a></li>
												</c:forEach>
            </ul>
        </div>
  </div>
  <jsp:include page="include/footindex.jsp"></jsp:include>
  </div>
</body>
</html>