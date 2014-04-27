<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<meta name="keywords" content="${seo_keywords}">
<meta name="description" content="${seo_desc}">
<link rel="stylesheet" type="text/css" href="/resources/css/css.css">
<script language="javascript" type="text/javascript"
	src="/resources/My97DatePicker/WdatePicker.js"></script>
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
					</table>
				</td>
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
													width="16" height="16"></td>
												<td>
													<table class="winstyle6522" cellspacing="0" cellpadding="0">
														<tbody>
															<tr>
																<td>当前位置： <span class="fontstyle6522">首页</span>&nbsp;<img
																	src="/resources/img/left_9.gif" align="absmiddle">&nbsp;<span
																	class="fontstyle6522">审核学生借用</span></td>
															</tr>
														</tbody>
													</table> <!--#endeditable-->
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td bgcolor="#ffffff"><br />
									<table border="0" cellspacing="0" cellpadding="0" width="100%">
										<tbody>
											<tr>
												<td class="list_content">
													<table class="table_border" width="100%">
														<tbody>
															<tr>
																<td colspan="4"><b>申请老师：</b>${teacherOrders.userId}_${teacherOrders.name}</td>
															</tr>
															<tr>
																<td colspan="4"><b>标题：</b>${teacherOrders.subject}</td>
															</tr>
															<c:forEach items="${listShebei}" var="row">
																<tr>
																	<td align="center">${row.code}</td>
																	<td  colspan="2" align="center">${row.name}</td>
																	<td align="center"><input type="checkbox" checked name="shebeis"  onclick="return false;" value="${row.id}"></td>
																</tr>
															</c:forEach>
															<tr>
																<td><b>开始时间：</b></td>
																<td><fmt:formatDate value="${teacherOrders.sDate}"
																		pattern="yyyy-MM-dd HH:mm:ss" /></td>
																<td><b>结束时间：</b></td>
																<td><fmt:formatDate value="${teacherOrders.eDate}"
																		pattern="yyyy-MM-dd HH:mm:ss" /></td>
															</tr>
														</tbody>
													</table> <br />

													<form action="/studentOrdersCheck/doCheck" method="post">
														<input type="hidden" name="id" value="${studentOrders.id}">
														<table class="table_border" width="100%">
															<tbody>
																<tr>
																	<td align="center"><b>申请学生ID：</b></td>
																	<td align="center">${studentOrders.userId}_${studentOrders.name}</td>
																</tr>
																<tr>
																	<td align="center"><b>状态：</b></td>
																	<td align="center"><select
																		name="studentOrderState">
																			<c:forEach items="${studentOrderState}" var="entry">
																				<option
																					<c:if test="${entry.key==studentOrders.state}"> selected </c:if>
																					value="${entry.key}">${entry.value}</option>
																			</c:forEach>
																	</select></td>
																</tr>
																<tr>
																	<td align="center"><b>开始时间：</b></td>
																	<td align="center"><fmt:formatDate
																			value="${studentOrders.sDate}"
																			pattern="yyyy-MM-dd HH:mm:ss" /></td>
																</tr>
																<tr>
																	<td align="center"><b>结束时间：</b></td>
																	<td align="center"><fmt:formatDate
																			value="${studentOrders.eDate}"
																			pattern="yyyy-MM-dd HH:mm:ss" /></td>
																</tr>
																<tr>
																	<td align="center"><b>备注：</b></td>
																	<td align="center"><textarea name="remark"
																			rows="3" cols="60">${studentOrders.remark}</textarea>
																	</td>
																</tr>
																<tr>
																	<td colspan="2" align="center"><input
																		type="submit" value="提交"></td>
																</tr>
															</tbody>
														</table>
													</form>
												</td>
											</tr>

										</tbody>
									</table></td>
							</tr>
						</tbody>
					</table></td>
			</tr>
		</tbody>
	</table>
	<jsp:include page="../include/foot.jsp"></jsp:include>
</body>
</html>