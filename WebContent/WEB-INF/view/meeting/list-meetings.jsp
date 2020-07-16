<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<title>Liste der Veranstaltungen</title>
	
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js" ></script>
	
	<script>
	function clickCounter(wert) {
		if (typeof(Storage) !== "undefined") {
			if (sessionStorage.clickcount) {
				sessionStorage.clickcount = Number(sessionStorage.clickcount)+1;
				sessionStorage.setItem("test", wert);
			} else {
				sessionStorage.clickcount = 1;
			}
			document.getElementById("result").innerHTML = "You have clicked the button " + sessionStorage.clickcount + " time(s) in this session.";
			document.getElementById("myID").innerHTML = sessionStorage.getItem("test");
	//		document.getElementById("myIDs").innerHTML = sessionStorage.getItem("qwertz");
		} else {
			document.getElementById("result").innerHTML = "Sorry, your browser does not support web storage...";
		}
	}
	function get(wort) {
		document.getElementById("myIDs").innerHTML = sessionStorage.getItem("joo");
		document.getElementById("myIDss").innerHTML = sessionStorage.getItem("joo");
		return sessionStorage.getItem(wort);
	}

	function set(a) {
		sessionStorage.setItem("joo", a);
	}
	
	function handler(elementRef) {
		console.log(elementRef);
	}
	
	
	function setUserInSessionStorage(id, firstname, lastname, isAdmin) {
		if (typeof(Storage) !== "undefined") {
			if (!sessionStorage.firstname) {
				sessionStorage.setItem("id", id);
				sessionStorage.setItem("firstname", firstname);
				sessionStorage.setItem("lastname", lastname);
				sessionStorage.setItem("isAdmin", isAdmin);
			}
		}
		var klo = sessionStorage.getItem("firstname");
	//	document.getElementsByName("hulk").value= sessionStorage.getItem("firstname");
	}
	

	function setUserId(id) {
		sessionStorage.setItem("id", id);
	}
	
	function setUserFirstname(firstname) {
		sessionStorage.setItem("firstname", firstname);
	}
	
	function getId() {
		document.getElementById("demo").innerHTML = (window.sessionStorage.getItem("id") == '1');
		return window.sessionStorage.getItem("id");
	}

	function getFirstname() {
		return sessionStorage.getItem("firstname");
	}

	function getIsAdmin() {
		return sessionStorage.getItem("isAdmin");
	}
	
	</script>
	
	
	
<script type="text/javascript">
<c:set var="Rababa" value="qwertz"/>
var gaul = '<c:out value="Hallo"/>';
</script>
	
</head>

<body onload="setUserInSessionStorage(${user.id}, '${user.firstname}', '${user.lastname}', '${user.isAdmin}')">

	<script type="text/javascript">
		var ert = "Peter";
	</script>


<input type="hidden" name="hulk" value="">
	<c:set var="id" value="3" />
	<c:set var="firstname" value="${ert}" />
	<c:set var="lastname" value="Jerry" />
	<c:set var="isAdmin" value="0" />
	<c:set var="Admin" value="${isAdmin == 1}" />
	
	
	<!--
<c:set var="selectedQuery" value="" />  
<script>
    $("#${selectedQuery}").val('Hallo');
</script>
-->


	USERID: ${user.id} <br>
	USERFIRSTNAME: ${user.firstname} <br>
	USERLASTNAME: ${user.lastname} <br>
	USERISADMIN: ${user.isAdmin} <br>

qwertz: ${selecedQuery}

	<c:set var="ups" value="getId(1)" />
	<p><button onclick="clickCounter('${ups}')" type="button">Click me!</button></p>
	<div id="result"></div>
	<div id="myID">id:</div>
	<div id="myIDs">ids:</div>
	<div id="myIDss">idss:</div>
	<div id="username" title="asdf">
		qqqqq<c:set var="username" value="${1}" />
	</div>
	<div id="demo">Testfeld: </div>

	<script>
	//	this.setUserInSessionStorage(id, firstname, lastname, isAdmin);
	//	this.setUserInSessionStorage(${user.id}, ${user.firstname}, ${user.lastname}, ${user.isAdmin})
	//	this.set(document.getElementById("username"));
	//	this.handler(this);
	//	setUserId(user.getId());
	//	setUserFirstname(${user.firstname});
	//	this.getId();

		
	//	function setUserInSessionStorage(id, firstname, lastname, isAdmin) {
	//		sessionStorage.setItem("id", id);
	//		sessionStorage.setItem("firstname", firstname);
	//		sessionStorage.setItem("lastname", lastname);
	//		sessionStorage.setItem("isAdmin", isAdmin);
	//	}
	</script>



	${firstname}, ${lastname}, ${isAdmin}<br>
	${user.firstname}, ${user.lastname}, ${user.isAdmin}
	<script>getId()</script>
	
	
	
	<h2>Veranstaltungen</h2>
	
	<hr>
	
	<input type="button" value="Veranstaltung hinzufügen" onclick="window.location.href='showAddPage'; return false;" />
	
	Angemeldet als 
		<c:if test="${Admin}">
			Admin
		</c:if>
		<c:if test="${!Admin}">
			${firstname} ${lastname}
		</c:if>
	<a href="../user/logout" onclick="sessionStorage.clear()">Logout</a>


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
					
					<c:if test="${isAdmin == 1}">
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
					<c:if test="${isAdmin == 0}">
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