<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: r0dr1g3z
  Date: 25.11.2020
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <div><label> Login : <input type="text" name="username"/> </label></div>
    <div><label> Hasło: <input type="password" name="password"/> </label></div>
    <c:if test="${not empty param.error}">
        Zły login lub hasło !!!
    </c:if>
    <div><input type="submit" value="Zaloguj się"/></div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
