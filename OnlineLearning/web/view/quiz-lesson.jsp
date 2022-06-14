<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Account: ${account.id}  QuizID: ${quiz.id}</h1>
        <h2>Start time: ${completedQuizTime.StartTime}</h2>
        <h2>EndTime time: ${completedQuizTime.EndTime}</h2>
        <a href="./quiz?id=${quiz.id}&redo-quiz=true">
            ${empty completedQuizTime.EndTime ? "Do quiz" : "Redo"}
        </a>
    </body>
</html>
