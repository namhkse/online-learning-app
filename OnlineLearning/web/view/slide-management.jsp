<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Manage Slide</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <!--Font Awesome-->
        <script src="https://kit.fontawesome.com/7b806b5ab9.js" crossorigin="anonymous"></script>
        <!--Jquery-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <link href="../css/slide-management.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
        <script src="../js/slide-management.js" type="text/javascript"></script>
    </head>

    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-2 min-vh-100 bg-dark p-0">
                    <jsp:include page="sidenav.jsp?page=Manage Slide"/>
                </div>
                <div class="col-sm-10 p-0">
                    <jsp:include page="navbar-header.jsp?page=Manage Slide"/>

                    <div class="container">
                        <div class="container-table">
                            <div class="table-content">
                                <div class="search">
                                    <a class="margin-auto-0" id="add-blog" href="slide-detail" ><i class="fa-solid fa-plus"></i> Add Slide</a>

                                    <form action="slide-list" method="GET" class="search-form">
                                        <input type="text" id="search" name="search" placeholder="Search by title or backlink">
                                        <input id="submit" type="submit" value="Search"/>
                                    </form> 

                                    <form action="slide-list" method="GET"> 
                                        Collection: 
                                        <select name="scid" id="collection" class="select-tag" onchange="this.form.submit()">
                                            <option value="-1" >All collection</option>
                                            <c:forEach items="${allSliderCollections}" var="collection" >
                                                <option value="${collection.sliderCollectionID}" ${scid == collection.sliderCollectionID ? "selected" : ""}>${collection.name}</option>
                                            </c:forEach>
                                        </select>
                                        Status: 
                                        <select name="display" class="select-tag" onchange="this.form.submit()">
                                            <option value="-1" >All Status</option>
                                            <option value="false" ${ display == "false" ? "selected" : ""}>Hidden</option>
                                            <option value="true" ${ display == "true" ? "selected" : ""}>Display</option>                                       
                                        </select>
                                    </form>
                                </div>
                                <table>
                                    <thead>
                                        <tr>
                                            <th>ID<i class="fa-solid fa-sort" onclick="sortTable(0)"></i></th>
                                            <th>Image</th>
                                            <th class="title-slide">Title<i class="fa-solid fa-sort" onclick="sortTable(2)"></i></th>
                                            <th>Slider collection<i class="fa-solid fa-sort" onclick="sortTable(3)"></i></th>
                                            <th class="backlink-slide">Backlink</th>
                                            <th>Display</th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody id="myTable">
                                        <c:forEach items="${requestScope.allSliders}" var="slider">
                                            <tr>
                                                <td>${slider.sliderID}</td>
                                                <td><img class="img-thumbnail-blog" src="../img/${slider.imageUrl}"></td>
                                                <td class="title-slide">${slider.title}</td>
                                                <td>${slider.sliderCollectionID.name}</td>
                                                <td class="backlink-slide">${slider.navigationLink}</td>
                                                <td class="toggle">
                                                    <c:if test="${slider.status == true}"><a id="display-toggle" href="../management/slide-list?id-hide=${slider.sliderID}"><i class="fa-solid fa-toggle-on"></i></a></c:if>
                                                    <c:if test="${slider.status == false}"><a id="display-toggle" href="../management/slide-list?id-show=${slider.sliderID}"><i class="fa-solid fa-toggle-off"></i></a></c:if>
                                                </td>
                                                <td><button class="action-btn first"><i class="fa-solid fa-pencil"></i><a href="../management/slide-detail?id=${slider.sliderID}">Edit</a></button></td>
                                                <td><button class="action-btn second"><i class="fa-solid fa-trash-can"></i><a href="../management/slide-list?id-delete=${slider.sliderID}" onclick="return confirm('Are you sure you want to delete this slide?');">Delete</a></button></td>
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
        </div>

        <!-- Bootstrap JavaScript -->    
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

    </body>

</html>