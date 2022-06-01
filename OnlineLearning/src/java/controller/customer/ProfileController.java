package controller.customer;

import dao.AccountDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Gender;

@WebServlet(name = "ProfileController", urlPatterns = {"/profile"})

public class ProfileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        request.getRequestDispatcher("view/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        AccountDAO accountDAO = new AccountDAO();
        String isNoti = "yes";
        request.setAttribute("isNoti", isNoti);
        Account oldAccount = (Account) request.getSession().getAttribute("account");
        int accountID = oldAccount.getAccountID();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        boolean gender = true;
        if (request.getParameter("gender").equalsIgnoreCase("female")) {
            gender = false;
        }
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String profilePictureUrl = request.getParameter("profilePictureUrl");
        Account acc = new Account();
        
        acc.setAccountID(accountID);
        acc.setFirstName(firstName);
        acc.setLastName(lastName);
        acc.setGender(Gender.of(gender));
        acc.setPhone(phone);
        acc.setAddress(address);
        acc.setProfilePictureUrl(profilePictureUrl);
        accountDAO.editProfile(acc);

        Account accountUpdate = accountDAO.getAccount(oldAccount.getEmail(), oldAccount.getPassword());
        request.getSession().setAttribute("account", accountUpdate);

        request.getRequestDispatcher("view/profile.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
