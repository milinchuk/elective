package controller.filters;

import model.entity.User;
import org.apache.log4j.Logger;
import utils.constants.AttributesHolder;
import utils.constants.LoggingMessagesHolder;
import utils.constants.PagesHolder;
import utils.constants.UrlHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter for authorization. Check if user authorize to get some content.
 * Forward to log in page if user is don't authorize.
 *
 * @author Anastasia Milinchuk
 * @see javax.servlet.Filter
 */
public class AuthFilter implements Filter {
    /**
     * Logger for logging operations
     */
    private static final Logger logger = Logger.getLogger(AuthFilter.class);

    /**
     * Filter initialization method.
     * @param config is filter configuration
     * @throws ServletException
     */
    public void init(FilterConfig config) throws ServletException {
    }

    /**
     * Body of filter. It checks user credentials. If user has no credentials -
     * forward to login page. If register user goes to login page - clear session.
     *
     * @param req is request
     * @param resp is response
     * @param chain is filter chain
     * @throws ServletException
     * @throws IOException
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // check credentials
        HttpServletRequest request = (HttpServletRequest) req;
        request.setCharacterEncoding(AttributesHolder.UTF_8);
        boolean isAuth = isAuthorize(request);
        if (request.getRequestURI().startsWith(UrlHolder.USER)) {
            if (!isAuth) {
                logger.info(LoggingMessagesHolder.TRY_LOGIN_UNAUTHORIZE);
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

    /**
     * Destroy method. Execute before filter would be deleted.
     */
    public void destroy() {
    }

    /**
     * It checks if user is authorize.
     *
     * @param request is request from client
     * @return true if user is authorize and false on the other case.
     */
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
