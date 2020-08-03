<%@ page import="com.patrikduch.oopr3.blog.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script
        src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">




<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    User actualUser = (User)session.getAttribute("actualUser");


    if(actualUser == null) {
        // Nutnost pro JSTL
        pageContext.setAttribute("isUserLogged", false);

    } else {

        if(session.getAttribute("auth") == null ) {

            if(session.getAttribute("isAdmin") != null) {
                pageContext.setAttribute("nonAdmin", session.getAttribute("isAdmin"));
            } else {
                pageContext.setAttribute("nonAdmin", false);
            }
        }

        // Nutnost pro JSTL
        pageContext.setAttribute("isUserLogged", true);
    }
%>


<nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
    <a class="navbar-brand" href="/">Duchův blog</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample09" aria-controls="navbarsExample09" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExample09">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Domů <span class="sr-only">(current)</span></a>
            </li>

            <c:if test="${not isUserLogged}">

                <li class="nav-item">
                    <a class="nav-link" href="register">Registrace</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="login">Prihlášení</a>
                </li>

            </c:if>

            <c:if test="${isUserLogged}">

                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">Profil</a>
                </li>

                <c:if test="${nonAdmin}">
                    <a class="nav-link" href="/admin">Administrace</a>
                </c:if>



                <li class="nav-item">
                    <a class="nav-link" href="/user/logout">Odhlásit se</a>
                </li>

            </c:if>

        </ul>

    </div>
</nav>