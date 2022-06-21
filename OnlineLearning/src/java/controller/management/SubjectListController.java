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
import model.Subject;
import model.SubjectCategory;
import model.SubjectMainCategory;

@WebServlet(name = "SubjectListController", urlPatterns = {"/management/subject-list"})
public class SubjectListController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubjectDAO subjectDAO = new SubjectDAO();
        SubjectCategoryDAO subjectCategoryDAO = new SubjectCategoryDAO();
        SubjectMainCategoryDAO subjectMainCategoryDAO = new SubjectMainCategoryDAO();
        
        String[] categoryIDs = null;
        ArrayList<Integer> categoryIDsInt = new ArrayList<>();
        String status = null;
        if (request.getParameterValues("categoryID") != null) {
            categoryIDs = request.getParameterValues("categoryID");
            for (int i = 0; i < categoryIDs.length; i++) {
                String categoryID = categoryIDs[i];
                categoryIDsInt.add(Integer.parseInt(categoryID));
            }
        }
        if (request.getParameter("status") != null) {
            if (request.getParameter("status").equals("-1")) {
                status = null;
            } else {
                status = request.getParameter("status");
            }
        }
        
        ArrayList<Subject> subjects = subjectDAO.getSubjectsByCategoryAndStatus(categoryIDs, status);
        ArrayList<Integer> numberCourse = subjectDAO.countCourseForAllSubject(subjects);
        ArrayList<SubjectCategory> subjectCategories = subjectCategoryDAO.getAllSubjectCategory();
        ArrayList<SubjectMainCategory> subjectMainCategories = subjectMainCategoryDAO.getAllSubjectMainCategories();
        
        request.setAttribute("display", status);
        request.setAttribute("categoryIDsInt", categoryIDsInt);
        request.setAttribute("subjects", subjects);
        request.setAttribute("subjectCategories", subjectCategories);
        request.setAttribute("numberCourse", numberCourse);
        request.setAttribute("subjectMainCategories", subjectMainCategories);
        
        request.getRequestDispatcher("/view/subject-list.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubjectDAO subjectDAO = new SubjectDAO();
        
        String categoryID = "";
        String[] categoryIDs = null;
        if (request.getParameter("categoryID").length() != 0) {
            categoryID = request.getParameter("categoryID");
            categoryIDs = categoryID.split(",");
        }
        String status = request.getParameter("status");
        if (status.equals("-1")) {
            status = null;
        }
        
        ArrayList<Subject> subjects = subjectDAO.getSubjectsByCategoryAndStatus(categoryIDs, status);
        ArrayList<Integer> numberCourse = subjectDAO.countCourseForAllSubject(subjects);
        
        for (int i = 0; i < subjects.size(); i++) {
            Subject get = subjects.get(i);
            response.getWriter().write("<tr>\n"
                    + "                                                <td>" + get.getSubjectId() + "</td>\n"
                    + "                                                <td><img class=\"img-thumbnail-blog\" src=\"../img/" + get.getImage() + "\"></td>\n"
                    + "                                                <td>" + get.getName() + "</td>\n"
                    + "                                                <td>" + get.getCategoryID().getName() + "</td>\n"
                    + "                                                <td>" + numberCourse.get(i) + "</td>\n"
                    + "                                                <td>" + get.getOwnerID().getFirstName() + " " + get.getOwnerID().getLastName() + "</td>");
            if (get.isStatus() == true) {
                response.getWriter().write("<td>Published</td>");
            } else {
                response.getWriter().write("<td>Unpublished</td>");
            }
            response.getWriter().write("<td><button class=\"action-btn first\"><i class=\"fa-solid fa-eye\"></i><a href=\"../management/slide-view?id=${slider.sliderID}\">View</a></button></td>\n"
                    + "                                                <td><button class=\"action-btn second\"><i class=\"fa-solid fa-pencil\"></i><a href=\"../management/slide-edit?id=${slider.sliderID}\">Edit</a></button></td>\n"
                    + "                                                <td><button class=\"action-btn third\"><i class=\"fa-solid fa-trash-can\"></i><a href=\"../management/slide-list?id-delete=${slider.sliderID}\" onclick=\"return confirm('Are you sure you want to delete this slide?');\">Delete</a></button></td>\n"
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
