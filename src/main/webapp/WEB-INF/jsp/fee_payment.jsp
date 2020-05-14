<%@ page language="java" contentType="text/html; charset=UTF-8" session="false"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><html>
<head>
    <title>Fees</title>
    <link href="https://fonts.googleapis.com/css2" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../../../css/main.css">
</head>

<body>
<head>
    <title>Fees</title>
    <link href="https://fonts.googleapis.com/css2" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../../css/main.css">
</head>
<div id="header">
    <img src="../../../img/logo.png" width="200" alt="Logo"/>
    <div align="right">
        <a href="/">Home</a>
        <a href="/modules">All Modules</a>
        <a href="/stats">Stats</a>
        <a href="/fee_payment">Fees</a>
        <a href="/settings">Settings</a>
        <a href="/logout">Log out</a>
    </div>
</div>

<div align="center">
    <c:choose>
        <c:when test="${isStaff}">
            <h3>There are no fees for staff.</h3>
        </c:when>
        <c:otherwise>
            <font color="red">${fn:escapeXml(errorMessage)}</font>
            <table border="1" cellpadding="5">
                <caption><h2>Your Current Fees</h2></caption>
                <tr>
                    <td>Total Fee Amount</td>
                    <td>€<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${feesTotal}"/></td>
                </tr>
                <tr>
                    <td>Amount Owed</td>
                    <td>€<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${feesOwed}"/></td>
                </tr>
                <tr>
                    <td>Make a Payment</td>
                    <td>
                        <form method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="number" name="feePayment" step="0.01" required/>
                            <input type="submit" value="Pay"/><br>
                        </form>
                    </td>
                </tr>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>