<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.registration"/></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/registration.css"/>
</head>
<body>
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
            <input type="date" name="dob" value="" required>
            <label><fmt:message key="label.email"/></label>
            <input type="email" name="email" value="" required
                   pattern="^[\w]{3,30}@(gmail|yandex|mail)\.(com|ru|by)$" title="***(3-30)@(gmail|yandex|mail)\.(com|ru|by)">
            <label><fmt:message key="label.phone"/></label>
            <input type="text" name="phone" value="" required
                   pattern="375(17|25|29|33|44)([1-9]{1})([0-9]{6})$" title="375(17|25|29|33|44)*******">
            <label><fmt:message key="label.password"/></label>
            <label class="pass_label" for="password"><fmt:message key="label.password_prompt"/></label>
            <input type="password" name="password" id="password" value="" required
                   pattern="^[\w]{6,18}$" title="Letters, numbers or symbol '_' (length between 6 and 18)">
            <label><fmt:message key="label.password_repeat"/></label>
            <input type="password" name="password2" id="password2" value="" required pattern="^[\w]{6,18}$">
            <br/>
            ${errorLoginPassMessage}
            <br/>
            ${wrongAction}
            <br/>
            ${nullPage}
            <br/>
            <input class="form_reg_button" type="submit" value="<fmt:message key="label.registration_button"/>"/>
            <br/>
        </div>
    </div>

    <script type="text/javascript">
        window.onload = function () {
            document.getElementById("password").onchange = validatePassword;
            document.getElementById("password2").onchange = validatePassword;
        }
        function validatePassword(){
            var pass2=document.getElementById("password2").value;
            var pass1=document.getElementById("password").value;
            if(pass1!=pass2)
                document.getElementById("password2").setCustomValidity(<fmt:message key="label.passwords_don't_match"/>);
            else
                document.getElementById("password2").setCustomValidity('');
        }
    </script>

</form>
</body>
</html>