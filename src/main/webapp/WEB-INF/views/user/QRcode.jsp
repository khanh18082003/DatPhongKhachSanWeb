<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="${pageContext.servletContext.contextPath}/" />
	<meta charset="UTF-8">
	<title>Sona | Create QR Code</title>
</head>
<body>
	<div class="breadcrumb-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="breadcrumb-text">
						<h2>Our Rooms</h2>
						<div class="bt-option">
							<a href="<c:url value="/home"/>">Home</a> <span>Book Room</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-4 col-md-6">
			<div class="room-item">
				<div class="qr-code">
					<img src="${qrImgSrc}" alt="qr-code">
				</div>
			</div>
		</div>
	</div>

	<script>
    function getData() {
        fetch('http://datphong.com:8080/DatPhongKhachSanWeb/PaymentStatusServlet')
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.status === 'success') {
                	console.log("Payment Successful!");
                	
                	setTimeout(function() {
                	    fetch('/DatPhongKhachSanWeb/booking-room-success', {
                	        method: 'POST',
                	        headers: {
                	            'Content-Type': 'application/x-www-form-urlencoded'
                	        },
                	        body: new URLSearchParams({
                	            maGD: data.maGD,
                	            amount: data.amount
                	        })
                	    })
                	    .then(response => {
                	        if (response.ok) {
                	            console.log('Booking room success');
                	            window.location.href = '/DatPhongKhachSanWeb/notification/200';
                	        } else {
                	            console.error('Post request failed', response.status, response.statusText);
                	        }
                	    })
                	    .catch(error => {
                	        console.error('Error:', error);
                	    });
                	}, 3000);
                }
            })
            .catch(error => console.error('Error:', error));
    }

    getData();
    </script>

</body>
</html>
