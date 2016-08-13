

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import java.util.EnumSet;


/**
 * Created by user on 8/12/16.
 */
        import org.eclipse.jetty.server.Server;
        import org.eclipse.jetty.servlet.ServletHandler;

/**
 * Created by barnett on 8/9/16.
 */
public class RoutingServer
{
    public static void main(String[] args)
    {
        Server server = new Server(9090);

        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(RepairServlet.class, "/splicing");

        try
        {
            server.start();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        try
        {
            server.join();
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
}
