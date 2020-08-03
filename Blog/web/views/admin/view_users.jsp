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
    <title><%= Const.PROJECT_NAME %> | Uživatel</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

<jsp:include page="/skeleton/header.jsp"></jsp:include>

<div class="container">

    <main role="main">
        <div class="jumbotron">

            <h1>Seznam uživatelů </h1>

            <c:out value="${date}"></c:out>


            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Uživatelské jméno</th>
                    <th scope="col">Email</th>
                    <th scope="col">Datum registrace</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="user" items="${userList}">
                    <tr>
                        <th scope="row"><c:out value="${user.id}"></c:out></th>
                        <td><c:out value="${user.username}"></c:out></td>
                        <td><c:out value="${user.email}"></c:out></td>
                        <td><c:out value="${user.registrationDate}"></c:out></td>
                    </tr>
                </c:forEach>




                </tbody>
            </table>









        </div>
    </main>


</div>

<jsp:include page="/skeleton/footer.jsp"></jsp:include>


</body>
</html>
