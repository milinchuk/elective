package controller.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by click on 11/15/2016.
 */
public class LogoutFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
