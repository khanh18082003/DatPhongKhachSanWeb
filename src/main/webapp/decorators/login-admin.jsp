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
	href="<c:url value='/template/user/img/Hotel Logo.png'/>"
	type="image/icon type">
<title><dec:title default="Login - Admin" /></title>
<link href='<c:url value="/template/admin/css/styles.css"/>'
	rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>
</head>
<body class="bg-primary">
	<dec:body />
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src='<c:url value="/template/admin/js/scripts.js"/>'></script>
	<!-- reCAPTCHA Libary -->
	<script src='https://www.google.com/recaptcha/api.js?hl=en'></script>
</body>

</html>