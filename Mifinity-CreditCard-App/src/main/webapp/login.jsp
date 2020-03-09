<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Credit Card Login</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="row justify-content-around">
		<form method="POST" action="${contextPath}/login"
			modelAttribute="login">
			<div class="form-group">
				<div>
					<h2>Enter your credentials</h2>
				</div>
				<br>
				<div class="text-center">
					<div ${error != null ? 'has-error' : ''}>
						<span>${message}</span>
						<div class="form-group-row">
							<label for="username">Username</label> <br> <input
								name="username" id="username" type="text" placeholder="userName"
								class="form-conrol" />
						</div>
						<br>
						<div class="form-group-row">
							<label for="password">Password</label> <br> <input
								name="password" id="password" type="password"
								placeholder="password" class="form-conrol" />
						</div>
						<br>
						<span>${error}</span> <input type="hidden"
							name="${_csrf.parameterName}" value="${_csrf.token}" />
						<div class="form-group-row">
							<button type="submit" class="btn btn-outline-primary">Confirm
								Identity</button>
						</div>
						<br>
						<div class = "form-group-row">
							<h5>
								<a href="${contextPath}/userRegistration">Do not have an
									account? Click here</a>
							</h5>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>