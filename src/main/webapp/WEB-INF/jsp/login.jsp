<html>

<head>
<title>Login</title>
</head>

<body>
	<font color="red">${errorMessage}</font>
	<form method="post">
		Name : <input type="text" name="name" /><br>
		Password : <input type="password" name="password" /> <br>
		<input type="submit"value="Submit" /><br>
	</form>
	Don't have an account? <a href="/register">Register</a>
</body>

</html>