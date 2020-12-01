<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: r0dr1g3z
  Date: 25.11.2020
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Strona dla nauczyciela</h1>
<h2>Witaj ${FullName}</h2>
Twoje klasy<br><br>
<c:forEach items="${SchoolClass}" var="schoolClass">
    <a href="/teacher/schoolClassDetails/${schoolClass.name}">${schoolClass.name}</a><br>
</c:forEach><br><br>
<form action="<c:url value="/logout"/>" method="post">
    <input class="fa fa-id-badge" type="submit" value="Wyloguj">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
