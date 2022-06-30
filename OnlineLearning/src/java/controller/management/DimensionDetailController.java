package controller.management;

import dao.DimensionDAO;
import dao.DimensionTypeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dimension;
import model.DimensionType;
import model.Subject;

@WebServlet(name = "DimensionDetailController", urlPatterns = {"/management/dimension-detail"})
public class DimensionDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String subjectID = request.getParameter("subjectID");
        DimensionTypeDAO dimensionTypeDAO = new DimensionTypeDAO();
        DimensionDAO dimensionDAO = new DimensionDAO();
        ArrayList<DimensionType> allDimensionTypes = dimensionTypeDAO.getAllDimensionTypes();
        String action = "";
        if (request.getParameter("dimensionID") == null) {
            action = "ADD";
        } else {
            int dimensionID = Integer.parseInt(request.getParameter("dimensionID"));
            Dimension dimension = dimensionDAO.getDimensionByID(dimensionID);
            action = "EDIT";

            request.setAttribute("dimensionID", request.getParameter("dimensionID"));
            request.setAttribute("dimension", dimension);
        }

        request.setAttribute("allDimensionTypes", allDimensionTypes);
        request.setAttribute("subjectID", subjectID);
        request.setAttribute("action", action);
        request.getRequestDispatcher("/view/dimension-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("ADD")) {
            addDimension(request, response);

        } else if (action.equalsIgnoreCase("EDIT")) {
            editDimension(request, response);
        }
    }

    private void editDimension(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Dimension dimension = new Dimension();

        int dimensionID = Integer.parseInt(request.getParameter("dimensionID"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int type = Integer.parseInt(request.getParameter("type"));
        int subjectID = Integer.parseInt(request.getParameter("subjectID"));

        dimension.setDimensionID(dimensionID);
        dimension.setName(name);
        dimension.setDescription(description);
        DimensionType dimensionType = new DimensionType();
        dimensionType.setTypeID(type);
        dimension.setTypeID(dimensionType);
        Subject subject = new Subject();
        subject.setSubjectId(subjectID);
        dimension.setSubjectID(subject);

        DimensionDAO dimensionDAO = new DimensionDAO();
        dimensionDAO.updateDimension(dimension);

        response.sendRedirect("subject-detail?subjectID=" + subjectID);
    }

    private void addDimension(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Dimension dimension = new Dimension();

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int type = Integer.parseInt(request.getParameter("type"));
        int subjectID = Integer.parseInt(request.getParameter("subjectID"));

        dimension.setName(name);
        dimension.setDescription(description);
        DimensionType dimensionType = new DimensionType();
        dimensionType.setTypeID(type);
        dimension.setTypeID(dimensionType);
        Subject subject = new Subject();
        subject.setSubjectId(subjectID);
        dimension.setSubjectID(subject);

        DimensionDAO dimensionDAO = new DimensionDAO();
        dimensionDAO.insertDimension(dimension);
        
        response.sendRedirect("subject-detail?subjectID=" + subjectID);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
