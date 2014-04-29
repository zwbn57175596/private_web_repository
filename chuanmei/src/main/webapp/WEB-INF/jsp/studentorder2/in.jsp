<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="now" class="java.util.Date" />
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
			<div class="manage_breadcrumb">当前位置：首页 &gt; 学生借用</div>
			<div class="manage_right_form">
				<form action="/studentOrders2/doIn" method="post">
					<input type="hidden" name="id" value="${teacherOrders.id}" /> <input
						type="hidden" name="sid" value="${studentOrders.id}" />
					<table class="table_border" width="100%">
						<tbody>
							<tr>
								<td colspan="4"><b>申请老师：</b>${teacherOrders.userId}_${teacherOrders.name}</td>
							</tr>
							<tr>
								<td colspan="4"><b>标题：</b>${teacherOrders.subject}</td>
							</tr>
							<tr>
								<td><b>开始时间：</b></td>
								<td><fmt:formatDate value="${teacherOrders.sDate}" pattern="yyyy-MM-dd" /></td>
								<td><b>结束时间：</b></td>
								<td><fmt:formatDate value="${teacherOrders.eDate}" pattern="yyyy-MM-dd" /></td>
							</tr>
							<tr>
								<td colspan="4"><font color="red"><b>特殊说明：</b></font>${teacherOrders.leaveWord}</td>
							</tr>
							<tr>
								<td><b>备注：</b></td>
								<td colspan="3"><textarea name="remark" rows="3" cols="80">${studentOrders.remark}</textarea></td>
							</tr>
							<tr>
								<td colspan="4" align="center"><input type="submit"
									value="申请" /></td>
							</tr>
						</tbody>
					</table>
					<br /> <br /> 设备列表
					<table class="table_border" width="100%">
						<tbody>
							<c:forEach items="${listShebei}" var="row">
								<tr>
									<td align="center">${row.code}</td>
									<td colspan="2" align="center">${row.name}</td>
									<td align="center"><input type="checkbox"
										checked="checked" name="shebeis" onclick="return false;"
										value="${row.shebeiId}" /></td>
								</tr>
								<tr>
									<td><b>预约时间：</b></td>
									<td colspan="3"> <input name="sTime" class="Wdate" readonly="readonly"
										style="width: 160px" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'
										<c:choose>
										<c:when test="${now < teacherOrders.sDate}">
										, minDate:'<fmt:formatDate value="${teacherOrders.sDate}" pattern="yyyy-MM-dd" />'
										, maxDate:'<fmt:formatDate value="${teacherOrders.eDate}" pattern="yyyy-MM-dd" />'
										, disabledDates:[
										</c:when>
										<c:when test="${now >= teacherOrders.sDate && now < teacherOrders.eDate}">
										, minDate:'<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />'
										, maxDate:'<fmt:formatDate value="${teacherOrders.eDate}" pattern="yyyy-MM-dd" />'
										, disabledDates:[
										</c:when>
										<c:otherwise>
										, minDate:'<fmt:formatDate value="${teacherOrders.eDate}" pattern="yyyy-MM-dd" />'
										, maxDate:'<fmt:formatDate value="${teacherOrders.eDate}" pattern="yyyy-MM-dd" />'
										, disabledDates:['<fmt:formatDate value="${teacherOrders.eDate}" pattern="yyyy-MM-dd" />',
										</c:otherwise>
										</c:choose>
										<c:forEach items="${row.shebeiOrderList}" var="s">
										<c:if test="${s.studentOrdersId != studentOrders.id}">
										'<fmt:formatDate value="${s.sDate}" pattern="yyyy-MM-dd" />',
										</c:if>
										</c:forEach>
										]})" 
										<c:forEach items="${studentOrders.shebeiOrders}" var="s">
										<c:if test="${s.shebeiId == row.shebeiId}">
										value="<fmt:formatDate value="${s.sDate}" pattern="yyyy-MM-dd" />"
										</c:if>
										</c:forEach>
										 /></td>
									<%-- <td><b>结束时间：</b></td>
									<td><input name="eTime" class="Wdate" readonly="readonly"
										style="width: 160px" type="text"
										value="${studentOrders.eDate}"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd', 
										minDate:'<fmt:formatDate value="${teacherOrders.sDate}" pattern="yyyy-MM-dd" />', 
										maxDate:'<fmt:formatDate value="${teacherOrders.eDate}" pattern="yyyy-MM-dd" />'})" /></td> --%>
								</tr>
								<tr>
									<td colspan="4"><table class="table_border" width="100%">
										<thead><tr><th>预约人</th><th>预约时间</th></tr></thead>
										<tbody>
											<c:forEach items="${row.shebeiOrderList}" var="s">
												<tr><td>${s.studentName}</td><td><fmt:formatDate value="${s.sDate}" pattern="yyyy-MM-dd" /></td></tr>
											</c:forEach>
										</tbody>
									</table></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function loadSbtype() {
			$.ajax({
						url : "/teacherOrders2/in2",
						data : {
							sbtype : 2
						},
						dataType : "json",
						success : function(list) {
							var html = "";
							if (typeof (list) == "object") {
								var len = list.length;
								for (var i = 0; i < len; i++) {
									var r = list[i];
									html = "<tr><td align=\"center\">" + r.code
											+ "</td><td align=\"center\">"
											+ r.name + "</td>";
									if (r.check > 0) {
										html += "<td align=\"center\"><input type=\"checkbox\" checked name=\"shebeis\" value=" + r.id + " /></td></tr>";
									} else {
										html += "<td align=\"center\"><input type=\"checkbox\" name=\"shebeis\" value=" + r.id + " /></td></tr>";
									} /// success end if check > 0
									$("#equi_list tbody").append(html);
								} // success end for 
							} // success end if typeof
						}
					});
		}

		$(document).ready(function() {
			$("#sbtype").bind("change", function() {
				loadSbtype();
			});
		});
	</script>
</body>
</html>