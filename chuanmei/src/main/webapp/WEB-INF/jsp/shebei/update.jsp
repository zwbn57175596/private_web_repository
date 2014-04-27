<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<div class="manage_breadcrumb">当前位置：首页 > 设备编辑</div>
			<form action="/shebei/upload" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="id" value="${shebei.id}">
				<table class="table_border" width="700px">
					<tbody>
						<tr>
							<td>ID</td>
							<td><input type="text" name="code" value="${shebei.code}">
							</td>
						</tr>
						<tr>
							<td>分类</td>
							<td><select name="type">
									<c:forEach items="${type}" var="entry">
										<option
											<c:if test="${entry.key==shebei.type}"> selected </c:if>
											value="${entry.key}">${entry.value}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>名称</td>
							<td><input type="text" name="name" value="${shebei.name}">
							</td>
						</tr>
						<tr>
							<td>描述</td>
							<td><textarea name="remark" cols="80" rows="3">${shebei.remark}</textarea></td>
						</tr>
						<tr>
							<td>图片</td>
							<td><input type="file" name="fileUpload" />建议尺寸400px*400px
								<c:if test="${not empty shebei.imgUrl}">
									<tr>
										<td>原文件</td>
										<td><img src="${shebei.imgUrl}" width="400" height="400">
										</td>
									</tr>
								</c:if></td>
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