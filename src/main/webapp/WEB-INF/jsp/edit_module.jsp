<%@ page language="java" contentType="text/html; charset=UTF-8" session="false"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Edit Module</title>
    <link href="https://fonts.googleapis.com/css2" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../../css/main.css">
</head>
<body>
<%@ include file="header_logged_in.jsp" %>

<div align="center">
    <caption>
        <h2>
            Edit Module
        </h2>
    </caption>
    <form method="post">
        <input type="hidden" name="id" value="${fn:escapeXml(module.id)}"  />
        <input>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="text" name="moduleCode"
               value="${fn:escapeXml(module.moduleCode)}"/><br/>

        <input type="text" name="moduleYear"
               value="${fn:escapeXml(module.moduleYear)}"/><br/>

        <input type="text" name="moduleName"
               value="${fn:escapeXml(module.moduleName)}"/><br/>

        <input type="text" name="moduleDescription"
               value="${fn:escapeXml(module.moduleDescription)}"/><br/>

        <input type="number" name="maximumStudents" size="45"
               value="${fn:escapeXml(module.maximumStudents)}"/><br/>

        <input type="hidden" name="coordinatorId" size="45"
               value="${fn:escapeXml(module.coordinatorId)}"/><br/>

        <input type="submit" value="Submit" /><br>
    </form>
</div>
</body>
</html>