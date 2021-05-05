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

<form class="radio" name="findProductsByTeam" method="POST" action="controller">
    <h3>Select a team:</h3>
    <p><input type="radio" name="team" value="All" checked>All</p>
    <p><input type="radio" name="team" value="Atletico Madrid">Atletico Madrid</p>
    <p><input type="radio" name="team" value="Barcelona">Barcelona</p>
    <p><input type="radio" name="team" value="Chelsea">Chelsea</p>
    <p><input type="radio" name="team" value="Inter Milan">Inter Milan</p>
    <p><input type="radio" name="team" value="Juventus">Juventus</p>
    <p><input type="radio" name="team" value="Liverpool">Liverpool</p>
    <p><input type="radio" name="team" value="Manchester City">Manchester City</p>
    <p><input type="radio" name="team" value="Manchester United">Manchester United</p>
    <p><input type="radio" name="team" value="Milan">Milan</p>
    <p><input type="radio" name="team" value="PSG">PSG</p>
    <p><input type="radio" name="team" value="Real Madrid">Real Madrid</p>

    <input type="hidden" name="command" value="find_products_by_team" />
    <input type="submit" value="Find" name="submit"/>
</form>

<ul class="products" >
    <c:forEach var="elem" items="${lst}" varStatus="status">
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
        <div style="color: red">
            <c:out value="${ elem.price }$" />
        </div>
        <form style="float: left; margin-left: 42.5%" name="addProduct" method="POST" action="controller">
            <input type="hidden" name="command" value="add_product_to_cart"/>
            <input type="hidden" name="product_id" value="${ elem.productId }">
            <input type="submit" value="+"/>
        </form>
        <form style="float: left" name="deleteProduct" method="POST" action="controller">
            <input type="hidden" name="command" value="delete_product_from_cart"/>
            <input type="hidden" name="product_id" value="${ elem.productId }">
            <input type="submit" value="-"/>
        </form>
    </li>
    </c:forEach>
</ul>

</body>
</html>
