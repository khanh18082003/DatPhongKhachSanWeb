<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin | Phong</title>
</head>
<body>
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-4">
				<h1 class="mt-4">Phòng</h1>
				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item active">Phòng</li>
				</ol>

				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-table me-1"></i> Hạng phòng
					</div>
					<div class="card-body">
						<table id="roomTable" class="datatable-table">
							<thead>
								<tr>
									<th>#</th>
									<th>Mã Phòng</th>
									<th>Tên Phòng</th>
									<th>Tầng</th>
									<th>Trạng Thái</th>
									<th>Hạng Phòng</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>#</th>
									<th>Mã Phòng</th>
									<th>Tên Phòng</th>
									<th>Tầng</th>
									<th>Trạng Thái</th>
									<th>Hạng Phòng</th>
								</tr>
							</tfoot>
							<tbody>
								<tr>
									<td>Row 1, Column 1</td>
									<td>Row 1, Column 2</td>
									<td>Row 1, Column 3</td>
									<td>Row 1, Column 4</td>

								</tr>
								<tr>
									<td>Row 2, Column 1</td>
									<td>Row 2, Column 2</td>
									<td>Row 2, Column 3</td>
									<td>Row 2, Column 4</td>

								</tr>
								<tr>
									<td>Row 3, Column 1</td>
									<td>Row 3, Column 2</td>
									<td>Row 3, Column 3</td>
									<td>Row 3, Column 4</td>

								</tr>
								<tr>
									<td>Row 4, Column 1</td>
									<td>Row 4, Column 2</td>
									<td>Row 4, Column 3</td>
									<td>Row 4, Column 4</td>

								</tr>
								<tr>
									<td>Row 5, Column 1</td>
									<td>Row 5, Column 2</td>
									<td>Row 5, Column 3</td>
									<td>Row 5, Column 4</td>

								</tr>
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