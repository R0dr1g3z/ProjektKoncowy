<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: r0dr1g3z
  Date: 03.12.2020
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Wszyscy dyrektorzy</h1>
<c:forEach items="${directors}" var="director">
    <c:out value="${director.fullName}"/>
    <a href="/owner/editDirector/${director.username}">Edytuj</a>
    <a href="/owner/removeDirector/${director.id}">Usuń</a><br>
</c:forEach>
<br>
<a href="/owner/createDirector">Dodaj dyrektora</a><br>
<a href="/owner/homepage">Strona główna</a>

</body>
</html>
