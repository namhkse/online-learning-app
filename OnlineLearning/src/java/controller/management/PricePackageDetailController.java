package controller.management;

import dao.PricePackageDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PricePackage;
import model.Subject;

@WebServlet(name = "PricePackageDetailController", urlPatterns = {"/management/price-package-detail"})
public class PricePackageDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        PricePackageDAO pricePackageDAO = new PricePackageDAO();

        String action = "";
        if (request.getParameter("priceID") == null) {
            action = "ADD";
        } else {
            int priceID = Integer.parseInt(request.getParameter("priceID"));
            PricePackage pricePackage = pricePackageDAO.getPricePackageByID(priceID);
            action = "EDIT";

            request.setAttribute("pricePackage", pricePackage);
        }

        request.setAttribute("subjectID", subjectID);
        request.setAttribute("action", action);
        request.getRequestDispatcher("/view/price-package-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("ADD")) {
            addPricePackage(request, response);
        } else if (action.equalsIgnoreCase("EDIT")) {
            editPricePackage(request, response);
        }
    }

    private void editPricePackage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PricePackage pricePackage = new PricePackage();

        int id = Integer.parseInt(request.getParameter("priceID"));
        String name = request.getParameter("name").trim();
        int duration = -1;
        if (request.getParameter("duration").trim().equals("") == false) {
            duration = Integer.parseInt(request.getParameter("duration").trim());
        }
        BigDecimal listPrice = BigDecimal.valueOf(Double.parseDouble(request.getParameter("listPrice").trim()));
        BigDecimal salePrice = BigDecimal.valueOf(Double.parseDouble(request.getParameter("salePrice").trim()));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        Subject subject = new Subject();
        subject.setSubjectId(subjectID);

        pricePackage.setPriceID(id);
        pricePackage.setName(name);
        pricePackage.setAccessDuration(duration);
        pricePackage.setListPrice(listPrice);
        pricePackage.setSalePrice(salePrice);
        pricePackage.setStatus(status);
        pricePackage.setSubjectID(subject);

        new PricePackageDAO().updatePricePackage(pricePackage);

        response.sendRedirect("subject-detail?subjectID=" + subjectID);
    }

    private void addPricePackage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PricePackage pricePackage = new PricePackage();

        String name = request.getParameter("name").trim();
        int duration = -1;
        if (request.getParameter("duration").trim().equals("") == false) {
            duration = Integer.parseInt(request.getParameter("duration").trim());
        }
        BigDecimal listPrice = BigDecimal.valueOf(Double.parseDouble(request.getParameter("listPrice").trim()));
        BigDecimal salePrice = BigDecimal.valueOf(Double.parseDouble(request.getParameter("salePrice").trim()));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        Subject subject = new Subject();
        subject.setSubjectId(subjectID);

        pricePackage.setName(name);
        pricePackage.setAccessDuration(duration);
        pricePackage.setListPrice(listPrice);
        pricePackage.setSalePrice(salePrice);
        pricePackage.setStatus(status);
        pricePackage.setSubjectID(subject);

        new PricePackageDAO().insertPricePackage(pricePackage);

        response.sendRedirect("subject-detail?subjectID=" + subjectID);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
