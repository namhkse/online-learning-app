package controller;

import dao.QuizLessonDAO;
import dao.QuizSessionDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.QuizLesson;
import util.SessionUtil;

@WebServlet(name = "QuizController", urlPatterns = {"/quiz"})
public class QuizController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quizId = Integer.parseInt(request.getParameter("id"));
        Account account = SessionUtil.getAccount(request);

        if (account == null) {
            response.sendError(401);
            return;
        }

        QuizLesson quiz = new QuizLessonDAO().findById(quizId);

        if ("true".equals(request.getParameter("redo-quiz"))) {
            try {
                new QuizSessionDAO().startQuizTime(account, quiz);
                response.sendRedirect("./takequiz?id=" + quiz.getId());
            } catch (IOException | SQLException ex) {
                response.sendError(500);
                ex.printStackTrace();
            }
        } else {
            Map completedQuizTime = new QuizSessionDAO().getTimeDoQuiz(account, quiz);
            request.setAttribute("quiz", quiz);
            request.setAttribute("completedQuizTime", completedQuizTime);
            request.getRequestDispatcher("./view/quiz-lesson.jsp").forward(request, response);
        }
    }
}
