<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${current_locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
</head>
<body>
<div class="header">

    <form name="allProducts" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="find_all_products"/>
        <input style="float:left;font-size:xx-large;color:blue;font-family:'Bradley Hand ITC';
                      margin-top: -10px;margin-right: 10px" type="submit" value="<fmt:message key="label.football_shop"/>"/>
    </form> <!-- todo куда переводить при нажатии -->

    <c:if test="${user_role == 'administrator'}">
        <form name="adminMainPage" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="to_admin_main_page"/>
            <input type="submit" value="<fmt:message key="label.admin_main"/>"/>
        </form>
    </c:if>
    <form name="allProducts" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="find_all_products"/>
        <input type="submit" value="<fmt:message key="label.all_products"/>"/>
    </form>

    <form name="jersey" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="find_products_by_type"/>
        <input type="hidden" name="type" value="jersey">
        <input type="submit" value="<fmt:message key="label.jersey"/>"/>
    </form>
    <form name="uniform" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="find_products_by_type"/>
        <input type="hidden" name="type" value="uniform">
        <input type="submit" value="<fmt:message key="label.uniform"/>"/>
    </form>
    <form name="cap" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="find_products_by_type"/>
        <input type="hidden" name="type" value="cap">
        <input type="submit" value="<fmt:message key="label.cap"/>"/>
    </form>
    <form name="scarf" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="find_products_by_type"/>
        <input type="hidden" name="type" value="scarf">
        <input type="submit" value="<fmt:message key="label.scarf"/>"/>
    </form>

    <c:if test="${user_role != 'guest'}">
        <form style="float: right" name="logout" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="logout"/>
            <input type="submit" value="<fmt:message key="label.logout_button"/>"/>
        </form>
        <form style="float: right" name="account" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="find_user_by_nickname"/>
            <input type="submit" value="<fmt:message key="label.account"/>"/>
        </form>
        <c:if test="${user_role == 'client'}">
            <div style="margin-top: -10px; padding: 0 15px; color: blue; font-size: medium; float: right">
                <fmt:message key="label.user"/><br>${nickname}
            </div>
        </c:if>
        <c:if test="${user_role == 'administrator'}">
            <div style="margin-top: -10px; padding: 0 15px; color: blueviolet; font-size: medium; float: right">
                <fmt:message key="label.admin"/><br>${nickname}
            </div>
        </c:if>
    </c:if>

    <c:if test="${user_role == 'guest'}">
        <form style="float: right" name="to_login_page" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="to_login_page"/>
            <input type="submit" value="<fmt:message key="label.login_button"/>"/>
        </form>
        <div style="padding: 0 15px; color: darkgreen; font-size: medium; float: right">
            <fmt:message key="label.guest"/>
        </div>
    </c:if>

    <form name="switchLocale" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="switch_locale"/>
        <button type="submit" name="locale" value="ru_RU" style="border:none;cursor:pointer;background:whitesmoke">
            <img src="https://upload.wikimedia.org/wikipedia/en/thumb/f/f3/Flag_of_Russia.svg/1200px-Flag_of_Russia.svg.png"
                 alt="" style="height: 20px; width: 30px">
        </button>
        <button type="submit" name="locale" value="en_US" style="border:none;cursor:pointer;background:whitesmoke">
            <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Flag_of_the_United_Kingdom.svg/1280px-Flag_of_the_United_Kingdom.svg.png"
                 alt="" style="height: 20px; width: 30px">
        </button>
    </form>

    <c:if test="${cart_size > 99}">
        <c:set var="width" value="30"></c:set>
    </c:if>
    <c:if test="${cart_size <= 99}">
        <c:set var="width" value="20"></c:set>
    </c:if>

    <div style="border-radius:10px;margin-right:30px;margin-top:-10px;float:right;color:white;background:dodgerblue;
                text-align: center; width: ${width}px; height: 20px">${cart_size}
    </div>

    <form style="float: right; margin-right: -10px"
          name="cartPage" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="find_products_by_ids"/>
        <input style="background-image:url('${pageContext.request.contextPath}/images/cart.png');
                    background-size:40px 40px;width:40px;height:40px;margin-top:-10px" type="submit" value=""/>
    </form>

    <form style="float: right; margin-top: -5px"
          name="favouritesPage" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="find_favourite_products"/>
        <input style="color: red; font-size: x-large" type="submit" value="&#10084;"/>
    </form>
    <br>
</div>
</body>
</html>
