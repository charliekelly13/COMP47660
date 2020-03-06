<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Settings</title>
</head>
<body>
    <h1>Settings</h1>

    <h3>De-activate your account</h3>
    <form action="settings/deactivate" method="post">
        <input type="submit" value="Deactivate" />
    </form>
</body>
</html>