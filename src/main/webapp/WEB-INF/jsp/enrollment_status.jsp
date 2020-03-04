<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Successfully enrolled</title>
</head>

<body>
You have successfully ${status}led in ${module.moduleCode}:${module.moduleName}. <a href="/">Click here</a> to view your modules.
<a href="./logout">Log out</a>
</body>

</html>