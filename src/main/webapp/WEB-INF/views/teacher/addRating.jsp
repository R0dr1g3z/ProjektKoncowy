<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: r0dr1g3z
  Date: 01.12.2020
  Time: 09:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/teacher/addTest/${Id}/${Name}">Dodaj ocenę ze sprawdzianu</a><br><br>
<a href="/teacher/addShortTest/${Id}/${Name}">Dodaj ocenę z kartkówki</a><br><br>
<a href="/teacher/addHomework/${Id}/${Name}">Dodaj ocenę z pracy domowej</a><br><br>
<a href="/teacher/addOther/${Id}/${Name}">Dodaj ocenę z inne</a><br><br>
</body>
</html>
