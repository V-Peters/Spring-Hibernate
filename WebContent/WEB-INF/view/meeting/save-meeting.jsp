<%@ page import="java.time.LocalDateTime"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>

<head>
	<title>Veranstaltung hinzufügen</title>

	<%! LocalDateTime now = LocalDateTime.now(); %>
	
	<c:set var="now" value="<%= now.toString().substring(0, 16) %>" />
	
	<c:if test="${meeting.datetime.isEmpty() == null}">
		<c:set var="time" value="<%= now.toString().substring(0, 16) %>" />
	</c:if>
	<c:if test="${meeting.datetime.isEmpty() != null}">
		<c:set var="time" value="${meeting.datetime.substring(0, 10)}T${meeting.datetime.substring(11, 16)}" />
	</c:if>
	
</head>

<body>

	<h2>Veranstaltungen</h2>
	<hr>
	<h3>Veranstaltung hinzufügen</h3>

	<form:form action="save" modelAttribute="meeting" method="POST">
		<form:hidden path="id" />

		<table>
			<tbody>
				<tr>
					<td><label>Name:</label></td>
					<td><form:input path="name" required="required" size="50"/></td>
				</tr>
				<tr>
					<td>Datum &<br> Uhrzeit:</td>
					<td><form:input type="datetime-local" path="datetime" value="${time}" min="${now}"/></td>
				</tr>
				<tr>
					<td>Anzeigen:</td>
					<td>
						<c:if test="${meeting.display}">
							<form:checkbox path="display" checked="checked"/>
						</c:if>
						<c:if test="${not meeting.display}">
							<form:checkbox path="display"/>
						</c:if>
					</td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Veranstaltung speichern" /></td>
				</tr>
			</tbody>
		</table>

	</form:form>

	<p>
		<a href="list">Zurück zur Veranstaltungsliste</a>
	</p>

</body>

</html>