package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.DBConnection.DBConnection;
import com.entity.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginform")
public class loginController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try {
			Connection con = DBConnection.getConnection();
			String SQL_QUERY = "select * from users where email=? and password=?";
			PreparedStatement ps = con.prepareStatement(SQL_QUERY);
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setAge(rs.getString("age"));
				user.setCity(rs.getString("city"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				
				HttpSession session = req.getSession();
				session.setAttribute("session", user);
				
				RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
				rd.forward(req, resp);
			}else {
				out.println("<h3 style='color:red'>Invalid Credentials</h3>");
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.html");
				requestDispatcher.include(req, resp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
