<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="dec"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/" />
<meta charset="UTF-8">
<link rel="icon"
	href="<c:url value='${pathLogo}'/>"
	type="image/icon type">
<!-- Font Icon -->
<link rel="stylesheet"
	href='<c:url value="/template/login/fonts/material-icon/css/material-design-iconic-font.min.css"/>'>
<link rel="stylesheet"
	href="https://unpkg.com/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<!-- Main css -->
<link rel="stylesheet"
	href='<c:url value="/template/login/css/style.css"/>'>
<title><dec:title default="Sona | Login" /></title>
</head>
<style>
	.menu {
		background-color: #fff;
	}
	
	.menu .logo {
		padding: 25px 0;
	}
</style>
<body>
	<div class="menu">
		<div class="container">
			<div class="row">
				<div class="col-lg-2">
					<div class="logo">
						<a href="<c:url value="/home"/>"> <img
							src="<c:url value="${pathLogoHotel}"/>" alt="Logo">
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="main">
		<dec:body />
	</div>

	<!-- JS -->
	<script
		src='<c:url value="/template/login/vendor/jquery/jquery.min.js"/>'></script>
	<script src='<c:url value="/template/login/js/main.js"/>'></script>
	<!-- reCAPTCHA Libary -->
	<script src='https://www.google.com/recaptcha/api.js?hl=en'></script>

</body>

</html>