<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Please Register to Mifinity App</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
</head>
</head>
<body>

	<div class="row justify-content-around">

		<form:form method="POST" modelAttribute="registration">
			<div class="form-group">
				<div>
					<h2>Welcome to Mifinity. Please Register</h2>
					<br>
				</div>
				<div class="text-center">
					<spring:bind path="userName">
						<div ${status.error ? 'has-error' : ''}>
							<label for="userName"><h4>
									Username
									<h4></h4></label> <br>
							<form:input type="text" path="userName" placeholder="userName"></form:input>
							<form:errors path="userName"></form:errors>
						</div>
					</spring:bind>
					<br>

					<spring:bind path="password">
						<div ${status.error ? 'has-error' : ''}>
							<h4>Password</h4>
							<form:input type="password" path="password"
								placeholder="password"></form:input>
							<form:errors path="password"></form:errors>
						</div>
					</spring:bind>
					<br>

					<spring:bind path="confirmPassword">
						<div ${status.error ? 'has-error' : ''}>
							<h4>Confirm Password</h4>
							<form:input type="password" path="confirmPassword"
								placeholder="Enter Password again"></form:input>
							<form:errors path="confirmPassword"></form:errors>
						</div>
					</spring:bind>

					<br>

					<button type="submit" class="btn btn-outline-primary">Submit</button>
				</div>
			</div>
		</form:form>
	</div>

</body>
</html>