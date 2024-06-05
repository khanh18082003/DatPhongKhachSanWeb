<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/" />
<meta charset="UTF-8">
<title>Sona | Room-Detail</title>
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
		<div class="container">
			<div class="row">
				<c:set var="checkin" value="${dateIn}" />
				<c:set var="checkout" value="${dateOut}" />
				<c:set var="room" value="${hp}" />
				<div class="col-lg-8">
					<div class="room-details-item">
						<img src="<c:url value="${room.anh}"/>" alt="" width="750"
							height="450">
						<div class="rd-text">
							<div class="rd-title">
								<h3>${room.tenHP}</h3>
								<div class="rdt-right">
								    <form action='<c:url value="/book-room"/>' method = "post">
										<span style="margin-right: 10px"> <select
											name ="sl" id="roomSelect" class="inputroom select-option">
												<c:forEach begin="1" end="${slPhong}" varStatus="status">
														<option value="${status.count}">${status.count} ${status.count == 1 ? 'Room' : 'Rooms'}</option>
												</c:forEach>
										</select>
										</span>
										<input type="hidden" name="checkin" value="${checkin}" />
										<input type="hidden" name="checkout" value="${checkout}" />
										<input type="hidden" name="id" value="${room.idHP}" />
										<button type ="submit" id="bookingLink">Booking Now</button>
									</form>

								</div>
							</div>
							<br /> <br /> <br />
							<h2> 
											<fmt:formatNumber value="${room.gia * (100 - discount)/100}"
												type="currency" currencySymbol="$" pattern="$#,##0.00" />
								<span>/Pernight</span>
							</h2>
							<table>
								<tbody>
									<tr>
										<td class="r-o">Capacity:</td>
										<td><c:choose>
												<c:when test="${room.soLuongNguoi ==1}">${room.soLuongNguoi} Person</c:when>
												<c:otherwise>${room.soLuongNguoi} Persons</c:otherwise>
											</c:choose></td>
									</tr>
									<tr>
										<td class="r-o">Room Type:</td>
										<td>${room.loaiPhong.tenLP}</td>
									</tr>
									<tr>
										<td class="r-o">Bed:</td>
										<td>${room.kieuPhong.tenKP}</td>
									</tr>
									<tr>
										<td class="r-o">Services:</td>
										<td>${room.moTa}</td>
									</tr>
								</tbody>
							</table>
							<p class="f-para">Motorhome or Trailer that is the question
								for you. Here are some of the advantages and disadvantages of
								both, so you will be confident when purchasing an RV. When
								comparing Rvs, a motorhome or a travel trailer, should you buy a
								motorhome or fifth wheeler? The advantages and disadvantages of
								both are studied so that you can make your choice wisely when
								purchasing an RV. Possessing a motorhome or fifth wheel is an
								achievement of a lifetime. It can be similar to sojourning with
								your residence as you search the various sites of our great
								land, America.</p>
							<p>The two commonly known recreational vehicle classes are
								the motorized and towable. Towable rvs are the travel trailers
								and the fifth wheel. The rv travel trailer or fifth wheel has
								the attraction of getting towed by a pickup or a car, thus
								giving the adaptability of possessing transportation for you
								when you are parked at your campsite.</p>
						</div>
					</div>
					<div class="rd-reviews">
						<h4>Reviews</h4>
						<div class="review-item">
							<div class="ri-pic">
								<img
									src="<c:url value="/template/user/img/room/avatar/avatar-1.jpg"/>"
									alt="">
							</div>
							<div class="ri-text">
								<span>27 Aug 2019</span>
								<div class="rating">
									<i class="icon_star"></i> <i class="icon_star"></i> <i
										class="icon_star"></i> <i class="icon_star"></i> <i
										class="icon_star-half_alt"></i>
								</div>
								<h5>Brandon Kelley</h5>
								<p>Neque porro qui squam est, qui dolorem ipsum quia dolor
									sit amet, consectetur, adipisci velit, sed quia non numquam
									eius modi tempora. incidunt ut labore et dolore magnam.</p>
							</div>
						</div>
						<div class="review-item">
							<div class="ri-pic">
								<img
									src="<c:url value="/template/user/img/room/avatar/avatar-2.jpg"/>"
									alt="">
							</div>
							<div class="ri-text">
								<span>27 Aug 2019</span>
								<div class="rating">
									<i class="icon_star"></i> <i class="icon_star"></i> <i
										class="icon_star"></i> <i class="icon_star"></i> <i
										class="icon_star-half_alt"></i>
								</div>
								<h5>Brandon Kelley</h5>
								<p>Neque porro qui squam est, qui dolorem ipsum quia dolor
									sit amet, consectetur, adipisci velit, sed quia non numquam
									eius modi tempora. incidunt ut labore et dolore magnam.</p>
							</div>
						</div>
					</div>
					<div class="review-add">
						<h4>Add Review</h4>
						<form action="#" class="ra-form">
							<div class="row">
								<div class="col-lg-6">
									<input type="text" placeholder="Name*">
								</div>
								<div class="col-lg-6">
									<input type="text" placeholder="Email*">
								</div>
								<div class="col-lg-12">
									<div>
										<h5>You Rating:</h5>
										<div class="rating">
											<i class="icon_star"></i> <i class="icon_star"></i> <i
												class="icon_star"></i> <i class="icon_star"></i> <i
												class="icon_star-half_alt"></i>
										</div>
									</div>
									<textarea placeholder="Your Review"></textarea>
									<button type="submit">Submit Now</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Room Details Section End -->
</body>
</html>