<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Online football store</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css"/>
</head>
<body>
<div class="header">
    <form name="toProducts" method="POST" action="controller">
        <input type="hidden" name="command" value="find_all_products"/>
        <input class="other_button" type="submit" value="<fmt:message key="label.products"/>"/>
    </form>
    <form name="findProducts" method="POST" action="controller">
        <input type="hidden" name="command" value="find_products_by_ids"/>
        <input type="submit" value="<fmt:message key="label.cart"/>"/>
    </form>
    <form style="float: right" name="logout" method="POST" action="controller">
        <input type="hidden" name="command" value="logout"/>
        <input class="other_button" type="submit" value="<fmt:message key="label.logout_button"/>"/>
    </form>
    <div style="padding: 0 15px; float: right; color: blue; font-size: large">${user_role}<br>${user}</div>
    <br/>
</div>
</body>
</html>
