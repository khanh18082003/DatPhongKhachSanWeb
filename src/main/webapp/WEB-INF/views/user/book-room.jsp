<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<<<<<<< HEAD

							<a href="<c:url value="/home"/>">Home</a> <span>Rooms</span>
=======
							<a href="<c:url value="/home"/>">Home</a> <span>Book
								Room</span>
>>>>>>> f105c8141dede0c0fc62734681f1676464436e1e
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Breadcrumb Section End -->
	<!-- Room Details Section Begin -->
	<section class="room-details-section spad">
		<form:form action='booking-room' method="post"
			modelAttribute="khachHang">
			<div class="container">
				<div class="row">
					<div class="col-lg-8">
						<div class="room-details-item">
							<h3>Enter Your Details</h3>
							<br />
							<div class="room-booking">
								<c:set var="readonly" value='true' />
								<c:if
									test="${khachHang.ho==null || khachHang.ho.trim().length()==0 
									|| khachHang.ten==null || khachHang.ten.trim().length()==0 
									|| khachHang.sdt==null || khachHang.sdt.trim().length()==0 }">
									<c:set var="readonly" value='false' />
								</c:if>
								<div class="fullName">
									<div class="panel" id="firstName">
										<label>First Name</label> <span><form:errors
												style="color:red" path="ho" cssClass="error" /></span>
										<form:input path="ho"
											oninput="this.value = this.value.replace(/\s{2,}/g, ' ').replace(/[^a-zA-Z\s]/g, '');"
											readonly='${readonly}' />
									</div>
									<div class="panel" id="lastName">
										<label>Last Name</label>
										<form:errors style="color:red" path="ten" cssClass="error" />
										<form:input path="ten"
											oninput="this.value = this.value.replace(/[^a-zA-Z]/g,'');"
											readonly='${readonly}' />
									</div>
								</div>

								<div class="panel">
									<label>Phone Number</label>
									<form:errors style="color:red" path="sdt" cssClass="error" />
									<form:input path="sdt" onkeypress="return isNumberKey(event)"
										readonly='${readonly}' />
								</div>
								<div class="panel">
									<label>Email:</label> <input
										value="${ctPhieuDat.phieuDat.taiKhoan.username}" readonly />
								</div>
								<button type="submit">Book Room</button>

							</div>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="bookingdetail">
							<h4>Your Booking Details</h4>
							<br />
							<div>
								<b style="font-size: 25px">${ctPhieuDat.hangPhong.tenHP}</b>
							</div>
							<div>
								<label>Check in: </label> <b> <fmt:formatDate
										pattern="dd MM yyyy" value="${ctPhieuDat.phieuDat.ngayBD}" /></b>
								<label style="color: #b7bfc6">(13:00 - 14:00)</label>
							</div>
							<div>
								<label>Check out: </label> <b><fmt:formatDate
										pattern="dd MM yyyy" value="${ctPhieuDat.phieuDat.ngayKT}" />
								</b> <label style="color: #b7bfc6">(11:30 - 12:30)</label>
							</div>
							<div>
								<label>Number of Rooms: </label> <b>${ctPhieuDat.sLPhong}</b>
							</div>
							<div style="font-size: 30px">
								<b>Total: <fmt:formatNumber
										value="${ctPhieuDat.hangPhong.gia * ctPhieuDat.sLPhong * soNgay * (100 - discount)/100}"
										type="currency" currencySymbol="$" pattern="$#,##0.00" /></b>
							</div>
						</div>
					</div>
				</div>

			</div>

		</form:form>
	</section>
	<!-- Room Details Section End -->
	<script type="text/javascript">
	function isNumberKey(evt) {
	    var charCode = (evt.which) ? evt.which : event.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	        return false;
	    }
	    return true;
	}</script>
</body>
</html>