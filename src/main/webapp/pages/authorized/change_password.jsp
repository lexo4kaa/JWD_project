<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" uri="customtags" %>
<fmt:setLocale value="${current_locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.change_password"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registration.css"/>
</head>
<jsp:include page="../common/header.jsp"/>
<body>

<form name="changePassword" id="form" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="change_password" />
    <div class="form_reg_block">
        <div class="form_reg_block_content">
            <p class="form_reg_block_head_text"><fmt:message key="label.change_password"/></p>
            <label><fmt:message key="label.old_password"/></label>
            <input type="password" name="old_password" value="" required
                   pattern="^[\w]{6,18}$" title="<fmt:message key="label.password_prompt"/>"/>
            <label><fmt:message key="label.new_password"/></label>
            <input type="password" id="password" name="password" value="" required
                   pattern="^[\w]{6,18}$" title="<fmt:message key="label.password_prompt"/>"/>
            <label><fmt:message key="label.new_password_repeat"/></label>
            <input type="password" id="password2" name="password2" value="" required
                   pattern="^[\w]{6,18}$" title="<fmt:message key="label.password_prompt"/>"/>
            <br>
            ${wrongAction}
            ${nullPage}
            ${updateError}
            <br>
            <input type="hidden" id="locale" name="locale" value="${ current_locale }" />
            <input class="form_reg_button" type="submit" value="<fmt:message key="label.save"/>" name="submit"/>
            <br>
        </div>
    </div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validatePassword.js"></script>
</body>
</html>
