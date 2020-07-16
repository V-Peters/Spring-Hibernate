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
	
	<form:form action="registerUser" modelAttribute="user" method="POST">
		<form:hidden path="id" />
		<table>
			<tbody>
				<tr>
					<td> Benutzername: </td>
					<td><form:input path="username" required="required" /></td>
				</tr>
				<tr>
					<td> Passwort: </td>
					<td><form:input type="password" path="password" required="required" /></td>
				</tr>
				<tr>
					<td> Passwort besätigen: </td>
					<td><form:input type="password" path="passwordCheck" required="required" onkeyup="if(this.password )"/></td>
				</tr>
				<tr>
					<td>
						<br>
					</td>
				</tr>
				<tr>
					<td> Vorname: </td>
					<td><form:input path="firstname" required="required" /></td>
				</tr>
				<tr>
					<td> Nachname: </td>
					<td><form:input path="lastname" required="required" /></td>
				</tr>
				<tr>
					<td> E-Mail: </td>
					<td><form:input path="email" required="required" /></td>
				</tr>
				<tr>
					<td> Firma/Unternehmen: </td>
					<td><form:input path="company" required="required" /></td>
				</tr>
			</tbody>
		</table>
		<br>
		<input type="submit" value="Registrieren" />

	</form:form>
</body>

</html>
