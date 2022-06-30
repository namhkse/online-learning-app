package controller.myCourse;

import dao.CourseDAO;
import dao.LessonDAO;
import dao.PricePackageDAO;
import dao.SubLessonDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Course;
import model.CoursePricePackage;
import model.SubLesson;

@WebServlet(name = "CourseDetailController", urlPatterns = {"/course-detail"})
public class CourseDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        try {
            int accountID = 0;
            int courseId = Integer.parseInt(request.getParameter("id"));
            if (account != null) {
                accountID = account.getAccountID();
            }

            Course course = new CourseDAO().getCourseByCourseId(courseId);
            request.setAttribute("course", course);
            
            boolean isCourseRegister = new CourseDAO().isRegister(accountID, courseId);
            request.setAttribute("isRegister", isCourseRegister);
            
            int numberLesson = new LessonDAO().getTotalLessonInCourse(courseId);
            request.setAttribute("numberLesson", numberLesson);
            
            int numberQuiz = new LessonDAO().getTotalQuizInCourse(courseId);
            request.setAttribute("numberQuiz", numberQuiz);
            
            ArrayList<SubLesson> listSubLesson = new SubLessonDAO().getListSubLessonByCourseID(courseId);
            request.setAttribute("listSubLesson", listSubLesson);
            
            ArrayList<Course> listTopFeatureCourse = new CourseDAO().getTopFeatureCourse(courseId);
            request.setAttribute("listTopFeatureCourse", listTopFeatureCourse);
            
            ArrayList<CoursePricePackage> listPrice = new PricePackageDAO().getListPricePackageOfCourse(courseId);
            request.setAttribute("listPrice", listPrice);
            
            request.getRequestDispatcher("view/course-detail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(404);
        }

       
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
