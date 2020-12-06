<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: r0dr1g3z
  Date: 27.11.2020
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Klasa ${schoolClass.name}</h1>
<a href="/director/homepage">Strona główna</a><br><br>
<a href="/director/addStudentToClass/${schoolClass.id}">Dodaj ucznia do klasy</a><br><br>
<a href="/director/addTeacherToClass/${schoolClass.id}">Dodaj nauczyciela do klasy</a><br><br>
Wychowawca klasy: ${schoolClass.educator}<br><br>
Lista wszystkich nauczycieli:<br><br>
<c:forEach items="${Teachers}" var="teacher">
    <c:out value="${teacher.fullName}"/>
    <a href="/director/removeTeacherFromClass/${teacher.id}/${schoolClass.id}">Usuń z klasy</a><br>
</c:forEach><br>
Lista wszystkich uczniów:<br><br>
<c:forEach items="${Students}" var="student">
    <c:out value="${student.fullName}"/>
    <a href="/director/removeStudentFromClass/${student.id}/${schoolClass.id}">Usuń z klasy</a><br>
</c:forEach>
</body>
</html>
