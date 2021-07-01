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
            <div class="buttons">
                <!-- todo -->
            </div>
        </li>
        <br style="clear:both">
        <hr>
    </c:forEach>

    <form style="float: right" name="addOrder" method="POST" action="controller">
        <input type="hidden" name="command" value="add_order"/>
        <input type="submit" value="<fmt:message key="label.add_order"/>"/>
    </form>
</ul>

<br style="clear:both">
<hr>
<tags:copyright/>
</body>
</html>
