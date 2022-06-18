<%@page import="model.CourseAccount"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="base-view/baseTag.jsp"></jsp:include> 
            <link rel="stylesheet" href="css/home.css"/>
            <link rel="stylesheet" href="css/my-course.css">
            
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


            <div id="my-course-header">
                <form id="form-search-info">
                    <input type="date" class="selected-tag"/>
                    <select class="selected-tag">
                        <option>All</option>
                        <option>In Progress</option>
                        <option>Not Learning</option>>
                    </select>
                    <div class="display-flex">
                        <input type="text" id="search-my-course" placeholder="Search for course..." onkeyup="searchCourse()">
                        <button type="submit" id="search-icon"><i class="fa-solid fa-magnifying-glass"></i></button>
                    </div>
                    <div class="display-flex position-relative afterTemp">
                        <p class="fa-solid fa-filter icon-filter"></p>
                        <div id="search-category">
                            <form>
                                <div id="holder-search-input">
                                    <input type="text" id="holder-search-category" onkeyup="searchCategory()"
                                           placeholder="Search for category...">
                                </div>
                                <div id="form-select-category">
                                    <div class="search-category-item">
                                        <div class="search-category-name">
                                            <input type="checkbox" id="id-category-1">
                                            <label for="id-category-1">Design and Graphics Design and Graphics Design and
                                                Graphics</label>
                                        </div>
                                        <div class="search-sub-category">
                                            <div class="search-sub-category-name">
                                                <input type="checkbox" id="id-sub-category-1">
                                                <label for="id-sub-category-1">Design and Graphics Design and Graphics
                                                    Design and Graphics</label>
                                            </div>
                                            <div class="search-sub-category-name">
                                                <input type="checkbox" id="id-sub-category-1">
                                                <label for="id-sub-category-1">Design and Graphics</label>
                                            </div>
                                            <div class="search-sub-category-name">
                                                <input type="checkbox" id="id-sub-category-1">
                                                <label for="id-sub-category-1">Design and Graphics</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="search-category-item">
                                        <div class="search-category-name">
                                            <input type="checkbox" id="id-category-1">
                                            <label for="id-category-1">Design and Graphics</label>
                                        </div>
                                        <div class="search-sub-category">
                                            <div class="search-sub-category-name">
                                                <input type="checkbox" id="id-sub-category-1">
                                                <label for="id-sub-category-1">Design and Graphics</label>
                                            </div>
                                            <div class="search-sub-category-name">
                                                <input type="checkbox" id="id-sub-category-1">
                                                <label for="id-sub-category-1">Design and Graphics</label>
                                            </div>
                                            <div class="search-sub-category-name">
                                                <input type="checkbox" id="id-sub-category-1">
                                                <label for="id-sub-category-1">Design and Graphics</label>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </form>
                        </div>


                    </div>

                </form>
            </div>

            <div class="my-course"> 
            <c:forEach items="${listCourseAccount}" var="course">
                <div class="my-course-item">
                    <div class="my-course-item-img">
                        <p class="option-course">
                            <i class="fa-solid fa-ellipsis-vertical "></i>
                        <div class="notice-unenroll">
                            <p>Unenroll this course?</p>
                            <form>
                                <div class="holder-button-delete">
                                    <input type="hidden" value="CourseID" class="id-course-delete">
                                    <button type="button" class="btn-unenroll" onclick="">Unenroll</button>
                                    <button type="button" class="btn-cancel" onclick="">Cancel</button>
                                </div>
                            </form>
                        </div>
                        </p>

                        <img src="${course.courseId.tinyPictureUrl}" alt="">
                        <div class="overlay-img">               
                            <a href="lesson?id=${course.courseId.courseId}" class="btn-continue">
                                ${course.process <= 0 ? "Start Learning" : "Continue"}
                            </a>
                        </div>
                    </div>
                    <div class="my-course-item-desc">
                        <div class="my-course-item-title">${course.courseId.name}</div>
                        <p class="my-course-item-date">${course.enrollDate}</p>
                        <div class="my-course-progress" style="--progress: ${course.process}%"></div>
                        <p class="text-progress">${course.process}% Complete</p>
                    </div>
                </div> 
            </c:forEach>
            </div>

        <jsp:include page="base-view/footerUser.jsp"></jsp:include>

        <script type="text/javascript" src="js/my-course.js"></script>
    </body>

</html>
