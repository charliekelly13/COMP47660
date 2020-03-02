<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <div align="center">
        <h1>Home</h1>
        <table border="1" cellpadding="5">
            <caption><h2>Modules</h2></caption>
            <tr>
                <th>ID</th>
                <th>Description</th>
                <th>Co-ordinator</th>
                <th>Enrollment</th>
            </tr>
        <c:forEach var="module" items="${modules}">
            <tr>
                <td><c:out value="${module.id}" /></td>

                <td><c:out value="${module.moduleDescription}" /></td>

                <td><c:out value="${module.coordinatorName}" /></td>
                <td>
                    <c:out value="${module.enrolledStudents}" />/<c:out value="${module.maximumStudents}" />
                </td>
            </tr>
        </c:forEach>
        </table>
    </div>
</body>
</html>

<%--this.moduleName = moduleName;--%>
<%--this.moduleDescription = moduleDescription;--%>
<%-- module id --%>
<%--this.coordinatorName = coordinatorName;--%>
<%--this.enrolledStudents = enrolledStudents;--%>
<%--this.maximumStudents = maximumStudents;--%>