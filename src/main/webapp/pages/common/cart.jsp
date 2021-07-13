<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" uri="customtags" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.cart"/></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/cart.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>

<ul class="cart" >
    <h1><fmt:message key="label.cart"/></h1>
    <c:if test="${cart_size == 0}">
        <h3><fmt:message key="label.cart_is_empty"/></h3>
    </c:if>
    <c:if test="${cart_size != 0}">
        <hr>
        <c:forEach var="elem" items="${cartProducts}" varStatus="status">
            <li class="product-wrapper">
                <div class="product">
                    <div class="product-photo">
                        <img src="${pageContext.request.contextPath}${elem.path}" alt="Oops">
                    </div>
                </div>
                <div class="description">
                    <c:out value="${ elem.team }" />
                    <c:out value="${ elem.type }" />
                    <c:out value="${ elem.year }" />
                </div>
                <div style="color: red">
                    <c:out value="${ elem.price }$" />
                </div>
                <form style="float: left; margin-left: 37.5%" name="addProduct" method="POST" action="controller">
                    <input type="hidden" name="command" value="add_product_to_cart"/>
                    <input type="hidden" name="product_id" value="${ elem.productId }">
                    <input type="submit" value="+"/>
                </form>
                <div style="float: left; text-align: center; width: 20px">
                    <c:set var="containsKey" value="${ sessionScope.cart.containsKey(elem.productId) }"/>
                    <c:if test="${ containsKey }">
                        <c:out value="${ sessionScope.cart.get(elem.productId) }"/>
                    </c:if>
                    <c:if test="${ !containsKey }">
                        <c:out value="0"/>
                    </c:if>
                </div>
                <form style="float: left" name="deleteProduct" method="POST" action="controller">
                    <input type="hidden" name="command" value="delete_product_from_cart"/>
                    <input type="hidden" name="product_id" value="${ elem.productId }">
                    <input type="submit" value="-"/>
                </form>
            </li>
            <br style="clear:both">
            <hr>
        </c:forEach>

        <h4><fmt:message key="label.total_cost"/> ${total_cost}<fmt:message key="label.currency"/></h4>
        <form style="float: right" name="addOrder" method="POST" action="controller">
            <input type="hidden" name="command" value="add_order"/>
            <input type="submit" value="<fmt:message key="label.buy"/>"/>
        </form>
    </c:if>
</ul>

<br style="clear:both">
<hr>
<tags:copyright/>
</body>
</html>
