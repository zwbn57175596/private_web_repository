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
			<div class="manage_breadcrumb">当前位置：首页  &gt; 老师借用</div>
			<div class="manage_right_form">
				<form action="/teacherOrders2/doIn" method="post" onsubmit="return fillForm()">
					<input type="hidden" id="id" name="id" value="${teacherOrders.id}" />
					<select name="sbtype" id="sbtype">
          <c:forEach items="${type}" var="entry">
            <option value="${entry.key}"
            	<c:if test="${entry.key == sbtype}">selected="selected"</c:if>
            >${entry.value}</option>
          </c:forEach>
          </select>
					<table class="table_border" width="100%" id="equi_list">
						<thead>
							<tr>
								<th>编号</th>
								<th>名称</th>
								<th>选择</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listShebei}" var="row">
							<tr>
								<td align="center">${row.code}</td>
								<td align="center">${row.name}</td>
								<td align="center"><input type="checkbox"
									<c:if test="${row.check>0}">checked</c:if>
									name="shebeis" value="${row.id}" /></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					<table class="table_border" width="100%">
						<tbody>
							<tr>
								<td colspan="4"><b>课程名称:</b><input type="text"
									class="input-title" value="${teacherOrders.subject }"
									name="subject" /></td>
							</tr>
							<tr>
                <td><b>授课班级名称:</b></td>
                <td><input name="className" 
                  style="width: 160px" type="text" value="${teacherOrders.claName}" /></td>
                <td><b>班级人数:</b></td>
                <td><input name="stuNum" style="width: 160px" type="text" value="${teacherOrders.stuNum}"/></td>
              </tr>
              <tr>
                <td colspan="4"><b>试验形式:</b>
	                <input type="radio" name="expType" value="图片" 
	                	<c:if test="${teacherOrders.expType == '图片'}"> checked="checked" </c:if> />图片、
	                <input type="radio" name="expType" value="短片"
	                	<c:if test="${teacherOrders.expType == '短片'}"> checked="checked" </c:if> />短片、
	                <input type="radio" name="expType" value="纪录片"
	                	<c:if test="${teacherOrders.expType == '纪录片'}"> checked="checked" </c:if> />纪录片、
	                <input type="radio" name="expType" value="MTV"
	                	<c:if test="${teacherOrders.expType == 'MTV'}"> checked="checked" </c:if> />MTV、
	                <input type="radio" name="expType" value="录音"
	                	<c:if test="${teacherOrders.expType == '录音'}"> checked="checked" </c:if> />录音、
	                <input type="radio" name="expType" value="毕业作品"
	                	<c:if test="${teacherOrders.expType == '毕业作品'}"> checked="checked" </c:if> />毕业作品、
	                <c:choose>
	                <c:when test="${teacherOrders.expType != '图片' && teacherOrders.expType != '短片' && 
	                	teacherOrders.expType != '纪录片' && teacherOrders.expType != 'MTV' && teacherOrders.expType != '录音' && teacherOrders.expType != '毕业作品'}">
                	<input type="radio" name="expType" checked="checked" value="${teacherOrders.expType}"  />其他
                	<input type="text" id="other_exp" value="${teacherOrders.expType}" />
	                </c:when>
	                <c:otherwise>
	                <input type="radio" name="expType" value="" />其他 <input type="text" id="other_exp" value="" />
	                </c:otherwise>
	                </c:choose>
                </td>
              </tr>
              <tr>
                <td colspan="4"><b>作业长度:</b>
	                <input type="radio" name="workTime" value="1"
	                	<c:if test="${teacherOrders.workTime == 1}"> checked="checked" </c:if> />1天、
	                <input type="radio" name="workTime" value="2"
	                	<c:if test="${teacherOrders.workTime == 2}"> checked="checked" </c:if> />2天、
	                <input type="radio" name="workTime" value="3"
	                	<c:if test="${teacherOrders.workTime == 3}"> checked="checked" </c:if> />3天、
	                <input type="radio" name="workTime" value="5"
	                	<c:if test="${teacherOrders.workTime == 5}"> checked="checked" </c:if> />5天、
	                <input type="radio" name="workTime" value="10"
	                	<c:if test="${teacherOrders.workTime == 10}"> checked="checked" </c:if> />10天、
	                <input type="radio" name="workTime" value="15"
	                	<c:if test="${teacherOrders.workTime == 15}"> checked="checked" </c:if> />15天
                </td>
              </tr>
  						<tr>
								<td><b>开始时间:</b></td>
								<td><input name="sTime" class="Wdate" readonly="readonly"
									value="${fn:substring(teacherOrders.sDate,0,10)}" style="width: 160px" type="text"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',disabledDays:[0,6]})" /></td>
								<td><b>结束时间:</b></td>
								<td><input name="eTime" class="Wdate" readonly="readonly"
									value="${fn:substring(teacherOrders.eDate,0,10)}" style="width: 160px" type="text"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',disabledDays:[0,6]})" /></td>
							</tr>
							<tr>
								<td colspan="4"><font color="red"><b>学生学号(多个之间用","隔开):</b>
								</font><input value="${teacherOrders.studentIds}" class="input-title"
									type="text" name="studentIds" size="100" /></td>
							</tr>
							<tr>
								<td colspan="4"><font color="red"><b>特殊说明:</b></font><input
									type="text" class="input-title"
									value="${teacherOrders.leaveWord }" name="leaveWord" /></td>
							</tr>
							<tr>
								<td colspan="4"><input type="submit" value="提交" /></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function fillForm() {
		$("input[name=expType]:radio:eq(6)").val($("#other_exp").val());
		return true;
	}
	function loadSbtype(t) {
		var d = $("#id").val();
		$.ajax({
			 url: "/teacherOrders2/in2",
			 data: {sbtype: t, id: d},
			 dataType: "json",
			 success: function(list){
				 // clear tbody
				 $("#equi_list tbody").html("");
				 var html = "";
				 if (typeof(list) == "object") {
						var len = list.length;
						for (var i = 0; i < len; i++ ) {
							var r = list[i];
							html = "<tr><td align=\"center\">" + r.code + "</td><td align=\"center\">" + r.name + "</td>";
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
		
	$(document).ready(function(){
		$("#sbtype").bind("change", function(){
			loadSbtype($("#sbtype").val());
		});
	});
	</script>
</body>
</html>