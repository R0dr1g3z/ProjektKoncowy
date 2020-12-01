<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: r0dr1g3z
  Date: 01.12.2020
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Edytowanie oceny ze sprawdzianu</h1>
<h2>Uczeń ${Student.fullName}</h2>
<form:form method="post" modelAttribute="Test">
    <form:hidden path="id"/>
    <form:hidden path="appUser"/>
    <label>Nazwa</label>
    <form:input path="name"/><br>
    <label>Ocena</label>
    <form:input path="rating"/><br>
    <label>Data</label>
    <form:input path="date"/><br>
    <label>Przedmiot</label>
    <form:input path="schoolSubject"/><br>
    <input type="submit" value="Dodaj ocenę"/>
</form:form>
</body>
</html>
