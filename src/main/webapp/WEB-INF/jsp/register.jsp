<html>

<head>
    <title>Register</title>
</head>

<body>
<font color="red">${errorMessage}</font>
<form method="post">
<%--    name, surname,  student ID, address, phone number, email address--%>
    First name: <input type="text" name="first-name" />
    Surname: <input type="text" name="surname" />
    Student ID: <input type="text" name="student-id" />
    Phone number: <input type="text" name="phone-number" />
    Email Address: <input type="text" name="email-address" />
    Username: <input type="text" name="username" />
    Password: <input type="password" name="password" />
    <input type="submit" />
</form>
<a href="/register">Login</a>
</body>

</html>