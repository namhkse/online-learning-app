package controller.management;

import dao.BlogCategoryDAO;
import dao.LessonTypeDAO;
import dao.QuestionLevelDAO;
import dao.RoleDAO;
import dao.SettingDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SettingDeleteController", urlPatterns = {"/management/setting-delete"})
public class SettingDeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int sid = Integer.parseInt(request.getParameter("sid"));
        int id = Integer.parseInt(request.getParameter("id"));
        String type = request.getParameter("type");
        System.out.println("sid" + sid);
        System.out.println("id" + id);
        System.out.println("sid" + type);

        SettingDAO settingDAO = new SettingDAO();
        settingDAO.deleteSetting(sid);
        if (type.equalsIgnoreCase("USER_ROLE")) {
            RoleDAO roleDAO = new RoleDAO();
            roleDAO.deleteRole(id);
            response.sendRedirect("../management/setting");
        }
        if (type.equalsIgnoreCase("CATEGORY_SUBJECT")) {
            SubjectDAO subjectDAO = new SubjectDAO();
            subjectDAO.deleteSubject(id);
            response.sendRedirect("../management/setting");
        }
        if (type.equalsIgnoreCase("LEVEL_QUESTION")) {
            QuestionLevelDAO questionLevelDAO = new QuestionLevelDAO();
            questionLevelDAO.deleteQuestionLevel(id);
            response.sendRedirect("../management/setting");
        }
        if (type.equalsIgnoreCase("TYPE_LESSON")) {
            LessonTypeDAO lessonTypeDAO = new LessonTypeDAO();
            lessonTypeDAO.deleteLessonType(id);
            response.sendRedirect("../management/setting");
        }
        if (type.equalsIgnoreCase("CATEGORY_POST")) {
            BlogCategoryDAO blogCategoryDAO = new BlogCategoryDAO();
            blogCategoryDAO.deleteBlogCategory(id);
            response.sendRedirect("../management/setting");
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
