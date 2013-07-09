package launcher;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Launcher {
    public static void main(String[] args)
            throws Exception {
        startAndReturnServer(8080);
    }

    public static Server startAndReturnServer(Integer serverPost)
            throws Exception {
        ServletHolder sh = new ServletHolder(ServletContainer.class);
        sh.setInitParameter("com.sun.jersey.config.property.resourceConfigClass",
                            "com.sun.jersey.api.core.PackagesResourceConfig");
        sh.setInitParameter("com.sun.jersey.config.property.packages", "service");
        sh.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
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
        server.start();
        return server;
    }
}
