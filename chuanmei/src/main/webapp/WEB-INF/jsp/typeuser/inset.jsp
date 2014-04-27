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
			<div class="manage_breadcrumb">当前位置：首页 > 设置审核管理</div>
			<font color="red">${info}</font>
			<table class="table_border" width="500px">
				<tbody>
					<tr>
						<th>序号</th>
						<th>名称</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${listShebeiType}" var="row">
						<tr>
							<td align="center">${row.id}</td>
							<td align="center">${row.name}</td>
							<td align="center"><a href="/typeuser/set/${row.id}">授权</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>