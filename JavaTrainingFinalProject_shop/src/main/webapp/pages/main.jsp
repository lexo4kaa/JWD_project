<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css"/>
    <!--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> -->
</head>

<h3>Welcome, ${user}!</h3>
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
<hr/>

<br/>
<a href="controller?command=logout">Logout</a>
</body>
</html>
