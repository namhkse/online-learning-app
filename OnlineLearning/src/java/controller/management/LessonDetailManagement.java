/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.management;

import dao.CourseDAO;
import dao.LessonDAO;
import dao.LessonTypeDAO;
import dao.SubLessonDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Course;
import model.Lesson;
import model.LessonType;
import model.SubLesson;

/**
 *
 * @author FPTSHOP
 */
@WebServlet(name = "LessonDetailManagement", urlPatterns = {"/management/lesson-detail"})
public class LessonDetailManagement extends HttpServlet {

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
        request.getRequestDispatcher("/view/lesson-detail-management.jsp").forward(request, response);
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
        if (request.getParameter("type") == null && request.getParameter("Lid") == null) {
            showLtypeLsub(request, response);
            request.getRequestDispatcher("/view/addedit-lesson-detail.jsp").forward(request, response);
        }
        if (request.getParameter("type") == null && request.getParameter("Lid") != null) {
            showLessonDetail(request, response);
            request.getRequestDispatcher("/view/addedit-lesson-detail.jsp").forward(request, response);
        }
        if (request.getParameter("type").equalsIgnoreCase("view")) {
            showLessonDetail(request, response);
            processRequest(request, response);
        }
    }

    private static void showLessonDetail(HttpServletRequest request, HttpServletResponse response) {
        int lessonId = Integer.parseInt(request.getParameter("Lid"));
        Lesson lesson = new LessonDAO().getAllLessonByID(lessonId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        ArrayList<LessonType> ltList = new LessonTypeDAO().getAllLessonTypes();
        ArrayList<SubLesson> lsList = new SubLessonDAO().getListSubLessonByCourseID(lesson.getCourseID().getCourseId());
        ArrayList<Lesson> order = new LessonDAO().getOrderBySublesson(lesson.getSubLessonID().getSubLessonID());

        if (lesson.getUpdatedTime() != null) {
            request.setAttribute("updateDate", lesson.getUpdatedTime().toLocalDateTime().format(formatter));
        }

        request.setAttribute("ltList", ltList);
        request.setAttribute("lsList", lsList);
        request.setAttribute("lesson", lesson);
        request.setAttribute("order", order);
        request.setAttribute("sizeOrder", (order.get(order.size() - 1).getOrder() + 1));
        request.setAttribute("Cid", lesson.getCourseID().getCourseId());
        request.setAttribute("createDate", lesson.getCreatedTime().toLocalDateTime().format(formatter));
    }

    private static void showLtypeLsub(HttpServletRequest request, HttpServletResponse response) {
        int courseId = Integer.parseInt(request.getParameter("Cid"));
        ArrayList<LessonType> ltList = new LessonTypeDAO().getAllLessonTypes();
        ArrayList<SubLesson> lsList = new SubLessonDAO().getListSubLessonByCourseID(courseId);
        ArrayList<Lesson> order = new LessonDAO().getOrderBySublesson(lsList.get(0).getSubLessonID());

        System.out.println("order: " + order.size());
        System.out.println("here: " + (order.get(order.size() - 1).getOrder() + 1));
        request.setAttribute("ltList", ltList);
        request.setAttribute("lsList", lsList);
        request.setAttribute("order", order);
        request.setAttribute("sizeOrder", (order.get(order.size() - 1).getOrder() + 1));
        request.setAttribute("Cid", courseId);
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
        String type = request.getParameter("type");

        Lesson lesson = new Lesson();
        ArrayList<LessonType> ltList = new LessonTypeDAO().getAllLessonTypes();
        ArrayList<SubLesson> lsList = new ArrayList<>();
        String mess = "";

        String lessonName = request.getParameter("lname");
        LocalDateTime startTime = LocalDateTime.parse(request.getParameter("starttime"));

        boolean status = true;
        if (request.getParameter("status").equals("0")) {
            status = false;
        }
        int subLesson = Integer.parseInt(request.getParameter("sublesson"));
        
        String videoUrl = request.getParameter("video");

        int order = Integer.parseInt(request.getParameter("order"));

        if (type.equalsIgnoreCase("edit")) {
            LocalDateTime updateTime = LocalDateTime.now();
            int lessonType = Integer.parseInt(request.getParameter("lessont"));

            lesson = new LessonDAO().getAllLessonByID(Integer.parseInt(request.getParameter("Lid")));

            lsList = new SubLessonDAO().getListSubLessonByCourseID(lesson.getCourseID().getCourseId());

            SubLesson sub = new SubLesson();
            sub.setSubLessonID(subLesson);
            LessonType ltype = new LessonType();
            ltype.setLessonTypeID(lessonType);

            lesson.setName(lessonName);
            lesson.setUpdatedTime(Timestamp.valueOf(updateTime));
            lesson.setStartLearningTime(Timestamp.valueOf(startTime));
            lesson.setStatus(status);
            lesson.setSubLessonID(sub);
            lesson.setVideoUrl(videoUrl);
            lesson.setLessonTypeID(ltype);
            // insert lấy lt,ls theo lesson, course

            if (order != lesson.getOrder()) {
                new LessonDAO().updateOrder(lesson.getOrder(), order, lesson.getCourseID().getCourseId());
            }
            
            new LessonDAO().updateLesson(lesson, lessonType);
            
            lesson = new LessonDAO().getAllLessonByID(Integer.parseInt(request.getParameter("Lid")));

            ArrayList<Lesson> orderList = new LessonDAO().getOrderBySublesson(lesson.getSubLessonID().getSubLessonID());
            
            mess = "successfully changed the lesson";

            request.setAttribute("lesson", lesson);
            request.setAttribute("ltList", ltList);
            request.setAttribute("lsList", lsList);
            request.setAttribute("mess", mess);
            request.setAttribute("order", orderList);
            request.setAttribute("isNoti", true);
            request.getRequestDispatcher("/view/addedit-lesson-detail.jsp").forward(request, response);
        }
        if (type.equalsIgnoreCase("add")) {
            int lessonType = Integer.parseInt(request.getParameter("lessontype"));
            LocalDateTime createTime = LocalDateTime.now();

            lesson = new Lesson();
            SubLesson sub = new SubLesson();
            sub.setSubLessonID(subLesson);
            LessonType ltype = new LessonType();
            ltype.setLessonTypeID(lessonType);
            Course c = new Course();
            c.setCourseId(new CourseDAO().getlCourseIdsublessonID(subLesson));

            lesson.setName(lessonName);
            lesson.setCreatedTime(Timestamp.valueOf(createTime));
            lesson.setStartLearningTime(Timestamp.valueOf(startTime));
            lesson.setStatus(status);
            lesson.setSubLessonID(sub);
            lesson.setVideoUrl(videoUrl);
            lesson.setLessonTypeID(ltype);
            lesson.setCourseID(c);
            lesson.setOrder(order);


            new LessonDAO().updateOrder(0, order, c.getCourseId());

            int lid = new LessonDAO().insertLesson(lesson, lessonType);
            
            response.sendRedirect("lesson-detail?Lid="+lid+"&type=view");
        }

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
