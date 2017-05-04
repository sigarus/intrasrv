package servlets;

import accounting.AccountService;
import accounting.UserProfile;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by SERGEY on 28.04.2017.
 */
public class AdminServlet extends HttpServlet {
    private final AccountService accountService;

    public AdminServlet(AccountService accountService){ this.accountService = accountService; }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!assertSession(req.getSession().getId(), resp)) {
            return;
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        PageGenerator page = PageGenerator.instance();


        resp.getWriter().println(page.getPage("admUsersList.html", accountService.getHashOfUsers()));


    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!assertSession(req.getSession().getId(), resp)) {
            return;
        }
        String command = req.getParameter("command");
        if (command.equals("")) {


        }
    }

     private boolean assertSession(String sessionId, HttpServletResponse resp) {
        UserProfile user = accountService.getUserBySession(sessionId);

         if (user == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
             try {
                 resp.getWriter().println("<a href='./index.html'>Enter the portal</a>");
             } catch (IOException e) {
                 e.printStackTrace();
             }
             return false;
        }
        if (!user.getAdm()) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            try {
                resp.getWriter().println("You are not admin!!!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        if (user.getBanned()) {
             resp.setContentType("text/html;charset=utf-8");
             resp.setStatus(HttpServletResponse.SC_OK);
             try {
                 resp.getWriter().println("You are banned!!!");
             } catch (IOException e) {
                 e.printStackTrace();
             }
             return false;
         }
        return true;
     }







}



