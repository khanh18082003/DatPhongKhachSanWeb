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
<title>Admin | Room Detail</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<div id="layoutSidenav_content">
		<main>

			<div class="container-fluid px-4">

				<h1 class="mt-4">Room Detail</h1>
				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item active">Room Detail</li>
				</ol>
				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-table me-1"></i> Room Detail
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>#</th>
									<th>Room Code</th>
									<th>Room Name</th>
									<th>Floor</th>
									<th>State</th>
									<th>Action</th>

								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>#</th>
									<th>Room Code</th>
									<th>Room Name</th>
									<th>Floor</th>
									<th>State</th>
									<th>Action</th>
									
								</tr>
							</tfoot>
							<tbody>

								<c:forEach var="list" items="${listPhong}" varStatus="status">

									<tr class="room-row">
										<td>${status.index + 1}</td>
										<td>${list.maPhong }</td>
										<td>${list.tenPhong }</td>
										<td>${list.tang } </td>
										<td>${list.trangThaiPhong.tenTTP } </td>
										
										<td>
											<div class="icon-container">
												<a href="editPhong.html?id=${list.maPhong}"
													class="btn-action"><i class="fas fa-edit"></i></a>
												<a href="deletePhong.html?id=${list.maPhong}"
													class="btn-action"><i class="fas fa-trash-alt"></i></a>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
							<a href="insertPhong.html" data-toggle="model"
								class="btn-action add-new">Add New</a>
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

</body>
</html>