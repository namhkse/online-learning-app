package controller.quiz;

import dao.LessonDAO;
import dao.QuestionDAO;
import dao.QuizDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Lesson;
import model.QuizLesson;

@WebServlet(name = "QuizzLessonController", urlPatterns = {"/quizz-lesson"})
public class QuizzLessonController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int lessonID = Integer.parseInt(request.getParameter("lessonID"));
        QuestionDAO questionDBContext = new QuestionDAO();
        QuizDAO quizDBContext = new QuizDAO();
        LessonDAO lessonDBContext = new LessonDAO();

        Lesson lessonByID = lessonDBContext.getLessonByID(lessonID);
        QuizLesson quizLessonByID = quizDBContext.getQuizLessonByID(lessonID);
        int countbyID = questionDBContext.countbyID(lessonID);

        request.setAttribute("lessonByID", lessonByID);
        request.setAttribute("quizLessonByID", quizLessonByID);
        request.setAttribute("countbyID", countbyID);

        request.getRequestDispatcher("/view/quizz-lesson.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
