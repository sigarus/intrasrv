package servlets;

import accounting.AccountService;
import accounting.UserProfile;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

        resp.getWriter().println(getUsersAccountsPage());


    }

    private String getUsersAccountsPage(){
        PageGenerator page = PageGenerator.instance();
        HashMap<String,Object> root = new HashMap<String,Object>();
        root.put("users",accountService.getUsersObjects());
        return page.getPage("admUsersList.html", root);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!assertSession(req.getSession().getId(), resp)) {
            return;
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        String action = req.getParameter("action");


        Map<String,String[]> params =  req.getParameterMap();
        UserProfile user = null;
        for (Map.Entry<String, String[]> pair : params.entrySet()) {
            if (pair.getKey().indexOf("id_") == 0){
               if (action.equals("delete")){
                   user = accountService.getUserById(Long.getLong(pair.getValue()[0]));
                  // accountService.deleteSessionById(Long.getLong(pair.getValue()[0]));
                   accountService.deleteUserById(Long.getLong(pair.getValue()[0]));

               }
               if (action.equals("ban")){
                   user = accountService.getUserById(Long.getLong(pair.getValue()[0]));
                   if (user != null){user.setBanned(!user.getBanned());}
               }
                if (action.equals("adm")){
                    user = accountService.getUserById(Long.getLong(pair.getValue()[0]));
                    if (user != null){user.setAdm(!user.getAdm());}
                }
            }

        }

        resp.getWriter().println(getUsersAccountsPage());



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



