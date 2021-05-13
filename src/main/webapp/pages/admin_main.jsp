<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" uri="customtags" %>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Admin Main</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css"/>
</head>
<jsp:include page="header.jsp"/>
<h3>Hi, ${user}!</h3>
<img src="https://media.giphy.com/media/Cmr1OMJ2FN0B2/giphy.gif" alt="Oops" width="150" height="150">
<hr/>

<form name="findUsersByNickname" method="POST" action="controller">
    <input type="hidden" name="command" value="find_users_by_nickname" />
    <input type="text" name="nickname" value=""/>
    <input type="submit" value="Find all users by nickname (or part)" name="submit"/>
</form>

<form name="findUsers" method="POST" action="controller">
    <input type="hidden" name="command" value="find_all_users"/>
    <input type="submit" value="Find all users" name="submit"/>
</form>
<br style="clear:both">
<hr>
<tags:copyright/>
</body>
</html>
