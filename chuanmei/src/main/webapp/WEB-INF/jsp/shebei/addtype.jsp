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
			<div class="manage_breadcrumb">当前位置：首页 > 设备分类编辑</div>
			<form action="/shebei/doaddtype" method="post">
				<input type="hidden" name="id" value="${shebeiType.id}">
				<table class="table_border" width="500px">
					<tbody>
						<tr>
							<td>名称</td>
							<td><input type="text" name="name"
								value="${shebeiType.name}"></td>
						</tr>

						<tr>
							<td colspan="2" align="center"><input type="submit" value="提交" /></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>
</html>