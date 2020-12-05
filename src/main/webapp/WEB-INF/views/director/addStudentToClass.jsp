<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: r0dr1g3z
  Date: 27.11.2020
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Dodawanie uczniów do klasy ${schoolClass.name}</h1>
<a href="/director/schoolClassDetails/${schoolClass.id}">Wróć</a><br><br>
<c:forEach items="${students}" var="student">
    <c:out value="${student.fullName}"/>
    <a href="/director/addedStudentToClass/${student.username}/${schoolClass.id}">DODAJ</a><br>
</c:forEach>
</body>
</html>
