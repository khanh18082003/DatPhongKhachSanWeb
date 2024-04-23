<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/" />
<meta charset="UTF-8">
<title>Sona | Sign-in</title>
</head>
<body>
	<!-- Sing in  Form -->
	<section class="sign-in">
		<div class="container">
			<div class="signin-content">
				<div class="signin-image">
					<figure>
						<img
							src='<c:url value="/template/login/images/signin-image.jpg"/>'
							alt="sing up image">
					</figure>
					<a href='<c:url value="/sign-up"/>' class="signup-image-link">Create
						an account</a>
				</div>

				<div class="signin-form">
					<h2 class="form-title">Sign up</h2>
					<form:form method="POST" action="${pageContext.servletContext.contextPath}/sign-in"
						modelAttribute="signInForm" class="register-form" id="login-form">
						<div class="form-group">
							<form:label path="username" cssErrorClass="label-has-error">
								<i class="zmdi zmdi-account material-icons-name"></i>
							</form:label>
							<form:input path="username" placeholder="Your email" />
							<form:errors path="username" cssClass="error"></form:errors>
						</div>
						<div class="form-group">
							<form:label path="password" cssErrorClass="label-has-error">
								<i class="zmdi zmdi-lock"></i>
							</form:label>
							<form:password path="password" placeholder="Password" autocomplete="off" />
							<form:errors path="password" cssClass="error"></form:errors>
						</div>

						<div class="g-recaptcha"
							data-sitekey="6LetK70pAAAAAKnkS5WngZAgvIjKupwwZNYn7HMs"></div>
						
							<span class="error">${reCapchaError}</span>
						
						<div class="form-group form-button">
							<input type="submit" name="signin" id="signin"
								class="form-submit" value="Log in" />
						</div>
					</form:form>
					<div class="social-login">
						<span class="social-label">Or login with</span>
						<ul class="socials">
							<li><a href="#"><i
									class="display-flex-center zmdi zmdi-facebook"></i></a></li>
							<li><a href="#"><i
									class="display-flex-center zmdi zmdi-twitter"></i></a></li>
							<li><a href="#"><i
									class="display-flex-center zmdi zmdi-google"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>