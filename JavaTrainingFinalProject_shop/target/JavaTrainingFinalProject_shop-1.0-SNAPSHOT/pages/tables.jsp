<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head><title>Tables</title></head>
<body>

We managed to find ${lst_length} people
<br/>

<table border="1">
    <c:forEach var="elem" items="${lst}" varStatus="status">
        <tr>
            <td><c:out value="${ elem.name }" /></td>
            <td><c:out value="${ elem.surname }" /></td>
            <td><c:out value="${ elem.nickname }" /></td>
            <td><c:out value="${ elem.email }" /></td>
            <td><c:out value="${ elem.role }" /></td>
        </tr>
    </c:forEach>
</table>

<br/>
<a href="controller?command=logout">Logout</a>

</body></html>