<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="imgbox">
	<ul id="banner_img">
		<c:forEach items="${listFocus}" var="row">
			<li style="display: none;"><a href="#"><img width="893"
					height="262" src="${row.imgUrl}"> </a></li>
		</c:forEach>
	</ul>
	<div class="clear"></div>
	<div class="imgnum">
		<span class="">1</span> <span class="">2</span> <span class="">3</span>
	</div>
</div>
