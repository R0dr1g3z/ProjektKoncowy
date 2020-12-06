<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: r0dr1g3z
  Date: 26.11.2020
  Time: 08:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Wszyscy uczniowie</h1>
<c:forEach items="${students}" var="student">
    <c:out value="${student.fullName}"/>
    <a href="/director/studentRatings/${student.id}">Oceny ucznia</a>
    <a href="/director/editStudent/${student.id}">Edytuj</a>
    <a href="/director/removeStudent/${student.id}">Usuń</a><br>
</c:forEach>
<br>
<a href="/director/createStudent">Dodaj ucznia</a><br>
<a href="/director/homepage">Strona główna</a>

</body>
</html>
