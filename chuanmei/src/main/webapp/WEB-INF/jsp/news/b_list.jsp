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
	function goNewsPage(type, page) {
		location.href = "/news/b_list/" + type + "?pageNo=" + page;
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
			<div class="manage_breadcrumb">当前位置：首页 > ${navigateName }</div>
			<table class="table_border" width="700px">
				<tbody>
					<tr>
						<th width="8%">序号</th>
						<th width="40%">标题</th>
						<th width="20%">时间</th>
						<th width="8%">发表人</th>
						<th>操作</th>
					</tr>
					<c:if test="${empty list}">
						<tr>
							<td colspan=5>记录为空！</td>
						</tr>
					</c:if>
					<c:forEach items="${list}" var="row">
						<tr>
							<td align="center" class="fontstyle6522">${row.id}</td>
							<td align="left" class="fontstyle6522">${row.title}</td>
							<td align="center" class="fontstyle6522"><fmt:formatDate
									value="${row.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td align="center" class="fontstyle6522">${row.addUserId}</td>
							<td align="center" class="fontstyle6522"><a
								href="/news/show/${row.id}">查看</a>&nbsp;<a
								href="/news/b_update?id=${row.id}">修改</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="list-page">
				第${pageNo}页
				<!-- 前一页  -->
				<c:if test="${pageNo>1}">
					<a href="javascript:goNewsPage(${type},${pageNo-1})">前一页</a>
				</c:if>
				<c:if test="${pageNo<=1}">
					<span class="disabled">前一页</span>
				</c:if>
				<!-- 后一页  -->
				<c:if test="${pageNo<totalPageNo}">
					<a href="javascript:goNewsPage(${type},${pageNo+1})">后一页</a>
				</c:if>
				<c:if test="${pageNo>=totalPageNo}">
					<span class="disabled">后一页</span>
				</c:if>
				&nbsp;共${count}条记录&nbsp;${totalPageNo}页数据
			</div>
		</div>
	</div>
</body>
</html>