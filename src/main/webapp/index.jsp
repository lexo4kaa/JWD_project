<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<jsp:forward page="/controller">
    <jsp:param name="command" value="first_init"/>
</jsp:forward>
</body>
</html>