<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8">
<title>Sona | Rooms</title>
</head>
<body>
	<div class="breadcrumb-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="breadcrumb-text">
						<h2>Our Rooms</h2>
						<div class="bt-option">
							<a href='<c:url value="/home"/>'>Home</a> <span>Rooms</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="lookfor-room">
		<form action='<c:url value="/rooms/index"/>'>
			<input id="date-in" class="inputroom date-input" name="dateIn"
				type="text" placeholder="Check in"
				value="${dateIn!=null ? dateIn : '' }"> <input id="date-out"
				class="inputroom date-input" name="dateOut" type="text"
				placeholder="Check out" value="${dateOut!=null ? dateOut : '' }">
			<select name="sLN" class="inputroom select-option">
				<c:forEach items="${listSLN}" var="sl">
					<option value="${sl}" ${sl == param.sLN ? 'selected' : ''}>${sl}
						${sl == 1 ? 'Person' : 'Persons'}</option>
				</c:forEach>
			</select> <select name="lP" class="inputroom select-option">
				<c:forEach items="${listLP}" var="lp">
					<option value="${lp.tenLP}"
						${lp.tenLP == param.lP ? 'selected' : ''}>${lp.tenLP}</option>
				</c:forEach>
			</select>

			<button class="inputroom" onclick="return showConfirmation()});"
				type="submit">Look For Room</button>
		</form>
	</div>
	<section class="rooms-section spad">
		<div class="container">
			<div class="row">
				<c:set var="pagecurrent" value="${pagecur}" />
				<c:set var="pageMax" value="${pageMax}" />
				<c:set var="roomAvailable" value="${roomAvai}" />
				<c:forEach items="${roomList}" var="room">
					<div class="col-lg-4 col-md-6">
						<div class="room-item">
							<img src="<c:url value="${room.anh}"/>" alt="" width="330" height="220">
							<div class="ri-text">
								<h4>${room.tenHP}</h4>
								<h3>
									<fmt:formatNumber value="${room.gia}" type="currency" currencySymbol="$" pattern="$#,##0.00" /> <span>/Pernight</span>
								</h3>
								<table>
									<tbody>
										<tr>
											<td class="r-o">Capacity:</td>
											<td><c:choose>
													<c:when test="${room.soLuongNguoi ==1}">${room.soLuongNguoi} Person</c:when>
													<c:otherwise>${room.soLuongNguoi} Persons</c:otherwise>
												</c:choose></td>
										</tr>
										<tr>
											<td class="r-o">Room Type:</td>
											<td>${room.loaiPhong.tenLP}</td>
										</tr>
										<tr>
											<td class="r-o">Bed:</td>
											<td>${room.kieuPhong.tenKP}</td>
										</tr>
										<tr>
											<td class="r-o">Services:</td>
											<td>${room.moTa}</td>
										</tr>
										<tr>
											<td id="roomAvailable" class="r-o">Available Room:</td>
											<td>${roomAvailable[room.idHP]}</td>
										</tr>
									</tbody>
								</table>
								<c:choose>
									<c:when
										test="${sLN == null || lP == null || dateOut == null || dateIn == null}">
										<a href='<c:url value="/rooms/index" />'
											onclick="return showConfirmation();" class="primary-btn">More
											Details</a>
									</c:when>
									<c:otherwise>
										<a onclick="return dontClick(${roomAvailable[room.idHP]})" href='<c:url value="/rooms/room-detail?id=${room.idHP}&dateIn=${dateIn}&dateOut=${dateOut}" />'
											class="primary-btn">More Details</a>
									</c:otherwise>
								</c:choose>
								<script>
									function showConfirmation() {		
										var element = document
												.querySelector('[name="sLN"]');
										var sLN = element.value;
										element = document
												.querySelector('[name="lP"]');
										var lP = element.value;
										element = document
												.querySelector('[name="dateIn"]');
										var dateIn = element.value;
										element = document
												.querySelector('[name="dateOut"]');
										var dateOut = element.value;
										if (sLN == '' || lP == '' || dateIn == '' || dateOut == '') {
											alert('To see availability and prices please enter your check-in and check-out dates.');
											return false;
										} else
                                            return true;
									}
									function dontClick(numberOfRoom){
										console.log(numberOfRoom);
									    if (numberOfRoom == 0) {
									        alert('This room is not available');
									        return false;
									    } else {
									        return true;
									    }
									}
								</script>
							</div>
						</div>
					</div>
				</c:forEach>
				<div class="col-lg-12">
					<div class="room-pagination">
						<c:set var="textParam" value="" />
						<c:if
							test="${dateIn!=null && dateOut!=null && sLN!=null && lP!=null}">
							<c:set var="textParam"
								value="&dateIn=${dateIn}&dateOut=${dateOut}&sLN=${sLN}&lP=${lP}" />
						</c:if>
						<c:if test="${pagecurrent>1}">
							<a
								href='<c:url value="/rooms/index?page=${pagecurrent-1}${textParam}" />'>Previous
								<i class="fa fa-long-arrow-left"></i>
							</a>
							<a
								href='<c:url value="/rooms/index?page=${pagecurrent-1}${textParam}" />'>${pagecurrent-1}</a>
						</c:if>
						<a
							href='<c:url value="/rooms/index?page=${pagecurrent}${textParam}" />'>${pagecurrent}</a>
						<c:if test="${pagecurrent<pageMax}">
							<a
								href='<c:url value="/rooms/index?page=${pagecurrent+1}${textParam}" />'>${pagecurrent+1}</a>
							<a
								href='<c:url value="/rooms/index?page=${pagecurrent+1}${textParam}" />'>Next
								<i class="fa fa-long-arrow-right"></i>
							</a>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>