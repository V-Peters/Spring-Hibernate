<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<title>Liste der Veranstaltungen</title>
</head>

<body>

	<c:set var="id" value="${user.id}" />
	<c:set var="firstname" value="${user.firstname}" />
	<c:set var="lastname" value="${user.lastname}" />
	<c:set var="isAdmin" value="${user.isAdmin}" />
	<c:set var="Admin" value="${isAdmin == 1}" />
	

	${firstname}, ${lastname}, ${isAdmin}<br>
	${user.firstname}, ${user.lastname}, ${user.isAdmin}
	
	<script type="text/javascript">
		document.getElementById("test").innerHTML = sessionStorage.getItem("id");
	</script>
	
	
	
	<h2>Veranstaltungen</h2>
	
	<hr>
	
	<c:if test="${Admin}">
		<input type="button" value="Veranstaltung hinzufügen" onclick="window.location.href='showAddPage'; return false;" />
	</c:if>
	
	
	Angemeldet als 
		<c:if test="${Admin}">
			Admin
		</c:if>
		<c:if test="${!Admin}">
			${firstname} ${lastname}
		</c:if>
	<a href="../user/logout">Logout</a>


	<table border="1">
		<tr>
			<th>ID</th>
			<th width=700>Veranstaltungsname</th>
			<th>Datum</th>
			<th>Uhrzeit</th>
			<c:if test="${Admin}">
				<th>Aktion</th>
				<th>Für User sichtbar</th>
				<th>Teilnehmerliste</th>
			</c:if>
			<c:if test="${!Admin}">
				<th>An Veranstaltung teilnehmen</th>
			</c:if>
		</tr>
		<c:set var="counter" value="${1}" />
		<c:forEach var="tempMeeting" items="${meetings}">
			<c:if test="${Admin or tempMeeting.display}">

				<c:url var="updateLink" value="showUpdatePage">
					<c:param name="meetingId" value="${tempMeeting.id}" />
				</c:url>
	
				<c:url var="deleteLink" value="delete">
					<c:param name="meetingId" value="${tempMeeting.id}" />
				</c:url>
	
				<c:url var="listParticipantsLink" value="../meetingUser/listParticipants">
					<c:param name="meetingId" value="${tempMeeting.id}" />
				</c:url>
				
				<c:url var="changeDisplayLink" value="changeDisplay">
					<c:param name="meetingId" value="${tempMeeting.id}" />
					<c:param name="displayValue" value="${tempMeeting.display}" />
				</c:url>
				
				<c:url var="signUpLink" value="../meetingUser/signUp">
					<c:param name="userId" value="${id}" />
					<c:param name="meetingId" value="${tempMeeting.id}" />
				</c:url>
				
				<c:url var="signOutLink" value="../meetingUser/signOut">
					<c:param name="userId" value="${id}" />
					<c:param name="meetingId" value="${tempMeeting.id}" />
				</c:url>
	
				<tr>
					<td align="right">${counter}</td>
					<td>${tempMeeting.getName()}</td>
					<td>${tempMeeting.getDatetime().substring(8, 10)}.${tempMeeting.getDatetime().substring(5, 7)}.${tempMeeting.getDatetime().substring(0, 4)}</td>
					<td>${tempMeeting.getDatetime().substring(10, 16)} Uhr</td>
					
					<c:if test="${Admin}">
					<td>
						<a href="${updateLink}">Bearbeiten</a>
						 | 
						<a href="${deleteLink}" onclick="if (!(confirm('Sind Sie sicher, dass Sie diese Veranstaltung löschen wollen?'))) return false">Löschen</a>
					</td>
					<td align="center">
						<c:if test="${tempMeeting.isDisplay()}">
							<a href="${changeDisplayLink}">Ja</a>
							<!--<input type="checkbox" name="meetingId" value="${tempMeeting.id}" checked="checked" onclick="${changeDisplayLink}" />-->
						</c:if>
						<c:if test="${not tempMeeting.isDisplay()}">
							<a href="${changeDisplayLink}">Nein</a>
							<!--<input type="checkbox" name="meetingId" value="${tempMeeting.id}" onclick="${changeDisplayLink}" />-->
						</c:if>
						<input type="hidden" name="display${counter}hidden" value="${tempMeeting.id}" />
					</td>
					<td align="center">
						<a href="${listParticipantsLink}">anzeigen</a>
					</td>
					</c:if>
					<c:if test="${!Admin}">
						<td align="center">
							<c:if test="${meetingsSignedUpTo.contains(tempMeeting.id)}">
								<a href="${signOutLink}">Ja</a>
								<!--<input type="checkbox" name="meetingId" value="${tempMeeting.id}" checked="checked" onclick="${changeDisplayLink}" />-->
							</c:if>
							<c:if test="${not meetingsSignedUpTo.contains(tempMeeting.id)}">
								<a href="${signUpLink}">Nein</a>
								<!--<input type="checkbox" name="meetingId" value="${tempMeeting.id}" onclick="${changeDisplayLink}" />-->
							</c:if>
						</td>
					</c:if>
					<c:set var="counter" value="${counter+1}" />
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<input type="hidden" name="end" value="${counter}" />

</body>
</html>