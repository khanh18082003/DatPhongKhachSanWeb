<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/" />
<meta charset="UTF-8">
<title>Sona | Sign-up</title>
</head>
<body>
	<!-- Sign up form -->
	<section class="signup">
		<div class="container">
			<div class="signup-content">
				<div class="signup-form">
					<h2 class="form-title">Sign up</h2>
					<form:form method="POST" action="${pageContext.servletContext.contextPath}/sign-up"
						modelAttribute="signUpForm" class="register-form"
						id="register-form">
						<div class="form-group">
							<form:label path="username" cssErrorClass="label-has-error">
								<i class="zmdi zmdi-account material-icons-name"></i>
							</form:label>
							<form:input path="username" type="email" placeholder="Enter Your Email" />
							<form:errors path="username" cssClass="error"></form:errors>
						</div>
						<div class="form-group">
							<form:label path="password" cssErrorClass="label-has-error"	>
								<i class="zmdi zmdi-lock"></i>
							</form:label>
							<form:password path="password" placeholder="Enter Password" />
							<form:errors path="password" cssClass="error"></form:errors>
						</div>
						<div class="form-group">
							<form:label path="confirmPassword" cssErrorClass="label-has-error">
								<i class="zmdi zmdi-lock-outline"></i>
							</form:label>
							<form:password path="confirmPassword" placeholder="Repeat your password" />
							<form:errors path="confirmPassword" cssClass="error"></form:errors>
						</div>
						<div class="g-recaptcha"
							data-sitekey="6LetK70pAAAAAKnkS5WngZAgvIjKupwwZNYn7HMs"></div>
						
							<span class="error">${reCapchaError}</span>
						
						<div class="form-group form-button">
							<input type="submit" name="signup" id="signup"
								class="form-submit" value="Register" />
						</div>
					</form:form>
				</div>
				<div class="signup-image">
					<figure>
						<img
							src='<c:url value="/template/login/images/signup-image.jpg"/>'
							alt="sing up image">
					</figure>
					<a href='<c:url value="/sign-in"/>' class="signup-image-link">I
						have already account</a>
				</div>
			</div>
		</div>
	</section>
</body>
</html>