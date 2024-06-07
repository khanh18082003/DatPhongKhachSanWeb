<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="dec"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title><dec:title default="Sona | Home" /></title>
<base href="${pageContext.servletContext.contextPath}/" />
<link rel="icon" href="<c:url value='${pathLogo}'/>" type="image/icon type">
<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet"
	href="<c:url value='/template/user/css/bootstrap.min.css'/>"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/template/user/css/font-awesome.min.css'/>"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/template/user/css/elegant-icons.css'/>"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/template/user/css/flaticon.css'/>" type="text/css">
<link rel="stylesheet"
	href="<c:url value='/template/user/css/owl.carousel.min.css'/>"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/template/user/css/nice-select.css'/>"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/template/user/css/jquery-ui.min.css'/>"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/template/user/css/magnific-popup.css'/>"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/template/user/css/slicknav.min.css'/>"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/template/user/css/style.css'/>" type="text/css">
</head>
<body>
	<%@ include file="/common/user/header.jsp"%>

	<dec:body />

	<%@ include file="/common/user/footer.jsp"%>

	<!-- Js Plugins -->
	<script src="<c:url value='/template/user/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/template/user/js/bootstrap.min.js'/>"></script>
	<script
		src="<c:url value='/template/user/js/jquery.magnific-popup.min.js'/>"></script>
	<script
		src="<c:url value='/template/user/js/jquery.nice-select.min.js'/>"></script>
	<script src="<c:url value='/template/user/js/jquery-ui.min.js'/>"></script>
	<script src="<c:url value='/template/user/js/jquery.slicknav.js'/>"></script>
	<script src="<c:url value='/template/user/js/owl.carousel.min.js'/>"></script>
	<script src="<c:url value='/template/user/js/main.js'/>"></script>
</body>
</html>