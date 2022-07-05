
package controller.management;

import dao.AccountDAO;
import dao.DimensionDAO;
import dao.PricePackageDAO;
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
import model.Dimension;
import model.PricePackage;
import model.Subject;
import model.SubjectCategory;
import model.SubjectMainCategory;

@WebServlet(name = "SubjectViewController", urlPatterns = {"/management/subject-view"})
public class SubjectViewController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        AccountDAO accountDAO = new AccountDAO();
        int subjectID = Integer.parseInt(request.getParameter("subjectID"));

        PricePackageDAO pricePackageDAO = new PricePackageDAO();
        ArrayList<PricePackage> pricePackages = pricePackageDAO.getAllPricePackages(subjectID);
        DimensionDAO dimensionDAO = new DimensionDAO();
        ArrayList<Dimension> dimensions = dimensionDAO.getDimensionsBySubjectID(subjectID);

        SubjectDAO subjectDAO = new SubjectDAO();
        Subject subject = subjectDAO.getSubjectByID(subjectID);
        ArrayList<Account> accounts = accountDAO.getListAccountCanAccessSubject(subjectID);

        request.setAttribute("subject", subject);
        request.setAttribute("accounts", accounts);
        request.setAttribute("dimensions", dimensions);
        request.setAttribute("pricePackages", pricePackages);
        request.setAttribute("subjectID", subjectID);
        SubjectCategoryDAO subjectCategoryDAO = new SubjectCategoryDAO();
        SubjectMainCategoryDAO subjectMainCategoryDAO = new SubjectMainCategoryDAO();
        ArrayList<SubjectCategory> subjectCategories = subjectCategoryDAO.getAllSubjectCategory();
        ArrayList<SubjectMainCategory> subjectMainCategories = subjectMainCategoryDAO.getAllSubjectMainCategories();
        ArrayList<Account> experts = accountDAO.getListExpert();

        request.setAttribute("experts", experts);
        request.setAttribute("subjectCategories", subjectCategories);
        request.setAttribute("subjectMainCategories", subjectMainCategories);
        request.getRequestDispatcher("/view/subject-view.jsp").forward(request, response);
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
