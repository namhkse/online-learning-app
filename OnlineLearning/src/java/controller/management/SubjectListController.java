package controller.management;

import dao.SubjectCategoryDAO;
import dao.SubjectDAO;
import dao.SubjectMainCategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Subject;
import model.SubjectCategory;
import model.SubjectMainCategory;

@WebServlet(name = "SubjectListController", urlPatterns = {"/management/subject-list"})
public class SubjectListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        Account account = (Account) request.getSession().getAttribute("account");
        SubjectDAO subjectDAO = new SubjectDAO();
        SubjectCategoryDAO subjectCategoryDAO = new SubjectCategoryDAO();
        SubjectMainCategoryDAO subjectMainCategoryDAO = new SubjectMainCategoryDAO();

        ArrayList<Subject> subjects = subjectDAO.getAllSubjects();
        ArrayList<Integer> numberCourse = subjectDAO.countCourseForAllSubject(subjects);
        ArrayList<SubjectCategory> subjectCategories = subjectCategoryDAO.getAllSubjectCategory();
        ArrayList<SubjectMainCategory> subjectMainCategories = subjectMainCategoryDAO.getAllSubjectMainCategories();
        ArrayList<Subject> listSubjectCanAccess = subjectDAO.getListSubjectCanAccess(account.getAccountID());

        request.setAttribute("subjects", subjects);
        request.setAttribute("listSubjectCanAccess", listSubjectCanAccess);
        request.setAttribute("subjectCategories", subjectCategories);
        request.setAttribute("numberCourse", numberCourse);
        request.setAttribute("subjectMainCategories", subjectMainCategories);

        request.getRequestDispatcher("/view/subject-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        SubjectDAO subjectDAO = new SubjectDAO();
        Account account = (Account) request.getSession().getAttribute("account");
        ArrayList<Subject> listSubjectCanAccess = subjectDAO.getListSubjectCanAccess(account.getAccountID());

        String categoryID = "";
        String mainCategoryID = "";
        String[] categoryIDs = null;
        String[] mainCategoryIDs = null;
        if (request.getParameter("categoryID").length() != 0) {
            categoryID = request.getParameter("categoryID");
            categoryIDs = categoryID.split(",");
        }
        if (request.getParameter("mainCategoryID").length() != 0) {
            mainCategoryID = request.getParameter("mainCategoryID");
            mainCategoryIDs = mainCategoryID.split(",");
        }
        String status = request.getParameter("status");
        if (status.equals("-1")) {
            status = null;
        }

        ArrayList<Subject> subjects = subjectDAO.getSubjectsByCategoryAndStatus(categoryIDs, mainCategoryIDs, status);
        ArrayList<Integer> numberCourse = subjectDAO.countCourseForAllSubject(subjects);

        for (int i = 0; i < subjects.size(); i++) {
            Subject get = subjects.get(i);
            response.getWriter().write("<tr>\n"
                    + "                                                <td>" + get.getSubjectId() + "</td>\n"
                    + "                                                <td><img class=\"img-thumbnail-blog\" src=\"../img/" + get.getImage() + "\"></td>\n"
                    + "                                                <td>" + get.getName() + "</td>\n"
                    + "                                                <td>");
            if (get.getCategoryID().getName() != null) {
                response.getWriter().write(get.getCategoryID().getName());
            } else {
                response.getWriter().write(get.getMainCategoryID().getName());
            }
            response.getWriter().write("</td>\n"
                    + "                                                <td>" + numberCourse.get(i) + "</td>\n"
                    + "                                                <td><a class=\"text-primary courses\" href=\"../management/course-list?Sid=" + get.getSubjectId() + "\">Courses</a></td>\n"
                    + "                                                <td>" + get.getOwnerID().getFirstName() + " " + get.getOwnerID().getLastName() + "</td>");
            if (get.isStatus() == true) {
                response.getWriter().write("<td>Published</td>");
            } else {
                response.getWriter().write("<td>Unpublished</td>");
            }
            response.getWriter().write("<td>\n");
            if (account.getRole().getId() == 4) {
                response.getWriter().write("                                                    <div id=\"action\">\n"
                        + "                                                        <a class=\"text-primary\" href=\"../management/subject-view?subjectID=" + get.getSubjectId() + "\">View</a>&nbsp;/&nbsp;"
                        + "                                                        <a class=\"text-primary\" href=\"../management/subject-detail?subjectID=" + get.getSubjectId() + "\">Edit</a>&nbsp;/&nbsp;"
                        + "                                                        <button class=\"text-danger\" onclick=\"deleteSubject(" + get.getSubjectId() + ", this)\">Delete</button>"
                        + "                                                    </div>\n");
            } else {
                for (int j = 0; j < listSubjectCanAccess.size(); j++) {
                    Subject get1 = listSubjectCanAccess.get(j);
                    if (get1.getSubjectId() == get.getSubjectId()) {
                        response.getWriter().write("                                                    <div id=\"action\">\n"
                                + "                                                        <a class=\"text-primary\" href=\"../management/subject-view?subjectID=" + get.getSubjectId() + "\">View</a>&nbsp;/&nbsp;"
                                + "                                                        <a class=\"text-primary\" href=\"../management/subject-detail?subjectID=" + get.getSubjectId() + "\">Edit</a>&nbsp;/&nbsp;"
                                + "                                                        <button class=\"text-danger\" onclick=\"deleteSubject(" + get.getSubjectId() + ", this)\">Delete</button>"
                                + "                                                    </div>\n");
                    }
                }
            }
            response.getWriter().write("                                                </td>\n"
                    + "                                            </tr>");
        }

        response.getWriter().flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("subjectID"));

        SubjectDAO subjectDAO = new SubjectDAO();
        subjectDAO.deleteSubject(id);

        response.setStatus(200);
        response.flushBuffer();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
