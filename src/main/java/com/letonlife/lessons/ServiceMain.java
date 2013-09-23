package com.letonlife.lessons;

import com.letonlife.lessons.service.BasicService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


/**
 *
 * 服务端
 *
 * @author leton li
 * @version 0.1
 */
public class ServiceMain {
    public static void main(String[] args) {
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        server.setHandler(context);

        ServletHolder servletHolder = new ServletHolder(new BasicService());
        context.addServlet(servletHolder, "/test");
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
