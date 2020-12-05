<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: r0dr1g3z
  Date: 29.11.2020
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="<c:url value="/resources/css/allStudentRatings.css" />" rel="stylesheet">
</head>
<body>
<h1>Lista wszystkich szkół</h1>
<a href="/owner/homepage">Wróć</a><br><br>
<a href="/owner/createSchool">Dodaj szkołę</a><br><br>
<table>
    <tr>
        <th>Nazwa</th>
        <th>Dyrektor</th>
        <th>Akcje</th>
    </tr>
    <c:forEach items="${schools}" var="school">
        <tr>
            <td><c:out value="${school.name}"/></td>
            <td><c:out value="${school.director.fullName}"/></td>
            <td>
                <a href="/owner/editSchool/">Edytuj</a>
                <a href="/owner/removeSchool/">Usuń</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
