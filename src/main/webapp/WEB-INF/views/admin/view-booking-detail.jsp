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
<title>Admin |Detail Booking History</title>
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
									<th>Code</th>
									<th>Name Room</th>
									<th>Quantity</th>
									<th>Price</th>
									<th>Action</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>#</th>
									<th>Code</th>
									<th>Name Room</th>
									<th>Quantity</th>
									<th>Price</th>
									<th>Action</th>
								</tr>
							</tfoot>
							<tbody>

								<c:forEach var="list" items="${listCTPD}" varStatus="status">

									<tr class="room-row">
										<td>${status.index + 1}</td>
										<td>${list.id }</td>
										<td>${list.hangPhong.tenHP }</td>
										<td>${list.sLPhong } </td>
										<td> <fmt:formatNumber value="${list.hangPhong.gia }"
												type="currency" currencySymbol="$" pattern="$#,##0.00" />
										 </td>
										 <td>
										 <div class="icon-container">
											<button onclick="window.print()">
											    <i class="fas fa-print"></i>
											</button>
											</div>
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

</body>
</html>