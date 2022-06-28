<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="base-view/baseTag.jsp"/>
        <title>JSP Page</title>
        <link href="./css/course-detail.css" rel="stylesheet">
    </head>
    <body>

        <jsp:include page="base-view/headerUser.jsp"/>

        <!-- Course Details Header Start -->

        <div class="course-detail-header" style="background-image: url('${course.thumbnailUrl}')">
            <div class="course-detail-header-left">
                <div class="course-detail-title">
                    <h1>${course.name}</h1>
                    <c:if test="${course.price <= 0}">
                        <p>Free</p>
                    </c:if>
                    <c:if test="${course.price > 0}">
                        <p>$ ${course.price}</p>
                    </c:if>
                    
                </div>
                <p class="course-detail-description">${course.description}</p>
                <div class="course-detail-author">
                    <img src="${course.instructorId.profilePictureUrl}" alt=""
                         class="course-detail-author-avatar">
                    <span class="course-detail-author-name">${course.instructorId.firstName} ${course.instructorId.lastName}</span>
                </div>
                <c:if test="${!isRegister}">
                    <a href="${sessionScope.account != null ? '#' : 'login'}" class="${sessionScope.account != null ? 'course-enroll-button' : 'course-login-button'}">
                        <p>Enroll</p>
                    </a>
                </c:if>
                <c:if test="${isRegister}">  
                    <a href="lesson?id=${course.courseId}" class="course-continue-button">Continue</a>
                </c:if>
            </div>
            <div class="course-detail-header-right">
                <embed src="${course.videoIntroduce}" class="course-detail-img">
            </div>

        </div>

        <!-- Course Details Header End- -->

        <!-- Course Details Body Start -->

        <div class="course-detail-body">
            <div class="course-detail-body-left">
                <h1 class="course-detail-body-title">About this Professional Certificate</h1>
                <p class="course-detail-body-desc">${course.aboutCourse}</p>
            </div>
            <div class="course-detail-body-right">
                <div class="course-detail-up">
                    <h1>Objectives</h1>
                    <ul>
                        <c:forEach items="${course.objectives}" var="obj">
                            <li><i style='color: #0dd20d; margin-right: 25px;' class="fa-solid fa-check"></i> ${obj}</li>  
                            </c:forEach>
                    </ul>
                </div>
                <div class="course-detail-down">
                    <h1>About course</h1>
                    <div>
                        <ul>
                            <li>Total lesson :  ${numberLesson}</li>
                            <li>Total quiz :  ${numberQuiz}</li>
                            <li>Rating :   
                                <ul style="display:inline-block">
                                    <li style="margin: 0">
                                        <c:forEach begin="1" end="${course.star}">
                                            <i class="c-star fa-solid fa-star selected"></i>
                                        </c:forEach>
                                        <c:forEach begin="${course.star + 1}" end="5">
                                            <i class="c-star fa-solid fa-star"></i>
                                        </c:forEach>
                                    </li>
                                </ul>
                            </li>
                            <li>Date published :  ${course.createdDate}</li>
                            <li>Total people learning :  ${course.numberPeopleLearning}</li>

                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <!-- Course Details Body End -->

        <div id="cd-overlay"></div>
        <div id="notice-buy-course">
            <div id="notice-buy-course-header">
                <h4>Enroll</h4>
                <i id="close-btn" class="fa-solid fa-xmark"></i>
            </div>
            <div id="notice-buy-course-body">
                <div class="display-flex">
                    <img src="${course.tinyPictureUrl}" alt="" id="notice-buy-course-img">
                    <div id="notice-buy-course-desc">
                        <h4>${course.name}</h4>
                        <ul style="margin-top: 15px">
                            <li>
                                <c:forEach begin="1" end="${course.star}">
                                    <i class="c-star fa-solid fa-star selected"></i>
                                </c:forEach>
                                <c:forEach begin="${course.star + 1}" end="5">
                                    <i class="c-star fa-solid fa-star"></i>
                                </c:forEach>
                            </li>
                        </ul>
                    </div>

                </div>
                <div id="notice-buy-course-price">
                    <div class="notice-price">
                        <c:if test="${course.price <= 0}">
                            <p class="display-inline-block">Price of course: &nbsp;<p class="price-num display-inline-block">Free</p></p>
                        </c:if>
                        <c:if test="${course.price > 0}">
                        <p class="display-inline-block">Price of course: &nbsp;$ &nbsp;<p class="price-num display-inline-block">${course.price}</p></p>
                        </c:if>
                    </div>
                    <div class="notice-price">
                        <p class="display-inline-block">Your balance: &nbsp;$ &nbsp;<p class="price-num display-inline-block">${sessionScope.account.balance}</p></p>
                    </div>
                </div>
                <p id="notice-not-enough" style="color:red"></p>
            </div>
            <div id="notice-buy-course-footer">
                <button type="button" id="btn-buy-course">Pay</button>
            </div>
        </div>

        <jsp:include page="base-view/footerUser.jsp"/>

        <script type="text/javascript" src="js/course-detail.js"></script>
    </body>
</html>
