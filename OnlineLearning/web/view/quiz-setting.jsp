<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Manage Quiz</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <!--Font Awesome-->
        <script src="https://kit.fontawesome.com/7b806b5ab9.js" crossorigin="anonymous"></script>
        <!--Jquery-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <link href="../css/setting.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
        <script src="../js/setting.js" type="text/javascript"></script>
        <!--<link rel="stylesheet" href="https://unpkg.com/flowbite@1.4.7/dist/flowbite.min.css" />-->
        <!--<script src="https://unpkg.com/flowbite@1.4.7/dist/flowbite.js"></script>-->
        <script src="../js/dashboard.js"></script>
        <script src="../js/table.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css">
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.js"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
    </head>

    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-2 min-vh-100 bg-dark p-0">
                    <jsp:include page="sidenav.jsp?page=Quiz"/>
                </div>
                <div class="col-sm-10 p-0">
                    <jsp:include page="navbar-header.jsp?page=Quiz"/>

                    <div class="container">
                        <div class="container-table">
                            <div class="table-content">
                                <div class="search">

                                    <form class="form-search" action="quizsetting" method="GET"> 
                                        <a  style="background-color: #365dcd;
                                            color: #fff;
                                            text-decoration: none;
                                            padding: 12px 12px; margin-right: 10px;
                                            border-radius: 10px ;" href="quizinsert" ><i class="fa-solid fa-plus"></i> Add Quiz</a>
                                        <select name="id" class="select-tag">
                                            <option value="-1" >Select Subject</option>
                                            <c:forEach items="${allSubjectName}" var="ctype" >
                                                <option value="${ctype.subjectId}" ${id == ctype.subjectId ? "selected" : ""}>${ctype.name}</option>
                                            </c:forEach>
                                        </select>
                                        <select name="type" class="select-tag">
                                            <option value="-1" >Select Type</option>
                                            <c:forEach items="${allQuizType}" var="ctype" >
                                                <option value="${ctype.type}" ${cid == ctype.type ? "selected" : ""}>${ctype.type}</option>
                                            </c:forEach>
                                        </select>

                                        <button
                                            style="background-color: #365dcd;
                                            text-decoration: none;
                                            padding: 12px 8px;
                                            margin-right: 8px;
                                            border-radius: 10px;
                                            border: 0;"
                                            type="submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">Filter</button>
                                    </form>  
                                </div>
                                <div class="align-content-center">
                                    <table id="table" class="table table-striped text-center" border="0">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Name</th>
                                                <th>Subject</th>
                                                <th>Level</th>
                                                <th>Total question</th>
                                                <th>Duration</th>
                                                <th>Pass rate</th>
                                                <th>Type</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody id="myTable">
                                            <c:forEach items="${listQuizLesson}" var="quiz">
                                                <tr>
                                                    <td>${quiz.quizID}</td>
                                                    <td>${quiz.name}</td>
                                                    <td>${quiz.subjectName}</td>
                                                    <td>${quiz.level}</td>
                                                    <td>${quiz.totalQues}</td>
                                                    <td>${quiz.examTimeInMinute} minutes</td>
                                                    <td>${quiz.passScore} mark</td>
                                                    <td>${quiz.type}</td>
                                                    <td><a class="text-primary" href="quizsettingdetail?id=${quiz.quizID}">View Detail</a></td>
                                                </tr>
                                            </c:forEach>    
                                        </tbody>
                                    </table>
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