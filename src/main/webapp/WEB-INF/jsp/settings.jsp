<%@ page language="java" contentType="text/html; charset=UTF-8" session="false"
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
<%@ include file="header_logged_in.jsp" %>
<h1>Settings</h1>

<c:choose>
    <c:when test="${fn:escapeXml(isStaff)}">
        <h3>No settings are currently available for staff.</h3>
    </c:when>
    <c:otherwise>
        <h3>De-activate your account</h3>
        <form action="settings/deactivate" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Deactivate" />
        </form>
        <br />
    </c:otherwise>
</c:choose>

</body>
</html>