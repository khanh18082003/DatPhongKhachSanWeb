<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create QR Code</title>
</head>
<body>
        <div class="qr-code" >
            <img src="${qrImgSrc}" alt="qr-code">
        </div>

    <script >
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
