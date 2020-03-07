<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${module.moduleCode}: ${module.moduleName}</title>
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
    <h1>${module.moduleCode}: ${module.moduleName}</h1>
    <p>
        Academic year: ${module.moduleYear}<br/>
        Description: ${module.moduleDescription}<br/>
        Co-ordinator: ${coordinator}<br/>
        ${amountOfStudents} out of ${module.maximumStudents} places available<br/>
        Grade: ${grade ? grade : "?"}
    </p>
    <form method="post" action="${module.id}/${status}" class="inline">
        <button type="submit">${status}</button>
    </form>
</body>
</html>