<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="base-view/baseTagAdmin.jsp"></jsp:include>

            <title>Management Post</title>
        </head>

        <body>
            <div class="display-flex min-height-100vh">

            <jsp:include page="base-view/headerAdmin.jsp"></jsp:include>

                <div class="width85">

                <jsp:include page="base-view/dropDownAdmin.jsp"></jsp:include>

                    <div class="container">
                        <h1 class="container-title">Post</h1>
                        <div class="container-table">
                            <div class="table-header">Manager post</div>
                            <div class="table-content">
                                <div class="search">
                                    <a class="margin-auto-0" id="add-blog" href="post-detail" ><i class="fa-solid fa-plus"></i> Add Blog</a>
                                    <form action="post-list" method="GET"> 
                                        Category: 
                                        <select name="cid" class="select-tag">
                                            <option value="-1" >Select category</option>
                                            <c:forEach items="${allBlogCategory}" var="blogCate" >
                                                <option value="${blogCate.blogCategoryID}" ${cid == blogCate.blogCategoryID ? "selected" : ""}>${blogCate.name}</option>
                                            </c:forEach>
                                        </select>
                                        Status: 
                                            <select name="display" class="select-tag">
                                                <option value="-1" >Select Status</option>
                                                <option value="false" ${ display == "false" ? "selected" : ""}>Hidden</option>
                                                <option value="true" ${ display == "true" ? "selected" : ""}>Display</option>                                       
                                        </select>

                                        <input id="submit" type="submit" value="Filter"/>
                                    </form>
                                <div>
                                    <form action="post-list" method="GET">
                                        <input type="text" id="search" name="search">
                                        <input id="submit" type="submit" value="Search"/>
                                    </form>
                                </div>
                            </div>
                            <table>
                                <thead>
                                    <tr>
                                        <th>ID<i class="fa-solid fa-sort" onclick="sortTable(0)"></i></th>
                                        <th>Thumbnail</th>
                                        <th class="width350">Title<i class="fa-solid fa-sort" onclick="sortTable(2)"></i></th>
                                        <th>Category<i class="fa-solid fa-sort" onclick="sortTable(3)"></i></th>
                                        <th>Date Created<i class="fa-solid fa-sort" onclick="sortTable(4)"></i></th>
                                        <th>Status<i class="fa-solid fa-sort" onclick="sortTable(5)"></i></th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody id="myTable">

                                    <c:forEach items="${listBlog}" var="blog" >
                                        <tr>
                                            <td>${blog.blogID}</td>
                                            <td><img class="img-thumbnail-blog" src="${blog.thumbnailUrl}"></td>
                                            <td>${blog.title}</td>

                                            <td>
                                                <c:forEach items="${blog.getBlogCategories()}" var="blogCategory">
                                                    ${blogCategory.name}
                                                </c:forEach>
                                            </td>

                                            <td>${blog.createdDate}</td>
                                            <td>${blog.display ? 'Display' : 'Hidden'}</td>
                                            <td><a id="myTable-change" href="post-detail?bid=${blog.blogID}" >Edit</a></td>
                                            <td><a id="myTable-change" href="#" onclick="deleteBlog(${blog.blogID})">Delete</a></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="table-paging">
                                <ul class="pagging-list">                                
                                    <%@include file="page.jsp"%>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>          
        </div>
    </body>

</html>