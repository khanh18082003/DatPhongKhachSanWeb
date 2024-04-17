<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
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
					<form method="POST" class="register-form" id="register-form">
						<div class="form-group">
							<label for="last_name"><i
								class="zmdi zmdi-folder-person material-icons-name"></i></label> <input
								type="text" name="last_name" id="last_name"
								placeholder="Your Last Name" />
						</div>
						<div class="form-group">
							<label for="first_name"><i
								class="zmdi zmdi-folder-person material-icons-name"></i></label> <input
								type="text" name="first_name" id="first_name"
								placeholder="Your First Name" />
						</div>
						<div class="form-group">
							<label for="sdt"><i
								class="zmdi zmdi-code-smartphone material-icons-name"></i></label> <input
								type="text" name="sdt" id="sdt" placeholder="Your Phone Number" />
						</div>
						<div class="form-group">
							<label for="email"><i class="zmdi zmdi-email"></i></label> <input
								type="email" name="email" id="email" placeholder="Your Email" />
						</div>
						<div class="form-group">
							<label for="username"><i
								class="zmdi zmdi-account material-icons-name"></i></label> <input
								type="text" name="username" id="username"
								placeholder="Enter Username" />
						</div>
						<div class="form-group">
							<label for="password"><i class="zmdi zmdi-lock"></i></label> <input
								type="password" name="password" id="password"
								placeholder="Enter Password" />
						</div>
						<div class="form-group">
							<label for="re-password"><i
								class="zmdi zmdi-lock-outline"></i></label> <input type="password"
								name="re_password" id="re_password"
								placeholder="Repeat your password" />
						</div>
						<div class="g-recaptcha"
							data-sitekey="6LetK70pAAAAAKnkS5WngZAgvIjKupwwZNYn7HMs"></div>
							
						<div class="form-group form-button">
							<input type="submit" name="signup" id="signup"
								class="form-submit" value="Register" />
						</div>
					</form>
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