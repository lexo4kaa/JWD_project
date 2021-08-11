<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" uri="customtags" %>
<fmt:setLocale value="${current_locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.products"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/products.css"/>
</head>
<body>
<jsp:include page="../common/header.jsp"/>

<form id="radio" class="radio" name="findProductsByTeam" method="POST" action="${pageContext.request.contextPath}/controller">
    <h3><fmt:message key="label.select_a_team"/></h3>
    <p><input type="radio" name="team" value="Atletico Madrid"><fmt:message key="label.atletico"/></p>
    <p><input type="radio" name="team" value="Barcelona"><fmt:message key="label.barcelona"/></p>
    <p><input type="radio" name="team" value="Chelsea"><fmt:message key="label.chelsea"/></p>
    <p><input type="radio" name="team" value="Inter Milan"><fmt:message key="label.inter"/></p>
    <p><input type="radio" name="team" value="Juventus"><fmt:message key="label.juventus"/></p>
    <p><input type="radio" name="team" value="Liverpool"><fmt:message key="label.liverpool"/></p>
    <p><input type="radio" name="team" value="Manchester City"><fmt:message key="label.manCity"/></p>
    <p><input type="radio" name="team" value="Manchester United"><fmt:message key="label.manU"/></p>
    <p><input type="radio" name="team" value="Milan"><fmt:message key="label.milan"/></p>
    <p><input type="radio" name="team" value="PSG"><fmt:message key="label.PSG"/></p>
    <p><input type="radio" name="team" value="Real Madrid"><fmt:message key="label.realMadrid"/></p>
    <p><input type="radio" name="team" value="all"><fmt:message key="label.all"/></p>

    <input type="hidden" id="locale" name="locale" value="${current_locale}" />
    <input type="hidden" name="command" value="find_products_by_team_and_type" />
    <input style="font-size:large;background:dodgerblue;color:whitesmoke;margin:10px;padding:5px 10px;cursor:pointer;
                  border:none;border-radius:3px;" name="submit" type="submit" value="<fmt:message key="label.find"/>"/>
</form>

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

                <form style="float: left; margin-left: 35%" name="deleteUnitOfProduct" method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="delete_unit_of_product_from_cart"/>
                    <input type="hidden" name="product_id" value="${ prod.productId }">
                    <input type="submit" value="-"/>
                </form>

                <c:set var="containsKey" value="${ cart.containsKey(prod.productId) }"/>
                <c:if test="${ containsKey }">
                    <c:set var="quantity" value="${ cart.get(prod.productId) }"/>
                </c:if>
                <c:if test="${ !containsKey }">
                    <c:set var="quantity" value="0"/>
                </c:if>

                <form style="float: left" name="changeQuantity" id="changeQuantity" method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="change_quantity_of_product_in_cart"/>
                    <input type="hidden" name="product_id" value="${ prod.productId }">
                    <input style="width: 40px" type="number" name="new_quantity"
                           value="${ quantity }" min="0" max="99" onblur="checkQuantity(this)"/>
                </form>

                <form style="float: left" name="addUnitOfProduct" method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="add_unit_of_product_to_cart"/>
                    <input type="hidden" name="product_id" value="${ prod.productId }">
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
                    <input style="border: none; background-color: white; font-size: x-large" type="submit" value="${heartType}"/>
                </form>
            </li>
        </c:forEach>
    </c:if>
</ul>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/checkQuantity.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/checkSelected.js"></script>
</body>
</html>
