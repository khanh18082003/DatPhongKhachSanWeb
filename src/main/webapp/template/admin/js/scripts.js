/*!
    * Start Bootstrap - SB Admin v7.0.7 (https://startbootstrap.com/template/sb-admin)
    * Copyright 2013-2023 Start Bootstrap
    * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
    */
    // 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
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
	  const roomData = getRoomData(id);

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

	function getRoomData(id) {
	  // Lấy dữ liệu của phòng từ cơ sở dữ liệu (ví dụ: API hoặc localStorage)
	  return {
	    id: 1,
	    name: 'Phòng Bình Dân',
	    type: 'double',
	    price: 800000
	  };
	}

	function updateRoom(id, name, type, price) {
	  // Cập nhật dữ liệu của phòng trong cơ sở dữ liệu (ví dụ: API hoặc localStorage)
	  // ...
	}


});

