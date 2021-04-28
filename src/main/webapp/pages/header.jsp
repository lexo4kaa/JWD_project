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

    <form name="toLogin" method="POST" action="controller">
        <input type="hidden" name="command" value="to_login_page"/>
        <input class="other_button" type="submit" value="<fmt:message key="label.login_button"/>"/>
    </form>
    <form name="toRegistration" method="POST" action="controller">
        <input type="hidden" name="command" value="to_registration_page"/>
        <input class="other_button" type="submit" value="<fmt:message key="label.registration"/>"/>
    </form>

</body>
</html>
