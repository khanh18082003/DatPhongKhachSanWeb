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
					<!-- <span class="close">&times;</span> -->
					<h2>Sửa thông tin phòng</h2>
					<form:form action="editHangPhong.html" method="POST" id="edit-form"
						modelAttribute="hangPhong">
						<label for="room-id">ID Hạng Phòng:</label>
						<form:input type="text" class="room-id" path="idHP"
							readonly="true" />
							
<%-- 						<p>${hangPhong.anh }</p> --%>

						<label for="room-name">Hạng Phòng:</label>
						<form:input type="text" class="room-name" path="tenHP"
							required="required" />

						<label for="room-type">Loại Phòng:</label>
						<form:select class="room-type" path="loaiPhong.maLP"
							items="${listLP}" itemValue="maLP" itemLabel="tenLP" />
					

						<label for="room-type">Kiểu Phòng:</label>
						<form:select class="room-type" path="kieuPhong.maKP"
							items="${listKP}" itemValue="maKP" itemLabel="tenKP" />
					

						<label for="room-type">Số Lượng Người:</label>
						<form:input type="number" class="room-quantity"
							path="soLuongNguoi" min="0" />

						<label for="room-type">Mô Tả:</label>
						<form:input type="text" class="room-description" path="moTa" />

						<label for="room-price">Giá Phòng:</label>
						<form:input type="number" class="room-price" path="gia" />
						<form:input type="hidden" path="anh" />
						<form:button type="submit">Lưu</form:button>
					</form:form>


				</div>
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

</body>
</html>