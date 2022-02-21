<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
	<title>Library Home Page</title>
</head>

<body>
	<h2>Crazy Library Home Page</h2>
	<hr>
	
	<p>
	Welcome to the crazy Library home page!
	</p>
	
	<hr>
	

	<p>
		User: <security:authentication property="principal.username" />
		<br><br>
		Role(s): <security:authentication property="principal.authorities" />
		<br><br>
		First name: ${user.firstName}, Last name: ${user.lastName}, Email: ${user.email}
	</p>
	
	<security:authorize access="hasRole('Manager')">
	
		<!-- Add a link to point to /manager ... this is for the managers -->
		
		<p>
			<a href="${pageContext.request.contextPath}/manager">Managership Meeting</a>
			(Only for Manager peeps)
		</p>

	</security:authorize>	
	
	
	<security:authorize access="hasRole('Admin')">

		<!-- Add a link to point to /systems ... this is for the admins -->
		
		<p>
			<a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
			(Only for Admin peeps)
		</p>
	
	</security:authorize>
	
	<hr>
	
	
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" />
	
	</form:form>
	
</body>

</html>









