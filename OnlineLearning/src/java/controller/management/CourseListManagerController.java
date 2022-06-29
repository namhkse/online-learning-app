/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.management;

import dao.CourseDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Course;
import model.Subject;

/**
 *
 * @author FPTSHOP
 */
@WebServlet(name = "CourseListManagerController", urlPatterns = {"/management/course-list"})
public class CourseListManagerController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/view/course-list-management.jsp").forward(request, response);
    }
    
    private static void getSubject(HttpServletRequest request, HttpServletResponse response){
        int subjectID = Integer.parseInt(request.getParameter("Sid"));
        Subject subject = new SubjectDAO().getSubjectByID(subjectID);
        request.setAttribute("subject", subject);
    }
    
    private static void getAllCourseBySid(HttpServletRequest request, HttpServletResponse response){
        int subjectID = Integer.parseInt(request.getParameter("Sid"));
        
        ArrayList<Course> clist = new CourseDAO().getAllCourseBySubjectID(subjectID, -1);
        
        request.setAttribute("CourseList", clist);
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getSubject(request, response);
        getAllCourseBySid(request, response);
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        insertTableByStatus(request, response);
    }
    
    private static void insertTableByStatus(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int status = Integer.parseInt(request.getParameter("status"));
        int subjectID = Integer.parseInt(request.getParameter("subjectid"));
        ArrayList<Course> clist = new ArrayList<>();
        
        if(status == 1){
            clist = new CourseDAO().getAllCourseBySubjectID(subjectID, 1);
        } else if(status == 0) {
            clist = new CourseDAO().getAllCourseBySubjectID(subjectID, 0);
        } else {
            clist = new CourseDAO().getAllCourseBySubjectID(subjectID, -1);
        }
        
        PrintWriter out = response.getWriter();
        for (Course course : clist) {
            out.print("                     <tr>\n" +
"                                                <td>"+course.getCourseId()+"</td>\n" +
"                                                <td>"+course.getName()+"</td>\n" +
"                                                <td>"+course.getPrice()+"</td>\n" +
"                                                <td><img class=\"img-thumbnail-blog\" src=\"../img"+course.getInstructorId().getProfilePictureUrl()+"\"></td>\n" +
"                                                <td>"+course.getInstructorId().getFirstName()+" "+course.getInstructorId().getLastName()+"</td>\n" +
"                                                <td>"+course.getInstructorId().getEmail()+"</td>\n" +
"                                                <td>"+(course.isStatus()==true?"Published":"Unpublished")+"</td>\n" +
"                                                <td><a href=\"../management/slide-view?id=${slider.sliderID}\">Lesson</a></td>\n" +
"                                                <td class=\"context\"><a href=\"../management/slide-view?id=${slider.sliderID}\"class=\"text-primary\">View</a> /\n" +
"                                                <a href=\"../management/slide-edit?id=${slider.sliderID}\"class=\"text-primary\">Edit</a> /\n" +
"                                                <a href=\"#\" onclick=\"deleteSubject(${subject.subjectId}, this)\"class=\"text-danger\">Delete</a></td>\n"+
"                                            </tr>");
        }
    }

    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        new CourseDAO().deleteCourse(Integer.parseInt(request.getParameter("Cid")));
    }
            
            
            
            
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
