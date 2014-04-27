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
	<div class="container">
		<jsp:include page="../include/head2.jsp"></jsp:include>
		<table cellspacing="0" cellpadding="0" width="951" class="style2"
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
										<table border="0" cellspacing="0" cellpadding="0" width="36%">
											<tbody>
												<tr>
													<td width="7%"><img src="/resources/img/left_8.gif"
														width="16" height="16"></td>
													<td>
														<table class="winstyle6522" cellspacing="0"
															cellpadding="0">
															<tbody>
																<tr>
																	<td>当前位置：首页> ${navigateName}> 详情</td>
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
														<h2 align="center">${news.title}</h2>
														<hr> ${news.content} <br /> <span
														style="float: right"><fmt:formatDate
																value="${news.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
													</span>
													</td>
												</tr>

											</tbody>
										</table></td>
								</tr>
							</tbody>
						</table></td>
				</tr>
			</tbody>
		</table>
		<jsp:include page="../include/foot.jsp"></jsp:include>
	</div>
</body>
</html>