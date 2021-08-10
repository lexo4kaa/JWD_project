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
<jsp:include page="../common/header.jsp"/><br>

<table>
    <tr>
        <th><fmt:message key="label.type"/></th>
        <th><fmt:message key="label.team"/></th>
        <th><fmt:message key="label.year"/></th>
        <th><fmt:message key="label.price"/></th>
        <th><fmt:message key="label.quantity"/></th>
    </tr>
    <c:forEach var="product" items="${info_cart_products}" varStatus="status">
        <tr>
            <td><c:out value="${ product.type }" /></td>
            <td><c:out value="${ product.team }" /></td>
            <td><c:out value="${ product.year }" /></td>
            <td><c:out value="${ product.price }" /></td>
            <td><c:out value="${ info_cart.get(product.productId) }" /></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>