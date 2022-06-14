package controller;

import dao.QuizSessionDAO;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.QuizLesson;
import model.QuizSession;
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

    private boolean canUserTakeQuiz(Account account, QuizLesson quiz) {
        QuizSessionDAO quizSessionDAO = new QuizSessionDAO();
        LocalDateTime current = LocalDateTime.now();
        QuizSession session = quizSessionDAO.find(account, quiz);

        if (current.isBefore(session.getExpiredTime())) {
            return true;
        }

        return false;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /* Sample Data */
        Account account = SessionUtil.getAccount(req);
        QuizLesson quiz = new QuizLesson();
        quiz.setId(Integer.parseInt(req.getParameter("id")));
        
        if(account == null) {
            resp.sendError(401);
            return;
        }
        
        if (canUserTakeQuiz(account, quiz)) {
            req.getRequestDispatcher("./view/question.jsp").forward(req, resp);
        } else {
            resp.sendError(403, "You can not do this quiz because out of time");
        }

    }
}
