<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="dec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><dec:title default="Sona | Update" /></title>
<!-- Icons font CSS-->
<link
	href='<c:url value="/template/update-form/vendor/mdi-font/css/material-design-iconic-font.min.css"/>'
	rel="stylesheet" media="all">
<link
	href="<c:url value="/template/update-form/vendor/font-awesome-4.7/css/font-awesome.min.css"/>"
	rel="stylesheet" media="all">
<!-- Font special for pages-->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i"
	rel="stylesheet">

<!-- Vendor CSS-->
<link
	href="<c:url value="/template/update-form/vendor/select2/select2.min.css"/>"
	rel="stylesheet" media="all">
<link
	href="<c:url value="/template/update-form/vendor/datepicker/daterangepicker.css"/>"
	rel="stylesheet" media="all">

<!-- Main CSS-->
<link href="<c:url value="/template/update-form/css/main.css"/>"
	rel="stylesheet" media="all">
</head>
<style>
	.page-wrapper {
		overflow: hidden;
		position: relative;
	}
	.menu {
		background-color: #fff;
	}
	
	.menu .logo {
		padding: 25px 0;
	}
	
	.container {
		width: 100%;
		padding-right: 15px;
		padding-left: 15px;
	}
	@media (min-width: 576px) {
		.container {
    		max-width: 540px;
    		padding-right: 30px;
			padding-left: 30px;
    	}
	}
	@media (min-width: 768px) {
		.container {
    		max-width: 720px;
    		padding-right: 50px;
			padding-left: 50px;
    	}
	}
	@media (min-width: 992px) {
		.container {
    		max-width: 960px;
    		padding-right: 70px;
			padding-left: 70px;
    	}
	}
	@media only screen and (min-width: 1200px) {
		.container {
			max-width: 1170px;
			padding-right: 100px;
			padding-left: 100px;
		}
	}
	
	@keyframes slideIn {
    	0% {
        	right: -300px; 
        	opacity: 0; 
    	}
    	100% {
        	right: 8px; 
        	opacity: 1;
    	}
	}
	
	@keyframes slideOut {
		0% {
        	right: 10px;
        	opacity: 1;
    	}
    	100% {
        	right: -300px;
        	opacity: 0;
    	}
	}
	
	.flash-box {
		display: flex;
		position: absolute;
		top: 45px;
		right: -300px;
		align-items: center;
		justify-content: center;
		width: 270px;
		background-color: #fff;
		border-radius: 5px;
		box-shadow: 0px 8px 10px rgba(0, 0, 0, 0.2);
	}
	
	.flash-box.show {
		animation: slideIn 1s forwards;
		animation-delay: 1s;
	}
	
	.flash-box::after {
		content: "";
		position: absolute;
		bottom: 0;
		right: 0;
		height: 3px;
		width: 0%;
		background-color: green;
		transition: width 2s;
	}
	
	.flash-box.show::after {
		width: 100%;
	}

	.flash-box.hide {
		right: 8px;
		animation: slideOut 1s forwards;
		animation-delay: 5s;
	}
	
	.icon-success {
		color: #fff;
		font-size: 24px;
	}
	
	.success-icon {
		padding: 14px;
		background-color: #4caf50;
		border-bottom-left-radius: 5px;
		border-top-left-radius: 5px; 
	}
	
	.success-text {
		font-size: 14px;
		font-weight: 600;
		margin-left: 10px;
	}
</style>
<body>
	<div class="menu">
		<div class="container">
			<div class="row">
				<div class="col-lg-2">
					<div class="logo">
						<a href="<c:url value="/home"/>"> <img
							src="<c:url value="/template/user/img/logo.png"/>" alt="Logo">
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<dec:body />
</body>
<!-- Jquery JS-->
<script
	src="<c:url value="/template/update-form/vendor/jquery/jquery.min.js"/>"></script>
<!-- Vendor JS-->
<script
	src="<c:url value="/template/update-form/vendor/select2/select2.min.js"/>"></script>
<script
	src="<c:url value="/template/update-form/vendor/datepicker/moment.min.js"/>"></script>
<script
	src="<c:url value="/template/update-form/vendor/datepicker/daterangepicker.js"/>"></script>

<!-- Main JS-->
<script src="<c:url value="/template/update-form/js/global.js"/>"></script>
</html>