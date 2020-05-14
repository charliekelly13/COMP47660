<%@ page language="java" contentType="text/html; charset=UTF-8" session="false"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Successfully enrolled</title>
    <link href="https://fonts.googleapis.com/css2" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../../../css/main.css">
</head>

<body>
<%@ include file="header_logged_in.jsp" %>

<p>You have successfully ${fn:escapeXml(status)}led in ${fn:escapeXml(module.moduleCode)}:${fn:escapeXml(module.moduleName)}. <a href="/">Click here</a> to view your modules.</p>
</body>

</html>