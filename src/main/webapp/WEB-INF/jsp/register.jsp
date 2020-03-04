<html>

<head>
    <title>Register</title>
</head>

<body>
<font color="red">${errorMessage}</font>
<form method="post">
<%--    name, surname,  student ID, address, phone number, email address--%>
    First name: <input type="text" name="firstName" /><br>
    Surname: <input type="text" name="lastName" /><br>
    Student ID: <input type="text" name="id" /><br>
    Nationality: <input type="text" name="nationality" /><br>
    Phone number: <input type="text" name="phoneNumber" /><br>
    Email Address: <input type="text" name="emailAddress" /><br>
    Gender: <input type="text" name="gender" /><br>
    Address: <input type="text" name="address" /><br>
    Username: <input type="text" name="username" /><br>
    Password: <input type="password" name="password" /><br>
    <input type="submit" value="Submit" /><br>
</form>
Already have an account? <a href="/login">Login</a>
</body>

</html>