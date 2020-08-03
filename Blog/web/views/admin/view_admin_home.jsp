<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.patrikduch.oopr3.blog.lib.Const" %>
<%@ page import="com.patrikduch.oopr3.blog.model.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Date" %><%--
  User: Patrik Duch
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title><%= Const.PROJECT_NAME %> | Administrace</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

<jsp:include page="/skeleton/header.jsp"></jsp:include>

<div class="container">

    <main role="main">
        <div class="jumbotron">

            <h1>Administrace</h1>

            <a href="/admin/users" class="btn btn-info" role="button">Uživatelé</a>
            <a href="/admin/categories" class="btn btn-info" role="button">Kategorie</a>

        </div>
    </main>


</div>

<jsp:include page="/skeleton/footer.jsp"></jsp:include>


</body>
</html>
