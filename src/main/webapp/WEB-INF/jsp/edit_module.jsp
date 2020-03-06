<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Edit Module</title>
    </head>
    <body>
        <div align="center">
            <caption>
                <h2>
                    Edit Module
                </h2>
            </caption>
            <form method="post">
<%--                id: <input type="text" name="id" /><br>--%>
<%--                moduleCode: <input type="text" name="moduleCode" /><br>--%>
<%--                moduleYear: <input type="text" name="moduleYear" /><br>--%>
<%--                moduleName: <input type="text" name="moduleName" /><br>--%>
<%--                moduleDescription: <input type="text" name="moduleDescription" /><br>--%>
<%--                maximumStudents: <input type="text" name="maximumStudents" /><br>--%>
<%--                <input type="submit" value="Submit" /><br>--%>
                <input type="hidden" name="id" value="${module.id}"  />

                <input type="text" name="moduleCode"
                       value="${module.moduleCode}"/><br/>

                <input type="text" name="moduleYear"
                       value="${module.moduleYear}"/><br/>

                <input type="text" name="moduleName"
                       value="${module.moduleName}"/><br/>

                <input type="text" name="moduleDescription"
                       value="${module.moduleDescription}"/><br/>

                <input type="number" name="maximumStudents" size="45"
                       value="${module.maximumStudents}"/><br/>

                <input type="hidden" name="coordinatorId" size="45"
                       value="45335"/><br/>

                <input type="submit" value="Submit" /><br>
            </form>
        </div>
        <a href="./logout">Log out</a>
    </body>
</html>