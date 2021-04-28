<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US" scope="session"/>
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
    <form name="logout" method="POST" action="controller">
        <input type="hidden" name="command" value="logout"/>
        <input class="other_button" type="submit" value="<fmt:message key="label.logout_button"/>"/>
    </form>
    <br/>
</div>
</body>
</html>
