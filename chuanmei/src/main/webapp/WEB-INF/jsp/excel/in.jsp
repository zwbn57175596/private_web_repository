<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<meta name="keywords" content="${seo_keywords}">
<meta name="description" content="${seo_desc}">
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
<script type="text/javascript">
	function exportInfo(roleId) {
		if (confirm("确定导出?")) {
			location.href = "/excel/export?roleId=" + roleId;
		} else
			return false;
	}
</script>
</head>
<body><div class="manage-container">
	<jsp:include page="../include/headback.jsp" flush="true"></jsp:include>
	<div class="manage_banner">
		<img src="/resources/images/manage_banner.png" width="995px"
			height="234px;" />
	</div>
	<div style="clear: both"></div>
	<jsp:include page="../include/navigate.jsp" flush="true"></jsp:include>
	<div class="manage_right">
		<div class="manage_breadcrumb">当前位置：首页 >数据导入</div>
		<form action="/excel/importInfo" enctype="multipart/form-data"
			method="post">
			<table class="table_border"  width="500px">
				<tbody>
					<tr>
						<td>说明</td>
						<td><font color="red">role列说明：1管理员 2老师 3学生 4审核员 <br />gender列说明：0男
								1女<br />class: 班级名称,没有的默认填0<br />样例：<a
								href="/resources/data.xls">下载</a>
						</font></td>
					</tr>
					<tr>
						<td>选择excel</td>
						<td><input type="file" name="excelFile"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="提交"></td>
					</tr>
				</tbody>
			</table>
		</form>


		<br /> <br /> <input type="button" value="导出管理员"
			onclick="javascript:exportInfo(1)"> <input type="button"
			value="导出学生" onclick="javascript:exportInfo(3)"> <input
			type="button" value="导出老师" onclick="javascript:exportInfo(2)">
		<input type="button" value="导出审核员" onclick="javascript:exportInfo(4)">
	</div>
</body>
</html>