package com.todo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todo.TodoService;


@WebServlet(urlPatterns="/add-todo.do")
public class AddServlet extends HttpServlet {
	

	TodoService todoService = new TodoService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/addtodo.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name= request.getParameter("todoname");
		String category= request.getParameter("todocategory");
		todoService.addTodo(name, category);
		response.sendRedirect("/todo.do"); 
	}
	
	


}
