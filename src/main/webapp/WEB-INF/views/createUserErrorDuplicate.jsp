<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: r0dr1g3z
  Date: 25.11.2020
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Tworzenie ${Role}</p>
<form:form method="post" modelAttribute="appUser">
    <label>Login</label>
    <form:input path="username"/><br>
    <label>Podany użytkownik już istnieje</label><br>
    <form:errors path="username"/>
    <label>Haslo</label>
    <form:password path="password"/><br>
    <form:errors path="password"/>
    <label>Imię</label>
    <form:input path="firstName"/><br>
    <form:errors path="firstName"/>
    <label>Nazwisko</label>
    <form:input path="lastName"/><br>
    <form:errors path="lastName"/>
    <input type="submit" value="Zapisz">
</form:form>
</body>
</html>
