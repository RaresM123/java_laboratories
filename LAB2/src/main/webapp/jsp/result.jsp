<%--
  Created by IntelliJ IDEA.
  User: rmunteanu
  Date: 10/25/2020
  Time: 6:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="rmunteanu.models.wordsDict"%>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Result JSP</title>
</head>
<body>
<h1>Result page</h1>
<%
    Map<String, wordsDict> records = (HashMap<String, wordsDict>)request.getAttribute("entries");
    for (String word : records.keySet())
    {
        out.print(word + ":" + records.get(word).getDefinition());
        out.print("<br/>");
    }
%>
</body>
</html>
