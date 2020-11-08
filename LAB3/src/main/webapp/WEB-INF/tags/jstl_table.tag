<%@tag description="JSTL Table tag" pageEncoding="UTF-8"%>
<%@taglib prefix = "tlib" uri = "/WEB-INF/custom_tag_definition.tld"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="entries" required="false" type="java.util.Collection"%>

<table>
    <tbody>
    <tr><th>Word</th><th>Definition</th><th>Language</th></tr>
    <c:forEach items="${entries}" var="entry">
        <tlib:definition word="${entry.word}" def="${entry.definition}"
                         language="${entry.language}"/>
    </c:forEach>
    </tbody>
</table>