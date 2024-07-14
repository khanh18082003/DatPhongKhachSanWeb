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
                        window.location.href = '/DatPhongKhachSanWeb/booking-room-success';
                    }, 3000);
                }
            })
            .catch(error => console.error('Error:', error));
    }

    getData();
    </script>

</body>
</html>
