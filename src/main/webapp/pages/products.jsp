<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/products.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<ul class="products" >
    <c:forEach var="elem" items="${lst}" varStatus="status">
    <li class="product-wrapper">
        <a href="" class="product">
            <div class="product-photo">
                <tr>
                    <td><img src="${pageContext.request.contextPath}${elem.path}" alt="Oops"></td>
                </tr>
            </div>
        </a>
        <td class="product-text"><c:out value="${ elem.team }" /></td>
        <td class="product-text"><c:out value="${ elem.type }" /></td>
        <td class="product-text"><c:out value="${ elem.year }" /></td><br>
        <td class="product-text"><c:out value="${ elem.price }$" /></td>
    </li>
    </c:forEach>
</ul>

</body>
</html>
