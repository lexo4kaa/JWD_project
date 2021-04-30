<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/products.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>


<div class="team">
    <h3>Team</h3>
    <div id="teamDropdown">
        <input type="radio" class="btn" value="all">All<br>
        <input type="radio" class="btn" value="Barcelona">Barcelona<br>
        <input type="radio" class="btn" value="Chelsea">Chelsea<br>
        <input type="radio" class="btn" value="Liverpool">Liverpool<br>
        <input type="radio" class="btn" value="PSG">PSG<br>
    </div>
</div>

<ul class="products" >
    <c:forEach var="elem" items="${lst}" varStatus="status">
    <li class="product-wrapper">
        <a href="" class="product">
            <div class="product-photo">
                <img src="${pageContext.request.contextPath}${elem.path}" alt="Oops">
            </div>
        </a>
        <div class="product-text"><c:out value="${ elem.team }" />
                                  <c:out value="${ elem.type }" />
                                  <c:out value="${ elem.year }" /></div>
        <form style="margin: 0 auto; width: 93%" name="buyProduct" method="POST" action="controller">
            <input type="hidden" name="command" value="add_product_to_cart"/> <!-- todo add function -->
            <input type="submit" value="<fmt:message key="label.buy"/>"/>
        </form>
        <div class="product-text"><c:out value="${ elem.price }$" />
    </li>
    </c:forEach>
</ul>

</body>
</html>
