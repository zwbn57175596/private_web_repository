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
			<div class="manage_breadcrumb">当前位置：首页 > 角色授权编辑</div>
			<form action="/authority/setrolefunclist" method="post">
				<input type="hidden" name="roleId" value="${roleId}"> 功能列表
					<ul>
						<c:forEach items="${listFuncs}" var="row">
							<li><input type="checkbox" name="funcparam"
								value="${row.funcId}"
								<c:if test="${row.parentId>0}">checked</c:if>>${row.funcName}</li>
						</c:forEach>
					</ul> <input type="submit" value="提交" />
			</form>
		</div>
	</div>
</body>
</html>