// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

var revenueData = JSON.parse(revenueDatalist);
// Bar Chart Example
var ctx = document.getElementById("myBarChart");
var myLineChart = new Chart(ctx, {
	type: 'bar',
	data: {
//		"January", "February", "March", "April", "May", "June", "July", "September" ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]
		labels: revenueData.map(item => item[1]),
		datasets: [{
			label: "Revenue",
			backgroundColor: "rgba(2,117,216,1)",
			borderColor: "rgba(2,117,216,1)",
//			4215, 5312, 6251, 7841, 9821, 14984 [4215, 5312, 6251, 7841, 9821, 14984]
			data: revenueData.map(item => item[2]),
		}],
	},
	options: {
		scales: {
			xAxes: [{
				time: {
					unit: 'month'
				},
				gridLines: {
					display: false
				},
				ticks: {
					maxTicksLimit: 6
				}
			}],
			yAxes: [{
				ticks: {
					min: 0,
					max: 15000,
					maxTicksLimit: 5
				},
				gridLines: {
					display: true
				}
			}],
		},
		legend: {
			display: false
		}
	}
});
/*// Khi tài liệu đã sẵn sàng, gọi hàm fetchData để lấy dữ liệu
document.addEventListener("DOMContentLoaded", function() {
	fetchData();
});

// Hàm lấy dữ liệu từ servlet và vẽ biểu đồ
function fetchData() {
	fetch('/data')
		.then(response => response.json())
		.then(data => {
			// Gọi hàm drawChart để vẽ biểu đồ với dữ liệu nhận được
			drawChart(data);
		})
		.catch(error => console.error('Error:', error));
}

// Hàm vẽ biểu đồ sử dụng Chart.js
function drawChart(revenueData) {
	var labels = revenueData.map(function(hoaDon) {
		return hoaDon.month; // Thay 'month' bằng tên thuộc tính thực tế của dữ liệu tháng
	});

	var dataPoints = revenueData.map(function(hoaDon) {
		return hoaDon.revenue; // Thay 'revenue' bằng tên thuộc tính thực tế của dữ liệu doanh thu
	});

	var ctx = document.getElementById("myBarChart").getContext('2d');
	var myBarChart = new Chart(ctx, {
		type: 'bar',
		data: {
			labels: labels,
			datasets: [{
				label: "Revenue",
				backgroundColor: "rgba(2,117,216,1)",
				borderColor: "rgba(2,117,216,1)",
				data: dataPoints,
			}],
		},
		options: {
			scales: {
				xAxes: [{
					time: {
						unit: 'month'
					},
					gridLines: {
						display: false
					},
					ticks: {
						maxTicksLimit: 6
					}
				}],
				yAxes: [{
					ticks: {
						min: 0,
						// Đặt max dựa trên dữ liệu để đảm bảo biểu đồ hiển thị tốt
						max: Math.max(...dataPoints) * 1.1,
						maxTicksLimit: 5
					},
					gridLines: {
						display: true
					}
				}],
			},
			legend: {
				display: false
			}
		}
	});
}*/