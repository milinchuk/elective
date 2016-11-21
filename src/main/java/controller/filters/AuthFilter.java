package controller.filters;

import model.entity.User;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by click on 11/15/2016.
 */
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // check credentials

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
