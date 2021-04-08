<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css"/>
</head>
<body>
<form name="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="login" />

    <div class="form_auth_block">
        <div class="form_auth_block_content">
            <p class="form_auth_block_head_text">Authorization</p>
            <label>Enter your login</label>
            <input type="text" name="login" value="">
            <label>Enter your password</label>
            <input type="password" name="password" value="">
            <br/>
            ${errorLoginPassMessage}
            ${wrongAction}
            ${nullPage}
            <br/>
            <input class="form_auth_button" type="submit" value="Log in"/>
            <br/>
            <a href="pages/registration.jsp">I haven't an account</a>

        </div>
    </div>

</form>

<br/>

</body>
</html>