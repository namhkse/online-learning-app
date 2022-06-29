/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.management;

import dao.PricePackageDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PricePackage;

@WebServlet(name = "PricePackageViewController", urlPatterns = {"/management/price-package-view"})
public class PricePackageViewController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        PricePackageDAO pricePackageDAO = new PricePackageDAO();
        int priceID = Integer.parseInt(request.getParameter("priceID"));
        PricePackage pricePackage = pricePackageDAO.getPricePackageByID(priceID);

        request.setAttribute("pricePackage", pricePackage);
        request.setAttribute("subjectID", subjectID);
        request.getRequestDispatcher("/view/price-package-view.jsp").forward(request, response);
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
