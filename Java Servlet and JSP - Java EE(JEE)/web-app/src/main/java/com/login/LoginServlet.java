package com.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todo.TodoService;


@WebServlet(urlPatterns="/login.do")
public class LoginServlet extends HttpServlet {
	
	userValidationService userService = new userValidationService();
	TodoService todoService = new TodoService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name= request.getParameter("name");
		request.setAttribute("name", name);
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		if(userService.isValid(name, password))
		{
			request.getSession().setAttribute("name", name);
			response.sendRedirect("/todo.do");
		}
		else
		{
			request.setAttribute("error", "Invalid User");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}

	}
}
