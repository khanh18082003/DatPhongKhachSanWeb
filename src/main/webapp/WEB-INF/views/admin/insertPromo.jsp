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
<title>Admin | Promotion</title>
</head>
<body>
	<div id="layoutSidenav_content">
		<main>
			<div id="edit-modal" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span> <span class="message">${message}</span>
					<h2>Insert New Promotion</h2>
					<form:form action="insertPromo.html" method="post" id="edit-form"
						modelAttribute="khuyenMai">
						<label for="promo-id">ID:</label>
						<form:input type="text" class="promo-id" path="maKM"
							required="required" />
						<form:errors path="maKM" />

						<label for="promo-name">Promotion:</label>
						<form:input type="text" class="promo-name" path="tenKM"
							required="required" />
						<form:errors path="tenKM" />

						<label for="promo-begin">Begin:</label>

						<input type="datetime-local" class="form-control mb-3"
							name="ngayBDStr">
						<form:input type="hidden" path="ngayBD" />

						<label for="promo-end">End:</label>
						<input type="datetime-local" class="form-control mb-3"
							name="ngayKTStr">
						<form:input type="hidden" path="ngayKT" />
						<form:errors path="ngayKT" />


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
		btnHuy.addEventListener('click', function() {
			/* // Sử dụng history.back() để quay lại trang trước
			history.back(); */
			window.location.href = '/DatPhongKhachSanWeb/admin/promotion.html';
		});
		const closeButton = document.querySelector('.close'); // Get the close button element

		closeButton.addEventListener('click', function() {
			// Handle the click event here
			window.location.href = '/DatPhongKhachSanWeb/admin/promotion.html';
		});
	</script>
</body>
</html>