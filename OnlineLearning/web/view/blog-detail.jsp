<%@page import="model.Blog"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.BlogCategory"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        

        <jsp:include page="base-view/baseTag.jsp"></jsp:include>

            <link href="./css/blog-detail.css" rel="stylesheet">

            <title>${blog.title}</title>

    </head>

    <body>

        <jsp:include page="base-view/headerUser.jsp"></jsp:include>

            <!-- Blog Details Start -->

            <div class="blog-detail">
                <h1 class="blog-detail-title" >${blog.title}</h1>
            <div class="blog-detail-author">
                            <img src="https://i.pinimg.com/564x/96/54/b2/9654b2c2c47716bbda36205127dc1d77.jpg" alt=""
                                class="blog-detail-author-avatar">
                <div class="blog-detail-create">
                    <p>${blog.authorID.firstName} ${blog.authorID.lastName}</p>
                    <p>${blog.createdDate}</p>
                </div>
            </div>
                <span>${blog.description}</span>
                <p>${blog.content}</p>
        </div>

        <!-- Blog Details End -->
    </body>

</html>