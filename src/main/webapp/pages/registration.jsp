<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.registration"/></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/registration.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>

<form name="registrationForm" method="POST" action="controller">
    <input type="hidden" name="command" value="registration" />

    <div class="form_reg_block">
        <div class="form_reg_block_content">
            <p class="form_reg_block_head_text"><fmt:message key="label.registration"/></p>
            <label><fmt:message key="label.name"/></label>
            <input type="text" name="name" value="" required pattern="^[A-Za-z]{1,30}$">
            <label><fmt:message key="label.surname"/></label>
            <input type="text" name="surname" value="" required pattern="^[A-Za-z]{1,30}$">
            <label><fmt:message key="label.nickname"/></label>
            <label class="nick_label" for="nickname_label"><fmt:message key="label.nickname_prompt"/></label>
            <input type="text" name="nickname" id="nickname_label" value="" required
                   pattern="^[\w]{3,18}$" title="Letters, numbers or symbol '_' (length between 3 and 18)">
            <label><fmt:message key="label.dob"/></label>
            <input type="date" name="dob" value="" min="1900-01-01" max="2003-09-01" required>
            <label><fmt:message key="label.email"/></label>
            <input type="email" name="email" value="" required
                   pattern="^[\w]{3,30}@gmail.com$" title="Letters length between 3 and 30 and then '@gmail.com'">
            <label><fmt:message key="label.phone"/></label>
            <input type="text" name="phone" value="" required
                   pattern="375(17|25|29|33|44)([1-9]{1})([0-9]{6})$" title="375(17|25|29|33|44)*******">
            <label><fmt:message key="label.password"/></label>
            <label class="pass_label" for="password"><fmt:message key="label.password_prompt"/></label>
            <input type="password" name="password" id="password" value="" required
                   pattern="^[\w]{6,18}$" title="Letters, numbers or symbol '_' (length between 6 and 18)">
            <label><fmt:message key="label.password_repeat"/></label>
            <input type="password" name="password2" id="password2" value="" required pattern="^[\w]{6,18}$">
            <br>
            ${errorLoginPassMessage}
            ${wrongAction}
            ${nullPage}
            ${registrationError}
            <br>
            <input class="form_reg_button" type="submit" value="<fmt:message key="label.registration_button"/>"/>
            <br>
        </div>
    </div>
</form>

<script type="text/javascript">

</script>

</body>
</html>