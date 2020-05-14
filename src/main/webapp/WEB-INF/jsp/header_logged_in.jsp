<%@ page language="java" contentType="text/html; charset=UTF-8" session="false"
         pageEncoding="UTF-8"%>

<style>
    body {
        align-content: center;
        background-color: #bed4f7;
    }
</style>

<div id="header">
    <img src="../../img/logo.png" width="200" alt="Logo"/>
    <div align="right">
        <a href="../../">Home</a>
        <a href="../../modules">All Modules</a>
        <a href="../../stats">Stats</a>
        <a href="../../fee_payment">Fees</a>
        <a href="../../settings">Settings</a>
        <a href="javascript:;" onclick="document.getElementById('logout_form').submit();">Log out</a>
        <form id="logout_form" action="${pageContext.request.contextPath}/logout" method="POST">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
</div>