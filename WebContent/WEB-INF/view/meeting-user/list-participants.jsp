<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Liste der Veranstaltungen</title>
</head>

<body>

	<h2>Veranstaltungen</h2>

	<input type="button" value="zurück zur Übersicht" onclick="window.location.href='../meeting/list'; return false;" />
	
	<table>
		<tr>
			<td align="center">
				<table>
					<tr>
						<th>${meeting.name}</th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th>${meeting.datetime.substring(8, 10)}.${meeting.datetime.substring(5, 7)}.${meeting.datetime.substring(0, 4)}</th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th>${meeting.datetime.substring(10, 16)} Uhr</th>
					</tr>
				</table>
			</td>
		</tr>
		<c:if test="${participants.isEmpty()}" >
			<tr>
				<td>
					Zu dieser Veranstaltung haben sich noch keine User angemeldet.
				</td>
			</tr>
		</c:if>
		<c:if test="${not participants.isEmpty()}" >
			<tr>
				<td>
					<table border="1">
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>E-mail</th>
							<th>Firma</th>
						</tr>
						<c:set var="counter" value="${1}" />
						<c:forEach var="tempUser" items="${participants}">
							<tr>
								<td>${counter}</td>
								<td>${tempUser.firstname} ${tempUser.lastname}</td>
								<td>${tempUser.email}</td>
								<td>${tempUser.company}</td>
								<c:set var="counter" value="${counter+1}" />
							</tr>
						</c:forEach>
					</table>
					<input type="hidden" name="end" value="${counter}" />
				</td>
			</tr>
		</c:if>
	</table>

</body>
</html>