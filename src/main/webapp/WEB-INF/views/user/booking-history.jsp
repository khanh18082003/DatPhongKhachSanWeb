<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8">
<title>Sona | Booking History</title>
</head>
<body>
	<div class="breadcrumb-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="breadcrumb-text">
						<h2>Our Rooms</h2>
						<div class="bt-option">
							<a href='<c:url value="/trang-chu"/>'>Home</a> <span>Booking History</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<section class="rooms-section spad">
		<div class="container">
			<div class="row">
				<c:forEach items="${bookingHistoryList}" var="room">
					<div class="col-lg-4 col-md-6">
						<div class="room-item">
							<div class="ri-text">
								<h4>${room[3]}</h4>
								<h3>
									<fmt:formatNumber value="${room[4]}"
										type="currency" currencySymbol="$" pattern="$#,##0.00" />
									<span>/Pernight</span>
								</h3>
								<table>
									<tbody>
										<tr>
											<td class="r-o"><b>Checkin:</b> </td>
											<td>${room[1]}</td>
										</tr>
										<tr>
											<td class="r-o"><b>Checkout:</b> </td>
											<td>${room[2]}</td>
										</tr>
										<tr>
											<td class="r-o"> <b>Toltal:</b> </td>
											<td><fmt:formatNumber value="${room[0]}"
										type="currency" currencySymbol="$" pattern="$#,##0.00" /></td>
										</tr>
										
										<tr>
											<td id="roomAvailable" class="r-o"><b>Status:</b> </td>
											<td>Da dat</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
</body>
</html>