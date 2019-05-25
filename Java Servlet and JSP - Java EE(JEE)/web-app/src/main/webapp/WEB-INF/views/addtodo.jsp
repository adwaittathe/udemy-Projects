<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<style>
	.footer {
		position: absolute;
		bottom: 0;
		width: 100%;
		height: 60px;
		background-color: #f5f5f5;
	}
</style>
<title>Todo</title>
</head>
<body>
<nav class="navbar navbar-default">
 <a href="/" class="navbar-brand">Brand</a>
 <ul class="nav navbar-nav">
       <li class="active"><a href="#">Home</a></li>
	   <li><a href="/todo.do">Todos</a></li>
	   <li><a href="http://www.google.com">Google</a></li>
 </ul>
 <ul class="nav navbar-nav navbar-right">
       <li><a href="/logout.do">Logout</a></li>
 </ul>
</nav>
<div class="container">



<h4>Add new item </h4>

<form class="form" action="/add-todo.do" method="post">
  <fieldset class="form-group">
  <label>Enter Todo: </label>
  <input type="text" name="todoname" class="form-control"/>
  </fieldset>
  <br>
  <fieldset class="form-group">
  <label>Enter Category: </label>
  <input type="text" name="todocategory" class="form-control"/>
  </fieldset>
  <br>

  <input class="btn btn-success" type="submit" value="Add new item"/>  
</form>


</div>

<footer class="footer">
		<p>footer content</p>
	</footer>

<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="webjars/jquery/1.1.9/jquery.min.js"></script>
</body>
</html>