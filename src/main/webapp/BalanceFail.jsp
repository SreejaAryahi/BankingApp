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
		<h1>
			<%
			session = request.getSession();
			out.println("Dear " + session.getAttribute("cust_name") + ", your balance could not be fetched please try again");
			%>
		</h1>
		<a href=HomePAge.jsp>REdirect</a>
	</div>
</body>
</html>