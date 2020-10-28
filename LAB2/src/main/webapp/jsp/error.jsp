<%--
  Created by IntelliJ IDEA.
  User: rmunteanu
  Date: 10/25/2020
  Time: 6:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error JSP</title>
</head>
<body>
<h1>Error page</h1>
<%
    out.print(request.getAttribute("error"));
%>
</body>
</html>