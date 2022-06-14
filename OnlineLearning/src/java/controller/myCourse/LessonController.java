package controller.myCourse;

import dao.CompletedLessonDAO;
import dao.LessonBeingLearnedDAO;
import dao.LessonDAO;
import dao.QuizLessonDAO;
import dao.QuizSessionDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.SubLessonDAO;
import java.util.Map;
import model.Account;
import model.CompletedLesson;
import model.Lesson;
import model.LessonBeingLearned;
import model.QuizLesson;
import model.SubLesson;

@WebServlet(name = "LessonController", urlPatterns = {"/lesson"})
public class LessonController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");

        LessonDAO lessonDao = new LessonDAO();
        int courseID = Integer.parseInt(request.getParameter("id"));

        int accountID = account.getAccountID();
        int numTotalLesson = lessonDao.getNumberLessonInCourse(courseID);
        int numLearningLesson = lessonDao.getNumberLessonLearningInCourse(accountID, courseID);

        String lessonID_raw = request.getParameter("lid");
        int lessonID = -1;
        if (lessonID_raw == null) {
            if (numLearningLesson < numTotalLesson) {
                Lesson lessonLearning = lessonDao.getLessonLearning(accountID, courseID);
                lessonID = lessonLearning.getLessonID();
                request.setAttribute("lessonLearning", lessonLearning);
            } else {
                lessonID = lessonDao.getLastLesson(courseID);
            }
        } else {
            lessonID = Integer.parseInt(lessonID_raw);
            Lesson lessonLearning = lessonDao.getLessonLearning(accountID, courseID);
            request.setAttribute("lessonLearning", lessonLearning);
        }

        LessonBeingLearned lessonBeingLearned
                = new LessonBeingLearnedDAO().getLessonBeingLearned(accountID, lessonID);

        ArrayList<SubLesson> listSubLesson = new SubLessonDAO().getListSubLessonByCourseID(courseID);

        ArrayList<CompletedLesson> listCompleted = new CompletedLessonDAO().getListCompletedLesson(accountID, courseID);

        Lesson lessonCurrent = lessonDao.getLessonPlay(accountID, courseID, lessonID);

        Lesson lessonFirst = lessonDao.getLessonFirstOfCourse(courseID);

        request.setAttribute("courseID", courseID);
        request.setAttribute("lessonFirst", lessonFirst);
        request.setAttribute("lessonCurrent", lessonCurrent);
        request.setAttribute("lessonBeingLearned", lessonBeingLearned);
        request.setAttribute("listSubLesson", listSubLesson);
        request.setAttribute("listCompleted", listCompleted);

        request.getRequestDispatcher("view/lesson.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        int accountID = account.getAccountID();
        int lessonID = Integer.parseInt(request.getParameter("lessonID"));
        int courseID = Integer.parseInt(request.getParameter("courseID"));

        new LessonBeingLearnedDAO().deleteLessonBeing(accountID, lessonID);

        Lesson lessonNext = new LessonDAO().getLessonNext(courseID, lessonID);

        int lessonIDNext = -1;
        if (lessonNext != null) {
            lessonIDNext = lessonNext.getLessonID();
        }
        new LessonDAO().insertCompletedLesson(accountID, lessonID);

        response.setContentType("application/json");
        response.getWriter().write("{\"lessonID\":\"" + lessonID + "\",");
        response.getWriter().write("\"lessonIDNext\":\"" + lessonIDNext + "\"}");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
