<%--
  Created by IntelliJ IDEA.
  User: rmunteanu
  Date: 10/25/2020
  Time: 5:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="rmunteanu.models.Languages"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Input JSP</title>
</head>
<body>
<%
    String language = request.getLocale().getLanguage();
    out.print("<fmt:setLocale value=\""+language+"\"/>");
%>

<fmt:setBundle basename="rmunteanu.Internationalization.Messages"
               var="msg"
               scope="page"/>
<h1>Words Dictionary</h1>
<form action="controller" method="POST">
    <label for="word"><fmt:message key="word"
                                   bundle="${msg}"/>
    </label>
    <input id="word" type="text" name="word"/>

    <label for="definition"><fmt:message key="def"
                                         bundle="${msg}"/></label>
    <input id="definition" type="text" name="definition"/>

    <label for="language"><fmt:message key="lang"
                                       bundle="${msg}"/></label>
    <select id="language" type="text" name="language">

        <%
            Languages languages = (Languages) session.getAttribute("languages");
            Cookie cookies[] = request.getCookies();
            String latestLanguage = null;
            if (cookies != null) {
                for (Cookie cookie : cookies)
                {
                    if (cookie.getName().equals("tema2.lastLanguage"))
                    {
                        latestLanguage = cookie.getValue();
                        break;
                    }
                }
            }
            for (String value : languages.getLanguages())
            {

                out.print("<option value=\"" + value + "\"");
                if (value.equals(latestLanguage))
                {
                    out.print(" selected");
                }
                out.print(">");
                out.print(value);
                out.print("</option>");
            }
        %>
    </select>
    <br>
    <img src="captcha/image.png" style="margin: 10px;">
    <br>
    <label>
        <input type="text" name="captcha">
    </label>
    <br><br>
    <input type="submit"/>
</form>
</body>
</html>