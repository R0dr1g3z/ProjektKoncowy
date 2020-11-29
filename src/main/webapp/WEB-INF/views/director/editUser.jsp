<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: r0dr1g3z
  Date: 26.11.2020
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="AppUser">
    <form:hidden path="id"/>
    <label>Login:</label>
    <form:input path="username"/><br>
    <label>Hasło:</label>
    <form:password path="password"/><br>
    <label>Imię:</label>
    <form:input path="firstName"/><br>
    <label>Nazwisko:</label>
    <form:input path="lastName"/><br>
    <input type="submit" value="Zapisz zmiany">
</form:form>
</body>
</html>
