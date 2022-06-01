package controller.blog;

import dao.BlogCategoryDAO;
import dao.BlogDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.BlogCategory;

@WebServlet(name = "BlogController", urlPatterns = {"/blog"})

public class BlogController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        request.getRequestDispatcher("view/blog.jsp").forward(request, response);
    }

    private ArrayList<Blog> ListAllBlogBySearch(HttpServletRequest request, HttpServletResponse response, int offset) {
        String search = request.getParameter("search");
        ArrayList<Blog> blist = blist = new BlogDAO().listAllBlogBySearch(search, offset);
        return blist;
    }

    private void blogByCategory(HttpServletRequest request, HttpServletResponse response) {
        int bcID = 0;
        int page = 1;

        if (request.getParameter("bcid") != null) {
            bcID = Integer.parseInt(request.getParameter("bcid"));
        }

        ArrayList<Blog> bList = new ArrayList<>();

        //get size and dive page by blog category
        int totalpageProduct = new BlogDAO().getSizeBlog(bcID);
        //if search not null then reassign the value of totalpageProduct
        if (request.getParameter("search") != null) {
            totalpageProduct = new BlogDAO().getSizeBlogBySearch(request.getParameter("search"));
        }
        int totalpage = totalpageProduct / 5;
        if (totalpageProduct % 5 != 0) {
            totalpage += 1;
        }

        //check if page is not null and satisfy the value in the given range
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
        //get offset from page to select 5 objects in database
        int offSet = (page - 1) * 5;
        //assign value to arraylist
        if (request.getParameter("search") == null) {
            //get value by blogcategoryID if search == null
            bList = new BlogDAO().listAllBlogByPage(bcID, offSet);
        } else {
            //get value by search if search != null
            bList = ListAllBlogBySearch(request, response, offSet);
        }
        //setAttribute
        request.setAttribute("blogList", bList);
        request.setAttribute("page", page);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("search", request.getParameter("search"));
        
        //Set Attribute for url with bcid or with search and without bcid or search
        if (request.getParameter("bcid") != null) {
            request.setAttribute("bcid", request.getParameter("bcid"));
            //setAttribute for page.jsp link when bcid != 0
            request.setAttribute("request", request.getRequestURI() + "?bcid=" + bcID + "&");
        } else if (request.getParameter("search") != null) {
            //setAttribute for page.jsp link when search != 0
            request.setAttribute("request", request.getRequestURI() + "?search=" + request.getParameter("search") + "&");
        } else {
            request.setAttribute("request", request.getRequestURI() + "?");
        }

    }

    private void ListAllBlogCategory(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<BlogCategory> bcList = new ArrayList<>();
        bcList = new BlogCategoryDAO().getAllBlogCategory();
        request.setAttribute("bcList", bcList);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        blogByCategory(request, response);
        ListAllBlogCategory(request, response);
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
