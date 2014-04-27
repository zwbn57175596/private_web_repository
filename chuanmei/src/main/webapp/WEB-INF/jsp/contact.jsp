<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中国传媒大学-联系我们</title>
<meta name="keywords" content="中国传媒大学-联系我们">
<meta name="description" content="中国传媒大学-联系我们">
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
</head>
<body>
	<div class="container">
		<jsp:include page="include/head2.jsp"></jsp:include>
		<div class="main-left">
			<h2 class="left-head">联系方式</h2>
			<div class="left-body left-logo">
				<div class="breadcrumb">联系我们</div>
			</div>
		</div>
		<div class="main-right">
			<img class="banner" width="742px" height="224px"
				src="/resources/images/contactus_banner.png" />
			<div class="breadcrumb">首页> 联系我们</div>
			${news.content}
		</div>
		<div style="clear: both"></div>
		<jsp:include page="include/foot.jsp"></jsp:include>
	</div>
</body>
</html>