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

@WebServlet("/Loan")

public class Loan  extends HttpServlet {
	private PreparedStatement pstmt;
	private Connection con;
	private ResultSet resultSet;

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		int option=Integer.parseInt(req.getParameter("option"));

		String url="jdbc:mysql://localhost:3306/projectbank";
		String user="root";
		String pwd="Sreejarac@18";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			String sql = "select * from loan where l_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, option); 
			resultSet = pstmt.executeQuery();
			if(resultSet.next()==true)
			{
			session.setAttribute("l_id", resultSet.getInt("l_id"));
			session.setAttribute("l_type", resultSet.getString("l_type"));
			session.setAttribute("tenure", resultSet.getInt("tenure"));
			session.setAttribute("interest", resultSet.getInt("interest"));
			session.setAttribute("description", resultSet.getString("description"));;
			resp.sendRedirect("LoanDetails.jsp");
			}
			else {
				resp.sendRedirect("LoanFail.html");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}



