<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8">
<title>Admin | Room Detail</title>
</head>
<body>
    <div id="layoutSidenav_content">
        <main>
            <div id="edit-modal" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span> <span class="message">${message}</span>
                    <h2>Insert New Room Detail</h2>
                    <form:form action="insertPhong.html" method="POST"
                        id="edit-form" modelAttribute="phong">
                        <label for="room-code">Room Code:</label>
                        <form:input type="text" class="room-id" path="maPhong"
                            required="required" />
                        <form:errors path="maPhong" />

                        <label for="room-name">Room Name:</label>
                        <form:input type="text" class="room-name" path="tenPhong"
                            required="required" />
                        <form:errors path="tenPhong" />

                        <label for="room-floor">Floor:</label>
                        <form:input type="number" class="room-floor" path="tang"
                            required="required" />
                        <form:errors path="tang" />

                        <label for="room-type">State:</label>
						<form:select class="room-type" path="trangThaiPhong.maTTP"
							items="${listTTP}" itemValue="maTTP" itemLabel="tenTTP" />

                        <label for="room-type">Hang Phong:</label>
                        <form:select class="room-type" path="hangPhong.idHP"
                            items="${listHP}" itemValue="idHP" itemLabel="tenHP" />

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
                            window.location.href = '/DatPhongKhachSanWeb/admin/phong.html';
                        });
        const closeButton = document.querySelector('.close'); // Get the close button element

        closeButton.addEventListener('click', function() {
                            // Handle the click event here
                            window.location.href = '/DatPhongKhachSanWeb/admin/phong.html';
                        });
    </script>
</body>
</html>
