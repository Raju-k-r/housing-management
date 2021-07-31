<%@ page import="com.dbms.housingmanagement.utils.Mapping" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Wishlist</title>

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

</head>

<style>

    * {
        margin: 0;
        padding: 0;
        -webkit-font-smoothing: antialiased;
        -webkit-text-shadow: rgba(0, 0, 0, .01) 0 0 1px;
        text-shadow: rgba(0, 0, 0, .01) 0 0 1px
    }

    body {
        font-family: 'Rubik', sans-serif;
        font-size: 14px;
        font-weight: 400;
        background: #0a043c;
        color: #000000
    }

    ul {
        list-style: none;
        margin-bottom: 0px
    }

    .button {
        display: inline-block;
        background: #0e8ce4;
        border-radius: 5px;
        height: 48px;
        -webkit-transition: all 200ms ease;
        -moz-transition: all 200ms ease;
        -ms-transition: all 200ms ease;
        -o-transition: all 200ms ease;
        transition: all 200ms ease
    }

    .button a {
        display: block;
        font-size: 18px;
        font-weight: 400;
        line-height: 48px;
        color: #FFFFFF;
        padding-left: 35px;
        padding-right: 35px
    }

    .button:hover {
        opacity: 0.8
    }

    .cart_section {
        width: 100%;
        padding-bottom: 111px
    }

    .cart_title {
        font-size: 30px;
        font-weight: 500
    }

    .cart_items {
        margin-top: 8px
    }

    .cart_list {
        border: solid 1px #e8e8e8;
        box-shadow: 0px 1px 5px rgba(0, 0, 0, 0.1);
        background-color: #fff
    }

    .cart_item {
        width: 100%;
        padding: 15px;
        padding-right: 46px
    }

    .cart_item_image {
        padding-top: 30px;
        width: 133px;
        height: 150px;
        float: left
    }

    .cart_item_image img {
        max-width: 100%
    }

    .cart_item_info {
        width: calc(100% - 133px);
        float: left;
        padding-top: 18px
    }

    .cart_item_name {
        margin-left: 7.53%
    }

    .cart_item_title {
        font-size: 14px;
        font-weight: 400;
        color: rgba(0, 0, 0, 0.5)
    }

    .cart_item_text {
        font-size: 18px;
        margin-top: 15px
    }

    /*.cart_item_text span {*/
    /*    display: inline-block;*/
    /*    width: 20px;*/
    /*    height: 20px;*/
    /*    border-radius: 50%;*/
    /*    margin-right: 11px;*/
    /*    -webkit-transform: translateY(4px);*/
    /*    -moz-transform: translateY(4px);*/
    /*    -ms-transform: translateY(4px);*/
    /*    -o-transform: translateY(4px);*/
    /*    transform: translateY(4px)*/
    /*}*/

    .cart_item_price {
        text-align: right
    }

    .cart_item_total {
        text-align: right
    }

    .order_total {
        width: 100%;
        height: 60px;
        margin-top: 30px;
        border: solid 1px #e8e8e8;
        box-shadow: 0px 1px 5px rgba(0, 0, 0, 0.1);
        padding-right: 46px;
        padding-left: 15px;
        background-color: #fff
    }

    .order_total_title {
        display: inline-block;
        font-size: 14px;
        color: rgba(0, 0, 0, 0.5);
        line-height: 60px
    }

    .order_total_amount {
        display: inline-block;
        font-size: 18px;
        font-weight: 500;
        margin-left: 26px;
        line-height: 60px
    }

    .cart_buttons {
        margin-top: 60px;
        text-align: right
    }

    .cart_button_clear {
        display: inline-block;
        border: none;
        font-size: 18px;
        font-weight: 400;
        line-height: 48px;
        color: #FFFFFF;
        background: #f05454;
        border: solid 1px #b2b2b2;
        padding-left: 35px;
        padding-right: 35px;
        outline: none;
        cursor: pointer;
        margin-right: 26px
    }

    .cart_button_clear:hover {
        border-color: #0e8ce4;
        color: #0e8ce4
    }

    .cart_button_checkout {
        display: inline-block;
        border: none;
        font-size: 18px;
        font-weight: 400;
        line-height: 48px;
        color: #FFFFFF;
        padding-left: 35px;
        padding-right: 35px;
        outline: none;
        cursor: pointer;
        vertical-align: top
    }

    .navbar {
        margin-bottom: 2.5rem;
        padding-bottom: 2.5rem;
        background-color: #f05454;
        color: #fff;
    }

    .navbar-brand {
        font-family: "Ubuntu";
        font-size: 2.5rem;
        font-weight: bold;
    }

</style>
<body>
<c:if test="${isAnyMessage}">
    <div class="alert alert-success" role="alert">
            ${message}
    </div>
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
                <a class="nav-link" href="#" data-toggle="modal" data-target="#profile">Profile</a>
            </li>
        </ul>
    </div>
</nav>

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
                        <img class="img-fluid image" src="data:image/jpeg;base64,${profilePhoto}" width="125px" height="125px" alt="Profile Picture" style=" border-width: medium; border-color: red; border-style: solid; margin-left : 30%; -webkit-border-radius: 75%; -moz-border-radius: 75%;">
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
                <a href="<%=Mapping.LOGOUT%>" class="btn-primary"
                   style="margin-left: 15rem; border-radius: 2rem; padding-left: 1.5rem; padding-right: 1.5rem; padding-top: 0.5rem; padding-bottom: 0.5rem"
                   type="submit">Sign Out</a>
            </div>
        </div>
    </div>

</div>

<div class="cart_section">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-10 offset-lg-1">
                <div class="cart_container">
                    <div class="cart_title" style="color:#FFFFFF;">WISH LIST<small> (${userWishList.size()} item added) </small></div>
                    <c:if test="${userWishList.size() == 0}">
                        <div class="alert alert-secondary" role="alert" style=" border-color: white; border-width: thick;width: 80%;margin: 100px 100px;text-align: center;">
                            <h4 class="alert-heading">Your Wish List is empty</h4>
                            <p> Add the posts which you like </p>
                        </div>

                    </c:if>
                    <c:forEach var="wishList" items="${userWishList}">

                        <div class="cart_items" style="background-color: #222831;">
                            <ul class="cart_list">
                                <a href="/removeWishList/${wishList.property.propertyId}" type="button" class="close" data-dismiss="modal" aria-label="Close" style="padding: 6px">
                                    <span aria-hidden="true" style="color: #FFFFFF">&times;</span>
                                </a>
                                <li class="cart_item clearfix" style="background-color: #222831; color: #FFFFFF">
                                    <div class="cart_item_image">
                                        <img class="image img-fluid" src="data:image/jpeg;base64,${wishList.propertyImage}"
                                             alt="Property Image"></div>
                                    <div class="cart_item_info d-flex flex-md-row flex-column justify-content-between">
                                        <div class="cart_item_name cart_info_col">
                                            <div class="cart_item_title" style="color: #FFFFFF">Name</div>
                                            <div class="cart_item_text">${wishList.property.name}</div>
                                        </div>
                                        <div class="cart_item_color cart_info_col" style="padding-left: 30px">
                                            <div class="cart_item_title" style="color: #FFFFFF">Address</div>
                                            <div class="cart_item_text"><span style="background-color:#999999;"></span>Property
                                                    ${wishList.property.address}
                                            </div>
                                        </div>
                                        <div class="cart_item_quantity cart_info_col" style="padding-left:30px">
                                            <div class="cart_item_title" style="color: #FFFFFF">Owner</div>
                                            <div class="cart_item_text"><p>${wishList.owner.name}</p></div>
                                        </div>
                                        <div class="cart_item_price cart_info_col" style="padding-left: 30px">
                                            <div class="cart_item_title" style="color: #FFFFFF">Amount</div>
                                            <div class="cart_item_text">₹${wishList.property.cost}</div>
                                        </div>

                                    </div>
                                </li>
                            </ul>
                        </div>
                    </c:forEach>
                    <div class="cart_buttons">
                        <button type="button" class="button cart_button_clear" style="color: red"><a href="${pageContext.request.contextPath}/">HOME</a></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
