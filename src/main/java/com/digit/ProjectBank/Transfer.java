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

@WebServlet("/Transfer")

public class Transfer extends HttpServlet {
	private PreparedStatement pstmt;
	private Connection con;
	private ResultSet res1;
	private ResultSet res2;
	private ResultSet res3;
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		

		String url = "jdbc:mysql://localhost:3306/projectbank";

		String user = "root";

		String pwd = "Sreejarac@18";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, pwd);
			//sender
			int cust_id = Integer.parseInt(req.getParameter("cust_id"));
			String ifsc_code = (req.getParameter("ifsc_code"));
			int sender_accno = Integer.parseInt(req.getParameter("sender_accno"));
			int pin = Integer.parseInt(req.getParameter("pin"));
			int amount = Integer.parseInt(req.getParameter("amount"));
			String bank_name = (req.getParameter("bank_name"));
			
			//Receiver
			
			String receiver_ifsc = (req.getParameter("receiver_ifsc"));
			int receiver_accno = Integer.parseInt(req.getParameter("receiver_accno"));
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");

			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("connection created");

			pstmt = con.prepareStatement("select * from bankapp where cust_id=? and ifsc_code=? and accno=? and pin=?");

			pstmt.setInt(1, cust_id);
			pstmt.setString(2, ifsc_code);
			pstmt.setInt(3, sender_accno);
			pstmt.setInt(4, pin);

			res1 = pstmt.executeQuery();

			if (res1.next() == true) {
				pstmt = con.prepareStatement("select * from bankapp where ifsc_code=? and accno=?");
				pstmt.setString(1, receiver_ifsc);
				pstmt.setInt(2, receiver_accno);
				res2 = pstmt.executeQuery();

				if (res2.next() == true) {
					pstmt = con.prepareStatement("select balance from bankapp where accno=?");
					pstmt.setInt(1, sender_accno);
					res3 = pstmt.executeQuery();
					res3.next();
					int balance = res3.getInt(1);

					if (balance > amount) {
						pstmt = con.prepareStatement("update bankapp set balance=balance-? where accno=?");
						pstmt.setInt(1, amount);
						pstmt.setInt(2, sender_accno);

						int x1 = pstmt.executeUpdate();

						if (x1 > 0) {
							pstmt = con.prepareStatement("update bankapp set balance=balance+? where accno=?");
							pstmt.setInt(1, amount);
							pstmt.setInt(2, receiver_accno);

							int x2 = pstmt.executeUpdate();

							if (x2 > 0) {
								pstmt = con.prepareStatement("insert into transferStatus values(?,?,?,?,?,?,?)");
								pstmt.setInt(1, cust_id);
								pstmt.setString(2, bank_name);
								pstmt.setString(3, ifsc_code);
								pstmt.setInt(4, sender_accno);
								pstmt.setString(5, receiver_ifsc);
								pstmt.setInt(6, receiver_accno);
								pstmt.setInt(7, amount);

								int x3 = pstmt.executeUpdate();
								if (x3 > 0) {
									resp.sendRedirect("TransferSuccess.html");

								} else {
									String transferdetails= "TransactionDetailsError";
											session.setAttribute("error",transferdetails);
									resp.sendRedirect("TransferFail.jsp");

								}

							} else {
								String transferdetails= "BalanceCreditError";
								session.setAttribute("error",transferdetails);
								resp.sendRedirect("TransferFail.jsp");

							}
						} else {
							String transferdetails= "BalanceDebitError";
							session.setAttribute("error",transferdetails);
							resp.sendRedirect("TransferFail.jsp");
						}

					} else {
						String transferdetails= "InsufficientBalanceError";
						session.setAttribute("error",transferdetails);
						resp.sendRedirect("TransferFail.jsp");
					}

				} else {
					String transferdetails= "RecieverCredentialsError";
					session.setAttribute("error",transferdetails);
					resp.sendRedirect("TransferFail.jsp");
				}

			} else {
				String transferdetails= "SenderCredentialsError";
				session.setAttribute("error",transferdetails);
				resp.sendRedirect("TransferFail.jsp");
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}



