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
<a href="/teacher/homepage">Strona główna</a><br><br>
Wychowawca klasy: ${schoolClass.educator}<br><br>
Lista wszystkich uczniów:<br><br>
<c:forEach items="${Students}" var="student">
    <c:out value="${student.fullName}"/>
    <a href="/teacher/studentRatings/${student.id}/${schoolClass.name}">Oceny ucznia</a><br>
</c:forEach>
</body>
</html>
