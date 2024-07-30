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
							<a href='<c:url value="/home"/>'>Home</a> <span>Booking History</span>
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
							<form action="<c:url value="/booking-history"/>" method="post">
							<input type="hidden" name="maPD" value="${room[6]}"/>
								<h4>${room[3]}</h4>
								<h3>
									<fmt:formatNumber value="${room[4]}"
										type="currency" currencySymbol="đ" pattern="#,##0đ" />
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
										type="currency" currencySymbol="đ" pattern="#,##0đ" /></td>
										</tr>
										<tr>
											<td id="roomAvailable" class="r-o"><b>Status:</b> </td>
											<td>${room[5]}</td>
										</tr>
										<tr>
										<c:if test="${room[5] == 'Booked'}">
												<td colspan="2"><button class="button-link" type="submit" onclick="return cancelBooking()">Cancel</button></td>
										</c:if>
										
										</tr>
										<c:if test="${room[5] != 'Booked'}">
											<tr>
											<td><br/></td>
											<td><br/></td>
											</tr>
										</c:if>
										
									</tbody>
								</table>
								<script type="text/javascript">
											function cancelBooking() {
												if (confirm('Are you sure to cancel this booking?')) {
                                                    return true;
                                                }
												return false;
											}
								</script>
								</form>
							</div>
						</div>
					</div>
				</c:forEach>
				
			</div>
		</div>
	</section>
</body>
</html>