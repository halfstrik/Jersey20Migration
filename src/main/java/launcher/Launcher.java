package launcher;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class Launcher {
    public static void main(String[] args)
            throws Exception {
        readConfigStartServerReturnConnector(8080);
    }

    public static ServerConnector readConfigStartServerReturnConnector(Integer serverPost)
            throws Exception {
        ServletHolder sh = new ServletHolder(ServletContainer.class);
        sh.setInitParameter("jersey.config.server.provider.packages", "service");
        sh.setInitOrder(1);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");
        context.addServlet(sh, "/rest/*");
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            // fix for Windows, so Jetty doesn't lock files
            context.getInitParams().put("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
        }

        Server server = new Server(serverPost);
        server.setHandler(context);
        ServerConnector connector = new ServerConnector(server);
        server.start();
        return connector;
    }
}
