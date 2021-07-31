<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.dbms.housingmanagement.utils.Mapping" %>
<%@ page import="com.dbms.housingmanagement.utils.ModelAttributes" %>
<%@ page import="com.dbms.housingmanagement.utils.Views" %>
<html>
<head>
    <title>Login</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <style>
        :root {
            --input-padding-x: 1.5rem;
            --input-padding-y: .75rem;
        }


        .container {
            margin-top: 5%;
            width: 35%;
            height: 80%;
        }


        body {
            background-image: url("https://images.pexels.com/photos/1571459/pexels-photo-1571459.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
            background-repeat: no-repeat;
            background-size: cover;
        }

        a {
            align-content: center;
        }

        .card {
            background-color: #f9f7cf;
            border: 0;
            width: auto;
            border-radius: 1rem;
            box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);
        }

        .card-title {
            margin-bottom: 2rem;
            font-weight: 300;
            font-size: 1.5rem;
        }

        .card-body {
            padding: 2rem;
        }

        .btn {
            font-size: 80%;
            border-radius: 5rem;
            letter-spacing: .1rem;
            font-weight: bold;
            padding: 1rem;
            transition: all 0.2s;
        }

        .form-label-group {
            position: relative;
            margin-bottom: 1rem;
        }

        .form-label-group input {
            height: auto;
            border-radius: 2rem;
        }

        .form-label-group > input,
        .form-label-group > label {
            padding: var(--input-padding-y) var(--input-padding-x);
        }

        .form-label-group > label {
            position: absolute;
            top: 0;
            left: 0;
            display: block;
            width: 100%;
            margin-bottom: 0;
            line-height: 1.5;
            color: #495057;
            border: 1px solid transparent;
            border-radius: .25rem;
            transition: all .1s ease-in-out;
        }

        .form-label-group input::-webkit-input-placeholder {
            color: transparent;
        }

        .form-label-group input:-ms-input-placeholder {
            color: transparent;
        }

        .form-label-group input::-ms-input-placeholder {
            color: transparent;
        }

        .form-label-group input::-moz-placeholder {
            color: transparent;
        }

        .form-label-group input::placeholder {
            color: transparent;
        }

        .form-label-group input:not(:placeholder-shown) {
            padding-top: calc(var(--input-padding-y) + var(--input-padding-y) * (2 / 3));
            padding-bottom: calc(var(--input-padding-y) / 3);
        }

        .form-label-group input:not(:placeholder-shown) ~ label {
            padding-top: calc(var(--input-padding-y) / 3);
            padding-bottom: calc(var(--input-padding-y) / 3);
            font-size: 12px;
            color: #777;
        }
    </style>

</head>
<body>
<c:if test="${isAnyMessage}">
    <div class="alert alert-success" role="alert">
            ${message}
    </div>
</c:if>
<section>
    <div class="container">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title text-center" style="font-weight: bolder; font-size: x-large; padding: 1rem">SIGN IN</h5>
                <form:form action="<%=Mapping.LOGIN%>" modelAttribute="loginForm" method="post">

                    <div class="form-label-group">
                        <input name="email" type="email" id="inputEmail" class="form-control"
                               placeholder="Email address" required autofocus/>
                        <form:errors path="email"/>
                        <label for="inputEmail">Email address</label>
                    </div>
                    <br>

                    <div class="form-label-group">
                        <input name="password" type="password" id="inputPassword" class="form-control"
                               placeholder="Password" required>
                        <label for="inputPassword">Password</label>
                        <form:errors path="password"/>
                    </div>
                    <span style="margin-left: 1rem; color: red; font-weight: bolder">${loginErrorMessage}</span>
                    <br>
                    <button name="Submit" class="btn-primary" style="margin-left: 15rem; border-radius: 2rem; padding-left: 1.5rem; padding-right: 1.5rem; padding-top: 0.5rem; padding-bottom: 0.5rem" type="submit">Sign In</button>
                </form:form>

                <hr class="my-4">

                <a class="nav-link" style="font-size: small; text-align: center" href="<%=Mapping.REGISTER%>">Not Signed In? Click hear to Register</a>


            </div>
        </div>

    </div>

</section>
</body>
</html>
