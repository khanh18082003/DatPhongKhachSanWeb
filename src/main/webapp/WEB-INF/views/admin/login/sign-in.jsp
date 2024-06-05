<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/" />
<meta charset="UTF-8">
<title>Login - Admin</title>
</head>
<body>
	<div id="layoutAuthentication">
		<div id="layoutAuthentication_content">
			<main>
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-lg-5">
							<div class="card shadow-lg border-0 rounded-lg mt-5">
								<div class="card-header">
									<h3 class="text-center font-weight-light my-4">Login</h3>
								</div>
								<div class="card-body">
									<form:form
										action="${pageContext.servletContext.contextPath}/admin/sign-in"
										method="POST" modelAttribute="taiKhoan">
										<div class="form-floating mb-3">
											<form:input class="form-control" path="username"
												placeholder="Username" />
											<form:label path="username">Username</form:label>
											<form:errors path="username" cssClass="error"></form:errors>
										</div>
										<div class="form-floating mb-3">
											<form:password path="password" class="form-control"
												placeholder="Password" />
											<form:label path="password">Password</form:label>
											<form:errors path="password" cssClass="error"></form:errors>
										</div>
										<div class="g-recaptcha"
											data-sitekey="6LetK70pAAAAAKnkS5WngZAgvIjKupwwZNYn7HMs"></div>
										<span class="error">${reCapchaError}</span>
										
										<div
											class="d-flex align-items-center justify-content-between mt-4 mb-0">
											<a class="small" href="password.html">Forgot Password?</a>
											<button class="btn btn-primary">Login</button>
										</div>
									</form:form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</main>
		</div>
		<div id="layoutAuthentication_footer">
			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid px-4">
					<div
						class="d-flex align-items-center justify-content-between small">
						<div class="text-muted">Copyright &copy; Your Website 2023</div>
						<div>
							<a href="#">Privacy Policy</a> &middot; <a href="#">Terms
								&amp; Conditions</a>
						</div>
					</div>
				</div>
			</footer>
		</div>
	</div>
</body>
</html>