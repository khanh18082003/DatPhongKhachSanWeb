<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!-- Page Preloder -->
<div id="preloder">
	<div class="loader"></div>
</div>
<div class="mobile-login">
	<a href='<c:url value="/sign-up"/>' title="Đăng nhập hoặc đăng ký"><i class="fa fa-user-circle"></i></a>
</div>
<!-- Offcanvas Menu Section Begin -->
<div class="offcanvas-menu-overlay"></div>
<div class="canvas-open">
	<i class="icon_menu"></i>
</div>
<div class="offcanvas-menu-wrapper">
	<div class="canvas-close">
		<i class="icon_close"></i>
	</div>
	<div class="search-icon  search-switch">
		<i class="icon_search"></i>
	</div>
	<nav class="mainmenu mobile-menu">
		<ul>
			<li class="active"><a href='<c:url value="/trang-chu"/>'>Home</a></li>
			<li><a href='<c:url value="/rooms/index"/>'>Rooms</a></li>
			<li><a href='<c:url value="/about-us"/>'>About Us</a></li>
			<li><a href="./pages.html">Pages</a>
				<ul class="dropdown">
					<li><a href="./room-details.html">Room Details</a></li>
					<li><a href="#">Deluxe Room</a></li>
					<li><a href="#">Family Room</a></li>
					<li><a href="#">Premium Room</a></li>
				</ul></li>
			<li><a href="./blog.html">News</a></li>
			<li><a href='<c:url value="/contact-us"/>'>Contact</a></li>
		</ul>
	</nav>
	<div id="mobile-menu-wrap"></div>
	<div class="top-social">
		<a href="#"><i class="fa fa-facebook"></i></a> 
		<a href="#"><i class="fa fa-twitter"></i></a> 
		<a href="#"><i class="fa fa-tripadvisor"></i></a>
		<a href="#"><i class="fa fa-instagram"></i></a>
	</div>
	<ul class="top-widget">
		<li><i class="fa fa-phone"></i> (12) 345 67890</li>
		<li><i class="fa fa-envelope"></i> nguyentrungk461@gmail.com</li>
	</ul>
</div>
<!-- Offcanvas Menu Section End -->

<!-- Header Section -->
<header class="header-section">
	<div class="top-nav">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<ul class="tn-left">
						<li><i class="fa fa-phone"></i> (12) 345 67890</li>
						<li><i class="fa fa-envelope"></i> nguyentrungk461@gmail.com</li>
					</ul>
				</div>
				<div class="col-lg-6">
					<div class="tn-right">
						<div class="top-social">
							<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
								class="fa fa-twitter"></i></a> <a href="#"><i
								class="fa fa-tripadvisor"></i></a> <a href="#"><i
								class="fa fa-instagram"></i></a>
						</div>
						<a class="login-btn" href='<c:url value="/sign-up"/>'>Đăng ký</a> 
						<a class="login-btn" href='<c:url value="/sign-in"/>'>Đăng nhập</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="menu-item">
		<div class="container">
			<div class="row">
				<div class="col-lg-2">
					<div class="logo">
						<a href='<c:url value="/trang-chu" />'> <img
							src="<c:url value='/template/user/img/logo.png'/>" alt="">
						</a>
					</div>
				</div>
				<div class="col-lg-10">
					<div class="nav-menu">
						<nav class="mainmenu">
							<ul>
								<li class="active"><a href='<c:url value="/trang-chu"/>'>Home</a></li>
								<li><a href='<c:url value="/rooms/index"/>'>Rooms</a></li>
								<li><a href='<c:url value="/about-us"/>'>About Us</a></li>
								<li><a href="./pages.html">Pages</a>
									<ul class="dropdown">
										<li><a href="./room-details.html">Room Details</a></li>
										<li><a href="./blog-details.html">Blog Details</a></li>
										<li><a href="#">Family Room</a></li>
										<li><a href="#">Premium Room</a></li>
									</ul></li>
								<li><a href="./blog.html">News</a></li>
								<li><a href='<c:url value="/contact-us"/>'>Contact</a></li>
							</ul>
						</nav>
						<div class="nav-right search-switch">
							<i class="icon_search"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</header>

<!-- Search model Begin -->
<div class="search-model">
	<div class="h-100 d-flex align-items-center justify-content-center">
		<div class="search-close-switch">
			<i class="icon_close"></i>
		</div>
		<form class="search-model-form">
			<input type="text" id="search-input" placeholder="Search here.....">
		</form>
	</div>
</div>
<!-- Search model end -->