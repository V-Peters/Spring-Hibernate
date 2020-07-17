<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>

<head>
	<title>User Registrierung</title>
	
</head>

<body>

	<h2>User Registrierung</h2>

	<hr>

	<a href="../index.jsp">Home</a>

	<hr>
	
	<a href="showLoginPage">Zur Anmeldung</a>
	
	<form:form action="registerUser" modelAttribute="registerUser" method="POST">
		<form:hidden path="id" />
		<form:hidden path="isAdmin" value="0" />
		<table>
			<tbody>
				<tr>
					<td> Benutzername: </td>
					<td><form:input path="username" required="required" minlength="4" /></td> 
					<td>${errorMessage}</td>
				</tr>
				<tr>
					<td> Passwort: </td>
					<td><form:input id="password" type="password" path="password" required="required" /></td>
				</tr>
				<tr>
					<td>
						<br>
					</td>
				</tr>
				<tr>
					<td> Vorname: </td>
					<td><form:input path="firstname" required="required" value="${user.firstname}" /></td>
				</tr>
				<tr>
					<td> Nachname: </td>
					<td><form:input path="lastname" required="required" value="${user.lastname}" /></td>
				</tr>
				<tr>
					<td> E-Mail: </td>
					<td><form:input path="email" required="required" value="${user.email}" /></td>
				</tr>
				<tr>
					<td> Firma/Unternehmen: </td>
					<td><form:input path="company" required="required" value="${user.company}" /></td>
				</tr>
			</tbody>
		</table>
		<br>
		<input type="submit" value="Registrieren" />
	</form:form>
	
</body>

</html>
