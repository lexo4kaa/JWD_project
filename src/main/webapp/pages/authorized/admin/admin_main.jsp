<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" uri="customtags" %>
<fmt:setLocale value="${current_locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.admin_main"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<jsp:include page="../../common/header.jsp"/>
<h2><fmt:message key="label.hi"/>, ${nickname}!</h2>
<img src="https://c.tenor.com/8WyEbWrvlnkAAAAC/lionel-messi-thumbs-up.gif" alt="Oops" width="200" height="150">
<hr/>

<form name="findUsersByNickname" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="find_users_by_nickname" />
    <input type="text" name="nickname" value="" pattern="^[\w]{3,18}$" title="<fmt:message key="label.nickname_prompt"/>"/>
    <input type="submit" value="<fmt:message key="label.find_users_by_part_of_nickname"/>" name="submit"/>
</form>

<form name="usersInfoPage" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="find_all_users"/>
    <input type="submit" value="<fmt:message key="label.users_info"/>" name="submit"/>
</form>

<form name="registrationPage" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="to_registration_page"/>
    <input type="submit" value="<fmt:message key="label.register_admin"/>"/>
</form>

<br style="clear:both">
<hr>
<tags:copyright/>
</body>
</html>
