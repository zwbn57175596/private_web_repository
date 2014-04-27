<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<meta name="keywords" content="${seo_keywords}">
<meta name="description" content="${seo_desc}">
<link href="/resources/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="container">
  <jsp:include page="../include/head2.jsp"></jsp:include>
  <div class="main-device-left">
  	<img src="/resources/images/device2_left_pic.png" />
    <h2 class="left-head">设备分类</h2>
    <div class="left-body left-logo"> 
    	<ul>
    		<c:forEach items="${listtype}" var="row">
        	<li><a href="/shebei/flist/${row.id}">${row.name}</a></li>
        	</c:forEach>
        </ul>
    </div>
  </div>
  <div class="main-device-right">
    <div class="breadcrumb">当前位置：首页> 设备动态> ${naviName}</div>
    <div class="right-body">
    	<ul>
    	<c:forEach items="${list}" var="row">
        	<li><a href="/shebei/show/${row.id}"><img src="${row.imgUrl}"/></a><div><a href="/shebei/show/${row.id}">${row.name}</a></div></li>
        	</c:forEach>
		</ul>
    </div>
  </div>
  <div style="clear:both"></div>
 <jsp:include page="../include/foot.jsp"></jsp:include>
</div>
</body>
</html>
