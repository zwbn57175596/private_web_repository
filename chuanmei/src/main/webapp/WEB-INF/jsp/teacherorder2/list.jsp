<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title}</title>
<meta name="keywords" content="${seo_keywords}" />
<meta name="description" content="${seo_desc}" />
<link rel="stylesheet" type="text/css" href="/resources/css/style.css" />
<script charset="utf-8" src="/resources/js/jquery-1.5.min.js"></script>
<script charset="utf-8" src="/resources/js/tr.js"></script>
<script type="text/javascript">
	function goTeacherPage(page) {
		location.href = "/teacherOrders2/list?pageNo=" + page;
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
		<jsp:include page="../include/navigate.jsp" flush="true"></jsp:include>
		<div class="manage_right">
			<div class="manage_breadcrumb">当前位置：首页  &gt; 借用列表</div>
			<div class="manage_right_form">
				<table class="table_border" width="100%">
					<tbody>
						<tr>
							<th>标题</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
						<c:if test="${empty list}">
							<tr>
								<td colspan="6">记录为空！</td>
							</tr>
						</c:if>
						<c:forEach items="${list}" var="row">
							<tr>
								<td align="left" title="${row.subject}">${fn:substring(row.subject,0,15)}</td>
								<td align="center"><fmt:formatDate value="${row.sDate}" pattern="yyyy-MM-dd" />
								</td>
								<td align="center"><fmt:formatDate value="${row.eDate}" pattern="yyyy-MM-dd" />
								</td>
								<td align="center"><select>
										<c:forEach items="${teacherOrderState}" var="entry">
											<option
												<c:if test="${entry.key==row.state}"> selected </c:if>
												value="${entry.key}">${entry.value}</option>
										</c:forEach>
								</select>
								</td>
								<td align="center"><a
									href="/teacherOrders2/show/${row.id}">查看</a> <c:if
										test="${row.state<1}">
										<a href="/teacherOrders2/inupdate?id=${row.id}&sbtype=${row.sbTypeId}">修改</a>
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="list-page">
					第${pageNo}页
					<!-- 前一页  -->
					<c:if test="${pageNo>1}">
						<a href="javascript:goTeacherPage(${pageNo-1})">前一页</a>
					</c:if>
					<c:if test="${pageNo<=1}">
						<span class="disabled">前一页</span>
					</c:if>
					<!-- 后一页  -->
					<c:if test="${pageNo<totalPageNo}">
						<a href="javascript:goTeacherPage(${pageNo+1})">后一页</a>
					</c:if>
					<c:if test="${pageNo>=totalPageNo}">
						<span class="disabled">后一页</span>
					</c:if>
					&nbsp;共${count}条记录&nbsp;${totalPageNo}页数据
				</div>
			</div>
		</div>
	</div>
</body>
</html>