<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="dec" %>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/" />
<meta charset="UTF-8">
<link rel="icon" href="<c:url value='/template/user/img/Hotel Logo.png'/>" type="image/icon type">
<!-- Font Icon -->
<link rel="stylesheet"
	href='<c:url value="/template/login/fonts/material-icon/css/material-design-iconic-font.min.css"/>'>

<!-- Main css -->
<link rel="stylesheet"
	href='<c:url value="/template/login/css/style.css"/>'>
<title><dec:title default="Sona | Login"/></title>
</head>
<body>
	<div class="main">
		<dec:body/>
	</div>

	<!-- JS -->
	<script
		src='<c:url value="/template/login/vendor/jquery/jquery.min.js"/>'></script>
	<script src='<c:url value="/template/login/js/main.js"/>'></script>
</body>

</html>