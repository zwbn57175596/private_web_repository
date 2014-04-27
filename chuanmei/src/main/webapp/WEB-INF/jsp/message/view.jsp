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
			<div class="manage_breadcrumb">当前位置：首页 > 消息> 查看消息</div>
			<table border="0" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
					<tr>
						<td class="list_content">
							<h2 align="center">${message.title}</h2> <br />
							<p>${message.content}</p> <br /> <span style="float: right"><fmt:formatDate
									value="${message.cDate}" pattern="yyyy-MM-dd HH:mm:ss" /> </span>
						</td>
					</tr>

				</tbody>
			</table>
		</div>
	</div>
</body>
</html>