package servlets;

import accounting.AccountService;
import accounting.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by SERGEY on 28.04.2017.
 */
public class SignInServlet extends HttpServlet {
    private final AccountService accountService;

    public SignInServlet(AccountService accountService){ this.accountService = accountService; }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");


        UserProfile user = accountService.getUserByLogin(login);
        if (user != null && password.equals(user.getPsssword())){
            accountService.addSession(req.getSession().getId(),user);
            resp.getWriter().println("User " + user.getLogin() + "! Welcome!");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        else{
            resp.getWriter().println("I do not know you.");
            resp.setStatus(HttpServletResponse.SC_OK);
        }




    }
}
