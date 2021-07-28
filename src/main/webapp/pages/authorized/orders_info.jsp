<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.users_info"/></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/info.css"/>
</head>
<body>
<jsp:include page="../common/header.jsp"/><br>
<c:if test="${ orders_size == 0 }">
    <h3><fmt:message key="label.no_results"/></h3>
</c:if>
<c:if test="${ orders_size != 0 }">
    <table>
        <tr>
            <th>Id</th>
            <th><fmt:message key="label.order_date"/></th>
            <th><fmt:message key="label.order_cost"/></th>
            <th><fmt:message key="label.method_of_receiving"/></th>
            <th><fmt:message key="label.method_of_payment"/></th>
        </tr>
        <c:forEach var="order" items="${orders}" varStatus="status">
            <tr>
                <td><c:out value="${ order.orderId }" /></td>
                <td><c:out value="${ order.orderDate }" /></td>
                <td><c:out value="${ order.orderCost }" /></td>
                <td><c:out value="${ order.methodOfReceiving }" /></td>
                <td><c:out value="${ order.methodOfPayment }" /></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>