<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/"/>
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
	<section class="rooms-section spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-6">
					<div class="room-item">
						<img src="<c:url value="/template/user/img/room/room-1.jpg"/>" alt="">
						<div class="ri-text">
							<h4>Premium King Room</h4>
							<h3>
								159$<span>/Pernight</span>
							</h3>
							<table>
								<tbody>
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
				<div class="col-lg-4 col-md-6">
					<div class="room-item">
						<img src="<c:url value="/template/user/img/room/room-2.jpg"/>" alt="">
						<div class="ri-text">
							<h4>Deluxe Room</h4>
							<h3>
								159$<span>/Pernight</span>
							</h3>
							<table>
								<tbody>
									<tr>
										<td class="r-o">Size:</td>
										<td>30 ft</td>
									</tr>
									<tr>
										<td class="r-o">Capacity:</td>
										<td>Max persion 5</td>
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
							<a href="<c:url value="/rooms/room-detail"/>" class="primary-btn">More Details</a>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="room-item">
						<img src="<c:url value="/template/user/img/room/room-3.jpg"/>" alt="">
						<div class="ri-text">
							<h4>Double Room</h4>
							<h3>
								159$<span>/Pernight</span>
							</h3>
							<table>
								<tbody>
									<tr>
										<td class="r-o">Size:</td>
										<td>30 ft</td>
									</tr>
									<tr>
										<td class="r-o">Capacity:</td>
										<td>Max persion 2</td>
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
				<div class="col-lg-4 col-md-6">
					<div class="room-item">
						<img src="<c:url value="/template/user/img/room/room-4.jpg"/>" alt="">
						<div class="ri-text">
							<h4>Luxury Room</h4>
							<h3>
								159$<span>/Pernight</span>
							</h3>
							<table>
								<tbody>
									<tr>
										<td class="r-o">Size:</td>
										<td>30 ft</td>
									</tr>
									<tr>
										<td class="r-o">Capacity:</td>
										<td>Max persion 1</td>
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
				<div class="col-lg-4 col-md-6">
					<div class="room-item">
						<img src="<c:url value="/template/user/img/room/room-5.jpg"/>" alt="">
						<div class="ri-text">
							<h4>Room With View</h4>
							<h3>
								159$<span>/Pernight</span>
							</h3>
							<table>
								<tbody>
									<tr>
										<td class="r-o">Size:</td>
										<td>30 ft</td>
									</tr>
									<tr>
										<td class="r-o">Capacity:</td>
										<td>Max persion 1</td>
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
				<div class="col-lg-4 col-md-6">
					<div class="room-item">
						<img src="<c:url value="/template/user/img/room/room-6.jpg"/>" alt="">
						<div class="ri-text">
							<h4>Small View</h4>
							<h3>
								159$<span>/Pernight</span>
							</h3>
							<table>
								<tbody>
									<tr>
										<td class="r-o">Size:</td>
										<td>30 ft</td>
									</tr>
									<tr>
										<td class="r-o">Capacity:</td>
										<td>Max persion 2</td>
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