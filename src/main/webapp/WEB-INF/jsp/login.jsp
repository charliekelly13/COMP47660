<%@ page language="java" contentType="text/html; charset=UTF-8" session="false"
		 pageEncoding="UTF-8"%>

<html>
	<head>
		<title>Login</title>
		<link href="https://fonts.googleapis.com/css2" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="css/main.css">
	</head>

	<body>
		<div id="header">
			<img src="img/logo.png" width="200" alt="Logo"/>
			<div align="right">
				<a href="/register">Register</a>
				<a href="/login">Log-in</a>
			</div>
		</div>

		<h1>Log in</h1>
		<h4>You must log in to view this page.</h4>
		<font color="red">${errorMessage}</font>
		<form method="post">
			Name:  <input type="text" name="username" /><br>
			Password: <input type="password" name="password" /> <br>
			<input type="submit"value="Submit" /><br>
		</form>
		Don't have an account? <a href="/register">Register</a>
	</body>
</html>