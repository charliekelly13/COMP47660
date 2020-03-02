<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books Store Application</title>
</head>
<body>
<center>
    <h1>Books Management</h1>
    <h2>
        <a href="/new">Add New Book</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All Books</a>

    </h2>
</center>
<div align="center">
    <form action="books" method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2> Add New Book</h2>
            </caption>
            <tr>
                <th>Title: </th>
                    <td>
                        <input type="text" name="book_name" size="45"
                               value="<c:out value='${book.book_name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Authors: </th>
                    <td>
                        <input type="text" name="author_name" size="45"
                               value="<c:out value='${book.author_name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>ISBN: </th>
                    <td>
                        <input type="text" name="isbn" size="45"
                               value="<c:out value='${book.isbn}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
