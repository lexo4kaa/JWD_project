<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" uri="customtags" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.cart"/></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/products.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>

<ul class="cart" >
    <c:forEach var="elem" items="${cartProducts}" varStatus="status">
        <li class="product-wrapper">
            <div class="product">
                <div class="product-photo">
                    <img src="${pageContext.request.contextPath}${elem.path}" alt="Oops">
                </div>
            </div>
            <div>
                <c:out value="${ elem.team }" />
                <c:out value="${ elem.type }" />
                <c:out value="${ elem.year }" />
            </div>
        </li>
    </c:forEach>
</ul>

<br style="clear:both">
<hr>
<tags:copyright/>
</body>
</html>
