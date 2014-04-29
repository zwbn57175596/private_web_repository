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
			<div class="manage_breadcrumb">当前位置：首页 &gt; 学生可借用订单列表</div>
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
								<td colspan=5>记录为空！</td>
							</tr>
						</c:if>
						<c:forEach items="${list}" var="row">
							<tr>
								<td align="left" title="${row.subject}">${fn:substring(row.subject,0,
																		15)}</td>
								<td align="center"><fmt:formatDate value="${row.sDate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td align="center"><fmt:formatDate value="${row.eDate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td align="center"><select>
										<c:forEach items="${teacherOrderState}" var="entry">
											<option
												<c:if test="${entry.key==row.state}"> selected </c:if>
												value="${entry.key}">${entry.value}</option>
										</c:forEach>
								</select></td>
								<td align="center"><a href="/studentOrders2/in?id=${row.id}">进入借用</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function loadSbtype() {
			$
					.ajax({
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