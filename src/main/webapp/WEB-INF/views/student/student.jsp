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
<h1>Strona dla studenta</h1>
<h2>Witaj ${FullName}</h2>
<h3>Twoje przedmioty</h3>
<a href="/student/biologyRatings">Biologia</a> <br><br>
<a href="/student/chemistryRatings">Chemia</a><br><br>
<a href="/student/physicsRatings">Fizyka</a><br><br>
<a href="/student/geographyRatings">Geografia</a> <br><br>
<a href="/student/historyRatings">Historia</a> <br><br>
<a href="/student/informaticRatings">Informatyka</a> <br><br>
<a href="/student/englishRatings">Język Angielski</a><br><br>
<a href="/student/polishRatings">Język Polski</a> <br><br>
<a href="/student/mathematicsRatings">Matematyka</a> <br><br>
<a href="/student/musicRatings">Muzyka</a> <br><br>
<a href="/student/artRatings">Plastyka</a> <br><br>
<a href="/student/religionRatings">Religia</a> <br><br>
<a href="/student/physicalRatings">WF</a> <br><br>
<form action="<c:url value="/logout"/>" method="post">
    <input class="fa fa-id-badge" type="submit" value="Wyloguj">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
