/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.management;

import dao.QuizDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.QuizLesson;
import model.Subject;

/**
 *
 * @author duc21
 */
@WebServlet(name = "QuizInsertController", urlPatterns = {"/management/quizinsert"})
public class QuizInsertController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubjectDAO SubjectDAO = new SubjectDAO();
        ArrayList<Subject> allSubjectName = SubjectDAO.getAllSubjectName();

        request.setAttribute("allSubjectName", allSubjectName);
        request.getRequestDispatcher("../view/quiz-insert.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuizDAO QuizDAO = new QuizDAO();
        String name = request.getParameter("name");
        int subjectID = Integer.parseInt(request.getParameter("subject"));
        String level = request.getParameter("level");
        int totalques = Integer.parseInt(request.getParameter("totalques"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        int passrate = Integer.parseInt(request.getParameter("passrate"));
        String type = request.getParameter("type");
        QuizLesson quizLessonLast = QuizDAO.getQuizLessonLast();
        
        QuizLesson QuizLesson = new QuizLesson();
        System.out.println("gfhjrnhg" + quizLessonLast.getQuizID()+1);
        QuizLesson.setLessonID(quizLessonLast.getQuizID()+1);
        QuizLesson.setName(name);
        QuizLesson.setSubjectID(subjectID);
        QuizLesson.setLevel(level);
        QuizLesson.setPassScore(passrate);
        QuizLesson.setExamTimeInMinute(duration);
        QuizLesson.setType(type);
        QuizLesson.setTotalQues(totalques);
        
        QuizDAO.insertQuizLesson(QuizLesson);
        response.sendRedirect("../management/quizsetting");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
