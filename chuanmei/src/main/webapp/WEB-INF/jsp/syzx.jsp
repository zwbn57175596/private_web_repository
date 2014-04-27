<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中国传媒大学-实验中心</title>
<meta name="keywords" content="中国传媒大学-实验中心">
<meta name="description" content="中国传媒大学-实验中心">
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
</head>
<body>
	<div class="container">
		<jsp:include page="include/head2.jsp"></jsp:include>
		<div class="main-left">
			<h2 class="left-head">实验中心</h2>
			<div class="left-body left-logo">
				<div class="breadcrumb exper_bread">中国传媒大学中心简介</div>
			</div>
		</div>
		<div class="main-right">
			<img class="banner" width="742px" height="224px"
				src="/resources/images/exper_banner.png" />
			<div class="breadcrumb">当前位置：首页> 实验中心简介</div>
			<div class="right-body exper-content">${news.content }</div>
		</div>
		<div style="clear: both"></div>
		<jsp:include page="include/foot.jsp"></jsp:include>
	</div>
</body>