package main.java;


import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

/**
 * Created by SERGEY on 25.04.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        Server server = new Server(8080);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("htmls");

        HandlerList hnd = new HandlerList();
        hnd.setHandlers(new Handler[] {resourceHandler});

        server.start();
        System.out.println("Server started");

        server.join();

    }
}
