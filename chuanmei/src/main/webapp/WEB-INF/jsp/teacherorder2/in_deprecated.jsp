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
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
<script charset="utf-8" src="/resources/js/jquery-1.5.min.js"></script>
<script charset="utf-8" src="/resources/js/tr.js"></script>
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
									<table border="0" cellspacing="0" cellpadding="0" width="30%">
										<tbody>
											<tr>
												<td width="7%"><img src="/resources/img/left_8.gif"
													width="16" height="16"></td>
												<td>
													<table class="winstyle6522" cellspacing="0" cellpadding="0">
														<tbody>
															<tr>
																<td>当前位置： <span class="fontstyle6522">首页</span>
																	&nbsp;<img src="/resources/img/left_9.gif"
																	align="absmiddle">&nbsp;<span
																	class="fontstyle6522">老师借用</span>
																</td>
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
													<form action="/teacherOrders/doIn" method="post">
														<input type="hidden" name="id" value="${teacherOrders.id}">
														<input type="hidden" name="sbtype" value="${sbtype}">
														<table  class="table_border"
															width="100%">
															<tbody>
															<tr><th>编号</th><th>名称</th><th>选择</th></tr>
																<c:forEach items="${listShebei}" var="row">
																	<tr>
																		<td align="center">${row.code}</td>
																		<td align="center">${row.name}</td>
																		<td align="center"><input type="checkbox"
																			<c:if test="${row.check>0}">checked</c:if>
																			name="shebeis" value="${row.id}"></td>
																	</tr>
																</c:forEach>
																</tbody>
																</table>
																<table class="table_border" width="100%">
																<tbody>
																<tr>
																	<td colspan="4"><b>标题:</b><input type="text"
																		class="input-title" value="${teacherOrders.subject }"
																		name="subject" /></td>
																</tr>
																
																<tr>
																	<td><b>开始时间:</b>
																	</td>
																	<td><input name="sTime" class="Wdate" readonly
																		value="${teacherOrders.sDate}" style="width: 160px"
																		type="text"
																		onClick="WdatePicker({dateFmt:'yyyy-MM-dd',disabledDays:[0,6]})">
																	</td>
																	<td><b>结束时间:</b>
																	</td>
																	<td><input name="eTime" class="Wdate" readonly
																		value="${teacherOrders.eDate}" style="width: 160px"
																		type="text"
																		onClick="WdatePicker({dateFmt:'yyyy-MM-dd',disabledDays:[0,6]})">
																	</td>
																</tr>
																<tr>
																	<td colspan="4"><font color="red"><b>学生学号(多个之间用","隔开):</b>
																	</font><input value="${teacherOrders.studentIds}"
																		class="input-title" type="text" name="studentIds" />
																	</td>
																</tr>
																<tr>
																	<td colspan="4"><font color="red"><b>特殊说明:</b></font><input type="text"
																		class="input-title"  value="${teacherOrders.leaveWord }"
																		name="leaveWord" /></td>
																</tr>
																<tr>
																	<td colspan="4"><input type="submit" value="提交" />
																	</td>
																</tr>
															</tbody>
														</table>
													</form></td>
											</tr>

										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table></td>
			</tr>
		</tbody>
	</table>
	<jsp:include page="../include/foot.jsp"></jsp:include>
</body>
</html>