package servlets;

import accounting.AccountService;
import accounting.UserProfile;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

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

            if (user.getBanned()){
                resp.getWriter().println("User " + user.getLogin() + " is banned !");
                resp.setStatus(HttpServletResponse.SC_OK);
            }
            else{
                accountService.addSession(req.getSession().getId(),user);
                PageGenerator page = PageGenerator.instance();
                HashMap<String,Object> map =new HashMap<String,Object>();
                map.put("login",user.getFirstName() + " " + user.getMiddleName());
                resp.getWriter().println(page.getPage("wellcome.html",map));

               // resp.getWriter().println("User " + user.getLogin() + "! Welcome!");
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        }
        else{
            resp.getWriter().println("Wrong login or password.");
            resp.setStatus(HttpServletResponse.SC_OK);
        }




    }
}
