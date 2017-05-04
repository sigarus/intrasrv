package servlets;

import accounting.AccountService;
import accounting.UserExistsException;
import accounting.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by SERGEY on 27.04.2017.
 */
public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService){
        this.accountService = accountService;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");

        UserProfile newuser = new UserProfile(req.getParameter("firstname"),
                                              req.getParameter("middlename"),
                                              req.getParameter("lastname"),
                                              req.getParameter("login"),
                                              req.getParameter("password"),
                                              false);

        try {
            accountService.addUser(newuser);
        } catch (UserExistsException userExistsExtension) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("User:" + newuser.getLogin() + "is already presents.");
            return;
        }
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("Stored user:" + newuser.getLogin());
    }
}
