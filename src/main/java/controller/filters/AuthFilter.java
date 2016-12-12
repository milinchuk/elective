package controller.filters;

import controller.security.Permission;
import model.entity.User;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import utils.constants.AttributesHolder;
import utils.constants.LoggingMessagesHanldler;
import utils.constants.PagesHolder;
import utils.constants.UrlHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by click on 11/15/2016.
 */
public class AuthFilter implements Filter {
    private static final Logger logger = Logger.getLogger(AuthFilter.class);
    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // check credentials
        HttpServletRequest request = (HttpServletRequest) req;
        request.setCharacterEncoding("UTF-8");
        boolean isAuth = isAuthorize(request);
        if (request.getRequestURI().startsWith(UrlHolder.USER)) {
            if (!isAuth) {
                logger.info(LoggingMessagesHanldler.TRY_LOGIN_UNAUTHORIZE);
                req.getRequestDispatcher(PagesHolder.LOGIN).forward(req, resp);
            }
        }

        if (request.getRequestURI().startsWith(UrlHolder.LOGIN)
                || request.getRequestURI().startsWith(UrlHolder.SIGNUP)) {
            if(isAuth){
                request.getSession().invalidate();
            }
        }
        chain.doFilter(req, resp);
    }

    public void destroy() {
    }

    private boolean isAuthorize(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if ((session.getAttribute(AttributesHolder.ID) != null)
                && (session.getAttribute(AttributesHolder.ROLE) != null)){
            String role = String.valueOf(session.getAttribute(AttributesHolder.ROLE));
            if ( (role.equals(String.valueOf(User.STUDENT)))
                    || (role.equals(String.valueOf(User.TUTOR))) ) {
                return true;
            }
        }
        return false;
    }

}
