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
<script language="javascript" type="text/javascript"
	src="/resources/My97DatePicker/WdatePicker.js"></script>
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
			<div class="manage_breadcrumb">当前位置：首页 &gt; 审核学生借用</div>
			<div class="manage_right_form">
				<table class="table_border" width="100%">
					<tbody>
						<tr>
							<td colspan="4"><b>申请老师：</b>${teacherOrders.userId}_${teacherOrders.name}</td>
						</tr>
						<tr>
							<td colspan="4"><b>课程名称：</b>${teacherOrders.subject}</td>
						</tr>
						<c:forEach items="${listShebei}" var="r">
							<tr>
								<td align="center" width="25%">${r.shebei.code}</td>
								<td colspan="2" align="center">${r.shebei.name}</td>
								<td align="center"><input type="checkbox" checked="checked" name="shebeis" onclick="return false;"
									value="${r.shebeiId}" /></td>
							</tr>
							<tr>
								<td><b>预约时间：</b></td>
								<td colspan="3"><fmt:formatDate value="${r.sDate}" pattern="yyyy-MM-dd" /></td>
								<%-- <td><b>结束时间：</b></td>
								<td><input name="eTime" class="Wdate" readonly="readonly"
									style="width: 160px" type="text"
									value="${studentOrders.eDate}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd', 
									minDate:'<fmt:formatDate value="${teacherOrders.sDate}" pattern="yyyy-MM-dd" />', 
									maxDate:'<fmt:formatDate value="${teacherOrders.eDate}" pattern="yyyy-MM-dd" />'})" /></td> --%>
							</tr>
						</c:forEach>					
					</tbody>
				</table>
				<br />

				<form action="/studentOrdersCheck2/doCheck" method="post">
					<input type="hidden" name="id" value="${studentOrders.id}" />
						<table class="table_border" width="100%">
							<tbody>
								<tr>
									<td align="center"><b>申请学生ID：</b></td>
									<td align="center">${studentOrders.userId}_${studentOrders.name}</td>
								</tr>
								<tr>
									<td align="center"><b>状态：</b></td>
									<td align="center"><select name="studentOrderState">
											<c:forEach items="${studentOrderState}" var="entry">
												<option
													<c:if test="${entry.key==studentOrders.state}"> selected </c:if>
													value="${entry.key}">${entry.value}</option>
											</c:forEach>
									</select></td>
								</tr>
								<%-- <tr>
									<td align="center"><b>开始时间：</b></td>
									<td align="center"><fmt:formatDate
											value="${studentOrders.sDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								</tr>
								<tr>
									<td align="center"><b>结束时间：</b></td>
									<td align="center"><fmt:formatDate
											value="${studentOrders.eDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								</tr> --%>
								<tr>
									<td align="center"><b>备注：</b></td>
									<td align="center"><textarea name="remark" rows="3"
											cols="60">${studentOrders.remark}</textarea></td>
								</tr>
								<tr>
									<td colspan="2" align="center"><input type="submit"
										value="提交" /></td>
								</tr>
							</tbody>
						</table>
				</form>

			</div>
		</div>
	</div>
</body>
</html>