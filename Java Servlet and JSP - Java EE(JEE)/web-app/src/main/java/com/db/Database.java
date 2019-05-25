package com.db;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import com.todo.Todo;

public class Database {
	
	
	public static void main(String args[])
	{	
			
	}
	public ArrayList<Todo> retrieveTodo(Connection conn) throws SQLException
	{
		ArrayList<Todo> todo_list = new ArrayList<Todo>();
		PreparedStatement prep= conn.prepareStatement("{call get_Todo()}");	
		prep.execute();
		ResultSet resultSet= prep.getResultSet();
		while(resultSet.next())
		{
			Todo tod_item= new Todo(resultSet.getString("Name"), resultSet.getString("Category"));
			todo_list.add(tod_item);
		}
		return todo_list;
	}

}
