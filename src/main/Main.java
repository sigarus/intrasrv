package main;


import accounting.AccountService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AdminServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;

/**
 * Created by SERGEY on 25.04.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception{

        AccountService accountService = new AccountService();

        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)),"/signup");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)),"/signin");
        context.addServlet(new ServletHolder(new AdminServlet(accountService)),"/admin");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("htmls");

        HandlerList hnd = new HandlerList();
        hnd.setHandlers(new Handler[] {resourceHandler,context});

        server.setHandler(hnd);
        server.start();
        System.out.println("Server started");

        server.join();

    }
}
