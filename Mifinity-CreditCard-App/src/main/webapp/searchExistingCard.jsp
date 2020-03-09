<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search your cards</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript">
function validateCardNumber()
{
    var number=document.getElementByName("number").value
    
    if(number==null || number=="" )
    {
        alert("Please enter the card number");
        return false;
    }
    return true;
  }
</script>
</head>
<body>
	<div>
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>

			<h2>
				Welcome ${pageContext.request.userPrincipal.name} |
				<button onclick="document.forms['logoutForm'].submit()" class="btn btn-outline-secondary">
					Logout</button>
			</h2>
			<form method="POST" action="${contextPath}/searchExistingCard" >
				<h2>Search Credit Cards</h2>
				<h4>Enter credit card number</h4>
				<input name="number" type="text"
					placeholder="Card Number"/> <br> <br>
				<input type="hidden" name="userName"
					value="${pageContext.request.userPrincipal.name}"></input>
					<input
							type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

				<input type="submit" value="Search Existing Card"></input>
			</form>
		</c:if>
	</div>
</body>
</html>