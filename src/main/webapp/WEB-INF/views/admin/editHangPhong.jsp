<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin | Room</title>
</head>
<body>
	<div id="layoutSidenav_content">
		<main>
			<div id="edit-modal" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span> <span class="message">${message}</span>
					<h2>Edit Room</h2>
					<form:form action="editHangPhong.html" method="POST" id="edit-form"
						modelAttribute="hangPhong">
						<label for="room-id">ID:</label>
						<form:input type="text" class="room-id" path="idHP"
							readonly="true" />

						<form:input type="hidden" path="anh" />

						<label for="room-name">Room:</label>
						<form:input type="text" class="room-name" path="tenHP"
							required="required" />
						<form:errors path="tenHP" />

						<label for="room-type">Type:</label>
						<form:select class="room-type" path="loaiPhong.maLP"
							items="${listLP}" itemValue="maLP" itemLabel="tenLP" />


						<label for="room-type">Category:</label>
						<form:select class="room-cate" path="kieuPhong.maKP"
							items="${listKP}" itemValue="maKP" itemLabel="tenKP" />


						<label for="room-type">Number Of People:</label>
						<form:input type="number" class="room-quantity"
							path="soLuongNguoi" min="1" value="1" />

						<label for="room-type">Description:</label>
						<form:input type="text" class="room-description" path="moTa"
							required="required" />

						<label for="room-price">Price:</label>
						<form:input type="number" class="room-price" path="gia" min="0"/>

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

							window.location.href = '/DatPhongKhachSanWeb/admin/hang-phong.html';
						});

		const closeButton = document.querySelector('.close'); // Get the close button element

		closeButton
				.addEventListener(
						'click',
						function() {
							// Handle the click event here
							window.location.href = '/DatPhongKhachSanWeb/admin/hang-phong.html';
						});
	</script>


</body>
</html>