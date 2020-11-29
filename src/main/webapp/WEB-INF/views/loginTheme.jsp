<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Dashboard</title>

    <!-- Custom fonts for this template-->
    <link href="<c:url value="/resources/vendor/fontawesome-free/css/all.min.css" />" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value="/resources/css/sb-admin-2.min.css" />" rel="stylesheet">

</head>

<body style="background-color: dodgerblue">

<div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

        <div class="col-xl-5 col-lg-5 col-md-9">

            <div class="card o-hidden border-0 shadow-lg my-5">
                    <!-- Nested Row within Card Body -->
                        <div class="col-lg-15">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Witamy!</h1>
                                </div>
                                <form method="post">
                                    <div class="form-group">
                                        <input type="text" name="username" class="form-control form-control-appUser" aria-describedby="emailHelp" placeholder="Login">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="password" class="form-control form-control-appUser" placeholder="Hasło">
                                    </div>
                                    <c:if test="${not empty param.error}">
                                        Zły login lub hasło !!!
                                    </c:if>
                                    <div><input type="button" value="Zaloguj się" class="btn btn-primary btn-appUser btn-block"></div>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>

</body>

</html>
