<!DOCTYPE html>
<html>

<head>
	<script>
		function setUserInSessionStorage(id, firstname, lastname, isAdmin) {
			if (typeof(Storage) !== "undefined") {
				if (!sessionStorage.firstname) {
					sessionStorage.setItem("id", id);
					sessionStorage.setItem("firstname", firstname);
					sessionStorage.setItem("lastname", lastname);
					sessionStorage.setItem("isAdmin", isAdmin);
				}
			}
		}
	</script>
</head>

<body onload="setUserInSessionStorage(${user.id}, '${user.firstname}', '${user.lastname}', '${user.isAdmin}')">
	<% response.sendRedirect("listMeetings"); %>
</body>

</html>
