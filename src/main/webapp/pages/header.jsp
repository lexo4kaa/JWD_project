<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css"/>
</head>
<body>
<div class="header">
    <c:if test="${user_role == 'administrator'}">
        <form name="adminMainPage" method="POST" action="controller">
            <input type="hidden" name="command" value="to_admin_main_page"/>
            <input type="submit" value="<fmt:message key="label.admin_main"/>"/>
        </form>
    </c:if>
    <form name="productsPage" method="POST" action="controller">
        <input type="hidden" name="command" value="to_products_page"/>
        <input type="submit" value="<fmt:message key="label.products"/>"/>
    </form>
    <form name="cartPage" method="POST" action="controller">
        <input type="hidden" name="command" value="find_products_by_ids"/>
        <input type="submit" value="<fmt:message key="label.cart"/>"/>
    </form>
    <div style="border-radius: 10px; margin-left: -15px; margin-top: -10px; float: left;
                color: white; background: blue; text-align: center; width: 20px; height: 20px">${cart_size}</div>
    <c:if test="${user_role != 'guest'}">
        <form style="float: right" name="logout" method="POST" action="controller">
            <input type="hidden" name="command" value="logout"/>
            <input type="submit" value="<fmt:message key="label.logout_button"/>"/>
        </form>
        <form style="float: right" name="account" method="POST" action="controller">
            <input type="hidden" name="command" value="to_account_page"/>
            <input type="submit" value="<fmt:message key="label.account"/>"/>
        </form>
        <c:if test="${user_role == 'client'}">
            <div style="margin-top:-5px; padding: 0 15px; float: right; color: blue">
                <fmt:message key="label.user"/><br>${nickname}
            </div>
        </c:if>
        <c:if test="${user_role == 'administrator'}">
            <div style="margin-top:-5px; padding: 0 15px; float: right; color: blueviolet">
                <fmt:message key="label.admin"/><br>${nickname}
            </div>
        </c:if>
    </c:if>
    <c:if test="${user_role == 'guest'}">
        <form style="float: right" name="to_login_page" method="POST" action="controller">
            <input type="hidden" name="command" value="to_login_page"/>
            <input type="submit" value="<fmt:message key="label.login_button"/>"/>
        </form>
        <div style="margin-top:-5px; padding: 0 15px; float: right; color: darkred">
            <fmt:message key="label.guest"/></div>
    </c:if>
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
