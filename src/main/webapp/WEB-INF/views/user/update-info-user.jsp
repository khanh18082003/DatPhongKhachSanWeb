<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sona | Update-User</title>
</head>
<body>
	<div class="page-wrapper bg-gra-03 p-t-45 p-b-50">
		<div class="wrapper wrapper--w790">
			<div class="card card-5">
				<div class="card-heading">
					<h2 class="title">Update Information Of User Form</h2>
				</div>
				<div class="card-body">
					<form:form
						action="${pageContext.servletContext.contextPath}/update-user"
						method="POST" modelAttribute="user">
						<div class="form-row m-b-55">
							<div class="name">Name</div>
							<div class="value">
								<div class="row row-space">
									<div class="col-2">
										<div class="input-group-desc">
											<form:input class="input--style-5" path="ho" />
											<label class="label--desc">first name</label>
										</div>
									</div>
									<div class="col-2">
										<div class="input-group-desc">
											<form:input class="input--style-5" path="ten" />
											<label class="label--desc">last name</label>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="form-row">
							<div class="name">
								<form:label path="email.username">Email</form:label>
							</div>
							<div class="value">
								<div class="input-group">
									<form:input class="input--style-5" type="email"
										path="email.username" disabled="true" />
								</div>
							</div>
						</div>
						<div class="form-row m-b-55">
							<div class="name">
								<form:label path="sdt">Phone</form:label>
							</div>
							<div class="value">
								<div class="input-group">
									<form:input class="input--style-5" path="sdt" />
								</div>
								<form:errors class="error"></form:errors>
							</div>
						</div>


						<div>
							<button class="btn btn--radius-2 btn--red" type="submit">Update</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div class="flash-box show">
			<div class="success-icon">
				<i class="fa fa-check icon-success"></i>
			</div>
			<div class="success-text">Update information of user successfully!</div>
		</div>
	</div>
</body>
</html>