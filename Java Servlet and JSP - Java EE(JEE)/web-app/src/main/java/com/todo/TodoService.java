package com.todo;

import java.util.ArrayList;

public class TodoService 
{
	public static ArrayList<Todo> todoList = new ArrayList<Todo>();
	static {
		todoList.add(new Todo("Learn J2E", "Study"));
		todoList.add(new Todo("Learn MVC","Study"));
		todoList.add(new Todo("Do Coding","Study"));
		todoList.add(new Todo("Do Dancing","Fun"));
		
	}
	
	public static ArrayList<Todo> retrieveList()
	{
		return todoList;
	}
	
	public static void addTodo(String name, String category)
	{
		todoList.add(new Todo(name, category));
	}
	public static void deleteTodo(String name, String category)
	{
		todoList.remove(new Todo(name, category));
	}

}
