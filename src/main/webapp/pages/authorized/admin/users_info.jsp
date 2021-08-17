<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${current_locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.users_info"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/orders_info.css"/>
</head>
<body>
<jsp:include page="../../common/header.jsp"/><br>
<c:if test="${ users_size == 0 }">
    <h3><fmt:message key="label.no_results"/></h3>
</c:if>
<c:if test="${ users_size != 0 }">
<table>
    <tr>
        <th>Id</th>
        <th><fmt:message key="label.name"/></th>
        <th><fmt:message key="label.surname"/></th>
        <th><fmt:message key="label.nickname"/></th>
        <th><fmt:message key="label.email"/></th>
        <th><fmt:message key="label.role"/></th>
        <th><fmt:message key="label.change_role"/></th>
        <th><fmt:message key="label.isBanned"/></th>
        <th><fmt:message key="label.ban"/>/<fmt:message key="label.unban"/></th>
    </tr>
    <c:forEach var="user" items="${users}" varStatus="status">
        <tr>
            <td><c:out value="${ user.userId }" /></td>
            <td><c:out value="${ user.name }" /></td>
            <td><c:out value="${ user.surname }" /></td>
            <td><c:out value="${ user.nickname }" /></td>
            <td><c:out value="${ user.email }" /></td>
            <c:if test="${ current_locale == 'en_US' }">
                <td><c:out value="${ user.role }" /></td>
            </c:if>
            <c:if test="${ current_locale != 'en_US' }">
                <c:if test="${ user.role == 'administrator' }">
                    <td><fmt:message key="label.admin"/></td>
                </c:if>
                <c:if test="${ user.role != 'administrator' }">
                    <td><fmt:message key="label.user"/></td>
                </c:if>
            </c:if>
            <td>
                <form style="margin: 0" name="changeUserRole" method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="change_role"/>
                    <input type="hidden" name="user_id" value="${ user.userId }">
                    <input type="submit" value="<fmt:message key="label.change_role"/>"/>
                </form>
            </td>
            <c:if test="${ current_locale == 'en_US' }">
                <td><c:out value="${ user.isBanned }" /></td>
            </c:if>
            <c:if test="${ current_locale != 'en_US' }">
                <c:if test="${ user.isBanned }">
                    <td><fmt:message key="label.yes"/></td>
                </c:if>
                <c:if test="${ !user.isBanned }">
                    <td><fmt:message key="label.no"/></td>
                </c:if>
            </c:if>
            <c:if test="${ user.isBanned }">
                <td>
                    <form style="margin: 0" name="deleteUserFromBlackList" method="POST" action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="delete_user_from_blacklist"/>
                        <input type="hidden" name="user_id" value="${ user.userId }">
                        <input type="submit" value="<fmt:message key="label.delete_from_blacklist"/>"/>
                    </form>
                </td>
            </c:if>
            <c:if test="${ !user.isBanned }">
                <td>
                    <form style="margin: 0" name="addUserToBlackList" method="POST" action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="add_user_to_blacklist"/>
                        <input type="hidden" name="user_id" value="${ user.userId }">
                        <input type="text" name="ban_reason" value="" pattern="^[\w]{0,100}$"
                               title="<fmt:message key="label.ban_reason_title"/>"/>
                        <input type="submit" value="<fmt:message key="label.add_to_blacklist"/>"/>
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    <div style="text-align: center; color: red">
        ${actOnYourselfMessage}
    </div>
</table>
</c:if>

</body>
</html>