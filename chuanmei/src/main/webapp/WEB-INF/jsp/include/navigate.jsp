<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty login_flag }">  
<div class="manage_left">
			<div class="left_head"><b>菜单列表</b></div>
			<ul>
			<c:forEach items="${myFuncs}" var="row">
				<li><a href="${row.url}">${row.funcName}</a></li>
				</c:forEach>
			</ul>
		</div>

</c:if>
<c:if test="${empty login_flag }"> 
<img align="middle" width="168" height="300" src="/resources/img/navi.jpg"/>
</c:if>