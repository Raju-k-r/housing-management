<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.dbms.housingmanagement.utils.Mapping" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Housing management</title>

    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@900&family=Ubuntu:wght@300&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="css/styles.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>

    <style>

        .navbar {
            padding-bottom: 2.5rem;
            background-color: #03506f;
            color: #fff;
        }

        .navbar-brand {
            font-family: "Ubuntu";
            font-size: 2.5rem;
            font-weight: bold;
        }

        .footer-icon {
            padding: 1%;
        }

        .error {
            border-color: red;
        }

        .card {
            background-color: #222831;
            margin-left: 5%;
            margin-top: 5%;
        }


        .pcard {
            background-color: #f5b461;
        }

        span {
            font-size: x-large;
            color: #a37140;
        }

        body {
            background-color: #0a043c;
        }

        input {
            border-color: red;
        }

        #footer {
            text-align: center;
            background-color: #fff;
            padding: 3% 15%;
        }

        @media (max-width: 767px) {
            .card {
                width: 90vw
            }
        }

        .image {
            text-align: center;
            width: 200px;
            height: 200px;
            -webkit-border-radius: 75%;
            -moz-border-radius: 75%;

        }

        #profile {
            margin-top: 1.5%;
            margin-left: 30%;
        }

        .profile {
            text-align: center;
            color: white;
        }

    </style>
</head>

<body>

<c:if test="${isAnyMessage}">
    <c:if test="${messageType eq 'SUCCESS'}">
        <div class="alert alert-success" role="alert">
                ${message}
        </div>
    </c:if>

    <c:if test="${messageType eq 'FAIL'}">
        <div class="alert alert-danger" role="alert">
            ${message}
        </div>
    </c:if>
</c:if>


<nav class="navbar  navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="">Housing Management</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">

            <li class="nav-item">
                <a href="<%=Mapping.ADD_NEW_POST%>" class="btn-primary" style="margin-left: 15rem; border-radius: 2rem; border-color: white;
                                                                        padding: 0.5rem 1.5rem;background-color: #f054"
                   type="submit">Add Post</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="<%=Mapping.HOME%>">Home</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="<%=Mapping.MY_WISH_LIST%>">Wish list</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" data-toggle="modal" data-target="#profile">Profile</a>
            </li>
        </ul>
    </div>
</nav>

<div class="row pb-5 mb-4" style="margin: 0">

    <c:if test="${posts.size() == 0}">
        <div class="alert alert-secondary" role="alert" style=" border-color: white; border-width: thick;width: 80%;margin: 100px 100px;text-align: center;">
            <h4 class="alert-heading">Your MyPost List is empty</h4>
            <p> Add the posts which you want to sell </p>
        </div>
    </c:if>
    <c:forEach var="post" items="${posts}">

        <div class="col-lg-4 col-md-6 mb-4 mb-lg-0">
            <!-- Card-->
            <div class="card rounded shadow-sm border-0" style="height: 450px;">
                <div class="card-body p-4">
                    <img src="data:image/jpeg;base64,${post.image.get(0)}" alt="This image is not Working"
                         class="img-fluid d-block mx-auto mb-3"
                         style="height: 250px; width: 350px; crop: auto">
                    <h5>
                        <a href="#" class="text-light" data-toggle="modal" data-id="${post.postId}"
                           data-target="#property${post.postId}">${post.property.name}</a>
                    </h5>
                    <p class="small text-muted font-italic">${post.property.address}</p>
                    <p style="font-weight: bolder; color: white">${post.property.cost} Rs</p>
                    <ul class="list-inline small">
                        <li class="list-inline-item m-0"><i class="fa fa-star text-success"></i></li>
                        <li class="list-inline-item m-0"><i class="fa fa-star text-success"></i></li>
                        <li class="list-inline-item m-0"><i class="fa fa-star text-success"></i></li>
                        <li class="list-inline-item m-0"><i class="fa fa-star text-success"></i></li>
                        <li class="list-inline-item m-0"><i class="fa fa-star-o text-success"></i></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="modal fade" id="property${post.postId}" data-backdrop="static" tabindex="-1"
             role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">

            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle1">${post.property.name}</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" style="padding: 0">
                        <div class="card" style="margin: 0">
                            <div class="card-img">

                                <div class="carousel slide" id="main-carousel" data-ride="carousel">
                                    <div class="carousel-inner">
                                        <div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel"
                                             data-interval="1000">
                                            <div class="carousel-inner">
                                                <c:forEach var="image" items="${post.image}" varStatus="counter">
                                                    <c:set var="active" value="${counter.first ? ' active':''}"/>
                                                    <div class="carousel-item ${active}">
                                                        <img src="data:image/jpeg;base64,${image}"
                                                             style="height: 250px; width: 450px; crop: auto"
                                                             class="d-block w-100" alt="...">
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <a href="#main-carousel" class="carousel-control-prev" data-slide="prev">
                                                <span class="carousel-control-prev-icon"></span>
                                                <span class="sr-only" aria-hidden="true">Prev</span>
                                            </a>
                                            <a href="#main-carousel" class="carousel-control-next" data-slide="next">
                                                <span class="carousel-control-next-icon"></span>
                                                <span class="sr-only" aria-hidden="true">Next</span>
                                            </a>
                                        </div>
                                    </div>
                                </div><!-- /.carousel -->

                            </div>

                        </div>
                        <div class="card-body"
                             style="font-family:Times New Roman, Times, serif;  padding: 20px; font-size: 16px; margin: 0; background-color: #f5b461">
                            <div class="row">
                                <p style="font-size: 24px;font-weight: bolder;color: white;padding-left: 10px">Property
                                    Details</p>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <p style="padding-left: 40px; font-weight: bolder; font-size: 16px">Address</p>
                                </div>
                                <div class="col">
                                    <p style="margin: 0">${post.property.address}</p>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col">
                                    <p style="padding-left: 40px; font-weight: bolder; font-size: 16px">Price</p>
                                </div>
                                <div class="col">
                                    <p>${post.property.cost}</p>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <p style="font-size: 24px;font-weight: bolder;color: white;padding-left: 10px">Owner
                                    Deatils</p>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <p style="padding-left: 40px; font-weight: bolder; font-size: 16px">Name</p>
                                </div>
                                <div class="col">
                                    <p>${post.owner.name}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <p style="padding-left: 40px; font-weight: bolder; font-size: 16px">Phone Number</p>
                                </div>
                                <div class="col">
                                    <p>${post.owner.phoneNumber}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <p style="padding-left: 40px; font-weight: bolder; font-size: 16px">Email</p>
                                </div>
                                <div class="col">
                                    <p>${post.owner.email}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:if test="${post.user.userId == user.userId}">
                        <div class="modal-footer">
                            <div class="modal-footer">
                                <a href="/deletePost/${post.property.propertyId}" class="btn-primary"
                                   style="margin-left: 15rem; border-radius: 2rem; padding-left: 1.5rem; padding-right: 1.5rem; padding-top: 0.5rem; padding-bottom: 0.5rem"
                                   type="submit">Delete Post</a>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${post.user.userId != user.userId}">
                        <div class="modal-footer">
                            <a href="/addToWishList/${post.property.propertyId}" class="btn-primary"
                               style="margin-left: 15rem; border-radius: 2rem; padding-left: 1.5rem; padding-right: 1.5rem; padding-top: 0.5rem; padding-bottom: 0.5rem"
                               type="submit">Add to Wishlist</a>
                        </div>
                    </c:if>
                </div>
            </div>


        </div>
    </c:forEach>
</div>

<div class="modal fade" id="profile" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content" style="padding: 16px">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Profile</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" style="padding: 0">

                <div class="pcard profile">

                    <div class="card-img" style="padding-top: 20px;">
                        <img class="img-fluid image" src="data:image/jpeg;base64,${profilePhoto}" alt="Profile Picture">
                    </div>
                    <div class="card-title">
                        <h3 style="margin-top: 2%">${user.firstName} ${user.lastName}</h3>
                    </div>
                    <hr>

                    <div class="row">
                        <div class="col">
                            <p style="padding-left: 40px; font-weight: bolder; font-size: 16px">Email</p>
                        </div>
                        <div class="col" style="text-align: left">
                            <p style="margin: 0">${user.email}</p>
                        </div>
                    </div>
                    <div class="row" style="margin-bottom: 10px">
                        <div class="col">
                            <p style="padding-left: 40px; font-weight: bolder; font-size: 16px">Address</p>
                        </div>
                        <div class="col" style="text-align: left">
                            <p style="margin: 0">${user.address}</p>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col">
                            <p style="padding-left: 40px; font-weight: bolder; font-size: 16px">Phone Number</p>
                        </div>
                        <div class="col" style="text-align: left">
                            <p style="margin: 0">${user.phoneNumber} </p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <p style="padding-left: 40px; font-weight: bolder; font-size: 16px">Adhar Number</p>
                        </div>
                        <div class="col" style="text-align: left">
                            <p style="margin: 0">${user.adharNumber}</p>
                        </div>
                    </div>


                </div>
            </div>
            <div class="modal-footer">
                <a href="/logout" class="btn-primary"
                   style="margin-left: 15rem; border-radius: 2rem; padding-left: 1.5rem; padding-right: 1.5rem; padding-top: 0.5rem; padding-bottom: 0.5rem"
                   type="submit">Sign Out</a>
            </div>
        </div>
    </div>

</div>

<footer id="footer">
    <p class="last"><i class="fab fa-twitter footer-icon"></i> <i class="fab fa-facebook-f footer-icon"></i> <i
            class="fab fa-instagram footer-icon"></i> <i class="fas fa-envelope footer-icon"></i></p>
    <p>© Copyright 2020 Housing Management</p>

</footer>

<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
<script>
    $(document).ready(function () {
        console.log("tooltip")
        $('[data-toggle="tooltip"]').tooltip();
    });

    $(function () {
        console.log("popover")
        $('[data-toggle="popover"]').popover()
    });

    $('.popover-dismiss').popover({
        trigger: 'focus'
    });
</script>

</body>
</html>
