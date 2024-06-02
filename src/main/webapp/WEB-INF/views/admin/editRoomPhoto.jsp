<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8">
<title>Admin | Room</title>
</head>
<body>
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-4">
				<div class="form-noi">

					<span class="close">&times;</span> 
					<span class="message">${message}</span>
					<h2>Photo</h2>
					<form:form action="editRoomPhoto.html" method="POST"
						modelAttribute="hangPhong" enctype="multipart/form-data">

						<form:select type="select" class="room-type hidden-select"
							path="loaiPhong.maLP" items="${listLP}" itemValue="maLP"
							itemLabel="tenLP" />
						<form:select type="select" class="room-type hidden-select"
							path="kieuPhong.maKP" items="${listKP}" itemValue="maKP"
							itemLabel="tenKP" />
						<form:input type="hidden" class="room-quantity"
							path="soLuongNguoi" min="1" value="1" />
						<form:input type="hidden" class="room-description" path="moTa" />
						<form:input type="hidden" class="room-price" path="gia" min="0"
							value="0" />

						<div class="form-group">
							<label for="tieu_de_anh">${hangPhong.idHP }:</label>
							<form:input type="text" class="room-id" path="idHP"
								required="required" readonly="true" />
							<label for="room-name">Room:</label>
							<form:input type="text" class="room-name" path="tenHP"
								required="required" readonly="true" />
						</div>
						<div class="form-group">
							<img style="display: block; max-width: 50%" alt=""
								src='<c:url value="${hangPhong.anh }"/>'>
						</div>
						<div class="form-group">
							<label for="chon_file_anh">Choose Photo:</label> <input
								type="file" class="room-photo" name="photo" required="required"
								onchange="previewImage(event)" />
							<form:errors path="anh" />
							<img id="photoPreview" src="#" alt="Preview"
								style="display: none; max-width: 50%;" />
						</div>
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

		function previewImage(event) {
			var reader = new FileReader();

			reader.onload = function() {
				var output = document.getElementById('photoPreview');
				output.src = reader.result;
				output.style.display = 'block';
			}

			reader.readAsDataURL(event.target.files[0]);
		}
	</script>
</body>
</html>