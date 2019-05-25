package com.todo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.Database;
import com.todo.TodoService;


@WebServlet(urlPatterns="/todo.do")
public class TodoServlet extends HttpServlet {
	
	Database db=new Database();
	TodoService todoService = new TodoService();
	//Database db =new Database();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn=null;
		Properties prop = new Properties();
		prop.load(new FileInputStream("DBProp"));
		String user= prop.getProperty("user");
		String pass= prop.getProperty("password");
		String connection= prop.getProperty("connection");
		try {
			conn = DriverManager.getConnection(connection,user,pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		try {
	
			request.setAttribute("todos", db.retrieveTodo(conn));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		request.setAttribute("todos", todoService.retrieveList());
		request.getRequestDispatcher("/WEB-INF/views/todo.jsp").forward(request, response);
	}
	

	
	


}
