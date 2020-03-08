<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Edit Module</title>
        <link href="https://fonts.googleapis.com/css2" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="../../css/main.css">
    </head>
    <body>
        <div id="header">
            <img src="../../img/logo.png" width="200" alt="Logo"/>
            <div align="right">
                <a href="../../">Home</a>
                <a href="../../modules">All Modules</a>
                <a href="../../stats">Stats</a>
                <a href="../../settings">Settings</a>
                <a href="../../logout">Log out</a>
            </div>
        </div>
        <div align="center">
            <caption>
                <h2>
                    Edit Module
                </h2>
            </caption>
            <form method="post">
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
                       value="${module.coordinatorId}"/><br/>

                <input type="submit" value="Submit" /><br>
            </form>
        </div>
    </body>
</html>