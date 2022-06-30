package controller.management;

import dao.AccountDAO;
import dao.DimensionDAO;
import dao.PricePackageDAO;
import dao.SubjectCategoryDAO;
import dao.SubjectDAO;
import dao.SubjectMainCategoryDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Account;
import model.Dimension;
import model.PricePackage;
import model.Subject;
import model.SubjectCategory;
import model.SubjectMainCategory;

@WebServlet(name = "SubjectDetailController", urlPatterns = {"/management/subject-detail"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class SubjectDetailController extends HttpServlet {

    private static final long SerialVersionUID = 1L;
    private static final String UPLOAD_DIR = "img";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        AccountDAO accountDAO = new AccountDAO();
        String action = "";
        if (request.getParameter("subjectID") == null) {
            action = "ADD";
        } else {
            int subjectID = Integer.parseInt(request.getParameter("subjectID"));

            PricePackageDAO pricePackageDAO = new PricePackageDAO();
            ArrayList<PricePackage> pricePackages = pricePackageDAO.getAllPricePackages(subjectID);
            DimensionDAO dimensionDAO = new DimensionDAO();
            ArrayList<Dimension> dimensions = dimensionDAO.getDimensionsBySubjectID(subjectID);

            SubjectDAO subjectDAO = new SubjectDAO();
            Subject subject = subjectDAO.getSubjectByID(subjectID);
            ArrayList<Account> accounts = accountDAO.getListAccountCanAccessSubject(subjectID);

            action = "EDIT";

            request.setAttribute("subject", subject);
            request.setAttribute("accounts", accounts);
            request.setAttribute("dimensions", dimensions);
            request.setAttribute("pricePackages", pricePackages);
            request.setAttribute("subjectID", subjectID);
        }
        SubjectCategoryDAO subjectCategoryDAO = new SubjectCategoryDAO();
        SubjectMainCategoryDAO subjectMainCategoryDAO = new SubjectMainCategoryDAO();
        ArrayList<SubjectCategory> subjectCategories = subjectCategoryDAO.getAllSubjectCategory();
        ArrayList<SubjectMainCategory> subjectMainCategories = subjectMainCategoryDAO.getAllSubjectMainCategories();
        ArrayList<Account> experts = accountDAO.getListExpert();

        request.setAttribute("action", action);
        request.setAttribute("experts", experts);
        request.setAttribute("subjectCategories", subjectCategories);
        request.setAttribute("subjectMainCategories", subjectMainCategories);
        request.getRequestDispatcher("/view/subject-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("id-deactive") != null || request.getParameter("id-active") != null) {
            changeStatusPricePackage(request, response);
        } else {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("utf-8");

            String action = request.getParameter("action");

            if (action.equalsIgnoreCase("ADD")) {
                addSubject(request, response);
            } else if (action.equalsIgnoreCase("EDIT")) {
                editSubject(request, response);
            }
        }
    }

    private void editSubject(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        String name = request.getParameter("name");
        int mainCategoryID = -1;
        int categoryID = -1;
        if (request.getParameter("mainCategoryID") != null) {
            mainCategoryID = Integer.parseInt(request.getParameter("mainCategoryID"));
        } else {
            categoryID = Integer.parseInt(request.getParameter("categoryID"));
        }
        String[] expertCanAccess = null;
        ArrayList<Integer> expertIDCanAccess = new ArrayList<>();
        if (request.getParameterValues("expertCanAccess") != null) {
            expertCanAccess = request.getParameterValues("expertCanAccess");
            for (int i = 0; i < expertCanAccess.length; i++) {
                String expertCanAcces = expertCanAccess[i];
                expertIDCanAccess.add(Integer.parseInt(expertCanAcces));
            }
        }
        boolean featured = Boolean.parseBoolean(request.getParameter("featured"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        String description = request.getParameter("description");

        Subject subject = new Subject();
        subject.setName(name);
        if (mainCategoryID == -1) {
            SubjectCategory category = new SubjectCategory();
            category.setCategoryID(categoryID);
            subject.setCategoryID(category);
        } else {
            SubjectMainCategory mainCategory = new SubjectMainCategory();
            mainCategory.setMainCategoryID(mainCategoryID);
            subject.setMainCategoryID(mainCategory);
        }
        subject.setFeatured(featured);
        subject.setStatus(status);
        String img = null;
        if (uploadFile(request).equals("")) {
            img = new SubjectDAO().getSubjectByID(subjectID).getImage();
        } else {
            img = uploadFile(request);
        }
        subject.setImage(img);
        subject.setDescription(description);
        subject.setSubjectId(subjectID);

        SubjectDAO subjectDAO = new SubjectDAO();
        subjectDAO.updateSubject(subject);
        AccountDAO accountDAO = new AccountDAO();
        if (expertCanAccess != null) {
            accountDAO.deleteAllAccountCanAccessSubject(subjectID);
            accountDAO.insertListAccountCanAccessSubject(subjectID, expertIDCanAccess);
        }

        response.sendRedirect("subject-list");
    }

    private void addSubject(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        String name = request.getParameter("name");
        int mainCategoryID = -1;
        int categoryID = -1;
        if (request.getParameter("mainCategoryID") != null) {
            mainCategoryID = Integer.parseInt(request.getParameter("mainCategoryID"));
        } else {
            categoryID = Integer.parseInt(request.getParameter("categoryID"));
        }
        String[] expertCanAccess = null;
        if (request.getParameterValues("expertCanAccess") != null) {
            expertCanAccess = request.getParameterValues("expertCanAccess");
        }
        ArrayList<Integer> expertIDCanAccess = new ArrayList<>();
        for (int i = 0; i < expertCanAccess.length; i++) {
            String expertCanAcces = expertCanAccess[i];
            expertIDCanAccess.add(Integer.parseInt(expertCanAcces));
        }
        boolean featured = false;
        if (request.getParameter("featured") != null) {
            featured = Boolean.parseBoolean(request.getParameter("featured"));
        }
        boolean status = false;
        if (request.getParameter("status") != null) {
            status = Boolean.parseBoolean(request.getParameter("status"));
        }
        String description = request.getParameter("description");

        Subject subject = new Subject();
        subject.setName(name);
        if (mainCategoryID == -1) {
            SubjectCategory category = new SubjectCategory();
            category.setCategoryID(categoryID);
            subject.setCategoryID(category);
        } else {
            SubjectMainCategory mainCategory = new SubjectMainCategory();
            mainCategory.setMainCategoryID(mainCategoryID);
            subject.setMainCategoryID(mainCategory);
        }
        subject.setFeatured(featured);
        subject.setStatus(status);
        String img = null;
        if (uploadFile(request).equals("")) {
            img = new SubjectDAO().getSubjectByID(subjectID).getImage();
        } else {
            img = uploadFile(request);
        }
        subject.setImage(img);
        subject.setDescription(description);
        subject.setSubjectId(subjectID);

        SubjectDAO subjectDAO = new SubjectDAO();
        subjectDAO.updateSubject(subject);
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.deleteAllAccountCanAccessSubject(subjectID);
        accountDAO.insertListAccountCanAccessSubject(subjectID, expertIDCanAccess);

        response.sendRedirect("subject-list");
    }

    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName = "";
        try {
            Part filePart = request.getPart("photo");
            fileName = (String) getFileName(filePart);
            String applicationPath = request.getServletContext().getRealPath("");
            String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }

        } catch (Exception e) {
            fileName = "";
        }
        return fileName;
    }

    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("*****partHeader :" + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }

    private void changeStatusPricePackage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PricePackageDAO pricePackageDAO = new PricePackageDAO();
        if (request.getParameter("id-deactive") != null) {
            pricePackageDAO.updateStatusPricePackage(Integer.parseInt(request.getParameter("id-deactive")), false);
        }
        if (request.getParameter("id-active") != null) {
            pricePackageDAO.updateStatusPricePackage(Integer.parseInt(request.getParameter("id-active")), true);
        }
        response.getWriter().flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("dimensionID") != null) {
            int idDelete = Integer.parseInt(request.getParameter("dimensionID"));
            new DimensionDAO().deleteDimension(idDelete);
        } else {
            int idDelete = Integer.parseInt(request.getParameter("priceID"));
            new PricePackageDAO().deletePricePackage(idDelete);
        }
        response.setStatus(200);
        response.flushBuffer();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
