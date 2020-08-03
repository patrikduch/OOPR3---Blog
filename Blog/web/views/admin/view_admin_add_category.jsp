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
    <title><%= Const.PROJECT_NAME %> | Přidání kategorie</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

<jsp:include page="/skeleton/header.jsp"></jsp:include>



<div class="container">

    <main role="main">
        <div class="jumbotron">

            <h1>Přidání kategorie</h1>


            <form method="POST" action="/admin/category/add">
                <div class="form-group">
                    <label for="categoryNameInput">Nazev kategorie</label>
                    <input type="text" class="form-control" id="categoryNameInput"
                           aria-describedby="emailHelp"
                           name="categoryNameInput"
                           placeholder="Zadejte název kategorie">
                </div>
                <div class="form-group">
                    <label for="categoryDescriptionInput">Popis kategorie</label>
                    <input type="text" class="form-control" id="categoryDescriptionInput"
                           name="categoryDescriptionInput"
                           placeholder="Zadejte popis kategorie">
                </div>

                <div class="form-group">
                    <label for="visibilitySelect">Viditelnost kategorie</label>
                    <select class="form-control" id="visibilitySelect" name="visibilitySelectInput">
                        <option>Ano</option>
                        <option>Ne</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
            </form>











        </div>
    </main>


</div>

<jsp:include page="/skeleton/footer.jsp"></jsp:include>


</body>
</html>
