<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="./base-view/baseTagAdmin.jsp"></jsp:include>
        
        <title>Management Account</title>
        
        <link rel="stylesheet" href="../css/account-list.css">      
        
    </head>
    <body>
        <div class="display-flex min-height-100vh">
            
            <jsp:include page="./base-view/headerAdmin.jsp"></jsp:include>
            
            <div class="width85">
                
                <jsp:include page="./base-view/dropDownAdmin.jsp"></jsp:include>

                <!-- Container Start -->

                <div class="container">
                    <h1 class="container-title">Account</h1>
                    <div class="container-table">
                        <div class="table-header">Manager account</div>
                        <div class="table-content">
                            <div class="search">
                                <label for="search">Search: </label>
                                <input type="text" id="search" name="search">
                            </div>
                            <table>
                                <thead>
                                    <tr>
                                        <th>First name<i class="fa-solid fa-sort" onclick="sortTable(0)"></i></th>
                                        <th>Last name<i class="fa-solid fa-sort" onclick="sortTable(1)"></i></th>
                                        <th>Email<i class="fa-solid fa-sort" onclick="sortTable(2)"></i></th>
                                        <th>Role<i class="fa-solid fa-sort" onclick="sortTable(3)"></i></th>
                                        <th>Created time<i class="fa-solid fa-sort" onclick="sortTable(4)"></i></th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody id="myTable">
                                    <c:forEach items="${accounts}" var="acc">
                                        <tr>
                                            <td>${acc.firstName}</td>
                                            <td>${acc.lastName}</td>
                                            <td>${acc.email}</td>
                                            <td>${acc.role.name}</td>
                                            <td>${acc.createdTime}</td>
                                            <td><a href="#">Edit</a></td>                         
                                            <td><a href="#">Delete</a></td>

                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="table-paging">
                                <ul class="pagging-list">
                                    <li class="previous">
                                        <a href="#">Previous</a>
                                    </li>
                                    <li class="pagging-active"><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li class="next">
                                        <a href="#">Next</a>
                                    </li>
                                </ul>
                            </div>                   
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
