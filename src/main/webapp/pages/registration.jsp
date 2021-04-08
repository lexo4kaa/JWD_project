<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/registration.css"/>
</head>
<body>
<form name="registrationForm" method="POST" action="controller">
    <input type="hidden" name="command" value="registration" />

    <div class="form_reg_block">
        <div class="form_reg_block_content">
            <p class="form_reg_block_head_text">Registration</p>
            <label>Name</label>
            <input type="text" name="name" value="" required>
            <label>Surname</label>
            <input type="text" name="surname" value="" required>
            <label>Nickname</label>
            <label class="recomend_label" for="nickname_label">Nickname length must be between 3 and 18</label>
            <input type="text" name="nickname" id="nickname_label" value="" required pattern="^[A-Za-z0-9_-]{3,18}$">
            <label>Date of birth</label>
            <input type="date" name="dob" value="" required>
            <label>Email</label>
            <input type="email" name="email" value="" required>
            <label>Phone</label>
            <input type="text" name="phone" value="" required pattern="375(17|25|29|33|44)([1-9]{1})([0-9]{6})$">
            <label>Password</label>
            <label class="recomend_label" for="password_label">Password length must be between 6 and 18</label>
            <input type="password" name="password" id="password_label" value="" required pattern="^[A-Za-z0-9_-]{6,18}$">
            <label>Repeat password</label>
            <input type="password" name="password2" value="" required pattern="^[A-Za-z0-9_-]{6,18}$">
            <br/>
            ${errorLoginPassMessage}
            <br/>
            ${wrongAction}
            <br/>
            ${nullPage}
            <br/>
            <input class="form_reg_button" type="submit" value="Register"/>
            <br/>
        </div>
    </div>

</form>
</body>
</html>