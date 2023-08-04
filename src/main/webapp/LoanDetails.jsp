<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Details</title>
</head>
<body>
	<%
	session = request.getSession();
	int l_id = (int)session.getAttribute("l_id");
	String l_type = (String)session.getAttribute("l_type");
	int tenure = (int)session.getAttribute("tenure");
	int interest = (int)session.getAttribute("interest");
	String description = (String)session.getAttribute("description");
	
	out.print(l_id   +   l_type  +  tenure  +  interest  +  description   );
	%>
	<a href = "HomePage.jsp">Click to redirect</a>

</body>
</html>