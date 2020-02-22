<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books Store Application</title>
</head>
<body>
<div style="text-align: center;">
    <h1>Books Management</h1>
    <h2>
        <a href="/new">Add New Book</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/">List All Books</a>

    </h2>
</div>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Books</h2></caption>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Authors</th>
            <th>ISBN</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="book" items="${listBooks}">
            <tr>
                <td><c:out value="${book.id}" /></td>
                <td><c:out value="${book.book_name}" /></td>
                <td><c:out value="${book.author_name}" /></td>
                <td><c:out value="${book.isbn}" /></td>
                <td>
                    <a href="/books/${book.id}"/>Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/delete/${book.id}"  />Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>