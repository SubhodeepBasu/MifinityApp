<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Credit Card Functions:</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div align = "center" >
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			<div class="text-center">
				<div>
					<h2>
						Welcome ${pageContext.request.userPrincipal.name} |
						<button onclick="document.forms['logoutForm'].submit()"
							class="btn btn-outline-primary">Logout</button>
					</h2>
				</div>
				<br>
				<br>
				<div class="text-center">
					<h2>Please select an option to proceed :</h2>
					<a href="${contextPath}/createNewCard"> -> Create new Card</a> <br></br>
					<a href="${contextPath}/searchExistingCard"> -> Search your
						cards</a>
				</div>
			</div>
		</c:if>
	</div>
</body>
</html>