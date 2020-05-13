<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Settings</title>
    <link href="https://fonts.googleapis.com/css2" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
<div id="header">
    <img src="img/logo.png" width="200" alt="Logo"/>
    <div align="right">
        <a href="/">Home</a>
        <a href="/modules">All Modules</a>
        <a href="/stats">Stats</a>
        <a href="/fee_payment">Fees</a>
        <a href="/settings">Settings</a>
        <a href="/logout">Log out</a>
    </div>
</div>
<h1>Settings</h1>

<c:choose>
    <c:when test="${fn:escapeXml(isStaff)}">
        <h3>No settings are currently available for staff.</h3>
    </c:when>
    <c:otherwise>
        <h3>De-activate your account</h3>
        <form action="settings/deactivate" method="post">
            <input type="hidden" name="csrfToken" value="${csrfToken}"/>
            <input type="submit" value="Deactivate" />
        </form>
        <br />
    </c:otherwise>
</c:choose>

</body>
</html>