<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <div style="text-align: center;">
        <h1>Home</h1>
        <c:choose>
            <c:when test="${loggedIn==true}">
                Logged in!
                <br />
            </c:when>
            <c:otherwise>
                Not logged in :(
                <br />
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>