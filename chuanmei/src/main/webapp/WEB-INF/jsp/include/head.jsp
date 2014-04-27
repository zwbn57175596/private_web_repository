<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="head">
	<h1 class="logo">
		<img src="/resources/images/logo.gif" alt="中国传媒大学艺术学部网上预约系统">
		中国传媒大学艺术学部网上预约系统
	</h1>
	<div class="login">
		<c:if test="${not empty login_flag }">  
				欢迎： ${login_flag}登录系统     &nbsp; &nbsp; &nbsp; &nbsp;<a
				href="/user/logout">退出</a>
		</c:if>
		<c:if test="${empty login_flag }">
			<form action="/user/login" method="post">
				设备预约&nbsp;&nbsp;ID:<input type="text" name="code" size="21">
				&nbsp;&nbsp;密码:<input type="password" name="pwd" size="21">
				&nbsp;&nbsp;<input class="login_button" type="submit" value="登录">
			</form>
		</c:if>
	</div>
	<div style="clear: both"></div>
	<ul>
		<li><a href="/user/index">首页</a></li>
		<li><a href="/user/syzx">实验中心</a></li>
		<li><a href="/shebei/flist/1">设备动态</a></li>
		<li><a href="/news/f_list/3">维护知识</a></li>
		<li><a href="/user/contact">联系我们</a></li>
	</ul>
	<div style="clear: both"></div>
</div>