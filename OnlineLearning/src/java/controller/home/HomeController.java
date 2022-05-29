/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.home;

import dao.AccountDAO;
import dao.CourseDAO;
import dao.SliderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Course;
import model.Slider;

@WebServlet(name = "HomeController", urlPatterns = {"/home"})

public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        AccountDAO accountDAO = new AccountDAO();
        Account acc = accountDAO.getAccount("weqsfub.ycvvjer@trgfmshan.sqalfs.net", "123456");
        request.getSession().setAttribute("account", acc);
        SliderDAO sliderDB = new SliderDAO();
        ArrayList<Slider> sliders = sliderDB.getSliders();
        CourseDAO courseDAO=new CourseDAO();
        ArrayList<Course> bestRatedCourses = courseDAO.getBestRatedCourses();
        request.setAttribute("bestRatedCourses", bestRatedCourses);
        request.setAttribute("sliders", sliders);
        request.getRequestDispatcher("view/home.jsp").forward(request, response);
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
