<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style>
.container {
	text-align: center;
	background-color: lavender;
}
</style>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h3>
			<%
			session = request.getSession();
			out.println("Balance = " + session.getAttribute("balance"));
			%>
		</h3>
		<a href="HomePage.jsp">Redirect</a>
	</div>

</body>
</html>