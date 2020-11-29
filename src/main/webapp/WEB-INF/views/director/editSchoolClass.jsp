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
<form:form method="post" modelAttribute="SchoolClass">
    <form:hidden path="id"/>
    <label>Nazwa</label>
    <form:input path="name"/><br>
    <label>Wychowawca</label>
    <form:select path="educator">
        <form:option value="---">Wybierz nauczyciela</form:option>
        <form:options items="${teachers}" itemLabel="fullName" itemValue="fullName"></form:options>
    </form:select><br>
    <input type="submit" value="Zapisz zmiany">
</form:form>
</body>
</html>
