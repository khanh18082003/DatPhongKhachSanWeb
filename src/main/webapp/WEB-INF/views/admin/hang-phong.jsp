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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
<title>Admin | Room</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<div id="layoutSidenav_content">
		<main>

			<div class="container-fluid px-4">
				<div id="edit-modal" class="modal">
					<div class="modal-content">
						<span class="close">&times;</span>
						<h2>Sửa thông tin phòng</h2>
						<form:form action="update.html" id="edit-form"
							modelAttribute="HangPhong">
							<form:input type="hidden" class="room-id" path="idHP"/>

							<label for="room-name">Hạng Phòng:</label>
							<form:input type="text" class="room-name" path="tenHP" required="required"/>

							<label for="room-type">Loại Phòng:</label>
							<form:select class="room-type" path="loaiPhong" items="${lp}" />

							<label for="room-type">Kiểu Phòng:</label>
							<form:select class="room-type" path="kieuPhong" items="${kp}" />

							<label for="room-type">Số Lượng Người:</label>
							<form:input type="number" class="room-quantity"
								path="soLuongNguoi" min="0" />

							<%-- <label for="room-type">Mô Tả:</label>
							<form:input type="text" class="room-description" path="moTa" /> --%>

							<label for="room-price">Giá Phòng:</label>
							<form:input type="number" class="room-price" path="gia" />

							<form:button type="submit">Lưu</form:button>
						</form:form>


					</div>
				</div>
				<h1 class="mt-4">Hạng phòng</h1>
				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item active">Hạng phòng</li>
				</ol>

				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-table me-1"></i> Hạng phòng
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>#</th>
									<th>ID Hạng Phòng</th>
									<th>Tên Hạng Phòng</th>
									<th>Loại Phòng</th>
									<th>Kiểu Phòng</th>
									<th>Số Lượng Người</th>
									<th>Mô Tả</th>
									<th>Giá</th>
									<th>Hành Động</th>

								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>#</th>
									<th>ID Hạng Phòng</th>
									<th>Tên Hạng Phòng</th>
									<th>Loại Phòng</th>
									<th>Kiểu Phòng</th>
									<th>Số Lượng Người</th>
									<th>Mô Tả</th>
									<th>Giá</th>
									<th>Hành Động</th>
								</tr>
							</tfoot>
							<tbody>
								<!-- 	<tr>
									<td>1</td>
									<td>PH001</td>
									<td>Phòng Bình Dân 1</td>
									<td>Giường đôi</td>
									<td>Có ban công</td>
									<td>2 người lớn, 2 trẻ em</td>
									<td>Phòng rộng rãi, thoáng mát, phù hợp cho gia đình.</td>
									<td>100.000 VNĐ</td>
									<td>
										<button class="btn-action" data-action="edit">Sửa</button> |
										<button class="btn-action" data-action="delete">Xóa</button>
									</td>

								</tr>
 -->
								<c:forEach var="list" items="${listHP}" varStatus="status">

									<tr class="room-row">
										<td>${status.index}</td>
										<td>${list.idHP }</td>
										<td>${list.tenHP }</td>
										<td>${list.loaiPhong.tenLP }</td>
										<td>${list.kieuPhong.tenKP }</td>
										<td>${list.soLuongNguoi }</td>
										<td>${list.gia }</td>
										<td><fmt:formatNumber value="${list.gia }"
												type="currency" currencyCode="VND" /></td>
										<%-- ${fn:substring(0, 10, list.moTa ) } --%>
										<td>
											<button class="btn-action" data-action="edit"
												name="${status.index}" id="editButton_${status.index}">Sửa</button> |

											<button class="btn-action" data-action="delete">Xóa</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
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
									 const editButtons = document.querySelectorAll('.btn-action[data-action="edit"]');

										editButtons.forEach(button => {
										  button.addEventListener('click', (event) => {
										    const row = event.target.closest('tr');
										    const id = row.dataset.id;

										    // Mở modal sửa với dữ liệu của phòng được chọn
										    openEditModal(id);
										  });
										});

										function openEditModal(id) {
										  // Lấy dữ liệu của phòng cần sửa
										  const roomData = getRoomDataById(id);

										  // Hiển thị modal sửa với dữ liệu đã lấy
										  const modal = document.getElementById('edit-modal');
										  modal.style.display = 'block';

										  const roomNameInput = document.getElementById('room-name');
										  roomNameInput.value = roomData.name;

										  const roomTypeSelect = document.getElementById('room-type');
										  roomTypeSelect.value = roomData.type;

										  const roomPriceInput = document.getElementById('room-price');
										  roomPriceInput.value = roomData.price;

										  const saveButton = document.getElementById('save-button');
										  saveButton.addEventListener('click', () => {
										    updateRoom(id, roomNameInput.value, roomTypeSelect.value, roomPriceInput.value);
										    closeEditModal();
										  });
										}

										function closeEditModal() {
										  const modal = document.getElementById('edit-modal');
										  modal.style.display = 'none';
										}
										
										const closeButton = document.querySelector('.close');
										closeButton.addEventListener('click', closeEditModal);

										function getRoomDataById(id) {
											  // Lấy danh sách phòng từ HTML, bạn có thể lấy từ model ở controller của bạn
											  const rooms = document.querySelectorAll('.room-row');

											  // Tìm phòng có ID tương ứng trong danh sách
											  for (let room of rooms) {
											    const roomId = room.dataset.id;
											    if (roomId === id) {
											      // Trả về thông tin của phòng nếu tìm thấy
											      return {
											        id: roomId,
											        name: room.querySelector('.room-name').textContent,
											        type: room.querySelector('.room-type').textContent,
											        price: room.querySelector('.room-price').textContent
											        // Thêm các trường khác nếu cần thiết
											      };
											    }
											  }

										function updateRoom(id, name, type, price) {
										  // Cập nhật dữ liệu của phòng trong cơ sở dữ liệu (ví dụ: API hoặc localStorage)
										  // ...
										}
									</script>

</body>
</html>