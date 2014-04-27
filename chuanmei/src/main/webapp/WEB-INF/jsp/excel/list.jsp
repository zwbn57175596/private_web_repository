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
<script type="text/javascript">
	function goUserPage(page) {
		document.getElementById('myform').action = "/excel/list?pageNo=" + page;
		document.getElementById('myform').submit();
	}
	function del(code) {
		if (confirm("确定删除?")) {
			location.href = "/excel/del?code=" + code;
		} else
			return false;
	}
</script>
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
			<div class="manage_breadcrumb">当前位置：首页 > 用户管理</div>
			<span style="float: right"><a href="/excel/in"><input
					type="button" class="mybutton" value="数据导入导出"></a> <a
				href="/excel/add"><input type="button" class="mybutton"
					value="添加用户"></a></span><br /> <br />
			<form action="/excel/list" id="myform" method="post">
				<input type="hidden" value="0" name="flag">
								<table border="0" cellspacing="0" cellpadding="0" width="700px">
									<tbody>
										<tr>
											<td>类型</td>
											<td><select name="roleId">
													<c:forEach items="${exportType}" var="entry">
														<option
															<c:if test="${entry.key==roleId}"> selected </c:if>
															value="${entry.key}">${entry.value}</option>
													</c:forEach>
											</select></td>
											<td>ID</td>
											<td><input style="width: 150px" type="text" name="code"
												value="${code}"></td>
											<td>名称</td>
											<td><input style="width: 150px" type="text" name="name"
												value="${name}"></td>
											<td>班级</td>
											<td><input style="width: 150px" type="text"
												name="uclass" value="${uclass}"></td>
											<td><input type="submit" value="搜索" /></td>
										</tr>
									</tbody>
								</table> <c:if test="${!empty list }">
									<table class="table_border" width="700px">
										<tbody>
											<th align="center">ID</th>
											<th align="center">名称</th>
											<th align="center">班级</th>
											<th align="center">添加时间</th>
											<th align="center">操作</th>
											<c:forEach items="${list}" var="user">
												<tr>
													<td align="center">${user.code}</td>
													<td align="center">${user.name}</td>
													<td align="center"><c:if test="${user.uclass=='0'}">无</c:if>
														<c:if test="${user.uclass!='0'}">${user.uclass}</c:if></td>
													<td align="center"><fmt:formatDate
															value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
													<td align="center"><a
														href="/excel/add?code=${user.code}">修改</a>&nbsp;&nbsp;<a
														href="javascript:del(${user.code})">删除</a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="list-page">
										第${pageNo}页
										<!-- 前一页  -->
										<c:if test="${pageNo>1}">
											<a href="javascript:goUserPage(${pageNo-1})">前一页</a>
										</c:if>
										<c:if test="${pageNo<=1}">
											<span class="disabled">前一页</span>
										</c:if>
										<!-- 后一页  -->
										<c:if test="${pageNo<totalPageNo}">
											<a href="javascript:goUserPage(${pageNo+1})">后一页</a>
										</c:if>
										<c:if test="${pageNo>=totalPageNo}">
											<span class="disabled">后一页</span>
										</c:if>
										&nbsp;共${count}条记录&nbsp;${totalPageNo}页数据
									</div>
								</c:if>
			</form>
		</div>
	</div>
</body>
</html>