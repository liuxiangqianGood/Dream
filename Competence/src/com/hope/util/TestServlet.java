package com.hope.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class TestServlet extends HttpServlet  {
	@Override
	
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(request, resp);
		String username=request.getParameter("username");
		request.setAttribute("username", username);
		 RequestDispatcher requestDispatcher =request.getRequestDispatcher("success.jsp");
		 requestDispatcher.forward(request, resp);
		
	}
}
