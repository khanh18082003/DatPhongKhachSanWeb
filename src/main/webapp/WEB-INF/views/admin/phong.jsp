<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tạo Phòng</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Tạo Phòng</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Mã Phòng</th>
                    <th>Tên Phòng</th>
                    <th>Tầng</th>
                    <th>Trạng Thái</th>
                    <th>Loại Phòng</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td><input type="text" class="form-control" name="maPhong1"></td>
                    <td><input type="text" class="form-control" name="tenPhong1"></td>
                    <td><input type="text" class="form-control" name="tang1"></td>
                    <td><input type="text" class="form-control" name="trangThai1"></td>
                    <td>
                        <select class="form-control" name="loaiPhong1">
                            <option>Đơn</option>
                            <option>Đôi</option>
                            <option>Double</option>
                            <option>VIP</option>
                            <option>Standard</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                    <td><input type="text" class="form-control" name="maPhong2"></td>
                    <td><input type="text" class="form-control" name="tenPhong2"></td>
                    <td><input type="text" class="form-control" name="tang2"></td>
                    <td><input type="text" class="form-control" name="trangThai2"></td>
                    <td>
                        <select class="form-control" name="loaiPhong2">
                            <option>Đơn</option>
                            <option>Đôi</option>
                            <option>Double</option>
                            <option>VIP</option>
                            <option>Standard</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>3</td>
                    <td><input type="text" class="form-control" name="maPhong3"></td>
                    <td><input type="text" class="form-control" name="tenPhong3"></td>
                    <td><input type="text" class="form-control" name="tang3"></td>
                    <td><input type="text" class="form-control" name="trangThai3"></td>
                    <td>
                        <select class="form-control" name="loaiPhong3">
                            <option>Đơn</option>
                            <option>Đôi</option>
                            <option>Double</option>
                            <option>VIP</option>
                            <option>Standard</option>
                        </select>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="text-center">
            <button type="submit" class="btn btn-primary">Tạo Phòng</button>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
