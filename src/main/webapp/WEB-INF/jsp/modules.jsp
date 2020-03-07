<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All Modules</title>
    <link href="https://fonts.googleapis.com/css2" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
    <div id="header">
        <img src="img/logo.png" width="200" alt="Logo"/>
        <div align="right">
            <a href="/settings">Settings</a>
            <a href="/logout">Log out</a>
        </div>
    </div>
    <div align="center">
        <h1>Home</h1>
        <table border="1" cellpadding="5">
            <caption><h2>All Modules</h2></caption>
            <tr>
                <th>Module code</th>
                <th>Module name</th>
                <th></th>
            </tr>

            <c:forEach var="module" items="${modules}">
                <tr>
                    <td><c:out value="${module.moduleCode}" /></td>

                    <td><c:out value="${module.moduleName}" /></td>

                    <td><a href="/modules/${module.id}">View details</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>