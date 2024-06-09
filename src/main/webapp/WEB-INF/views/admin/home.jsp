<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin | Home</title>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
</head>

<body>
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-4">
				<h1 class="mt-4">Dashboard</h1>
				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item active">Dashboard</li>
				</ol>
				<div class="row">
					<!--<div class="col-xl-3 col-md-6">
						<div class="card bg-primary text-white mb-4">
							<div class="card-body">Primary Card</div>
							<div
								class="card-footer d-flex align-items-center justify-content-between">
								<a class="small text-white stretched-link" href="#">View
									Details</a>
								<div class="small text-white">
									<i class="fas fa-angle-right"></i>
								</div>
							</div>
						</div>
					</div>
					 <div class="col-xl-3 col-md-6">
						<div class="card bg-warning text-white mb-4">
							<div class="card-body">Warning Card</div>
							<div
								class="card-footer d-flex align-items-center justify-content-between">
								<a class="small text-white stretched-link" href="#">View
									Details</a>
								<div class="small text-white">
									<i class="fas fa-angle-right"></i>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-md=6">
						<div class="card bg-success text-white mb-4">
							<div class="card-body">Success Card</div>
							<div
								class="card-footer d-flex align-items-center justify-content-between">
								<a class="small text-white stretched-link" href="#">View
									Details</a>
								<div class="small text-white">
									<i class="fas fa-angle-right"></i>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-md-6">
						<div class="card bg-danger text-white mb-4">
							<div class="card-body">Danger Card</div>
							<div
								class="card-footer d-flex align-items-center justify-content-between">
								<a class="small text-white stretched-link" href="#">View
									Details</a>
								<div class="small text-white">
									<i class="fas fa-angle-right"></i>
								</div>
							</div>
						</div>
					</div> -->
				</div>
				<div class="row">
					<div class="col-xl-6">
						<div class="card mb-4">
							<div class="card-header">
								<i class="fas fa-chart-area me-1"></i> Current Month Revenue
								Area Chart

								<div>
									<label for="thisMonth">Month: ${thisMonth }</label> <label
										for="thisYear">Year: ${thisYear }</label>
								</div>

							</div>
							<div class="card-body">
								<canvas id="myAreaChart" width="100%" height="40"></canvas>
							</div>
						</div>
					</div>
					<div class="col-xl-6">
						<div class="card mb-4">


							<div class="card-header">
								<i class="fas fa-chart-bar me-1"></i> Revenue Bar Chart
								<!-- Dropdown để chọn năm -->
								<div>
									<label for="yearSelect">Select Year:</label> <select
										id="yearSelect" name="year">
										<c:forEach var="yearOption" items="${yearList}">
											<option value="${yearOption}"
												${yearOption == year ? 'selected="selected"' : ''}>${yearOption}</option>
										</c:forEach>
									</select>
								</div>


							</div>
							<div class="card-body">
								<canvas id="myBarChart" width="100%" height="40"></canvas>
							</div>
						</div>
					</div>
				</div>
				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-table me-1"></i> Revenue
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>#</th>
									<th>ID</th>
									<th>Date</th>
									<th>Line Total</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>#</th>
									<th>ID</th>
									<th>Date</th>
									<th>Line Total</th>
								</tr>
							</tfoot>
							<tbody>
								<c:forEach var="list" items="${revenueList}" varStatus="status">
									<tr class="room-row">
										<td>${status.index + 1 }</td>
										<td>${list.maHD }</td>
										<td>${list.ngayLap }</td>
										<td><fmt:formatNumber value="${list.tongTien }"
												type="currency" currencySymbol="$" pattern="$#,##0.00" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<div class="card-footer text-muted">
						<strong>Total Revenue: </strong><span id="total">$0.00</span>
					</div>
				</div>
			</div>
		</main>

		<footer class="py-4 bg-light mt-auto">
			<div class="container-fluid px-4">
				<div class="d-flex align-items-center justify-content-between small">
					<div class="text-muted">Copyright &copy; Your Website 2023</div>
					<div>
						<a href="#">Privacy Policy</a> &middot; <a href="#">Terms &
							Conditions</a>
					</div>
				</div>
			</div>
		</footer>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js">
		
	</script>
	<script>
		$(document).ready(function() {
			$('#datatablesSimple').DataTable();

			// Các mã khác...
			$('#datatablesSimple_filter, #datatablesSimple_length, #datatablesSimple_info, #datatablesSimple_paginate').css('display', 'none');
			
			 $('#yearSelect').change(function() {
		            // Gửi form khi một năm mới được chọn
		            var selectedYear = $(this).val();
		            window.location.href = '?year=' + selectedYear; // để chuyển trang với parameter
		        });
			
			var revenueData = JSON.parse('${revenueDataList}');
			
			var ctx = document.getElementById("myBarChart").getContext('2d');
		    var myLineChart = new Chart(ctx, {
		        type: 'bar',
		        data: {
		        	 labels: revenueData.map(item => item[1]),
		            datasets: [{
		                label: "Revenue",
		                backgroundColor: "rgba(2,117,216,1)",
		                borderColor: "rgba(2,117,216,1)",
		                data: revenueData.map(item => item[2]),
		            }],
		        },
		        options: {
		            scales: {
		                x: {
		                    time: {
		                        unit: 'month'
		                    },
		                    grid: {
		                        display: false
		                    },
		                    ticks: {
		                        maxTicksLimit: 6
		                    }
		                },
		                y: {
		                    ticks: {
		                        min: 0,
		                        max: 15000,
		                        maxTicksLimit: 5
		                    },
		                    grid: {
		                        display: true
		                    }
		                },
		            },
		            plugins: {
		                legend: {
		                    display: false
		                }
		            }
		        }
		    }); 
		    
		    
		 // Area Chart Example
		 	revenueData = JSON.parse('${currentMonthRevenue}');
		    var ctx = document.getElementById("myAreaChart");
		    var myLineChart = new Chart(ctx, {
		      type: 'line',
		      data: {
		        labels: revenueData.map(item => item[0]),
		        datasets: [{
		          label: "Revenue",
		          lineTension: 0.3,
		          backgroundColor: "rgba(2,117,216,0.2)",
		          borderColor: "rgba(2,117,216,1)",
		          pointRadius: 5,
		          pointBackgroundColor: "rgba(2,117,216,1)",
		          pointBorderColor: "rgba(255,255,255,0.8)",
		          pointHoverRadius: 5,
		          pointHoverBackgroundColor: "rgba(2,117,216,1)",
		          pointHitRadius: 50,
		          pointBorderWidth: 2,
		          data: revenueData.map(item => item[1]),
		        }],
		      },
		      options: {
		        scales: {
		          xAxes: [{
		            time: {
		              unit: 'date'
		            },
		            gridLines: {
		              display: false
		            },
		            ticks: {
		              maxTicksLimit: 7
		            }
		          }],
		          yAxes: [{
		            ticks: {
		              min: 0,
		              max: 1000,
		              maxTicksLimit: 5
		            },
		            gridLines: {
		              color: "rgba(0, 0, 0, .125)",
		            }
		          }],
		        },
		        legend: {
		          display: false
		        }
		      }
		    });
		    
		    var table = $('#datatablesSimple').DataTable();

		    function updateTotal() {
		        var total = 0;
		        // Get all rows that match the search criteria
		        var rows = table.rows({ search: 'applied' }).data();
		        // Iterate over each row
		        for (var i = 0; i < rows.length; i++) {
		            var data = rows[i];
		            // Extract the value from the fourth column, remove $ and commas, convert to float
		            var lineTotal = parseFloat(data[3].replace(/[$,]/g, ''));
		            total += lineTotal;
		        }
		        // Update the total in the HTML element
		        $('#total').html('$' + total.toFixed(2));
		    }

		    // Recalculate total on table draw
		    table.on('draw', function() {
		        updateTotal();
		    });

		    // Initial call to update total
		    updateTotal();

		    /* table.on('draw', function() { ... }); là cú pháp của jQuery để đăng ký một trình xử lý sự kiện.
		    table là một đối tượng jQuery đại diện cho bảng DataTable.
		    'draw' là tên của sự kiện mà bạn đang lắng nghe.
		    function() { ... } là hàm callback sẽ được gọi khi sự kiện 'draw' được kích hoạt. */

			
		});
		
	</script>
</body>
</html>