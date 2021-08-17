<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${current_locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.login_title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
</head>
<body>
<jsp:include page="../common/header.jsp"/>

<div class="form_auth_block">
    <div class="form_auth_block_content">
        <br>
        <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="login"/>
            <label><fmt:message key="label.login_input"/></label>
            <input type="text" name="login" value="" pattern="^[\w]{3,18}$" title="<fmt:message key="label.nickname_prompt"/>">
            <label><fmt:message key="label.password_input"/></label>
            <input type="password" name="password" value="" pattern="^[\w]{6,18}$" title="<fmt:message key="label.password_prompt"/>">
            <br>
            <div style="text-align: center; color: red">
                ${errorLoginPassMessage}
                ${wrongAction}
                ${nullPage}
                ${banMessage}
            </div>
            <br>
            <input class="form_login" type="submit" value="<fmt:message key="label.login_button"/>"/>
            <br>
        </form>
        <form name="toRegistration" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="to_registration_page"/>
            <input class="form_no_account" type="submit" value="<fmt:message key="label.no_account"/>"/>
        </form>
    </div>
</div>

</body>
</html>