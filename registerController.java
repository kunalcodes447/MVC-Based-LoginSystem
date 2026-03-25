package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.DBConnection.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registerform")
public class registerController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String city = req.getParameter("city");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try {
			Connection con = DBConnection.getConnection();
			String SQL_QUERY = "insert into users values(?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(SQL_QUERY);
			pstmt.setString(1, name);
			pstmt.setString(2, age);
			pstmt.setString(3, city);
			pstmt.setString(4, email);
			pstmt.setString(5, password);
			
			int count = pstmt.executeUpdate();
			if(count>0) {
				out.println("<h3 style='color:green'>Register Successful </h3>");
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.html");
				requestDispatcher.include(req, resp);
				
			}else {
				out.println("<h3 style='color:red'>Register Failed</h3>");
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/register.html");
				requestDispatcher.include(req, resp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
