<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
<!-- todo mb translate it -->
Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name or type: ${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.errorData.throwable}
</body>
</html>