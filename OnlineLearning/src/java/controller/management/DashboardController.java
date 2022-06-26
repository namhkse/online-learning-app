package controller.management;

import dao.StatistDAO;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
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
        StatistDAO statistDAO = new StatistDAO();
        LocalDate today = LocalDate.now();

        int newAccountInThisMonth = statistDAO.getNumberOfNewAccount(
                today.format(DateTimeFormatter.ofPattern("MM/yyyy"))).get("NumberNewAccount");

        int newAccountInLastMonth = statistDAO.getNumberOfNewAccount(
                today.minusMonths(1).format(DateTimeFormatter.ofPattern("MM/yyyy"))).get("NumberNewAccount");

        req.setAttribute("newAccountInThisMonth", newAccountInThisMonth);
        req.setAttribute("newAccountInLastMonth", newAccountInLastMonth);

        int numberVisitPageToday = statistDAO.getNumberVisitPage(today);
        req.setAttribute("numberVisitPageToday", numberVisitPageToday);

        double revenueThisYear = statistDAO.calculateRevenueInYear(today.getYear());
        double revenueLastYear = statistDAO.calculateRevenueInYear(today.getYear() - 1);
        double totalEarning = statistDAO.getAllEarning();

        req.setAttribute("totalEarning", totalEarning);
        req.setAttribute("revenueRatio", calRevenueChange(revenueLastYear, revenueThisYear));
        req.setAttribute("revenueThisYear", revenueThisYear);
        dispatcher.forward(req, resp);
    }

    private double calRevenueChange(double lastYear, double thisYear) {
        if (lastYear == 0) {
            return 100;
        }
        return ((thisYear - lastYear) / lastYear) * 100;
    }
}
