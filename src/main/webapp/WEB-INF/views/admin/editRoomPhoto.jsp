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
					<h2>Photo</h2>
					<form:form action="editHangPhong.html" method="POST"
						modelAttribute="hangPhong" enctype="multipart/form-data">
						<div class="form-group">
							<label for="tieu_de_anh">${hangPhong.idHP }:</label>
							<form:input type="text" class="room-id" path="idHP"
								required="required" />
						</div>
						<!-- <div class="form-group">
							<label for="mo_ta_anh">Mô tả ảnh:</label>
							<textarea id="mo_ta_anh" name="mo_ta_anh"></textarea>
						</div> -->
						<div class="form-group">
							<img alt="" src='<c:url value="${hangPhong.anh }"/>'>
						</div>
						<div class="form-group">
							<label for="chon_file_anh">Choose Photo:</label> <input
								type="file" id="chon_file_anh" name="chon_file_anh" required>
						</div>
						<div class="form-group">
							<button type="button" class="btn btn-cancel" id="huy">Cancel</button>
							<button type="submit" class="btn btn-save">Save</button>
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
		btnHuy.addEventListener('click', function() {
			// Sử dụng history.back() để quay lại trang trước
			history.back();
		});
	</script>
</body>
</html>