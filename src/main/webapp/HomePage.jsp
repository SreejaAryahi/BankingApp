<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HomePage</title>
</head>
<body>


	<h1 align = "center">Welcome</h1>
	<%
		session = request.getSession();
		String s1 = (String)session.getAttribute("customer_name");
		out.println(s1+", Welcome to your account. Please select an operation to perfom.");
	%>
	<div style = "text-align : center">
	<a href="CheckBalance"><br><br>1. Check Balance</a>
	<a href="ChangePassword.html"><br><br>2. Change Password</a>
	<a href="Loan.jsp"><br><br>2. Apply Loan</a>
	<a href="Transfer.html"><br><br>2. Transfer Amount</a>
	<a href="ViewTransaction.jsp"><br><br>2. View All Transactions</a>
	<a href= "LogOut"><br><br>4. Logout</a>
	</div>
	


</body>
</html>