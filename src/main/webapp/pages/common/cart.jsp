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
        <c:forEach var="prod" items="${cartProducts}" varStatus="status">
            <li class="product-wrapper">
                <div class="product">
                    <div class="product-photo">
                        <img src="${pageContext.request.contextPath}${prod.path}" alt="Oops">
                    </div>
                </div>
                <div class="description">
                    <c:out value="${ prod.team }" />
                    <c:out value="${ prod.type }" />
                    <c:out value="${ prod.year }" />
                </div>
                <div style="color: blue">
                    <c:out value="${ prod.price }$" />
                </div>
                <form style="float: left; margin-left: 400px; margin-top: -30px" name="addProduct" method="POST" action="controller">
                    <input type="hidden" name="command" value="add_product_to_cart"/>
                    <input type="hidden" name="product_id" value="${ prod.productId }">
                    <input type="submit" value="+"/>
                </form>

                <c:set var="containsKey" value="${ cart.containsKey(prod.productId) }"/>
                <c:if test="${ containsKey }">
                    <c:set var="quantity" value="${ cart.get(prod.productId) }"/>
                </c:if>
                <c:if test="${ !containsKey }">
                    <c:set var="quantity" value="0"/>
                </c:if>

                <form style="float: left; margin-top: -30px" name="changeQuantity" id="changeQuantity" method="POST" action="controller">
                    <input type="hidden" name="command" value="change_quantity_of_product_in_cart"/>
                    <input type="hidden" name="product_id" value="${ prod.productId }">
                    <input style="width: 40px" type="number" name="new_quantity"
                           value="${ quantity }" min="0" max="99" onblur="checkQuantity(this)"/>
                </form>

                <form style="float: left; margin-top: -30px" name="deleteProduct" method="POST" action="controller">
                    <input type="hidden" name="command" value="delete_product_from_cart"/>
                    <input type="hidden" name="product_id" value="${ prod.productId }">
                    <input type="submit" value="-"/>
                </form>
            </li>
            <br style="clear:both">
            <hr>
        </c:forEach>

        <h3><fmt:message key="label.total_cost"/> ${total_cost}<fmt:message key="label.currency"/></h3>
        <br>

        <form name="addOrder" method="POST" action="controller">
            <div style="float:left; width: 500px">
                <h4>Select method of receiving</h4>
                <select style="float: left" name="methodOfReceiving">
                    <option value="self-delivery" selected><fmt:message key="label.self_delivery"/></option>
                    <option value="delivery"><fmt:message key="label.delivery"/></option>
                </select>
            </div>
            <div style="float:left; width: 500px">
                <h4>Select method of payment</h4>
                <select style="float: left" name="methodOfPayment">
                    <option value="cash" selected><fmt:message key="label.cash"/></option>
                    <option value="card"><fmt:message key="label.card"/></option>
                </select>
            </div>
            <br style="clear:both">
            <input type="hidden" name="command" value="add_order"/>
            <input style="font-size:large;background:dodgerblue;color:whitesmoke;margin:10px;padding:5px 10px;cursor:pointer;
                        border:none;border-radius:3px;" type="submit" value="<fmt:message key="label.buy"/>"/>
        </form>
    </c:if>
</ul>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/checkQuantity.js"></script>

<br style="clear:both">
<hr>
<tags:copyright/>
</body>
</html>
