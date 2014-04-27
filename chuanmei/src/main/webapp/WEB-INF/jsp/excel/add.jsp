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
			<div class="manage_breadcrumb">当前位置：首页 > 增加用户</div>
			<form action="/excel/update" method="post">
				<table border="0" cellspacing="0" cellpadding="0" width="500px">
					<tbody>
						<tr>
							<td>类别</td>
							<td><select name="roleId">
									<c:forEach items="${exportType}" var="entry">
										<option <c:if test="${entry.key==roleId}"> selected </c:if>
											value="${entry.key}">${entry.value}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>ID</td>
							<td><input type="text" name="code" value="${user.code}"><font
								color="red">*</font></td>
						</tr>
						<tr>
							<td>名称</td>
							<td><input type="text" name="name" value="${user.name}"><font
								color="red">*</font></td>
						</tr>
						<tr>
							<td>密码</td>
							<td><input type="text" name="pwd" value="${user.pwd}"><font
								color="red">*</font></td>
						</tr>
						<tr>
							<td>性别</td>
							<td><select name="gender">
									<c:forEach items="${gender}" var="entry">
										<option
											<c:if test="${entry.key==user.gender}"> selected </c:if>
											value="${entry.key}">${entry.value}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>班级</td>
							<td><input type="text" name="uclass" value="${user.uclass}"><font
								color="red">*非学生直接填0</font></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="提交" /></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>
</html>