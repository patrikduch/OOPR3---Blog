<%--
  User: Patrik Duch
  Date: 18.11.2018
  Time: 18:12
--%>

<%@ page import="com.patrikduch.oopr3.blog.lib.Const" %>
<%@ page import="com.patrikduch.oopr3.blog.helper.RegistrationConstraint" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title><%= Const.PROJECT_NAME %> | Registrace</title>




</head>
<body>

<jsp:include page="/skeleton/header.jsp"></jsp:include>

<div class="container">
    <main role="main">
        <div class="jumbotron">
            <h1>Registrace </h1>
            <form id="myForm" method="POST" action="register">
                <div class="form-group">
                    <label for="usernameInput">Přihlašovací jméno</label>
                    <input type="text" class="form-control" id="usernameInput"
                           name="usernameInput"
                           placeholder="Zadejte vaše přihlašovací jméno"

                    />

                    <p id="usernameExistsErrorMessage"></p>
                    <p id="usernameErrorMessage"></p>
                    <p id="usernameFormatMessage"></p>

                </div>

                <div class="form-group">
                    <label for="emailInput">E-mailová adresa</label>
                    <input type="email" class="form-control" id="emailInput" name="emailInput"
                           aria-describedby="emailHelp"

                           placeholder="Zadejte e-mailovou adresu">


                    <p id="emailErrorMessage"></p>
                    <p id="emailErrorFormatMessage"></p>
                    <p id="emailAlreadyExistsErrorMessage"></p>


                </div>
                <div class="form-group">
                    <label for="firstPasswordInput">Heslo</label>
                    <input type="password"
                           class="form-control"
                           name="firstPasswordInput"
                           id="firstPasswordInput"

                           placeholder="Zadejte vaše heslo">

                    <p id="passwordErrorMessage"></p>
                    <p id="passwordFormatMessage"></p>


                </div>

                <div class="form-group">
                    <label for="secondPasswordInput">Potvrzeni hesla</label>
                    <input type="password" class="form-control"
                           name="secondPassword" id="secondPasswordInput"

                           placeholder="Zadejte heslo znova">

                    <p id="passwordEqualityErrorMessage"></p>
                </div>

                <button id="submitFormBtn" type="submit" class="btn btn-primary">Zaregistrovat se</button>


            </form>

        </div>
    </main>


    <script>

        var usernameLengthConstraint;
        var usernameExistsConstraint;
        var passwordLengthConstraint;
        var emailLengthConstraint;
        var emailFormatConstraint;
        var emailAlreadyExists;

        function ajaxCall(url, type, data) {

            return $.ajax({
                url: 'registerChecker',
                type: 'POST',
                data: data
            });
        }

        function usernameLengthProcess() {

            $("p#usernameErrorMessage")[0].style.display = "none";

            $("input[type=text]#usernameInput").on("input", function () {

                var input = $("input[type=text]#usernameInput").val();

                var tmp = ajaxCall('registerChecker','POST', { username : input});

                tmp.then(function (value) {
                    $("#usernameErrorMessage").css("color","red")
                    $("p#usernameErrorMessage")[0].style.display = "block";
                    $("p#usernameErrorMessage")[0].innerText = value["usernameLengthTest"];

                    $("p#usernameExistsErrorMessage")[0].innerText = value["usernameExistsTest"];
                    $("#usernameExistsErrorMessage").css("color","red")
                    $("p#usernameExistsErrorMessage")[0].style.display = "block";



                    x(value["usernameLengthTest"]).then(function(done) {
                        usernameLengthConstraint = done;
                    });

                    x(value["usernameExistsTest"]).then(function(done) {
                        usernameExistsConstraint = done;
                    });



                }).catch(function (reason) {
                    console.log("Zpracování uživatelského jména selhalo")
                });
            });
        }


        function emailLengthProcess() {


            $("input[type=email]#emailInput").on("input", function () {

                var input = $("input[type=email]#emailInput").val();
                var tmp = ajaxCall('registerChecker','POST', { email : input});

                tmp.then(function (value) {
                    $("#emailErrorMessage").css("color","red");
                    $("#emailErrorFormatMessage").css("color","red");

                    $("p#emailErrorMessage")[0].style.display = "block";
                    $("p#emailErrorMessage")[0].innerText = value["emailLengthTest"];
                    $("p#emailErrorFormatMessage")[0].innerText = value["emailFormatTest"];


                    $("p#emailAlreadyExistsErrorMessage")[0].innerText = value["emailExistsTest"];
                    $("#emailAlreadyExistsErrorMessage").css("color","red")
                    $("p#emailAlreadyExistsErrorMessage")[0].style.display = "block";




                    x(value["emailLengthTest"]).then(function(done) {
                        emailLengthConstraint = done;
                    });

                    x(value["emailFormatTest"]).then(function(done) {
                        emailFormatConstraint = done;
                    });

                    x(value["emailExistsTest"]).then(function(done) {
                        emailAlreadyExists = done;
                    });




                }).catch(function (reason) {
                    console.log("Zpracování emailu uživatele selhalo")
                });
            });



        }


        function passwordLengthProcess() {

            $("p#passwordErrorMessage")[0].style.display = "none";

            $("input[type=password]#firstPasswordInput").on("input", function () {

                var input = $("input[type=password]#firstPasswordInput").val();
                var tmp = ajaxCall('registerChecker','POST', { password : input});


                tmp.then(function (value) {

                    $("p#passwordErrorMessage").css("color","red")
                    $("p#passwordErrorMessage")[0].style.display = "block";
                    $("p#passwordErrorMessage")[0].innerText = value["passwordLengthTest"];

                    x(value["passwordLengthTest"]).then(function(done) {
                       passwordLengthConstraint = done;
                    });

                }).catch(function (reason) {
                    console.log("Zpracování hesla uživatele selhalo")
                });

            });
        }



        function passwordEqualityProcess() {

            $("input[type=password]#secondPasswordInput").on("input", function () {

                var firstPassword = $("input[type=password]#firstPasswordInput").val();
                var secondPassword = $("input[type=password]#secondPasswordInput").val();

                var promise = new Promise(function(resolve, reject) {
                    window.setTimeout(function() {
                        resolve();
                    },10);
                });


                promise.then(function (value) {

                    if(firstPassword == secondPassword) {

                        $("p#passwordEqualityErrorMessage")[0].style.display = "block";
                        $("p#passwordEqualityErrorMessage")[0].innerText = "";
                    } else {

                        $("p#passwordEqualityErrorMessage").css("color","red")
                        $("p#passwordEqualityErrorMessage")[0].innerText = "Hesla se neshoduji";
                    }

                });




            });







            $("input[type=password]#firstPasswordInput").on("input", function () {

                var firstPassword = $("input[type=password]#firstPasswordInput").val();
                var secondPassword = $("input[type=password]#secondPasswordInput").val();

                var promise = new Promise(function(resolve, reject) {
                    window.setTimeout(function() {
                        resolve();
                    },10);
                });


                promise.then(function (value) {

                    if(firstPassword == secondPassword) {

                        $("p#passwordEqualityErrorMessage")[0].style.display = "block";
                        $("p#passwordEqualityErrorMessage")[0].innerText = "";
                    } else {

                        $("p#passwordEqualityErrorMessage").css("color","red")
                        $("p#passwordEqualityErrorMessage")[0].innerText = "Hesla se neshoduji";
                    }

                });




            });


        }


        function x(obj) {
            var promise = new Promise(function(resolve, reject) {
                window.setTimeout(function() {
                    resolve(obj);
                },10);
            });
            return promise;
        }


        $("#submitFormBtn").on("click", function(){

            if(usernameLengthConstraint != '' || passwordLengthConstraint != ''
                || emailLengthConstraint != '' || emailFormatConstraint != '' ||
                usernameExistsConstraint != '' || emailAlreadyExists != '') {

                return false;
            }

            return true;

        });



        $(document ).ready(function() {

            console.log("Dokument nacten");

            usernameLengthProcess();
            passwordLengthProcess();
            emailLengthProcess();
            passwordEqualityProcess();
        });

    </script>



</div>

<jsp:include page="/skeleton/footer.jsp"></jsp:include>


</body>
</html>
