<%@ page language="java" contentType="text/html; charset=UTF-8" session="false"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>${fn:escapeXml(module.moduleCode)}: ${fn:escapeXml(module.moduleName)}</title>
    <link rel="stylesheet" type="text/css" href="../../css/main.css">
    <link href="https://fonts.googleapis.com/css2" rel="stylesheet">
</head>
<body>
<%@ include file="header_logged_in.jsp" %>
<h1>${fn:escapeXml(module.moduleCode)}: ${fn:escapeXml(module.moduleName)}</h1>
<p>
    Academic year: ${fn:escapeXml(module.moduleYear)}<br/>
    Description: ${fn:escapeXml(module.moduleDescription)}<br/>
    Co-ordinator: ${fn:escapeXml(coordinator)}<br/>
    <c:if test="${fn:escapeXml(module.terminated)}">
    <b>Module is terminated.</b><br/>
    </c:if>
    ${fn:escapeXml(amountOfStudents)} student enrolled out of ${fn:escapeXml(module.maximumStudents)} available places<br/>
    <c:choose>
    <c:when test="${isStaff}">
    <a href="${module.id}/edit">Edit module details</a>
    <c:if test="${module.terminated}">
<h3>Add grade</h3>
<form method="post" action="${module.id}/grade" class="inline">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    Student ID: <input type="text" name="studentID"/>
    Grade (as percentage): <input type="text" name="grade"/>
    <button type="submit">Submit grade</button>
</form>
<br />
</c:if>
</c:when>
<c:otherwise>
    <form method="post" action="${module.id}/${status}" class="inline">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <c:choose>
            <c:when test="${module.terminated}">
                Students can't enrol in a terminated module.
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${feesPaid}">
                        <button type="submit">${status}</button>
                    </c:when>
                    <c:otherwise>
                        <font color="red">You must pay your fees before enroling in a module.</font>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </form>
    <br />
    <c:if test="${status.equals('unenrol')}">
        Your grade: ${grade}
    </c:if>
</c:otherwise>
</c:choose>

<br/><br/>

<table border="1" cellpadding="5">
    <tr>
        <th>Grade range</th>
        <c:forEach var="gradeEntry" items="${gradeMap}">
            <td><c:out value="${gradeEntry.key}" /></td>
        </c:forEach>
    </tr>
    <tr>
        <th>Count</th>
        <c:forEach var="gradeEntry" items="${gradeMap}">
            <td><c:out value="${gradeEntry.value}" /></td>
        </c:forEach>
    </tr>
</table>
</p>
</body>
</html>