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
    <title><%= Const.PROJECT_NAME %> | Kategorie</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

<jsp:include page="/skeleton/header.jsp"></jsp:include>

<div class="container">

    <main role="main">
        <div class="jumbotron">

            <h1>Kategorie</h1>

            <a href="/admin/category/add" class="btn btn-outline-dark" role="button">+ Přidat kategorii</a>



            <br>
            <br>

            <c:if test="${categoryList != null}">


                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Název kategorie</th>
                        <th scope="col">Popis kategorie</th>
                        <th scope="col">Viditelnost</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="category" items="${categoryList}">
                        <tr>
                            <th scope="row"><c:out value="${category.id}"></c:out></th>
                            <td><c:out value="${category.name}"></c:out></td>
                            <td><c:out value="${category.description}"></c:out></td>

                            <c:if test="${category.visible}">
                                <td>Zapnuta</td>
                            </c:if>

                            <c:if test="${not category.visible}">
                                <td>Vypnuta</td>
                            </c:if>

                            <td><a href="/admin/category/remove?id=${category.id}" class="btn btn-outline-danger" role="button">Smazat</a></td>

                        </tr>
                    </c:forEach>




                    </tbody>
                </table>





            </c:if>










        </div>
    </main>


</div>

<jsp:include page="/skeleton/footer.jsp"></jsp:include>


</body>
</html>
