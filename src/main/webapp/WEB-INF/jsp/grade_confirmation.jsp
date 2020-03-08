<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Grade Confirmed</title>
    <link href="https://fonts.googleapis.com/css2" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../../../css/main.css">
</head>

<body>
<div id="header">
    <img src="../../../img/logo.png" width="200" alt="Logo"/>
    <div align="right">
        <a href="/">Home</a>
        <a href="/modules">All Modules</a>
        <a href="/settings">Settings</a>
        <a href="/logout">Log out</a>
    </div>
</div>
<p>Grade successfully inputted. <a href="/modules/${module.id}">Click here</a> to return to the module page.</p>
</body>

</html>