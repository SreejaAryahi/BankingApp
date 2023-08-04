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

@WebServlet("/ChangePassword")

public class ChangePassword  extends HttpServlet {
	private PreparedStatement pstmt;
	private Connection con;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session=req.getSession();
        int accno=(int)session.getAttribute("accno");
        int password=Integer.parseInt(req.getParameter("old_pwd"));
        int n_pwd=Integer.parseInt(req.getParameter("n_pwd"));
        int c_pwd=Integer.parseInt(req.getParameter("c_pwd"));
        if(c_pwd==n_pwd)
        {
            String url="jdbc:mysql://localhost:3306/projectbank";
            String user="root";
            String pwd="Sreejarac@18";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url,user,pwd);
                String sql="update BankApp set pin=? where accno=? and pin=?";
                pstmt=con.prepareStatement(sql);
                pstmt.setInt(1,n_pwd);
                pstmt.setInt(2,accno);
                pstmt.setInt(3,password);
                int x = pstmt.executeUpdate();
                if(x > 0) {
                resp.sendRedirect("PasswordChangeSuccess.html");
                }
                else {
                	resp.sendRedirect("PasswordChangeFail.html");
                }
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            resp.sendRedirect("PasswordChangeFail.html");
        }

 

 

    }
	
}
