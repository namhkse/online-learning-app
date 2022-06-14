<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Manage Setting</title>
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
        <link rel="stylesheet" href="https://unpkg.com/flowbite@1.4.7/dist/flowbite.min.css" />
        <script src="https://unpkg.com/flowbite@1.4.7/dist/flowbite.js"></script>
        <script src="../js/dashboard.js"></script>
        <script src="../js/table.js"></script>
    </head>

    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-2 min-vh-100 bg-dark p-0">
                    <jsp:include page="sidenav.jsp?page=Setting"/>
                </div>
                <div class="col-sm-10 p-0">
                    <jsp:include page="navbar-header.jsp?page=Setting"/>

                    <div class="container">
                        <div class="container-table">
                            <div class="table-content">
                                <div class="search">
                                    <a class="margin-auto-0" id="add-blog" href="setting-insert" ><i class="fa-solid fa-plus"></i> Add Setting</a>

                                    <form action="setting" method="GET"> 
                                        <select name="cid" class="select-tag">
                                            <option value="-1" >Select Type</option>
                                            <c:forEach items="${allSetting_Type}" var="ctype" >
                                                <option value="${ctype.type}" ${cid == ctype.type ? "selected" : ""}>${ctype.type}</option>
                                            </c:forEach>
                                        </select>
                                        <select name="status" class="select-tag">
                                            <option value="-1" >Select Status</option>
                                            <option value="false" ${ status == "false" ? "selected" : ""}>Deactive</option>
                                            <option value="true" ${ status == "true" ? "selected" : ""}>Active</option>                                       
                                        </select>

                                        <button type="submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">Filter</button>
                                    </form>  
                                    <div>
                                        <form action="setting" method="GET">
                                            <input type="text" id="search" name="search" placeholder="Enter value">
                                            <button type="submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">Search</button>
                                        </form>
                                    </div>
                                </div>
                                <table>
                                    <thead>
                                        <tr>
                                            <th>ID<i class="fa-solid fa-sort" onclick="sortTable(0)"></i></th>
                                            <th class="width350">TYPE<i class="fa-solid fa-sort" onclick="sortTable(1)"></i></th>
                                            <th>VALUE<i class="fa-solid fa-sort" onclick="sortTable(2)"></i></th>
                                            <th>ORDER<i class="fa-solid fa-sort" onclick="sortTable(3)"></i></th>
                                            <th>STATUS<i class="fa-solid fa-sort" onclick="sortTable(4)"></i></th>
                                            <th>ACTION</th>
                                        </tr>
                                    </thead>
                                    <tbody id="myTable">
                                        <c:forEach items="${listSetting}" var="setting">
                                            <tr>
                                                <td>${setting.id}</td>
                                                <td>${setting.type}</td>
                                                <td>${setting.name}</td>
                                                <td>${setting.order}</td>
                                                <td>${setting.status ? 'Active' : 'Deactive'}</td>
                                                <td><button type="button" class="focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2"><a href="setting-detail?SettingID=${setting.settingID}">Edit</a></button>
                                                    <button type="button" class="focus:outline-none text-white bg-red-700 hover:bg-red-800 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2"><a href="#" onclick="deleteSetting(${setting.settingID},${setting.id}, '${setting.type}')">Delete</a></button></td>
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

            <!-- Bootstrap JavaScript -->    
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    </body>

</html>