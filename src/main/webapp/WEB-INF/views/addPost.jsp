<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.dbms.housingmanagement.utils.Mapping" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

</head>

<style>
    :root {
        --input-padding-x: 1.5rem;
        --input-padding-y: .75rem;
    }

    .container {
        width: 60%;
        height: 80%;
        margin-top: 3%;
    }

    body {
        background-image: url("https://images.pexels.com/photos/1571459/pexels-photo-1571459.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        background-repeat: no-repeat;
        background-size: cover;
    }

    .card {
        background-color: #f9f7cf;
        border: 0;
        border-radius: 1rem;
        box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);
        overflow: hidden;
    }

    .card-title {
        margin-top: 2rem;
        /*font-weight: 300;*/
        font-size: 1.5rem;
        font-weight: bold;
    }


    .card-body {
        padding: 2rem;
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

<body>

<section id="Login">

    <div class="container">

        <div class="card  ">
            <h5 class="card-title text-center">New Post</h5>


            <form:form action="<%=Mapping.ADD_NEW_POST%>" modelAttribute="newPostForm" method="POST"
                       enctype="multipart/form-data">
                <div class="card-body">
                    <div class="row">
                        <div class="col">

                            <div class="form-label-group">
                                <input name="propertyName" type="text" id="propertyName" class="form-control"
                                       placeholder="Email address" required autofocus/>
                                <form:errors path="propertyName"/>
                                <label for="propertyName">Property Name</label>
                            </div>

                            <div class="form-label-group">
                                <input name="address" type="text" id="address" class="form-control"
                                       placeholder="Address" required autofocus/>
                                <form:errors path="address"/>
                                <label for="address">Address</label>
                            </div>

                            <div class="form-label-group">
                                <input name="price" type="text" id="price" class="form-control" placeholder="Price"
                                       required autofocus/>
                                <form:errors path="price"/>
                                <label for="price">Price</label>
                            </div>

                            <div class="form-label-group">
                                <textarea id="details" name="details" class="form-control" placeholder="Details"
                                          style="border-radius: 1rem"></textarea>
                                <form:errors path="details"/>
                            </div>

                        </div>

                        <div class="col">

                            <div class="form-label-group">
                                <input name="ownerName" type="text" id="ownerName" class="form-control"
                                       placeholder="Owner Name" required autofocus/>
                                <form:errors path="ownerName"/>
                                <label for="ownerName">Owner Name</label>
                            </div>


                            <div class="form-label-group">
                                <input name="phoneNumber" type="tel" id="phoneNumber" class="form-control"
                                       placeholder="Phone Number" required autofocus/>
                                <form:errors path="phoneNumber"/>
                                <label for="phoneNumber">Phone Number</label>
                            </div>

                            <div class="form-label-group">
                                <input name="email" type="email" id="email" class="form-control" placeholder="Email"
                                       required autofocus/>
                                <form:errors path="email"/>
                                <label for="email">Email</label>
                            </div>
                        </div>

                        <span style="margin-left: 1rem; color: red; font-weight: bolder">${newFormErrorMessage}</span>



                    </div>
                    <input name="image" type="file" accept="image/jpeg" multiple="multiple">
                    <button name="Submit" style="float: right; margin-right: 2rem; font-weight: bolder" class="btn btn-lg btn-primary text-uppercase" type="submit">POST
                    </button>


                </div>
            </form:form>
        </div>

    </div>

</section>

</body>
</html>
