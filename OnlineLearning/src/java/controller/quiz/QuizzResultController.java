package controller.quiz;

import dao.AnswerDAO;
import dao.CompletedLessonDAO;
import dao.CompletedQuestionDAO;
import dao.QuestionDAO;
import dao.QuizDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Answer;
import model.CompletedLesson;
import model.CompletedQuestion;
import model.Question;
import model.QuizLesson;

/**
 *
 * @author duc21
 */
@WebServlet(name = "QuizzResultController", urlPatterns = {"/quizz-result"})
public class QuizzResultController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        request.getRequestDispatcher("/view/quizz-result.jsp").forward(request, response);
    }

    private void getQuestion(HttpServletRequest request, HttpServletResponse response) {
        int lessonID = Integer.parseInt(request.getParameter("lID"));
        Account acc = (Account) request.getSession().getAttribute("account");
        int AccountID = acc.getAccountID();
        CompletedLessonDAO completedLessonDAO = new CompletedLessonDAO();
        ArrayList<CompletedQuestion> list = new CompletedQuestionDAO().listAllQuizReview(AccountID, lessonID);
        ArrayList<Answer> answerList = new AnswerDAO().listAllAnsByQues(lessonID, list.get(0).getQuestionID().getId());
        ArrayList<CompletedQuestion> total = new CompletedQuestionDAO().listSizeQuizReview(AccountID, lessonID);
        resultColor(request, response, total);
        int count = CountTotalQues(lessonID);
        LocalDate datenow = java.time.LocalDate.now();
        LocalTime timenow = java.time.LocalTime.now();
        float timeDo1Ques = 0;

        CompletedLesson CompletedLesson = completedLessonDAO.CompletedLesson(lessonID);
        Timestamp startTime = CompletedLesson.getStartTime();
        int start = startTime.getHours() * 3600 + startTime.getMinutes() * 60 + startTime.getSeconds();
        Timestamp endTime = CompletedLesson.getEndTime();
        int end = endTime.getHours() * 3600 + endTime.getMinutes() * 60 + endTime.getSeconds();
        int timeDoing = end - start;
        timeDo1Ques = (float) timeDoing / count;
        request.setAttribute("lessonID", lessonID);
        request.setAttribute("AnswerQuiz", answerList);
        request.setAttribute("FirstQuiz", list.get(0));
        request.setAttribute("totoalQuiz", total);
        request.setAttribute("count", count);
        request.setAttribute("datenow", datenow);
        request.setAttribute("timenow", timenow);
        request.setAttribute("timeDoing", timeDoing);
        request.setAttribute("timeDo1Ques", timeDo1Ques);

    }

    private int CountTotalQues(int lessonID) {
        QuestionDAO questionDAO = new QuestionDAO();
        int count = questionDAO.count(lessonID);
        return count;
    }

    private void resultColor(HttpServletRequest request, HttpServletResponse response, ArrayList<CompletedQuestion> list) {
        int lessonID = Integer.parseInt(request.getParameter("lID"));
        float total = 0;
        float aver = 10 / (float) (list.size());
        float mul = 0;
        int size = 0;
        int countunanswer = 0;
        QuizDAO quizDAO = new QuizDAO();
        QuizLesson quizLesson = quizDAO.getQuizLessonByID(lessonID);
        for (int i = 0; i < list.size(); i++) {
            ArrayList<Answer> ans = new AnswerDAO().listquestionbyQuestionID(lessonID, list.get(i).getQuestionID().getId());
            ArrayList<CompletedQuestion> comp = new CompletedQuestionDAO().getSelectedAnswerByQuestion(12, list.get(i).getQuestionID().getId());
            if (ans.size() > 1) {
                int count = 0;
                mul = aver / (float) ans.size();
                if (comp.size() == ans.size()) {
                    for (int j = 0; j < ans.size(); j++) {
                        System.out.println("ans: " + ans.get(j).getAnswerID() + ", comp: " + comp.get(j).getSelectedAnswerID());
                        if (ans.get(j).getAnswerID() == comp.get(j).getSelectedAnswerID()) {
                            total += mul;
                            count++;
                        }
                    }
                }
                if (count == ans.size()) {
                    size++;
                }
            } else {
                if (ans.get(0).getAnswerID() == comp.get(0).getSelectedAnswerID() && comp.get(0).getSelectedAnswerID() != 0) {
                    total += aver;
                    size++;
                }
            }
        }
        System.out.println("total =" + total);
        total = total * 10;
        String formattedString = String.format("%.02f", total);
        Boolean checkPass;
        if (size >= quizLesson.getPassScore()) {
            checkPass = true;
        } else {
            checkPass = false;
        }
        request.setAttribute("total", formattedString);
        request.setAttribute("size", size);
        request.setAttribute("checkPass", checkPass);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getQuestion(request, response);
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
