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
								<td colspan="2">课程名称<input type="text" readonly="readonly"
									class="input-title" value="${teacherOrders.subject }"
									name="subject" /></td>
								<td colspan="2"><b>设备类型选择:</b>
									<select name="sbtype" id="sbtype" disabled="disabled">
				          <c:forEach items="${type}" var="entry">
				            <option value="${entry.key}"
				            	<c:if test="${entry.key == teacherOrders.sbTypeId}">selected="selected"</c:if>
				            >${entry.value}</option>
				          </c:forEach>
				          </select>
								</td>
							</tr>
							<tr>
                <td><b>授课班级名称:</b></td>
                <td><input name="className" readonly="readonly"
                  style="width: 160px" type="text" value="${teacherOrders.claName}" /></td>
                <td><b>班级人数:</b></td>
                <td><input name="stuNum" readonly="readonly" style="width: 160px" type="text" value="${teacherOrders.stuNum}"/></td>
              </tr>
              <tr>
                <td colspan="4"><b>试验形式:</b>
	                <input type="radio" name="expType" value="图片" disabled="disabled"
	                	<c:if test="${teacherOrders.expType == '图片'}"> checked="checked" </c:if> />图片、
	                <input type="radio" name="expType" value="短片" disabled="disabled"
	                	<c:if test="${teacherOrders.expType == '短片'}"> checked="checked" </c:if> />短片、
	                <input type="radio" name="expType" value="纪录片" disabled="disabled"
	                	<c:if test="${teacherOrders.expType == '纪录片'}"> checked="checked" </c:if> />纪录片、
	                <input type="radio" name="expType" value="MTV" disabled="disabled"
	                	<c:if test="${teacherOrders.expType == 'MTV'}"> checked="checked" </c:if> />MTV、
	                <input type="radio" name="expType" value="录音" disabled="disabled"
	                	<c:if test="${teacherOrders.expType == '录音'}"> checked="checked" </c:if> />录音、
	                <input type="radio" name="expType" value="毕业作品" disabled="disabled"
	                	<c:if test="${teacherOrders.expType == '毕业作品'}"> checked="checked" </c:if> />毕业作品、
	                <c:choose>
	                <c:when test="${teacherOrders.expType != '图片' && teacherOrders.expType != '短片' && 
	                	teacherOrders.expType != '纪录片' && teacherOrders.expType != 'MTV' && teacherOrders.expType != '录音' && teacherOrders.expType != '毕业作品'}">
                	<input type="radio" disabled="disabled" name="expType" checked="checked" value="${teacherOrders.expType}"  />其他
                	<input type="text" readonly="readonly" id="other_exp" value="${teacherOrders.expType}" />
	                </c:when>
	                <c:otherwise>
	                <input type="radio" disabled="disabled" name="expType" value="" />其他 <input type="text" id="other_exp" value="" />
	                </c:otherwise>
	                </c:choose>
                </td>
              </tr>
              <tr>
                <td colspan="4"><b>作业长度:</b>
	                <input type="radio" name="workTime" value="1" disabled="disabled"
	                	<c:if test="${teacherOrders.workTime == 1}"> checked="checked" </c:if> />1天、
	                <input type="radio" name="workTime" value="2" disabled="disabled"
	                	<c:if test="${teacherOrders.workTime == 2}"> checked="checked" </c:if> />2天、
	                <input type="radio" name="workTime" value="3" disabled="disabled"
	                	<c:if test="${teacherOrders.workTime == 3}"> checked="checked" </c:if> />3天、
	                <input type="radio" name="workTime" value="5" disabled="disabled"
	                	<c:if test="${teacherOrders.workTime == 5}"> checked="checked" </c:if> />5天、
	                <input type="radio" name="workTime" value="10" disabled="disabled"
	                	<c:if test="${teacherOrders.workTime == 10}"> checked="checked" </c:if> />10天、
	                <input type="radio" name="workTime" value="15" disabled="disabled"
	                	<c:if test="${teacherOrders.workTime == 15}"> checked="checked" </c:if> />15天
                </td>
              </tr>
							<tr>
								<td><b>开始时间：</b></td>
								<td><fmt:formatDate value="${teacherOrders.sDate}" pattern="yyyy-MM-dd" /></td>
								<td><b>结束时间：</b></td>
								<td><fmt:formatDate value="${teacherOrders.eDate}" pattern="yyyy-MM-dd" /></td>
							</tr>
							<tr>
							<td colspan="3"><font color="red"><b id="stu_tips">
									<c:choose> <c:when test="${teacherOrders.haveGroup == 1}">组长学号(多个之间用\",\"隔开):</c:when>
									<c:otherwise>学生学号(多个之间用","隔开):</c:otherwise> </c:choose> </b> </font>
									<input value="${teacherOrders.studentIds}" class="input-title" type="text" id="studentIds" 
										name="studentIds" size="60" readonly="readonly" /></td>
								<td>是否分组实习<input type="checkbox" name="haveGroup" id="haveGroup" value="1" disabled="disabled"  
									<c:if test="${teacherOrders.haveGroup == 1}">checked="checked"</c:if> /></td>
						</tr>
						<tr>
								<td colspan="4"><font color="red"><b>特殊说明</b></font><input type="text"
									class="input-title"  value="${teacherOrders.leaveWord}" readonly="readonly"
									name="leaveWord" /></td>
							</tr>
							<tr><td colspan="4" style="text-align: center;"><b>设备列表：</b></td></tr>
							<c:forEach items="${listShebei}" var="row">
								<tr>
									<td align="center">${row.code}</td>
									<td colspan="2" align="center">${row.name}</td>
									<td align="center"><input type="radio" name="shebeis" value="${row.id}" 
										<c:if test="${row.id == studentOrders.shebeiOrders[0].shebeiId}">checked="checked"</c:if> /></td>
								</tr>
								<tr>
									<td><b>预约时间：</b></td>
									<td colspan="3"> <input name="sTime" class="Wdate" readonly="readonly" 
										style="width: 160px" type="text"
										<c:if test="${row.id != studentOrders.shebeiOrders[0].shebeiId}">disabled="disabled"</c:if>
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd'
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
										<c:forEach items="${row.orderList}" var="s">
										<c:if test="${s.studentOrdersId != studentOrders.id}">
										'<fmt:formatDate value="${s.sDate}" pattern="yyyy-MM-dd" />',
										</c:if>
										</c:forEach>
										],disabledDays:[0,6]})" 
										<c:forEach items="${studentOrders.shebeiOrders}" var="s">
										<c:if test="${s.shebeiId == row.id}">
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
								<tr class="book_record" style="display:none">
									<td colspan="4"><table class="table_border" width="100%">
										<thead><tr><th>预约人</th><th>预约时间</th></tr></thead>
										<tbody>
											<c:forEach items="${row.orderList}" var="s">
												<tr><td>${s.studentName}</td><td><fmt:formatDate value="${s.sDate}" pattern="yyyy-MM-dd" /></td></tr>
											</c:forEach>
										</tbody>
									</table></td>
								</tr>
							</c:forEach>
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
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$(":radio[name='shebeis']").bind("click", function(e){
				if($(this).attr("checked") == true) {
					$(".book_record").hide();
					$(":input[name='sTime']").val("");
					$(":input[name='sTime']").attr("disabled", "disabled");
					var i = $.inArray($(this)[0], $(":radio[name='shebeis']"));
					$(".book_record").eq(i).show();
					$(":input[name='sTime']").eq(i).removeAttr("disabled");
				}
			});
			
		});
	</script>
</body>
</html>