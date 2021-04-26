<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.login_title"/></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css"/>
</head>
<body>
<form name="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="login"/>

    <div class="form_auth_block">
        <div class="form_auth_block_content">
            <p class="form_auth_block_head_text"><fmt:message key="label.login_title"/></p>
            <label><fmt:message key="label.login_input"/></label>
            <input type="text" name="login" value="">
            <label><fmt:message key="label.password_input"/></label>
            <input type="password" name="password" value="">
            <br/>
            ${errorLoginPassMessage}
            ${wrongAction}
            ${nullPage}
            <br/>
            <input class="form_auth_button" type="submit" value="<fmt:message key="label.login_title"/>"/>
            <br/>
        </div>
    </div>

</form>

<form name="toRegistration" method="POST" action="controller">
    <input type="hidden" name="command" value="to_registration_page"/>

    <input class="other_button" type="submit" value="<fmt:message key="label.no_account"/>"/>
</form>
<br/>
</body>
</html>