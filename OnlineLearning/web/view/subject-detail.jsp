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
        <link href="../css/subject-detail.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
        <script src="../js/subject-detail.js" type="text/javascript"></script>
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
                        <div class="container-table">
                            <!-- Tab links -->
                            <div class="tab">
                                <button class="tablinks" onclick="openCity(event, 'Overview')">Overview</button>
                                <button class="tablinks active" onclick="openCity(event, 'Dimension')">Dimension</button>
                                <button class="tablinks" onclick="openCity(event, 'Price')">Price Package</button>
                            </div>

                            <!-- Tab content -->
                            <div id="Overview" class="tabcontent">
                                <h3>London</h3>
                                <p>London is the capital city of England.</p>
                            </div>

                            <div id="Dimension" class="tabcontent active">
                                <a class="margin-auto-0" id="add-dimension" href="../management/dimension-detail?subjectID=${subjectID}" ><i class="fa-solid fa-plus"></i> Add Dimension</a>
                                <table>
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Type</th>
                                            <th>Dimension</th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${dimensionsBySubjectID}" var="dimension">
                                            <tr>
                                                <td>${dimension.dimensionID}</td>
                                                <td>${dimension.typeID.name}</td>
                                                <td>${dimension.name}</td>
                                                <td><button class="action-btn first"><i class="fa-solid fa-pencil"></i><a href="../management/dimension-detail?subjectID=${subjectID}&dimensionID=${dimension.dimensionID}">Edit</a></button></td>
                                                <td><button class="action-btn second" onclick="deleteDimension(${dimension.dimensionID}, this)"><i class="fa-solid fa-trash-can"></i> Delete</button></td>

                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                            </div>

                            <div id="Price" class="tabcontent">
                                <h3>Tokyo</h3>
                                <p>Tokyo is the capital of Japan.</p>
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