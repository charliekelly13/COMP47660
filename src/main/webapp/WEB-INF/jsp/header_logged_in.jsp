<%@ page language="java" contentType="text/html; charset=UTF-8" session="false"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="header">
    <img src="../../img/logo.png" width="200" alt="Logo"/>
    <div align="right">
        <a href="../../">Home</a>
        <a href="../../modules">All Modules</a>
        <a href="../../stats">Stats</a>
        <a href="../../fee_payment">Fees</a>
        <a href="../../settings">Settings</a>
        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
            <input type="submit" value="Logout" />
        </form:form>
    </div>
</div>