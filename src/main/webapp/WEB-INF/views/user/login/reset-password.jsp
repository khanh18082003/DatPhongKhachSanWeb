<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/"/>
<meta charset="UTF-8">
<title>Sona | Reset-Password</title>
</head>
<body>
	<div class="container">
		<div class="signup-form">
			<h2 class="form-title">Reset Password</h2>
			<form:form action="${pageContext.servletContext.contextPath}/reset-password?token=${token}" method="POST"
				modelAttribute="resetPasswordForm">
				<input type="hidden" name="token" value="${token}" />
				<div class="form-group">
					<form:label path="password" cssErrorClass="label-has-error">
						<i class="zmdi zmdi-account material-icons-name"></i>
					</form:label>
					<form:password path="password" placeholder="Enter New Password" />
					<form:errors path="password" cssClass="error"></form:errors>
				</div>
				<div class="form-group">
					<form:label path="confirmPassword" class="label-has-error">
						<i class="zmdi zmdi-account material-icons-name"></i>
					</form:label>
					<form:password path="confirmPassword" placeholder="Enter Confirm Password" />
					<form:errors path="confirmPassword" cssClass="error"></form:errors>
				</div>
				<div class="form-group form-button">
					<input type="submit" name="reset" id="reset" class="form-submit"
						value="Reset Password" />
				</div>
			</form:form>
		</div>
	</div>

</body>
</html>