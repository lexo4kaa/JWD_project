<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css"/>
</head>

<jsp:include page="header.jsp"/>

<h3>Welcome, dear ${user}!</h3>
<img src="https://media.giphy.com/media/Cmr1OMJ2FN0B2/giphy.gif" alt="Oops" width="150" height="150">
<hr/>

<form name="findProducts" method="POST" action="controller">
    <input type="hidden" name="command" value="find_all_products" />
    <input type="submit" value="Find all products" name="submit"/>
</form>

<br/>
<a href="controller?command=logout">Logout</a> <!-- todo сделать функцией -->
</body>
</html>
