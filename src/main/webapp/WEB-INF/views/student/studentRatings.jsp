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
<h1>Oceny ucznia ${student.fullName}</h1>
<h2>Oceny z ${schoolSubject}</h2>
<a href="/student/homepage">Wróć</a><br><br>
<table>
    <tr>
        <th>Nazwa</th>
        <th>Ocena</th>
        <th>Data</th>
        <th>Rodzaj</th>
    </tr>
    <c:forEach items="${tests}" var="test">
        <tr>
            <td><c:out value="${test.name}"/></td>
            <td><c:out value="${test.rating}"/></td>
            <td><c:out value="${test.date}"/></td>
            <td><c:out value="Sprawdzian"/></td>
        </tr>
    </c:forEach>
    <c:forEach items="${shortTests}" var="shortTest">
        <tr>
            <td><c:out value="${shortTest.name}"/></td>
            <td><c:out value="${shortTest.rating}"/></td>
            <td><c:out value="${shortTest.date}"/></td>
            <td><c:out value="Kartkówka"/></td>
        </tr>
    </c:forEach>
    <c:forEach items="${homeworks}" var="homework">
        <tr>
            <td><c:out value="${homework.name}"/></td>
            <td><c:out value="${homework.rating}"/></td>
            <td><c:out value="${homework.date}"/></td>
            <td><c:out value="Praca domowa"/></td>
        </tr>
    </c:forEach>
    <c:forEach items="${others}" var="other">
        <tr>
            <td><c:out value="${other.name}"/></td>
            <td><c:out value="${other.rating}"/></td>
            <td><c:out value="${other.date}"/></td>
            <td><c:out value="Inne"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
