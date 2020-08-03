<%--
  User: Patrik Duch
  Date: 18.11.2018
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.patrikduch.oopr3.blog.lib.Const" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%

    Calendar cal = Calendar.getInstance();
    SimpleDateFormat dateOnly = new SimpleDateFormat("MM/dd/yyyy");
    pageContext.setAttribute("time", dateOnly.format(cal.getTime()));

%>
<html>
<head>
    <title><%= Const.PROJECT_NAME %> | Domů</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

<jsp:include page="/skeleton/header.jsp"></jsp:include>

<div class="container">

    <main role="main">
        <div class="jumbotron">
            <div class="col-sm-8 mx-auto">
                <h1>OOPR3 - Projekt</h1>

                <p>Autor: Patrik Duch</p>
                <p>Osobní číslo: R17478</p>


            </div>
        </div>
    </main>


</div>


<div class="container">
    <div class="row">
        <div class="col l6 s12">
            <h5 class="white-text">Nejnovější uživatelé</h5>

            <c:choose>
                <c:when test="${userList == null}">
                    Žádný uživatel nebyl nalezen
                </c:when>
                <c:otherwise>
                    <c:forEach var="user" items="${userList}">
                        ${user.id}, ${user.username} <br/>
                    </c:forEach>
                </c:otherwise>
            </c:choose>



            <%--
            <%

              for (User user : userList) { %>
              <p class="grey-text text-lighten-4"><%= user.getUsername() %></p>
            <%
              }%>

            --%>


        </div>

    </div>
</div>



</body>
</html>
