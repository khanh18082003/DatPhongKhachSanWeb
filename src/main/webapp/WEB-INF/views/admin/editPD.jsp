<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8">
<title>Admin | Promotion Detail</title>
</head>
<body>
	<div id="layoutSidenav_content">
		<main>
			<div id="edit-modal" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span> <span class="message">${message}</span>
					<h2>Edit Promotion Detail</h2>
					<form:form action="editPD.html" method="post" id="edit-form"
						modelAttribute="CTKM">
						<%-- <label for="promo-id">ID:</label>
						<form:input type="number" class="pd-id" path="id" min="0"
							required="required" readonly="true" /> --%>
						<form:input type="hidden" path="id" />

						<label for="promo-name">Promotion:</label>
						<form:input type="hidden" class="promo-name" path="khuyenMai.maKM"
							required="required" readonly="true" />
						<input type="text" class="promo-name"
							value="${CTKM.khuyenMai.tenKM }" required="required"
							readonly="true" />


						<label for="room-type">Room:</label>
						<form:input type="hidden" class="room-name" path="hangPhong.idHP" />

						<input type="text" class="room-name"
							value="${CTKM.hangPhong.tenHP }" readonly="true" />
						<form:errors path="hangPhong" />

						<label for="pd-percent">Percent:</label>
						<form:input type="number" class="pd-percent" path="phanTramGiam"
							min="0" required="required" />

						<div class="form-group button-container">
							<button type="button" class="btn-edit btn-cancel" id="huy">Cancel</button>
							<button type="submit" class="btn-edit btn-save">Save</button>
						</div>

					</form:form>


				</div>
			</div>


		</main>


		<footer class="py-4 bg-light mt-auto">
			<div class="container-fluid px-4">
				<div class="d-flex align-items-center justify-content-between small">
					<div class="text-muted">Copyright &copy; Your Website 2023</div>
					<div>
						<a href="#">Privacy Policy</a> &middot; <a href="#">Terms
							&amp; Conditions</a>
					</div>
				</div>
			</div>
		</footer>
	</div>

	<script>
		const btnHuy = document.getElementById('huy');
		btnHuy
				.addEventListener(
						'click',
						function() {
							/* // Sử dụng history.back() để quay lại trang trước
							history.back(); */
							window.location.href = '/DatPhongKhachSanWeb/admin/promotion-detail?id=${idKM}';
						});
		const closeButton = document.querySelector('.close'); // Get the close button element

		closeButton
				.addEventListener(
						'click',
						function() {
							// Handle the click event here
							window.location.href = '/DatPhongKhachSanWeb/admin/promotion-detail?id=${idKM}';
						});
	</script>
</body>
</html>