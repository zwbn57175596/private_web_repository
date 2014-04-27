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
  <div class="breadcrumb">当前位置：首页 > 设备动态 > 设备介绍 </div>
  <div class="middle-left">
  	<img width="400px" height="400px" class="device-img" src="${shebei.imgUrl}" />
  </div>
  <div class="middle-right"> 
  	<div class="breadcrumb device ft16">${shebei.name}</div>
    <hr/>
    <div class="device-content">
    	${shebei.remark}
    </div>
  </div>
  <div style="clear:both"></div>
  <jsp:include page="../include/foot.jsp"></jsp:include>
</div>
</body>
</html>
