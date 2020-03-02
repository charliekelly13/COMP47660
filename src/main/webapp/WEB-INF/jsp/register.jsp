<html>

<head>
    <title>Register</title>
</head>

<body>
<font color="red">${errorMessage}</font>
<form method="post">
<%--    name, surname,  student ID, address, phone number, email address--%>
    First name: <input type="text" name="first-name" /><br>
    Surname: <input type="text" name="surname" /><br>
    Student ID: <input type="text" name="student-id" /><br>
    Phone number: <input type="text" name="phone-number" /><br>
    Email Address: <input type="text" name="email-address" /><br>
    Username: <input type="text" name="username" /><br>
    Password: <input type="password" name="password" /><br>
    <input type="submit" value="Submit" /><br>
</form>
Already have an account? <a href="/login">Login</a>
</body>

</html>