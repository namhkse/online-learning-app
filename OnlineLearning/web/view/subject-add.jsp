<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Subject Detail</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <!--Font Awesome-->
        <script src="https://kit.fontawesome.com/7b806b5ab9.js" crossorigin="anonymous"></script>
        <!--Jquery-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <link href="../css/subject-add.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <link href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
        <script src="../js/subject-detail.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css">
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.js"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>

    </head>

    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-2 min-vh-100 bg-dark p-0">
                    <jsp:include page="sidenav.jsp?page=Manage Subject"/>
                </div>
                <div class="col-sm-10 p-0">
                    <jsp:include page="navbar-header.jsp?page=Subject Detail"/>
                    <div class="container">
                        <a class="back" href="../management/subject-list"><i class="fa-solid fa-angle-left"></i>Back</a>
                        <div class="container-table">

                            <form action="../management/subject-add" method="post" class="form-submit" enctype="multipart/form-data">
                                <div class="form-content">
                                    <div class="left-part">
                                        <h4 class="title">Subject Name</h4>
                                        <input type="text" name="name" maxlength="200" class="input-box" required>
                                        <div id="category-item">
                                            <h4 class="title">Category</h4>
                                            <div class="filter-category" onmouseover="showFilterBox()" onmouseout="hideFilterBox()" >
                                                <div id="current-category-box">
                                                    <span id="current-category">${subject.categoryID.name == null ? subject.mainCategoryID.name : subject.categoryID.name}</span>&nbsp;<i class="fa-solid fa-caret-down filter-icon"></i>
                                                </div>
                                                <div id="filter-category-box">
                                                    <div class="search-category-box">
                                                        <input onkeyup="searchCategory(this)" id="search-category" type="text" value="" placeholder="Search for category name"/>
                                                    </div>
                                                    <div id="category-checkbox">
                                                        <c:forEach var="mainCategory" items="${subjectMainCategories}">
                                                            <div>
                                                                <div class="main-category">
                                                                    <input name="mainCategoryID" value="${mainCategory.mainCategoryID}" onchange="checkedCategory(this)" id="maincategory${mainCategory.mainCategoryID}" type="checkbox" />
                                                                    <label for="maincategory${mainCategory.mainCategoryID}">${mainCategory.name}</label>
                                                                </div>
                                                                <c:forEach var="category" items="${subjectCategories}">
                                                                    <c:if test="${category.mainCategoryID.mainCategoryID == mainCategory.mainCategoryID}">
                                                                        <div class="category">
                                                                            <input name="categoryID" onchange="checkedCategory(this)" id="category${category.categoryID}" type="checkbox" value="${category.categoryID}" />
                                                                            <label for="category${category.categoryID}">${category.name}</label>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <c:if test="${sessionScope.account.role.id==4}">
                                            <div id="list-expert">
                                                <h4 class="title">Expert can access</h4>
                                                <div class="expert-box" onmouseover="showExpertBox()" onmouseout="hideExpertBox()" >
                                                    <i class="fa-solid fa-list-check filter-icon"></i>
                                                    <div id="filter-expert-box">
                                                        <div class="search-category-box">
                                                            <input onkeyup="searchExpert(this)" id="search-category" type="text" value="" placeholder="Search for expert"/>
                                                        </div>
                                                        <div id="expert-checkbox">
                                                            <c:forEach var="expert" items="${experts}">
                                                                <div class="expert-info">
                                                                    <input id="expert${expert.accountID}" type="checkbox" name="expertCanAccess" value="${expert.accountID}" />
                                                                    <label for="expert${expert.accountID}">${expert.accountID} &nbsp;&nbsp;&nbsp; ${expert.firstName} ${expert.lastName}</label>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div> 
                                        </c:if>
                                        <div id="featured-status">
                                            <div>
                                                <input <c:if test="${sessionScope.account.role.id!=4}">disabled="disabled"</c:if> id="featured-subject" type="checkbox" name="featured" value="true" /> 
                                                    <label for="featured-subject">Featured Subject</label>
                                                </div>
                                                <div>
                                                    <h4 class="title">Status</h4>
                                                    <select name="status" class="select-tag">
                                                        <option value="true" ${subject.status == true ? "selected" : ""}>Published</option>
                                                    <option value="false" ${subject.status == false ? "selected" : ""}>Unpublished</option>
                                                </select>
                                            </div>
                                        </div>
                                        <h4 class="title">Description</h4>
                                        <textarea name="description" maxlength="2000" class="input-box" rows="5" cols="50" required></textarea>
                                    </div>
                                    <div class="upload-img">
                                        <input type="file" name="photo" id="file" class="inputfile" data-multiple-caption="{count} files selected" accept="image/*" required >
                                    </div>
                                </div>
                                <input type="submit" value="ADD" class="save">
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap JavaScript -->    
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

    </body>

</html>