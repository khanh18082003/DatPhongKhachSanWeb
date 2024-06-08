<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8">
<title>Booking Details</title>
</head>
<body>
    <h2>Booking Details</h2>
    <table border="1">
        <thead>
            <tr>
                <th>MAPD</th>
                <th>NgayDat</th>
                <th>NgayBD</th>
                <th>NgayKT</th>
                <th>SLPHONG</th>
                <th>TENHP</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${bookingDetails}" var="detail">
                <tr>
                    <td>${detail.phieuDat.maPD}</td>
                    <td><fmt:formatDate value="${detail.phieuDat.ngayDat}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
                    <td><fmt:formatDate value="${detail.phieuDat.ngayBD}" pattern="dd/MM/yyyy" /></td>
                    <td><fmt:formatDate value="${detail.phieuDat.ngayKT}" pattern="dd/MM/yyyy" /></td>
                    <td>${detail.sLPhong}</td>
                    <td>${detail.hangPhong.tenHP}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <button onclick="window.print()">Print</button>
</body>
</html>
