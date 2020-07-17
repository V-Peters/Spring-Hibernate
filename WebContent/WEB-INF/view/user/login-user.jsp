<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>

<head>
	<title>User Login</title>
</head>

<body onload="sessionStorage.clear()">

	<h2>User Login</h2>

	<hr>

	<a href="../index.jsp">Home</a>

	<hr>
	
	<a href="showRegisterPage">Zur Registrierung</a>
	
	<form:form action="loginUser" modelAttribute="loginUser" method="POST">
		<form:hidden path="id" />
		<form:hidden path="firstname" />
		<form:hidden path="lastname" />
		<form:hidden path="email" />
		<form:hidden path="company" />
	
		<table>
			<tbody>
				<tr>
					<td><label>Benutzername:</label></td>
					<td><form:input path="username" required="required" /></td>
				</tr>
				<tr>
					<td><label>Passwort:</label></td>
					<td><form:input type="password" path="password" required="required" /></td>
				</tr>
			</tbody>
		</table>
		
		<br>
		
		<input type="submit" value="Anmelden" />
	
	</form:form>

</body>

</html>