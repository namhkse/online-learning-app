<%@page import="java.sql.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="base-view/baseTag.jsp"></jsp:include>

            <link href="css/blog.css" rel="stylesheet" type="text/css" />

            <title>Blog</title>
        </head>

        <body>

        <jsp:include page="base-view/headerUser.jsp"></jsp:include>

            <!-- Intro Start -->

            <div id="intro" 
            <c:forEach items="${bcList}" var="bc">
                <c:if test="${bc.blogCategoryID == bcid}">
                    style ="background-image: url('${bc.thumbnailUrl}')"
                </c:if>
            </c:forEach>
            >
                <div class="intro-child">
                    <h1>Blogs</h1>
                </div>
            </div>

            <!-- Intro End -->

            <!-- Blog Start -->

            <div class="container-xxl">
                <div class="search">
                    <div class="search-child">
                        <form action="blog" method="get">
                            <input type="text" value="${search}" name="search" placeholder="Search" id="search-input">
                    </form>
                </div>
            </div>
            <div id="container">

                <div class="blog-container">
                    <div class="blog-container-left">
                        <ul class="blog-list" id="container-left">
                            <c:choose>
                                <c:when test="${empty blogList}">
                                    <p>No matching records found</p>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${blogList}" var="bl">
                                        <li class="blog-item">
                                            <a href="blog-detail?blogID=${bl.blogID}">
                                                <div class="blog-item-header">
                                                    <div class="blog-item-author">
                                                        <img src="${bl.getAuthorID().profilePictureUrl}"
                                                             alt="" class="blog-item-author-avatar">
                                                        <span class="blog-item-author-name">${bl.getAuthorID().firstName} ${bl.getAuthorID().lastName}</span>
                                                    </div>
                                                </div>
                                                <div class="blog-item-body">
                                                    <div class="blog-item-body-left">
                                                        <h3 class="blog-item-body-title">${bl.getTitle()}</h3>
                                                        <p class="blog-item-body-description">${bl.getDescription()}</p>
                                                        <p class="blog-item-body-time">${bl.createdDate}</p>
                                                    </div>
                                                    <div class="blog-item-body-right">
                                                        <img src="${bl.getThumbnailUrl()}"
                                                             alt="" class="blog-item-body-right-img">
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>

                        </ul>
                        <c:choose>
                            <c:when test="${not empty blogList}">                        
                                <%@include file="page.jsp" %>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="blog-container-right">
                        <div class="blog-container-right-title">
                            <h3>Category blog</h3>
                        </div>
                        <ul class="blog-container-right-toppic-list">
                            <c:forEach items="${bcList}" var="bc">
                                <li class="blog-container-right-toppic-item">
                                    <a href="blog?bcid=${bc.getBlogCategoryID()}" class="${bc.getBlogCategoryID() == bcid ? 'active-category-blog' : ''}">${bc.getName()}</a>
                                </c:forEach>
                            <li class="blog-container-right-toppic-item">
                                <a href="blog" class="${bcid == null ? 'active-category-blog' : ''}">All Category</a>
                            </li>
                        </ul>

                    </div>
                </div>
            </div>
        </div>

        <!-- Blog End -->
        
        <jsp:include page="base-view/footerUser.jsp"></jsp:include>
    </body>

</html>
