<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Dashboard</title>
        <!-- Bootstrap CSS -->
        <link href="../node_modules/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
        <!--Font Awesome-->
        <script src="https://kit.fontawesome.com/7b806b5ab9.js" crossorigin="anonymous"></script>
        <!--JQuery-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

        <style>
            .table-wraper {
                overflow: auto;
                height: 40vh;
            }
        </style>
    </head>

    <body>
        <div class="container-fluid">
            <div class="row">
                <!--Sidebar-->
                <div class="col-1 col-sm-2 bg-dark p-0 collapse-horizontal overflow-auto vh-100" id="navbarTogglerDemo01">
                    <jsp:include page="sidenav.jsp?page=Dashboard"/>
                </div>
                
                <!--Page Content-->
                <div class="col p-0 vh-100 overflow-auto">
                    <jsp:include page="navbar-header.jsp?page=Dashboard"/>

                    <div class="container bg-light">
                        <div class="row">
                            <div class="col-6 col-md-3 mb-3">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title">Accounts</h5>
                                        <div class="row">
                                            <div class="col-10">
                                                <h2 class="mb-0 mr-3 font-weight-bold">+${newAccountInThisMonth} </h2>
                                            </div>
                                            <div class="col-2 fs-4">
                                                <i class="fa-solid fa-user"></i>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer text-white bg-success">
                                        Last Month: ${newAccountInLastMonth}
                                    </div>
                                </div>
                            </div>

                            <div class="col-6 col-md-3 mb-3">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title">Revenue</h5>
                                        <div class="d-flex align-items-center">
                                            <h2 class="mb-0 mr-3 font-weight-bold">
                                                <fmt:setLocale value = "en_US"/>
                                                <fmt:formatNumber value="${revenueThisYear}" type="currency" />
                                            </h2>

                                            <c:if test="${revenueRatio >= 0}">
                                                <span class="font-size-md font-weight-bold text-success ms-2">
                                                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${revenueRatio}" />%
                                                    <i class="fa-solid fa-arrow-trend-up"></i>
                                                </span>
                                            </c:if>
                                            <c:if test="${revenueRatio < 0}">
                                                <span class="font-size-md font-weight-bold ml-3 text-danger ms-2">
                                                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${revenueRatio}" />%
                                                    <i class="fa-solid fa-arrow-trend-down"></i>
                                                </span>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="card-footer text-white  ${revenueRatio < 0 ? "bg-danger" : "bg-info"}">
                                        Compare to last year
                                    </div>
                                </div>
                            </div>

                            <div class="col-6 col-md-3 mb-3">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title">Visit Page</h5>

                                        <div class="row">
                                            <div class="col-10">
                                                <h2 class="mb-0 font-weight-bold">${numberVisitPageToday}</h2>
                                            </div>
                                            <div class="col-2 fs-4">
                                                <i class="fa-solid fa-eye"></i>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer text-white bg-primary">
                                        Lastest
                                    </div>
                                </div>
                            </div>

                            <div class="col-6 col-md-3 mb-1 mb-3">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title">Total Earning</h5>

                                        <div class="row">
                                            <div class="col-10">
                                                <h2 class="mb-0 mr-3 font-weight-bold">
                                                    <fmt:setLocale value = "en_US"/>
                                                    <fmt:formatNumber value="${totalEarning}" type="currency" />
                                                </h2>
                                            </div>
                                            <div class="col-2 fs-4">
                                                <i class="fa-solid fa-chart-simple"></i>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer text-white bg-info">
                                        Lastest
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row m-4 mx-0">
                            <div>
                                <h5 class="card-title">Revenue Statistics</h5>
                                <div class="card p-4">
                                    <canvas id="revenueChart"></canvas>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12 col-md-8">
                                <canvas id="myChart"></canvas>
                            </div>
                            <div class="col-10 col-md-4">
                                <canvas id="subjectChart"></canvas>
                            </div>
                        </div>
                                                    
                        <div class="row mt-4 border rounded m-0 p-2">
                            <div class="col-md-4">
                                <canvas id="blogTrendChart"></canvas>
                            </div>

                            <div class="col-md-8">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="card-title">Most Enrolled Courses</div>
                                        <div class="table-wraper">
                                            <table id="courseEnrollTable" class="table">
                                                <thead>
                                                <th>#Top</th>
                                                <th>Course</th>
                                                <th>Registration</th>
                                                </thead>
                                                <tbody>
                                                    <!--Data goes here-->
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="../js/statistics.js"></script>
    </body>
</html>