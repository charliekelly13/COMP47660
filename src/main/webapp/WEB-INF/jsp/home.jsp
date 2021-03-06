<%@ page language="java" contentType="text/html; charset=UTF-8" session="false"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Home</title>
    <link href="https://fonts.googleapis.com/css2" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
<%@ include file="header_logged_in.jsp" %>
<div align="center">
    <h1>Home</h1>
    <p>Welcome ${student == null ? fn:escapeXml(staff.firstName) : fn:escapeXml(student.firstName)}!</p>
    <table border="1" cellpadding="5">
        <caption><h2>Your ${student == null ? "Taught" : "Enrolled"} Modules</h2></caption>
        <tr>
            <th>Module code</th>
            <th>Module name</th>
            <th></th>
        </tr>

        <c:forEach var="module" items="${modules}">
            <tr>
                <td><c:out value="${fn:escapeXml(module.moduleCode)}" /></td>

                <td><c:out value="${fn:escapeXml(module.moduleName)}" /></td>

                <td><a href="/modules/${fn:escapeXml(module.id)}">View details</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>