<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${currentLocale}"/>
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
    <form name="findProducts" method="POST" action="controller">
        <input type="hidden" name="command" value="find_products_by_ids"/>
        <input type="submit" value="<fmt:message key="label.cart"/>"/>
    </form>
    <form style="float: right" name="logout" method="POST" action="controller">
        <input type="hidden" name="command" value="logout"/>
        <input class="other_button" type="submit" value="<fmt:message key="label.logout_button"/>"/>
    </form>
    <form style="float: right" name="to_login_page" method="POST" action="controller">
        <input type="hidden" name="command" value="to_login_page"/>
        <input class="other_button" type="submit" value="<fmt:message key="label.login_button"/>"/>
    </form>
    <div style="padding: 0 15px; float: right; color: blue; font-size: large">${user_role}<br>${user}</div>
    <form style="float: right" name="switchLocale" method="POST" action="controller">
        <input type="hidden" name="command" value="switch_locale"/>
        <button type="submit" name="locale" value="ru_RU" style="margin: 0">
            <img src="https://upload.wikimedia.org/wikipedia/en/thumb/f/f3/Flag_of_Russia.svg/1200px-Flag_of_Russia.svg.png"
                 alt="" style="height: 20px; width: 30px">
        </button>
        <button type="submit" name="locale" value="en_US">
            <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Flag_of_the_United_Kingdom.svg/1280px-Flag_of_the_United_Kingdom.svg.png"
                 alt="" style="height: 20px; width: 30px">
        </button>
    </form>
    <br/>
</div>
</body>
</html>
