<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" uri="customtags" %>
<fmt:setLocale value="${current_locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.products"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product_info.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="wrapper">
    <h1>${info_product.team} ${info_product.type} ${info_product.year}</h1>
    <div class="product-photo">
        <img src="${pageContext.request.contextPath}${info_product.path}">
    </div>
    <div class="description">

        <p><b><fmt:message key="label.specification"/>:</b> ${info_product.specification}</p>
        <p><b><fmt:message key="label.year"/>:</b> ${info_product.year}</p>
        <p><b><fmt:message key="label.type"/>:</b> ${info_product.type}</p>
        <p><b><fmt:message key="label.team"/>:</b> ${info_product.team}</p>
        <p><b><fmt:message key="label.price"/>:</b> ${info_product.price}$</p>

        <c:set var="containsKey" value="${ cart.containsKey(info_product.productId) }"/>
        <c:if test="${ containsKey }">
            <c:set var="quantity" value="${ cart.get(info_product.productId) }"/>
        </c:if>
        <c:if test="${ !containsKey }">
            <c:set var="quantity" value="0"/>
        </c:if>

        <form style="float: left;" name="deleteUnitOfProduct" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="change_quantity_of_product_in_cart"/>
            <input type="hidden" name="product_id" value="${ info_product.productId }">
            <input type="hidden" name="new_quantity" value="${ quantity - 1 }">
            <input type="submit" value="-"/>
        </form>

        <form style="float: left" name="changeQuantity" id="changeQuantity" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="change_quantity_of_product_in_cart"/>
            <input type="hidden" name="product_id" value="${ info_product.productId }">
            <input style="width: 40px" type="number" name="new_quantity"
                   value="${ quantity }" min="0" max="99" onblur="checkQuantity(this)"/>
        </form>

        <form style="float: left" name="addUnitOfProduct" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="change_quantity_of_product_in_cart"/>
            <input type="hidden" name="product_id" value="${ info_product.productId }">
            <input type="hidden" name="new_quantity" value="${ quantity + 1 }">
            <input type="submit" value="+"/>
        </form>

        <c:set var="isFavourite" value="${ favourites.contains(info_product.productId) }"/>
        <c:if test="${ isFavourite }">
            <c:set var="heartType" value="&#10084;"/>
        </c:if>
        <c:if test="${ !isFavourite }">
            <c:set var="heartType" value="&#9825;"/>
        </c:if>

        <form class="heart" name="changeStatusOfFavouriteProduct" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="change_status_of_favourite_product"/>
            <input type="hidden" name="product_id" value="${ info_product.productId }">
            <input style="border:none;background-color:white;color:red;font-size:xx-large;margin-top:-15px;margin-left:20px"
                   type="submit" value="${heartType}"/>
        </form>

    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/checkQuantity.js"></script>
</body>
</html>
