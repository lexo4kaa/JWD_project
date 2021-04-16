<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/products.css"/>
</head>
<body>
<ul class="products">
    <c:forEach var="elem" items="${lst}" varStatus="status">
    <li class="product-wrapper">
        <a href="" class="product">
            <div class="product-photo">
                <tr>
                    <td><c:out value="${ elem.type }" /></td>
                    <td><c:out value="${ elem.team }" /></td>
                    <td><c:out value="${ elem.year }" /></td>
                    <td><c:out value="${ elem.price }$" /></td>
                    <td><img src="${pageContext.request.contextPath}${elem.path}" alt="альтернативный текст"></td>
                </tr>
            </div>
        </a>
    </li>
    </c:forEach>
</ul>

<br/>
<a href="controller?command=logout">Logout</a>

</body>
</html>
