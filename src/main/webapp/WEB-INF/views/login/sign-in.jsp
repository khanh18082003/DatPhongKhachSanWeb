<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
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
					<form method="POST" class="register-form" id="login-form">
						<div class="form-group">
							<label for="your_name"><i
								class="zmdi zmdi-account material-icons-name"></i></label> <input
								type="text" name="your_name" id="your_name"
								placeholder="Your Name" />
						</div>
						<div class="form-group">
							<label for="your_pass"><i class="zmdi zmdi-lock"></i></label> <input
								type="password" name="your_pass" id="your_pass"
								placeholder="Password" />
						</div>
						<div class="form-group">
							<input type="checkbox" name="remember-me" id="remember-me"
								class="agree-term" /> <label for="remember-me"
								class="label-agree-term"><span><span></span></span>Remember
								me</label>
						</div>
						<div class="g-recaptcha"
							data-sitekey="6LetK70pAAAAAKnkS5WngZAgvIjKupwwZNYn7HMs"></div>

						<div class="form-group form-button">
							<input type="submit" name="signin" id="signin"
								class="form-submit" value="Log in" />
						</div>
					</form>
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