package controller;

import dao.QuizSessionDAO;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.QuizLesson;
import model.Account;

import util.SessionUtil;

@WebServlet(name = "LessonController", urlPatterns = {"/lesson"})
public class LessonController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = SessionUtil.getAccount(req);

        if (account == null) {
            resp.sendError(401);
            return;
        }

        QuizLesson quiz = new QuizLesson();
        quiz.setId(6);

        if ("true".equals(req.getParameter("redo-quiz"))) {
            try {
                new QuizSessionDAO().startQuizTime(account, quiz);
                resp.sendRedirect("./quiz?id=" + quiz.getId());
                return;
            } catch (Exception ex) {
                resp.sendError(500);
                ex.printStackTrace();
            }
        }
        Map completedQuizTime = new QuizSessionDAO().getTimeDoQuiz(account, quiz);

        req.setAttribute("quiz", quiz);
        req.setAttribute("completedQuizTime", completedQuizTime);
        
        req.getRequestDispatcher("./view/quiz-lesson.jsp").forward(req, resp);
    }
}
