package controller.myCourse;

import dao.CourseAccountDAO;
import dao.SubjectCategoryDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.CourseAccount;
import model.Subject;
import model.SubjectCategory;

@WebServlet(name = "MyCourseController", urlPatterns = {"/my-course"})
public class MyCourseController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        Account account = (Account) request.getSession().getAttribute("account");
//        if (account == null) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You need to login first");
//            return;
//        }
//        int accountID = account.getAccountID();
        int accountID = 12;
        ArrayList<CourseAccount> listCourseAccount = new CourseAccountDAO().getListCourseAccount(accountID);
        request.setAttribute("listCourseAccount", listCourseAccount);
        
        ArrayList<SubjectCategory> listSC = new SubjectCategoryDAO().getAllSubjectCategory();
        request.setAttribute("listSC", listSC);
        request.getRequestDispatcher("view/my-course.jsp").forward(request, response);
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
