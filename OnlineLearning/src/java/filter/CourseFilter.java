package filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Role;

public class CourseFilter implements Filter {
    
    private FilterConfig filterConfig = null;
    
    public CourseFilter() {
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Account loggedAccount = (Account) req.getSession().getAttribute("account");
        String roleName = loggedAccount.getRole().getName();
        
        if (Role.EXPERT.equals(roleName)
                || Role.ADMIN.equals(roleName)) {
            chain.doFilter(request, response);
        } else {
            resp.sendError(403);
        }
    }
    
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }
    
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
    
    public void destroy() {
    }
    
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
}
