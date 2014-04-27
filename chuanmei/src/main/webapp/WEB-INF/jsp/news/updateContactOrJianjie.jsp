<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<meta name="keywords" content="${seo_keywords}">
<meta name="description" content="${seo_desc}">
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
<link rel="stylesheet"
	href="/resources/kindeditor-4.1.10/themes/default/default.css" />
<script charset="utf-8"
	src="/resources/kindeditor-4.1.10/kindeditor-min.js"></script>
<script charset="utf-8" src="/resources/kindeditor-4.1.10/lang/zh_CN.js"></script>

<script>
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="content"]', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
					'bold', 'italic', 'underline', 'removeformat', '|',
					'justifyleft', 'justifycenter', 'justifyright',
					'insertorderedlist', 'insertunorderedlist', '|',
					'emoticons', 'image', 'link' ]
		});
	});
</script>

</head>
<body>
	<div class="manage-container">
		<jsp:include page="../include/headback.jsp" flush="true"></jsp:include>
		<div class="manage_banner">
			<img src="/resources/images/manage_banner.png" width="995px"
				height="234px;" />
		</div>
		<div style="clear: both"></div>
		<jsp:include page="../include/navigate.jsp" flush="true"></jsp:include>
		<div class="manage_right">
			<div class="manage_breadcrumb">当前位置：首页 > ${navi}</div>
			<form action="/news/doupdateContactOrJianjie" method="post">
				<input type="hidden" name="id" value="${news.id}" />
				<table border="0" cellspacing="0" cellpadding="0" width="700px">
					<tbody>

						<tr>
							<td>标题</td>
							<td><c:if test="${news.id==1}">
									<input type="text" class="input-title" name="title" readOnly
										value="联系我们">
								</c:if>
								<c:if test="${news.id==2}">
									<input type="text" class="input-title" name="title" readOnly
										value="实验中心简介">
								</c:if><font color="red">${info}</font></td>
						</tr>
						<tr>
							<td>内容</td>
							<td><textarea name="content"
									style="width: 400px; height: 300px; visibility: hidden;">${news.content}</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit"
								value="提交"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>
</html>