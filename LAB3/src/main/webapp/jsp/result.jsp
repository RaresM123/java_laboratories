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
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collection" %>
<%@taglib prefix = "tlib" uri = "/WEB-INF/custom_tag_definition.tld"%>
<%@taglib prefix="tcustom" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Result JSP</title>
    <style>
        table,th,td
        {
            border:1px solid black;
        }
    </style>
</head>
<body>
<h1>Result page</h1>
<%
    Collection<wordsDict> records = (Collection)request.getAttribute("entries");
    for (wordsDict word : records)
    {
        out.print(word.getWord() + ":" + word.getDefinition() + ":" + word.getCurrentDate());
        out.print("<br/>");
    }
%>
<%--    <tcustom:jstl_table entries="${requestScope.entries}"/>--%>
</body>
</html>
