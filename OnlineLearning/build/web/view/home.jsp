<%-- 
    Document   : home_guest
    Created on : May 25, 2022, 11:05:11 AM
    Author     : midni
--%>

<%@page import="model.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Slider"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="css/base.css" rel="stylesheet" type="text/css"/>
        <link href="css/index.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
        <% Account acc = (Account) request.getSession().getAttribute("account");%>
    </head>
    <body>


        <!-- Navbar Start -->
        <% if (acc == null) { %>
        <nav class="navbar">
            <a href="home" class="navbar-brand">
                <h2><i class="fa-solid fa-book"></i>eLEARNING</h2>
            </a>
            <div class="navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav">
                    <a href="home" class="nav-item nav-link active">Home</a>
                    <a href="courses.html" class="nav-item nav-link">Courses</a>
                    <a href="blog.html" class="nav-item nav-link">Blog</a>
                </div>
                <a href="login" class="btn-primary">Join Now <i class="fa-solid fa-arrow-right"></i></a>
            </div>
        </nav>
        <%} else {%> 
        <nav class="navbar">
            <a href="home" class="navbar-brand">
                <h2><i class="fa-solid fa-book"></i>eLEARNING</h2>
            </a>
            <div class="navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav">
                    <a href="#" class="nav-item nav-link active">Home</a>
                    <a href="#" class="nav-item nav-link">Courses</a>
                    <a href="#" class="nav-item nav-link">My Courses</a>
                    <a href="#" class="nav-item nav-link">Blog</a>
                </div>
                <div id="top-bar">
                    <ul>
                        <div class="topbar-divider"></div>
                        <!-- Nav Item - User Information -->
                        <li>
                            <a href="#" id="dropdown-toggle" onclick="dropdown()">
                                <span>${account.firstName} ${account.lastName}</span>
                                <img src="${account.profilePictureUrl}">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div id="dropdown-menu">
                                <a class="dropdown-item" href="profile">
                                    <i class="fa-solid fa-address-card"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fa-solid fa-gear"></i>
                                    Settings
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fa-solid fa-list-check"></i>
                                    Activity Log
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#">
                                    <i class="fa-solid fa-right-from-bracket"></i>
                                    Logout
                                </a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <%}%> 
        <!-- Navbar End -->

        <!-- Slider Start -->

        <div class="slider-container">
            <c:forEach items="${requestScope.sliders}" var="slider">
                <div class="slider-item mySlide_current">
                    <i class="slide-icon-right fas fa-angle-right" onclick="plusSlides(1)"></i>
                    <i class="slide-icon-left fas fa-angle-left" onclick="plusSlides(-1)"></i>
                    <!--<img src="img/carousel-1.jpg" alt="">-->
                    <img src="${slider.imageUrl}" alt="">
                    <div class="slider-desc-parent">
                        <div class="slider-desc">
                            <h5>${slider.subTitle}</h5>
                            <h1>${slider.title}</h1>
                            <p>${slider.description}</p>
                            <a href="courses.html" class="">Join Now</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Slider End -->

        <div class="footer">
            <ul>
                <li>
                    <h4>Contact</h4>
                    <p><i class="fa-solid fa-location-dot"></i>Ha Noi, Viet Nam</p>
                    <p><i class="fa-solid fa-phone"></i>+84 123 456 789</p>
                    <p><i class="fa-solid fa-envelope"></i>Email@gmail.com</p>
                </li>
                <li>
                    <h4>Gallery</h4>
                    <div class="gallery-img">
                        <img src="img/course-1.jpg" alt="">
                        <img src="img/course-2.jpg" alt="">
                        <img src="img/course-3.jpg" alt="">
                        <img src="img/course-3.jpg" alt="">
                        <img src="img/course-1.jpg" alt="">
                        <img src="img/course-2.jpg" alt="">

                    </div>
                </li>
                <li>
                    <h4>Newsletter</h4>
                    <p>Dolor amet sit justo amet elitr clita ipsum elitr est.</p>
                </li>
            </ul>
        </div>


        <script src="js/home.js" type="text/javascript"></script>
    </body>
</html>
