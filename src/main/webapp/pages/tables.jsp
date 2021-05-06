<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head><title>Tables</title></head>
<body>
<jsp:include page="header.jsp"/><br>

<p>We managed to find ${lst_length} people</p>
<table border="1">
    <c:forEach var="elem" items="${lst}" varStatus="status">
        <tr>
            <td><c:out value="${ elem.userId }" /></td>
            <td><c:out value="${ elem.name }" /></td>
            <td><c:out value="${ elem.surname }" /></td>
            <td><c:out value="${ elem.nickname }" /></td>
            <td><c:out value="${ elem.email }" /></td>
            <td><c:out value="${ elem.role }" /></td>
            <td>
                <form style="margin: 0" name="deleteUser" method="POST" action="controller">
                    <input type="hidden" name="command" value="delete_user"/>
                    <input type="hidden" name="user_id" value="${ elem.userId }">
                    <input type="submit" value="x"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>