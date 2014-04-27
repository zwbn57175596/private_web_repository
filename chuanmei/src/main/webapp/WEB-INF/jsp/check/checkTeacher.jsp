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
																<td>当前位置：<span class="fontstyle6522">首页</span>&nbsp;<img
																	src="/resources/img/left_9.gif" align="absmiddle">&nbsp;<span
																	class="fontstyle6522">审核老师借用详情</span></td>
															</tr>
														</tbody>
													</table> <!--#endeditable--></td>
											</tr>
										</tbody>
									</table></td>
							</tr>
							<tr>
								<td bgcolor="#ffffff"><br />
									<table border="0" cellspacing="0" cellpadding="0" width="100%">
										<tbody>
											<tr>
												<td class="list_content">
													<table class="table_border"
														width="100%">
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
																	<td colspan="2" align="center">${row.name}</td>
																	<td align="center"><input type="checkbox" checked name="shebeis" value="${row.id}"></td>
																</tr>
															</c:forEach>
															<tr>
																<td><b>开始时间：</b>
																</td>
																<td><fmt:formatDate value="${teacherOrders.sDate}"
																		pattern="yyyy-MM-dd HH:mm:ss" />
																</td>
																<td><b>结束时间：</b>
																</td>
																<td><fmt:formatDate value="${teacherOrders.eDate}"
																		pattern="yyyy-MM-dd HH:mm:ss" />
																</td>
															</tr>
															<tr>
																<td colspan="4"><font color="red"><b>领取学生学号：(多个之间用"，"隔开)
																</font></b>${teacherOrders.studentIds}</td>
															</tr>
															<tr>
																<td colspan="4"><font color="red"><b>特殊说明：</b></font>${teacherOrders.leaveWord}</td>
															</tr>
															
															
														</tbody>
													</table>
													<br/>
													<form action="/teacherOrdersCheck/doCheck" method="post">
														<input type="hidden" name="id" value="${teacherOrders.id}">
														<table class="table_border"
															width="100%">
															<tbody>
																<tr>
																	<td align="center"><b>状态：</b>
																	</td>
																	<td align="center"><select
																		name="teacherOrderState">
																			<c:forEach items="${teacherOrderState}" var="entry">
																				<option
																					<c:if test="${entry.key==teacherOrders.state}"> selected </c:if>
																					value="${entry.key}">${entry.value}</option>
																			</c:forEach>
																	</select>
																	<br/><font color="red">如果审核通过，那么该批设备被置为已借用状态，其他老师不可见；当该订单置为结束状态后，该批设备会被置为可见状态。</font>
																	</td>
																</tr>

																<tr>
																	<td align="center"><b>备注：</b>
																	</td>
																	<td align="center"><textarea name="remark"
																			rows="3" cols="80">${teacherOrders.remark}</textarea>
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
					</table>
				</td>
			</tr>
		</tbody>
	</table>
	<jsp:include page="../include/foot.jsp"></jsp:include>
</body>
</html>