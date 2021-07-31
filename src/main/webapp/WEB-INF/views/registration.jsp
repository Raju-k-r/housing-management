<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.dbms.housingmanagement.utils.Mapping" %>
<html>
<head>
    <title>Register</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <style>

        :root {
            --input-padding-x: 1.5rem;
            --input-padding-y: .75rem;
        }

        .container {
            margin-top: 5%;
            width: 60%;
            height: 80%;
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
            font-size: 1.5rem;
            font-weight: bold;
        }

        .card-body {
            padding: 2rem;
        }


        .form-label-group {
            position: relative;
            margin-bottom: 1rem;
            padding-left: 1rem;
            padding-right: 1rem;
        }

        .form-label-group input {
            height: auto;
            border-radius: 1rem;
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
            width: 80%;
            margin-bottom: 0;
            line-height: 1.5;
            color: #495057;
            border: 1px solid transparent;
            border-radius: .25rem;
            transition: all .1s ease-in-out;
            margin-left: 1rem;
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

        #imageUpload {
            display: none;
        }

        #profileImage {
            cursor: pointer;
        }

        #profile-container {
            margin-left: auto;
            margin-right: auto;
            width: 140px;
            height: 140px;
            overflow: hidden;
            padding: .5rem;
        }

        #profile-container img {
            width: 130px;
            height: 130px;
            border-radius: 135px;
        }

        span {
            color: red;
        }

    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

</head>
<body>
<section id="Login">

    <div class="container">

        <div class="card">
            <h5 class="card-title text-center">Register</h5>


            <form:form action="<%=Mapping.REGISTER%>" modelAttribute="registrationForm" method="POST"
                       enctype="multipart/form-data">

                <div class="card-body">

                    <div class="form-group">
                        <div class="row row-cols-2 justify-content-between" style="margin: 0">
                            <div class="col col-6 mt-4">
                                <div class="form-label-group" style="margin-left: -30px; padding-right: 0">
                                    <input name="firstName" type="text" id="firstName" class="form-control"
                                           placeholder="First name" required autofocus/>
                                    <form:errors path="firstName"/>
                                    <label for="firstName">First Name</label>
                                </div>

                                <div class="form-label-group" style="margin-left: -30px; padding-right: 0">
                                    <input name="lastName" type="text" id="lastName" class="form-control"
                                           placeholder="Last Name" required autofocus/>
                                    <form:errors path="lastName"/>
                                    <label for="lastName">Last Name</label>
                                </div>
                            </div>
                            <div class="col col-6">
                                <div id="profile-container" class="form-label-group">
                                    <img id="profileImage"
                                         src="https://thumbs.dreamstime.com/b/creative-illustration-default-avatar-profile-placeholder-isolated-background-art-design-grey-photo-blank-template-mockup-144855718.jpg"
                                         alt="Profile Image"/>
                                    <input id="imageUpload" type="file"
                                           name="profilePhoto" placeholder="Photo" required="">
                                </div>
                            </div>
                        </div>

                        <div class="row row-cols-2 justify-content-between">

                            <div class="form-label-group">
                                <input name="email" type="email" id="email" class="form-control" placeholder="Email"
                                       required autofocus/>
                                <form:errors path="email"/>
                                <label for="email">Email</label>
                            </div>

                            <div class="form-label-group">
                                <input name="phoneNumber" type="tel" id="phoneNumber" class="form-control"
                                       placeholder="Phone Number" required autofocus/>
                                <form:errors path="phoneNumber"/>
                                <label for="phoneNumber">Phone Number</label>
                            </div>

                            <div class="form-label-group">
                                <input name="adharNumber" type="number" maxlength="12" minlength="12" id="adharNumber"
                                       class="form-control"
                                       placeholder="Adhar Number" autofocus/>
                                <form:errors path="adharNumber"/>
                                <label for="adharNumber">Adhar Number</label>
                            </div>

                            <div class="form-label-group">
                                <input name="address" type="text" id="address" class="form-control"
                                       placeholder="Address" required autofocus/>
                                <form:errors path="address"/>
                                <label for="address">Address</label>
                            </div>

                            <div class="form-label-group">
                                <input name="password" type="password" id="password" class="form-control"
                                       placeholder="Password" required autofocus/>
                                <form:errors path="password"/>
                                <label for="password">Password</label>
                            </div>

                            <div class="form-label-group">
                                <input name="confirmPassword" type="password" id="confirmPassword" class="form-control"
                                       placeholder="Confirm Password" required autofocus/>
                                <form:errors path="confirmPassword"/>
                                <label for="confirmPassword">Confirm Password</label>
                            </div>
                        </div>

                    </div>

                    <span style="margin-left: 1rem; color: red; font-weight: bolder">${registrationErrorMessage}</span>

                    <button name="Submit" class="btn btn-primary text-uppercase"
                            style="margin-left: 35.5rem; border-radius: 2rem" type="submit">Register
                    </button>

                </div>
            </form:form>
        </div>

    </div>

</section>

<script>

    $("#profileImage").click(function () {
        $("#imageUpload").click();
    });

    function fasterPreview(uploader) {
        if (uploader.files && uploader.files[0]) {
            $('#profileImage').attr('src',
                window.URL.createObjectURL(uploader.files[0]));
        }
    }

    $("#imageUpload").change(function () {
        fasterPreview(this);
    });
</script>

</body>
</html>


