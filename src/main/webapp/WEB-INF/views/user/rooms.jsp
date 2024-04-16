<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/" />
<meta charset="UTF-8">
<title>Sona | Rooms</title>
</head>
<body>
	<div class="breadcrumb-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="breadcrumb-text">
						<h2>Our Rooms</h2>
						<div class="bt-option">
							<a href='<c:url value="/trang-chu"/>'>Home</a> <span>Rooms</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="lookfor-room">
		<form action="#">
			<input class="inputroom date-input" type="text"
				placeholder="Check in" id="date-in"> <input
				class="inputroom date-input" type="text" placeholder="Check out"
				id="date-out"> <select class="inputroom select-option">
				<option value="">2 Adults</option>
				<option value="">3 Adults</option>
			</select> <select class="inputroom select-option">
				<option value="">1 Room</option>
				<option value="">2 Room</option>
			</select>
			<button class="inputroom" type="submit">Look For Room</button>
		</form>
	</div>
	<section class="rooms-section spad">
		<div class="container">
			<div class="row">
				<c:forEach items="${roomList}" var="room">
					<div class="col-lg-4 col-md-6">
						<div class="room-item">
							<img src="<c:url value="${room.anh}"/>"
								alt="">
							<div class="ri-text">
								<h4>${room.tenHP}</h4>
								<h3>
									${room.gia}<span>/Pernight</span>
								</h3>
								<table>
									<tbody>
										<tr>
											<td class="r-o">Size:</td>
											<td>${room.soLuongNguoi} Adults</td>
										</tr>
										<tr>
											<td class="r-o">Size:</td>
											<td>30 ft</td>
										</tr>
										<tr>
											<td class="r-o">Capacity:</td>
											<td>Max persion 3</td>
										</tr>
										<tr>
											<td class="r-o">Bed:</td>
											<td>King Beds</td>
										</tr>
										<tr>
											<td class="r-o">Services:</td>
											<td>Wifi, Television, Bathroom,...</td>
										</tr>
									</tbody>
								</table>
								<a href="#" class="primary-btn">More Details</a>
							</div>
						</div>
					</div>
				</c:forEach>
				<div class="col-lg-12">
					<div class="room-pagination">
						<a href="#">1</a> <a href="#">2</a> <a href="#">Next <i
							class="fa fa-long-arrow-right"></i></a>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>