<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Card Details</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>
	<div class="row justify-content-around">
		<div class="row"class="col-md-6">
			<div >

				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<form id="logoutForm" method="POST" action="${contextPath}/logout">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
					<div class="row">
						<h3>Welcome ${pageContext.request.userPrincipal.name} |</h3>
						&nbsp;
						<button class="btn btn-warning"
							onclick="document.forms['logoutForm'].submit()">Logout</button>
					</div>
					<br>
					<form:form method="POST" modelAttribute="createNewCard"
						action="${contextPath}/createNewCard">
						<h3 class="text-primary">Create Credit Card</h3>
						<spring:bind path="cardHolder">
							<div ${status.error ? 'has-error' : ''}>
								<h5>Cardholder Name</h5>
								<form:input type="text" path="cardHolder"
									placeholder="Card Holder"></form:input>
								<form:errors path="cardHolder" class="text-danger"></form:errors>
							</div>
						</spring:bind>
						<br>

						<spring:bind path="number">
							<div ${status.error ? 'has-error' : ''}>
								<h5>Credit Card Number</h5>
								<form:input type="text" path="number"
									placeholder="Card Number"></form:input>
								<form:errors path="number" class="text-danger"></form:errors>
							</div>
						</spring:bind>
						<br>

						<spring:bind path="expiryDate">
							<div ${status.error ? 'has-error' : ''}>
								<h5>Expiry Date</h5>
								<form:input type="text" path="expiryDate"
									placeholder="Expiry Date"></form:input>
								<form:errors path="expiryDate" class="text-danger"></form:errors>
							</div>
						</spring:bind>

						<input type="hidden" name="userName"
							value="${pageContext.request.userPrincipal.name}"></input>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

						<br>


						<button class="btn btn-success" type="submit">Create</button>
						
						<br><br>
						
						<c:if test="${newCardCreated == true}">
							<a href="${contextPath}/userFunctions.jsp">Card Created Successfully. Go back to card functions?</a>
						</c:if>
						
					</form:form>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>