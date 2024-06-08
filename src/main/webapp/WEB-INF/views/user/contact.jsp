<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/" />
<meta charset="UTF-8">
<title>Sona | Contact</title>
</head>
<body>
	<!-- Contact Section Begin -->
	<section class="contact-section spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-4">
					<div class="contact-text">
						<h2>Contact Info</h2>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit,
							sed do eiusmod tempor incididunt ut labore et dolore magna
							aliqua.</p>
						<table>
							<tbody>
								<tr>
									<td class="c-o">Address:</td>
									<td>${address}</td>
								</tr>
								<tr>
									<td class="c-o">Phone:</td>
									<td>+(84) ${sdt}</td>
								</tr>
								<tr>
									<td class="c-o">Email:</td>
									<td>${email}</td>
								</tr>
								<tr>
									<td class="c-o">Fax:</td>
									<td>+(84) 378 277 559</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-lg-7 offset-lg-1">
					<form action="<c:url value="/contact-us"/>" class="contact-form" method="POST">
						<div class="row">
							<div class="col-lg-6">
								<input type="text" placeholder="Your Name" name="nameUser">
								<p class="error">${errorName}</p>
							</div>
							<div class="col-lg-6">
								<input type="text" placeholder="Your Email" name="emailUser" value="${emailUser}">
								<p class="error">${errorEmail}</p>
							</div>
							<div class="col-lg-12">
								<textarea placeholder="Your Message" name="messageUser"></textarea>
								<p class="error">${errorMess}</p>
								<button type="submit">Submit Now</button>
								<p>${success}</p>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="map">
				<iframe
					src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3918.520072541708!2d106.78408977573646!3d10.847992257868146!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752772b245dff1%3A0xb838977f3d419d!2sPosts%20and%20Telecommunications%20Institute%20of%20Technology%20HCM%20Branch!5e0!3m2!1sen!2s!4v1714830596777!5m2!1sen!2s"
					height="470" style="border: 0;" allowfullscreen="" loading="lazy"
					referrerpolicy="no-referrer-when-downgrade"></iframe>
			</div>
		</div>
	</section>
	<!-- Contact Section End -->
</body>
</html>