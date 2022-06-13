package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Lesson;
import model.QuizLesson;
import util.SessionUtil;

@WebServlet(name = "TakeQuizController", urlPatterns = {"/quiz"})
public class TakeQuizController extends HttpServlet {

    private LocalDateTime startTimeTakeQuiz(HttpServletRequest req, Account account, QuizLesson quiz) {
        HttpSession session = req.getSession();
        LocalDateTime time = (LocalDateTime) session.getAttribute("quiz." + quiz.getId());
        if (time == null) {
            time = LocalDateTime.now();
            session.setAttribute("quiz." + quiz.getId(), time);
        }
        return time;
    }

    private void start(HttpServletRequest req, QuizLesson quiz) {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Account account = SessionUtil.getAccount(req);
        /* Sample Data */
        QuizLesson quiz = new QuizLesson();
        quiz.setId(Integer.parseInt(req.getParameter("id")));

        LocalDateTime time = startTimeTakeQuiz(req, account, quiz);
        req.setAttribute("startTime", time);
        req.getRequestDispatcher("./view/question.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
