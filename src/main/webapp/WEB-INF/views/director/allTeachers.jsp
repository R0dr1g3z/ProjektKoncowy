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
<h1>Wszyscy nauczyciele</h1>
<c:forEach items="${teachers}" var="teacher">
    <c:out value="${teacher.fullName}"/>
    <a href="/director/editTeacher/${teacher.username}">Edytuj</a>
    <a href="/director/removeTeacher/${teacher.id}">Usuń</a><br>
</c:forEach>
<br>
<a href="/director/createTeacher">Dodaj nauczyciela</a><br>
<a href="/director/homepage">Strona główna</a>

</body>
</html>
