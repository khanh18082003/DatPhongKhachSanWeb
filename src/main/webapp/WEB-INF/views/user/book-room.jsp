<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/" />
<meta charset="UTF-8">
<title>Sona | Book-Room</title>
</head>
<body>
	<!-- Breadcrumb Section Begin -->
	<div class="breadcrumb-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="breadcrumb-text">
						<h2>Our Rooms</h2>
						<div class="bt-option">
							<a href="<c:url value="/trang-chu"/>">Home</a> <span>Rooms</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Breadcrumb Section End -->
	<!-- Room Details Section Begin -->
	<section class="room-details-section spad">
	<form action='<c:url value="/booking-room"/>' method="post">
		<div class="container">
			<div class="row">
				<div class="col-lg-8">
					<div class="room-details-item">
						<h3>Enter Your Details</h3>
						<br />
						<div class="room-booking">
								<div class="fullName">
									<div class="panel" id="firstName">
										<label>First Name</label> <input type="text" id="fristName">
									</div>
									<div class="panel" id="lastName">
										<label>Last Name</label> <input type="text" id="lastName">
									</div>
								</div>
								<div class="panel">
									<label>Email:</label> <input type="text" id="email" name = "email">
								</div>
								<div class="panel">
									<label>Confirm Email</label> <input type="text"
										id="confirmEmail">
								</div>
								<div class="panel">
									<label>Number Phone</label> <input type="text" id="phoneNumber">
								</div>
								<button type="submit">Book Room</button>
							
						</div>
					</div>
				</div>
				<div class="col-lg-4">
				<c:set var="checkin" value="${dateIn}" />
				<c:set var="checkout" value="${dateOut}" />
				<c:set var="numberOfRoom" value="${numberOfRoom}" />
				<input type="hidden" name="checkin" value="${checkin}" />
    			<input type="hidden" name="checkout" value="${checkout}" />
    			<input type="hidden" name="idHP" value="${idHP}"/>
    			<input type="hidden" name="numberOfRoom" value="${numberOfRoom}"/>
					<div class="bookingdetail">
						<h4>Your Booking Details</h4>
						<br/>
						<div>
							<label>Check in: </label>
							<b>${checkin} </b>
							<label style="color: #b7bfc6">(13:00 - 14:00)</label>
						</div>
						<div>
							<label>Check out: </label>
							<b>${checkout} </b>
							<label style="color: #b7bfc6">(11:30 - 12:30)</label>
						</div>
						<div>
							<label>Number of Rooms: </label>
							<b>${numberOfRoom}</b>
						</div>
						<div style="font-size: 30px">
							<b>Total: <fmt:formatNumber  value="${price * numberOfRoom}" type="currency"
									currencySymbol="$" pattern="$#,##0.00" /></b>
						</div>
					</div>
				</div>
			</div>
			
		</div>
		
		</form>
	</section>
	<!-- Room Details Section End -->
</body>
</html>