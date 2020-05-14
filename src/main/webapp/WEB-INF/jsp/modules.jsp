<%@ page language="java" contentType="text/html; charset=UTF-8" session="false"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>All Modules</title>
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link href="https://fonts.googleapis.com/css2" rel="stylesheet">
</head>
<body>
<%@ include file="header_logged_in.jsp" %>
<div align="center">
    <h1>All Modules</h1>
    Search:

    <form method="get" class="inline">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="text" name="searchTerm"/>
        <button>Search</button>
    </form>

    <table border="1" cellpadding="5">
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