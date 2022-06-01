package controller.blog;

import dao.BlogDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;

@WebServlet(name = "BlogDetailController", urlPatterns = {"/blog-detail"})
public class BlogDetailController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        
        String raw_blogID = request.getParameter("blogID");
        
        int blogID = Integer.parseInt(raw_blogID);
        
        BlogDAO blogDao = new BlogDAO();
        Blog blog = blogDao.getBlogByID(blogID);
        int newNumberOfView = blog.getNumberOfView() + 1;
        blogDao.increaseNumberOfView(blogID, newNumberOfView);
        request.setAttribute("blog", blog);
        request.getRequestDispatcher("view/blog-detail.jsp").forward(request, response);
        
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
