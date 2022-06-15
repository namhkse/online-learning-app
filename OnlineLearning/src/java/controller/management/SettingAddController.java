package controller.management;

import dao.BlogCategoryDAO;
import dao.LessonTypeDAO;
import dao.QuestionLevelDAO;
import dao.RoleDAO;
import dao.SettingDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BlogCategory;
import model.LessonType;
import model.QuestionLevel;
import model.Role;
import model.Setting;
import model.Subject;

@WebServlet(name = "SettingAddController", urlPatterns = {"/management/setting-insert"})
public class SettingAddController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SettingDAO settingDAO = new SettingDAO();
        ArrayList<Setting> allSettings_DistinctType = settingDAO.getAllSettings_DistinctType();
        request.setAttribute("allSettings_DistinctType", allSettings_DistinctType);
        request.getRequestDispatcher("../view/setting-insert.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SettingDAO settingDAO = new SettingDAO();
        String type = request.getParameter("type");
        String name = request.getParameter("value");
        int order = Integer.parseInt(request.getParameter("order"));
        String status_raw = request.getParameter("status");
        Boolean status;
        if (status_raw.equalsIgnoreCase("active")) {
            status = true;
        } else {
            status = false;
        }
        Setting setting = new Setting();
        setting.setName(name);
        setting.setType(type);
        setting.setStatus(status);
        setting.setOrder(order);

        if (type.equalsIgnoreCase("USER_ROLE")) {
            Role role = new Role();
            role.setName(name);
            role.setOrder(order);
            role.setStatus(status);
            role.setType(type);
            RoleDAO RoleDAO = new RoleDAO();
            Role roleLast = RoleDAO.getRoleLast();
            RoleDAO.insertRole(role);
            setting.setId(roleLast.getId() + 1);
            settingDAO.insertSetting(setting);
        }
        if (type.equalsIgnoreCase("CATEGORY_SUBJECT")) {
            Subject subject = new Subject();
            subject.setName(name);
            subject.setOrder(order);
            subject.setStatus(status);
            subject.setType(type);
            SubjectDAO subjectDAO = new SubjectDAO();
            Subject subjectLast = subjectDAO.getSubjectLast();
            subjectDAO.insertSubject(subject);
            setting.setId(subjectLast.getSubjectId() + 1);
            settingDAO.insertSetting(setting);
        }
        if (type.equalsIgnoreCase("LEVEL_QUESTION")) {
            QuestionLevel questionLevel = new QuestionLevel();
            questionLevel.setLevelName(name);
            questionLevel.setOrder(order);
            questionLevel.setStatus(status);
            questionLevel.setType(type);
            QuestionLevelDAO questionLevelDAO = new QuestionLevelDAO();
            QuestionLevel QuestionLevelLast = questionLevelDAO.getQuestionLevelLast();
            questionLevelDAO.insertQuestionLevel(questionLevel);
            setting.setId(QuestionLevelLast.getId() + 1);
            settingDAO.insertSetting(setting);
        }
        if (type.equalsIgnoreCase("TYPE_LESSON")) {
            LessonType lessonType = new LessonType();
            lessonType.setName(name);
            lessonType.setOrder(order);
            lessonType.setStatus(status);
            lessonType.setType(type);
            LessonTypeDAO lessonTypeDAO = new LessonTypeDAO();
            LessonType LessonTypeLast = lessonTypeDAO.getLessonTypeLast();
            lessonTypeDAO.insertLessonType(lessonType);
            setting.setId(LessonTypeLast.getLessonTypeID() + 1);
            settingDAO.insertSetting(setting);
        }
        if (type.equalsIgnoreCase("CATEGORY_POST")) {
            BlogCategory blogCategory = new BlogCategory();
            blogCategory.setName(name);
            blogCategory.setOrder(order);
            blogCategory.setStatus(status);
            blogCategory.setType(type);
            BlogCategoryDAO blogCategoryDAO = new BlogCategoryDAO();
            BlogCategory BlogCategoryLast = blogCategoryDAO.getBlogCategoryLast();
            blogCategoryDAO.insertBlogCategory(blogCategory);
            setting.setId(BlogCategoryLast.getBlogCategoryID() + 1);
            settingDAO.insertSetting(setting);
        }
        response.sendRedirect("../management/setting");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
