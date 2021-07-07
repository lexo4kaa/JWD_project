<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" uri="customtags" %>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.account"/></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/registration.css"/>
</head>
<jsp:include page="header.jsp"/>
<body>

<form name="updateAccount" method="POST" action="controller">
    <input type="hidden" name="command" value="update_account" />

    <div class="form_reg_block">
        <div class="form_reg_block_content">
            <p class="form_reg_block_head_text"><fmt:message key="label.account"/></p>
            <label><fmt:message key="label.name"/></label>
            <input type="text" name="name" value="${profile.name}" required pattern="^[A-Za-z]{1,30}$">
            <label><fmt:message key="label.surname"/></label>
            <input type="text" name="surname" value="${profile.surname}" required pattern="^[A-Za-z]{1,30}$">
            <label><fmt:message key="label.nickname"/></label>
            <label style="font-size: medium" for="nickname_label"><fmt:message key="label.nickname_prompt"/></label>
            <input type="text" name="nickname" id="nickname_label" value="${profile.nickname}" required
                   pattern="^[\w]{3,18}$" title="<fmt:message key="label.nickname_prompt"/>">
            <label><fmt:message key="label.dob"/></label>
            <input type="date" name="dob" value="${profile.dob}" min="1900-01-01" max="2003-09-01" required>
            <label><fmt:message key="label.email"/></label>
            <label style="font-size: medium" for="email_label"><fmt:message key="label.email_prompt"/></label>
            <input type="email" name="email" id="email_label" value="${profile.email}" required
                   pattern="^[\w]{3,30}@gmail.com$" title="<fmt:message key="label.email_prompt"/>">
            <label><fmt:message key="label.phone"/></label>
            <label style="font-size: medium" for="phone_label"><fmt:message key="label.phone_prompt"/></label>
            <input type="text" name="phone" id="phone_label" value="${profile.phone}" required
                   pattern="375(17|25|29|33|44)([1-9]{1})([0-9]{6})$" title="<fmt:message key="label.phone_prompt"/>">
            <br>
            ${wrongAction}
            ${nullPage}
            ${registrationError}
            <br>
            <input class="form_reg_button" type="submit" value="<fmt:message key="label.save"/>"/>
            <br>
        </div>
    </div>
</form>

</body>
</html>
