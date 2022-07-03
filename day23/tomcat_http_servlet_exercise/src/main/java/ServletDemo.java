import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(value = "/Servlet",initParams = {@WebInitParam(name="key",value = "Servlet已初始化...")},loadOnStartup = 1)
public class ServletDemo implements Servlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        String key = config.getInitParameter("key");
        System.out.println("init Servlet: " + key);
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("你正在访问ServletDemo...");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
