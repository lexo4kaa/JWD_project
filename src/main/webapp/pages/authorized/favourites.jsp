<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" uri="customtags" %>
<fmt:setLocale value="${current_locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.products"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/favourites.css"/>
</head>
<body>
<jsp:include page="../common/header.jsp"/>

<ul class="products">
    <c:if test="${favourite_products.size() == 0}">
        <h3 style="margin: 20px"><fmt:message key="label.no_results"/></h3>
    </c:if>
    <c:if test="${favourite_products.size() != 0}">
        <c:forEach var="prod" items="${favourite_products}" varStatus="status">
            <li class="product-wrapper">
                <div class="product">
                    <div class="product-photo">
                        <form name="infoAboutProduct" method="POST" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="find_info_about_product"/>
                            <input type="hidden" name="product_id" value="${ prod.productId }">
                            <input style="position:absolute;top:0;bottom:0;left:0;right:0;width:200px;height:200px;
                                    background-size:200px;cursor:pointer;margin: auto;border:none;
                                    background-image:url('${pageContext.request.contextPath}${prod.path}');" type="submit" value=""/>

                        </form>
                    </div>
                </div>
                <div>
                    <c:out value="${ prod.team }" />
                    <c:out value="${ prod.type }" />
                    <c:out value="${ prod.year }" />
                </div>
                <div style="color: blue">
                    <c:out value="${ prod.price }$" />
                </div>

                <c:set var="containsKey" value="${ cart.containsKey(prod.productId) }"/>
                <c:if test="${ containsKey }">
                    <c:set var="quantity" value="${ cart.get(prod.productId) }"/>
                </c:if>
                <c:if test="${ !containsKey }">
                    <c:set var="quantity" value="0"/>
                </c:if>

                <form style="float: left; margin-left: 35%" name="deleteUnitOfProduct" method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="change_quantity_of_product_in_cart"/>
                    <input type="hidden" name="product_id" value="${ prod.productId }">
                    <input type="hidden" name="new_quantity" value="${ quantity - 1 }">
                    <input type="submit" value="-"/>
                </form>

                <form style="float: left" name="changeQuantity" id="changeQuantity" method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="change_quantity_of_product_in_cart"/>
                    <input type="hidden" name="product_id" value="${ prod.productId }">
                    <input style="width: 40px" type="number" name="new_quantity"
                           value="${ quantity }" min="0" max="99" onblur="checkQuantity(this)"/>
                </form>

                <form style="float: left" name="addUnitOfProduct" method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="change_quantity_of_product_in_cart"/>
                    <input type="hidden" name="product_id" value="${ prod.productId }">
                    <input type="hidden" name="new_quantity" value="${ quantity + 1 }">
                    <input type="submit" value="+"/>
                </form>

                <c:set var="isFavourite" value="${ favourites.contains(prod.productId) }"/>
                <c:if test="${ isFavourite }">
                    <c:set var="heartType" value="&#10084;"/>
                </c:if>
                <c:if test="${ !isFavourite }">
                    <c:set var="heartType" value="&#9825;"/>
                </c:if>

                <br style="clear:both">
                <form style="text-align: center" name="changeStatusOfFavouriteProduct" method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="change_status_of_favourite_product"/>
                    <input type="hidden" name="product_id" value="${ prod.productId }">
                    <input style="border:none;background-color:white;color:red;font-size:x-large" type="submit" value="${heartType}"/>
                </form>
            </li>
        </c:forEach>
    </c:if>
</ul>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/checkQuantity.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/checkSelected.js"></script>
</body>
</html>
