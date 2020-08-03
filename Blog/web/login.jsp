<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.patrikduch.oopr3.blog.lib.Const" %><%--
  Created by IntelliJ IDEA.
  User: Patrik Duch
  Date: 18.11.2018
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%= Const.PROJECT_NAME %> | Přihlášení</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

<jsp:include page="errorRegistrationReset.jsp"></jsp:include>
<jsp:include page="/skeleton/header.jsp"></jsp:include>



<%
    boolean incorrectLogin =  false;
    if(session.getAttribute("errorLogin") != null) {
        incorrectLogin = (boolean)session.getAttribute("errorLogin");
    }

    pageContext.setAttribute("errorLogin", incorrectLogin);

%>

<div class="container">

        <main role="main">
            <div class="jumbotron">

                <h1>Přihlášení </h1>
                <form method="POST" action="LoginServlet">

                    <div class="form-group">
                        <label for="exampleInputEmail1">E-mailová adresa</label>
                        <input type="email" name="email"
                               class="form-control" id="exampleInputEmail1"
                               aria-describedby="emailHelp" placeholder="Zadejte vaši e-mailovou adresu">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Heslo</label>
                        <input type="password" name="password" class="form-control"
                               id="exampleInputPassword1"
                               placeholder="Password">
                    </div>

                    <c:if test="${errorLogin}">
                        <p style="color:red">Přihlášení se nezdařilo. </p>
                    </c:if>

                    <button type="submit" class="btn btn-primary">Přihlásit se</button>
                </form>
            </div>
        </main>


    </div>

<jsp:include page="/skeleton/footer.jsp"></jsp:include>


</body>
</html>
