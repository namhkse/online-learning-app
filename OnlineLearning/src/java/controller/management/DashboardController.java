package controller.management;

import dao.StatisticDAO;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DashboardController", urlPatterns = {"/management/dashboard"})
public class DashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/dashboard.jsp");
        StatisticDAO statistDAO = new StatisticDAO();
        LocalDate today = LocalDate.now();
        LocalDate todayInLastMonth = today.minusMonths(1);

        int newAccountInThisMonth = statistDAO.countNewAccount(today.getMonthValue(), today.getYear());
        req.setAttribute("newAccountInThisMonth", newAccountInThisMonth);
        
        int newAccountInLastMonth = statistDAO.countNewAccount(todayInLastMonth.getMonthValue(), todayInLastMonth.getYear());
        req.setAttribute("newAccountInLastMonth", newAccountInLastMonth);

        int numberVisitPageToday = statistDAO.getNumberVisitPage(today);
        req.setAttribute("numberVisitPageToday", numberVisitPageToday);

        double revenueThisYear = statistDAO.calculateRevenueInYear(today.getYear());
        req.setAttribute("revenueThisYear", revenueThisYear);
        
        double revenueLastYear = statistDAO.calculateRevenueInYear(today.getYear() - 1);
        req.setAttribute("revenueRatio", caculateRevenueRatio(revenueLastYear, revenueThisYear));
        
        double totalEarning = statistDAO.getAllEarning();
        req.setAttribute("totalEarning", totalEarning);

        dispatcher.forward(req, resp);
    }

    private double caculateRevenueRatio(double lastYear, double thisYear) {
        if (lastYear == 0) {
            return 100;
        }
        return ((thisYear - lastYear) / lastYear) * 100;
    }
}
