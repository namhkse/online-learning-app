package controller.management;

import dao.DimensionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dimension;

@WebServlet(name = "SubjectDetailController", urlPatterns = {"/management/subject-detail"})
public class SubjectDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        DimensionDAO dimensionDAO = new DimensionDAO();
        ArrayList<Dimension> dimensions = dimensionDAO.getDimensionsBySubjectID(subjectID);

        if (request.getParameter("search") != null) {
            dimensions = dimensionDAO.getDimensionsByNameAndSubjectID(request.getParameter("search"), subjectID);
        }

        request.setAttribute("dimensionsBySubjectID", dimensions);
        request.setAttribute("subjectID", subjectID);
        request.getRequestDispatcher("/view/subject-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idDelete = Integer.parseInt(request.getParameter("dimensionID"));
        new DimensionDAO().deleteDimension(idDelete);
        response.setStatus(200);
        response.flushBuffer();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
