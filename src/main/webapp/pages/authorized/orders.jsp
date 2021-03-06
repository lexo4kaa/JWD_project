<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${current_locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.users_info"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/orders.css"/>
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
            <th><fmt:message key="label.method_of_payment"/></th>
            <th><fmt:message key="label.method_of_receiving"/></th>
            <th><fmt:message key="label.address"/></th>
            <th><fmt:message key="label.more_about_products"/></th>
        </tr>
        <c:forEach var="order" items="${orders}" varStatus="status">
            <tr>
                <td><c:out value="${ order.orderId }" /></td>
                <td><c:out value="${ order.orderDate }" /></td>
                <td><c:out value="${ order.orderCost }" /></td>
                <c:if test="${ current_locale == 'en_US' }">
                    <td><c:out value="${ order.methodOfPayment }" /></td>
                    <td><c:out value="${ order.methodOfReceiving }" /></td>
                </c:if>
                <c:if test="${ current_locale != 'en_US' }">
                    <c:if test="${ order.methodOfPayment == 'cash' }">
                        <td><fmt:message key="label.cash"/></td>
                    </c:if>
                    <c:if test="${ order.methodOfPayment == 'cart' }">
                        <td><fmt:message key="label.cart"/></td>
                    </c:if>
                    <c:if test="${ order.methodOfReceiving == 'self-delivery' }">
                        <td><fmt:message key="label.self_delivery"/></td>
                    </c:if>
                    <c:if test="${ order.methodOfReceiving == 'delivery' }">
                        <td><fmt:message key="label.delivery"/></td>
                    </c:if>
                </c:if>
                <td><c:out value="${ order.address }" /></td>
                <td>
                    <form style="margin: 0" name="getInfo" method="POST" action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="find_info_about_order"/>
                        <input type="hidden" name="order_id" value="${ order.orderId }">
                        <input type="submit" value="<fmt:message key="label.more_about_products"/>"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>