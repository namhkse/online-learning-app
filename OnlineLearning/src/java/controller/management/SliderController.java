package controller.management;

import dao.SliderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Slider;

@WebServlet(name = "SliderController", urlPatterns = {"/management/slide-list"})
public class SliderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        SliderDAO sliderDAO = new SliderDAO();
        if (request.getParameter("id-delete") != null) {
            sliderDAO.deleteSlider(Integer.parseInt(request.getParameter("id-delete")));
        }
        if (request.getParameter("id-hide") != null) {
            sliderDAO.setStatusSlider(Integer.parseInt(request.getParameter("id-hide")), false);
        }
        if (request.getParameter("id-show") != null) {
            sliderDAO.setStatusSlider(Integer.parseInt(request.getParameter("id-show")), true);
        }
        String display = "-1";
        if (request.getParameter("display") != null) {
            display = request.getParameter("display");
        }
        ArrayList<Slider> allSliders;
        int totalpage, totalpageProduct;
        int page = 1;
        String search = null;
        totalpageProduct = sliderDAO.countAllSliders();
        if (!display.equals("-1")) {
            totalpageProduct = sliderDAO.countSlidersByStatus(display);
        } 
        if (request.getParameter("search") != null) {
            search = request.getParameter("search");
            totalpageProduct = sliderDAO.countSlidersByTitleOrBacklink(search);
        }

        totalpage = totalpageProduct / 5;
        if (totalpageProduct % 5 != 0) {
            totalpage += 1;
        }
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));

            } catch (NumberFormatException e) {
                page = 1;
            }
            if (page < 1 || page > totalpage) {
                page = 1;
            }
        }
        int offSet = (page - 1) * 5;
        if (!display.equals("-1")) {
            allSliders = sliderDAO.getSlidersByStatus(display, offSet);
        } else {
            allSliders = sliderDAO.getAllSliders(offSet);
        }
        if (search != null) {
            allSliders = sliderDAO.getSlidersByTitleOrBacklink(search, offSet);
        }
        request.setAttribute("display", display);
        request.setAttribute("allSliders", allSliders);
        request.setAttribute("page", page);
        request.setAttribute("totalpage", totalpage);
        if (!display.equals("-1")) {
            request.setAttribute("request", request.getRequestURI() + "?display=" + display + "&");
        } else {
            request.setAttribute("request", request.getRequestURI() + "?");
        }
        if (search != null) {
            request.setAttribute("request", request.getRequestURI() + "?search=" + search + "&");
        }
        request.getRequestDispatcher("/view/slide-management.jsp").forward(request, response);
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
