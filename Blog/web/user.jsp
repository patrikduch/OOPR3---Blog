<%@ page import="com.patrikduch.oopr3.blog.lib.Const" %>
<%@ page import="com.patrikduch.oopr3.blog.model.User" %><%--
  User: Patrik Duch
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User actualUser = (User)session.getAttribute("actualUser");

    if(actualUser == null) { // Neni zde co pohledavat
        actualUser = new User();
        response.sendRedirect("/");
    }



%>


<html>
<head>
    <title><%= Const.PROJECT_NAME %> | Uživatel</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

<jsp:include page="/skeleton/header.jsp"></jsp:include>

<div class="container">

    <main role="main">
        <div class="jumbotron">

            <h1>Profil uživatele </h1>

            Uživatelské jméno: <%= actualUser.getUsername() %> <br>

            E-mailová adresa: <%= actualUser.getEmail() %> <br>

            Datum registrace: <%= actualUser.getRegistrationDate() %> <br>

            <a href="LogoutServlet"> Odhlásit se </a>

        </div>
    </main>


</div>

<jsp:include page="/skeleton/footer.jsp"></jsp:include>


</body>
</html>
