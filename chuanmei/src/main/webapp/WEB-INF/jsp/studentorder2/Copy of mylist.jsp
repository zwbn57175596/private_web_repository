<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<meta name="keywords" content="${seo_keywords}">
<meta name="description" content="${seo_desc}">
<link rel="stylesheet" type="text/css" href="/resources/css/css.css">
<script charset="utf-8" src="/resources/js/jquery-1.5.min.js"></script>
<script charset="utf-8" src="/resources/js/tr.js"></script>
<script type="text/javascript">
function goStudentPage(page) {
	location.href = "/studentOrders/mylist?pageNo=" + page;
}
</script>
</head>
<body>
	<jsp:include page="../include/head.jsp"></jsp:include>
	<table cellspacing="0" cellpadding="0" width="1003" class="style2"
		align="center" style="background: #c0c0c0">
		<tbody>
			<tr bgcolor="#ffffff">
				<td valign="top" width="210">
					<table border="0" class="style2" cellspacing="0" cellpadding="0"
						width="100%">
						<tbody>
							<tr>
								<td height="432" valign="top"><jsp:include
										page="../include/navigate.jsp"></jsp:include></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
						</tbody>
					</table></td>
				<td valign="top" width="793"><br>
					<table border="0" cellspacing="0" cellpadding="0" width="90%"
						align="center">
						<tbody>
							<tr>
								<td bgcolor="#ffffff">
									<table border="0" cellspacing="0" cellpadding="0" width="50%">
										<tbody>
											<tr>
												<td width="7%"><img src="/resources/img/left_8.gif"
													width="16" height="16">
												</td>
												<td>
													<table class="winstyle6522" cellspacing="0" cellpadding="0">
														<tbody>
															<tr>
																<td>当前位置：<span
																		class="fontstyle6522">首页</span>&nbsp;<img
																	src="/resources/img/left_9.gif" align="absmiddle">&nbsp;<span class="fontstyle6522">学生已借用订单列表</span></td>
															</tr>
														</tbody>
													</table> <!--#endeditable--></td>
											</tr>
										</tbody>
									</table></td>
							</tr>
							<tr>
								<td bgcolor="#ffffff"><br/><font color="red">${info}</font>
									<table border="0" cellspacing="0" cellpadding="0" width="100%">
										<tbody>
											<tr>
												<td class="list_content">
													<table class="table_border"
														width="100%">
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
																	<td colspan=5>记录为空！</td>
																</tr>
															</c:if>
															<c:forEach items="${list}" var="row">
																<tr <c:if test="${row.isRed==1}">bgcolor="red"</c:if> >
																	<td align="left">${row.title}</td>
																	<td align="center"><fmt:formatDate
																			value="${row.sDate}" pattern="yyyy-MM-dd HH:mm:ss" />
																	</td>
																	<td align="center"><fmt:formatDate
																			value="${row.eDate}" pattern="yyyy-MM-dd HH:mm:ss" />
																	</td>
																	<td align="center"><select>
																			<c:forEach items="${studentOrderState}" var="entry">
																				<option
																					<c:if test="${entry.key==row.state}"> selected </c:if>
																					value="${entry.key}">${entry.value}</option>
																			</c:forEach>
																	</select></td>
																	<td align="center"><a href="/studentOrders/view?id=${row.orderId}&myid=${row.id}">查看</a> &nbsp;<c:if test="${row.state<1}"><a href="/studentOrders/in?id=${row.orderId}&sid=${row.id}">修改</a></c:if></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
															<div class="list-page">
					第${pageNo}页
					<!-- 前一页  -->
					<c:if test="${pageNo>1}"> <a	href="javascript:goStudentPage(${pageNo-1})">前一页</a> </c:if>
					<c:if test="${pageNo<=1}"><span class="disabled">前一页</span></c:if>
					<!-- 后一页  -->
					<c:if test="${pageNo<totalPageNo}"><a href="javascript:goStudentPage(${pageNo+1})">后一页</a></c:if>
					<c:if test="${pageNo>=totalPageNo}">
					<span class="disabled">后一页</span></c:if>&nbsp;共${count}条记录&nbsp;${totalPageNo}页数据
				</div>
												</td>
											</tr>

										</tbody>
									</table></td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
	<jsp:include page="../include/foot.jsp"></jsp:include>
</body>
</html>