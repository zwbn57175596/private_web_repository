<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<meta name="keywords" content="${seo_keywords}">
<meta name="description" content="${seo_desc}">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
<script charset="utf-8" src="/resources/js/jquery-1.5.min.js"></script>
<script charset="utf-8" src="/resources/js/tr.js"></script>
</head>
<body>
	<div class="manage-container">
		<jsp:include page="../include/headback.jsp" flush="true"></jsp:include>
		<div class="manage_banner">
			<img src="/resources/images/manage_banner.png" width="995px"
				height="234px;" />
		</div>
		<div style="clear: both"></div>
		<jsp:include page="../include/navigate.jsp" flush="true"></jsp:include>
		<div class="manage_right">
			<div class="manage_breadcrumb">当前位置：首页 > 个人资料</div>
							<table class="table_border" width="500px">
								<tbody>
									<tr>
										<td>ID</td>
										<td>${user.code}</td>
									</tr>
									<tr>
										<td>姓名</td>
										<td>${user.name}</td>
									</tr>
									<tr>
										<td>性别</td>
										<td><c:if test="${user.gender==0}">男</c:if> <c:if
												test="${user.gender==1}">女</c:if></td>
									</tr>
									<tr>
										<td>班级</td>
										<td><c:if test="${user.uclass=='0'}">无</c:if> <c:if
												test="${user.uclass!='0'}">${user.uclass}</c:if></td>
									</tr>
									<tr>
										<td>最后更新时间</td>
										<td><fmt:formatDate value="${user.createTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
									</tr>

									<tr>
										<td>角色</td>
										<td>${role.roleName }</td>
									</tr>
								</tbody>
							</table>
		</div>
	</div>
</body>
</html>