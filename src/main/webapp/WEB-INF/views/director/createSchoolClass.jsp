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
<p>Tworzenie Klasy</p>
<form:form method="post" modelAttribute="SchoolClass">
    <label>Nazwa klasy</label>
    <form:input path="name"/><br>
    <input type="submit" value="Zapisz">
</form:form>
</body>
</html>
