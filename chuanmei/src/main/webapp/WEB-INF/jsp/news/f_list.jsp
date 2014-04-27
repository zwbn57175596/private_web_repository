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
		location.href = "/news/f_list/" + type + "?pageNo=" + page;
	}
</script>
</head>
<body>
	<div class="container">
		<jsp:include page="../include/head2.jsp"></jsp:include>
		<div class="main-left">
			<h2 class="left-head">维护知识</h2>
			<div class="left-body left-logo maintian">
				<img src="/resources/images/maintain_left.png">
			</div>
		</div>

		<div class="main-right">
			<div class="breadcrumb">当前位置：首页> 维护知识</div>
			<div class="right-body maintain-bg">
				<table cellpadding="0" cellspacing="0" border="0">
					<tbody>
						<c:if test="${empty list}">
							<tr><th>记录为空！</th>
							</tr>
						</c:if>
						<c:forEach items="${list}" var="row">
							<tr>
								<th>·<a href="/news/show/${row.id}" title="${row.title}">${row.title}</a></th>
								<td align="right"><fmt:formatDate value="${row.createTime}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
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
		<div style="clear: both"></div>
		<jsp:include page="../include/foot.jsp"></jsp:include>
	</div>
</body>
</html>
