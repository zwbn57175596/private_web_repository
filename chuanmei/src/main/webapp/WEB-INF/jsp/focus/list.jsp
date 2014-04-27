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
			<div class="manage_breadcrumb">当前位置：首页 > 焦点图列表</div>
			<table class="table_border" width="600px">
				<tbody>
					<tr>
						<th>序号</th>
						<th>名称</th>
						<th>时间</th>
						<th>缩略图</th>
						<th>操作</th>
					</tr>
					<c:if test="${empty list}">
						<tr>
							<td colspan=5>记录为空！</td>
						</tr>
					</c:if>
					<c:forEach items="${list}" var="row">
						<tr>
							<td>${row.id}</td>
							<td>${row.name}</td>
							<td><fmt:formatDate value="${row.createTime}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><img src="${row.imgUrl}" width="150" height="100" /></td>
							<td><a href="/focus/update?id=${row.id}">修改</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>