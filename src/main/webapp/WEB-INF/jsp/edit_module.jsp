<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Module</title>
</head>
<body>
<div align="center">
    <form action="save" method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Edit Module
                </h2>
            </caption>
            <input type="hidden" name="id" value="<c:out value='${module.id}' />"  />
            <tr>


                <%--<td><c:out value="${module.id}" /></td>--%>

                <%--<td><c:out value="${module.moduleDescription}" /></td>--%>

                <%--<td><c:out value="${module.coordinatorName}" /></td>--%>
                <%--<td>--%>
                <%--    <c:out value="${module.enrolledStudents}" />/<c:out value="${module.maximumStudents}" />--%>
                <th>Description: </th>
                <td>
                    <input type="text" name="moduleDescription" size="45"
                           value="<c:out value='${module.moduleDescription}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>ISBN: </th>
                <td>
                    <input type="text" name="isbn" size="45"
                           value="<c:out value='${book.isbn}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
    </form>
</div>
<a href="./logout">Log out</a>
</body>
</html>