package com.digit.ProjectBank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")

public class Login extends HttpServlet{
	private PreparedStatement pstmt;
	private Connection con;
	private ResultSet resultSet;

	@Override

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession() ;

		int cust_id = Integer.parseInt(req.getParameter("cust_id"));

		int pin = Integer.parseInt(req.getParameter("pin"));

		String url = "jdbc:mysql://localhost:3306/projectbank";

		String user = "root";

		String pwd = "Sreejarac@18";



		//Database connection

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, pwd);
			pstmt = con.prepareStatement("Select * from bankapp where cust_id=? and pin=?");
			pstmt.setInt(1, cust_id);
			pstmt.setInt(2, pin);
			
			resultSet = pstmt.executeQuery();

			if(resultSet.next()==true) {
				session.setAttribute("accno", resultSet.getInt("accno"));
				session.setAttribute("customer_name", resultSet.getString("customer_name"));

				resp.sendRedirect("/ProjectBank/HomePage.jsp");

			}

			else {

				resp.sendRedirect("/ProjectBank/LoginFail.html");

			}



		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

}
