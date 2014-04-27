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
<script type="text/javascript">
	function del(id) {
		if (confirm("确定删除?")) {
			location.href = "/shebei/del?id=" + id;
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
			<div class="manage_breadcrumb">当前位置：首页 > 设备列表</div>
			<font color=red>${info}</font><span style="float: right"><a
				href="/shebei/update"><input type="button" class="mybutton"
					value="添加设备"></a></span><br />
			<table class="table_border" width="700px">
				<tbody>
					<tr>
						<th>ID</th>
						<th>分类</th>
						<th>名称</th>
						<th>库存</th>
						<th>缩略图</th>
						<th>操作</th>
					</tr>
					<c:if test="${empty list}">
						<tr>
							<td colspan=6>记录为空！</td>
						</tr>
					</c:if>
					<c:forEach items="${list}" var="row">
						<tr>
							<td align="center">${row.code}</td>
							<td align="center"><select>
									<c:forEach items="${type}" var="entry">
										<option <c:if test="${entry.key==row.type}"> selected </c:if>
											value="${entry.key}">${entry.value}</option>
									</c:forEach>
							</select></td>
							<td align="center">${row.name}</td>
							<td align="center">${row.num}</td>
							<td align="center"><img src="${row.imgUrl}" width="150"
								height="60" /></td>
							<td align="center"><a href="/shebei/update?id=${row.id}">修改</a>&nbsp;<a
								href="javascript:del(${row.id})">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
</body>
</html>