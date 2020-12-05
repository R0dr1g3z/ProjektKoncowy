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
<h1>Wszystkie klasy</h1>
<c:forEach items="${schoolClasses}" var="schoolClass">
    <c:out value="${schoolClass.name}"/>
    <a href="/director/schoolClassDetails/${schoolClass.id}">Szczegóły klasy</a>
    <a href="/director/editSchoolClass/${schoolClass.id}">Edytuj</a>
    <a href="/director/removeSchoolClass/${schoolClass.id}">Usuń</a><br>
</c:forEach>
<br>
<a href="/director/createSchoolClass">Dodaj klasę</a><br>
<a href="/director/homepage">Strona główna</a>

</body>
</html>
