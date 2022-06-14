<%@page import="model.CourseAccount"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="base-view/baseTag.jsp"></jsp:include> 
            <link rel="stylesheet" href="css/home.css"/>
            <link rel="stylesheet" href="css/courses.css">

            <title>My Courses</title>
        </head>

        <body>


        <jsp:include page="base-view/headerUser.jsp"></jsp:include>

            <!-- Intro Start -->

            <div id="intro">
                <div class="intro-child">
                    <h1>My Courses</h1>
                </div>
            </div>

            <!-- Intro End -->
            <div class="container-my-course">
            <c:forEach items="${listCourseAccount}" var="ca">
                <div class="my-course">    
                    <div class="my-course-item">
                        <div class="tiny-picture">
                            <img src="${ca.courseId.thumbnailUrl}" alt="">
                            <div><a href="lesson?id=${ca.courseId.courseId}">Continue</a></div>
                        </div>
                        <div class="desc-best-course"> 
                            <h2>${ca.courseId.price} $</h2>
                            <div class="rated-star">
                                <i class="fa-solid fa-star voted"></i>
                                <i class="fa-solid fa-star voted"></i>
                                <i class="fa-solid fa-star voted"></i>
                                <i class="fa-solid fa-star voted"></i>
                                <i class="fa-regular fa-star voted"></i>                
                            </div>
                            <h3>${ca.courseId.name}</h3>
                        </div>
                        <div class="progress">
                            <span class="progress-value">${ca.process} %</span>
                            <progress class="progress-bar" value="${ca.process}" max="100"> </progress>
                        </div>
                        <div class="display-flex">
                            <div class="item-desc">
                                <i class="fa-solid fa-user-tie"></i>
                                <span>${ca.courseId.instructorId.firstName} ${ca.courseId.instructorId.lastName}</span>
                            </div>         
                        </div>
                    </div>
                </div> 
            </c:forEach>        
        </div>

        <jsp:include page="base-view/footerUser.jsp"></jsp:include>


    </body>

</html>
