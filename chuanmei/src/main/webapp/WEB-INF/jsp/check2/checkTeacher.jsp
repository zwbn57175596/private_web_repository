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
			<div class="manage_breadcrumb">当前位置：首页 &gt; 审核老师借用详情</div>
			<div class="manage_right_form">
				<table class="table_border" width="100%">
					<tbody>
						<c:forEach items="${listShebei}" var="row">
							<tr>
								<td align="center">${row.code}</td>
								<td colspan="2" align="center">${row.name}</td>
								<td align="center"><input type="checkbox" checked="checked"
									name="shebeis" disabled="disabled" value="${row.id}" /></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="4">课程名称<input type="text" readonly="readonly"
								class="input-title" value="${teacherOrders.subject }"
								name="subject" /></td>
						</tr>
						<tr>
							<td><b>授课班级名称:</b></td>
							<td><input name="className" readonly="readonly"
								style="width: 160px" type="text"
								value="${teacherOrders.claName}" /></td>
							<td><b>班级人数:</b></td>
							<td><input name="stuNum" readonly="readonly"
								style="width: 160px" type="text" value="${teacherOrders.stuNum}" /></td>
						</tr>
						<tr>
							<td colspan="4"><b>试验形式:</b> <input type="radio"
								name="expType" value="图片" disabled="disabled"
								<c:if test="${teacherOrders.expType == '图片'}"> checked="checked" </c:if> />图片、
								<input type="radio" name="expType" value="短片"
								disabled="disabled"
								<c:if test="${teacherOrders.expType == '短片'}"> checked="checked" </c:if> />短片、
								<input type="radio" name="expType" value="纪录片"
								disabled="disabled"
								<c:if test="${teacherOrders.expType == '纪录片'}"> checked="checked" </c:if> />纪录片、
								<input type="radio" name="expType" value="MTV"
								disabled="disabled"
								<c:if test="${teacherOrders.expType == 'MTV'}"> checked="checked" </c:if> />MTV、
								<input type="radio" name="expType" value="录音"
								disabled="disabled"
								<c:if test="${teacherOrders.expType == '录音'}"> checked="checked" </c:if> />录音、
								<input type="radio" name="expType" value="毕业作品"
								disabled="disabled"
								<c:if test="${teacherOrders.expType == '毕业作品'}"> checked="checked" </c:if> />毕业作品、
								<c:choose>
									<c:when
										test="${teacherOrders.expType != '图片' && teacherOrders.expType != '短片' && 
	                	teacherOrders.expType != '纪录片' && teacherOrders.expType != 'MTV' && 
	                	teacherOrders.expType != '录音' && teacherOrders.expType != '毕业作品'}">
										<input type="radio" disabled="disabled" name="expType"
											checked="checked" value="${teacherOrders.expType}" />其他
                	<input type="text" readonly="readonly" id="other_exp"
											value="${teacherOrders.expType}" />
									</c:when>
									<c:otherwise>
										<input type="radio" disabled="disabled" name="expType"
											value="" />其他 <input type="text" id="other_exp" value="" />
									</c:otherwise>
								</c:choose></td>
						</tr>
						<tr>
							<td colspan="4"><b>作业长度:</b> <input type="radio"
								name="workTime" value="1" disabled="disabled"
								<c:if test="${teacherOrders.workTime == 1}"> checked="checked" </c:if> />1天、
								<input type="radio" name="workTime" value="2"
								disabled="disabled"
								<c:if test="${teacherOrders.workTime == 2}"> checked="checked" </c:if> />2天、
								<input type="radio" name="workTime" value="3"
								disabled="disabled"
								<c:if test="${teacherOrders.workTime == 3}"> checked="checked" </c:if> />3天、
								<input type="radio" name="workTime" value="5"
								disabled="disabled"
								<c:if test="${teacherOrders.workTime == 5}"> checked="checked" </c:if> />5天、
								<input type="radio" name="workTime" value="10"
								disabled="disabled"
								<c:if test="${teacherOrders.workTime == 10}"> checked="checked" </c:if> />10天、
								<input type="radio" name="workTime" value="15"
								disabled="disabled"
								<c:if test="${teacherOrders.workTime == 15}"> checked="checked" </c:if> />15天
							</td>
						</tr>
						<tr>
							<td><b>开始时间:</b></td>
							<td><input name="sTime" class="Wdate" readonly="readonly"
								value="${fn:substring(teacherOrders.sDate,0,10)}"
								style="width: 160px" type="text" /></td>
							<td><b>结束时间:</b></td>
							<td><input name="eTime" class="Wdate" readonly="readonly"
								value="${fn:substring(teacherOrders.eDate,0,10)}"
								style="width: 160px" type="text" /></td>
						</tr>
						<tr>
							<td colspan="4"><font color="red">学生学号(多个之间用","隔开)</font><input
								value="${teacherOrders.studentIds}" class="input-title"
								readonly="readonly" type="text" name="studentIds" /></td>
						</tr>
						<tr>
							<td colspan="4"><font color="red"><b>特殊说明</b></font><input
								type="text" class="input-title"
								value="${teacherOrders.leaveWord}" readonly="readonly"
								name="leaveWord" /></td>
						</tr>
					</tbody>
				</table>
				<form action="/teacherOrdersCheck2/doCheck" method="post">
					<input type="hidden" name="id" value="${teacherOrders.id}" />
					<table class="table_border" width="100%">
						<tbody>
							<tr>
								<td align="center"><b>状态：</b></td>
								<td align="center"><select name="teacherOrderState">
										<c:forEach items="${teacherOrderState}" var="entry">
											<option
												<c:if test="${entry.key==teacherOrders.state}"> selected </c:if>
												value="${entry.key}">${entry.value}</option>
										</c:forEach>
								</select> 
								<br /><!--  <font color="red">如果审核通过，那么该批设备被置为已借用状态，其他老师不可见；当该订单置为结束状态后，该批设备会被置为可见状态。</font> -->
								</td>
							</tr>
							<tr>
								<td align="center"><b>备注：</b></td>
								<td align="center"><textarea name="remark" rows="3"
										cols="80">${teacherOrders.remark}</textarea></td>
							</tr>
							<tr>
								<td colspan="2" align="center"><input type="submit" value="提交" /></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>