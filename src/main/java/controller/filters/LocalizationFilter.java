package controller.filters;

import i18n.LocaleHolder;
import utils.constants.AttributesHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by click on 11/25/2016.
 */
public class LocalizationFilter implements Filter {
    protected LocaleHolder localeHolder;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        localeHolder = new LocaleHolder(LocaleHolder.DEFAULT);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = ((HttpServletRequest) request);
        HttpSession session = req.getSession();
        Locale locale = localeHolder.getCurrentLocale();
        if(req.getParameter(AttributesHolder.LANG) != null) {
            for (Locale loc : LocaleHolder.SUPPORTED) {
                if (loc.getLanguage().equals(req.getParameter(AttributesHolder.LANG))) {
                    localeHolder.setCurrentLocale(loc);
                    locale = loc;
                    break;
                }
            }
        }
        req.setAttribute(AttributesHolder.LOCALE, locale);
        response.setLocale(locale);
        session.setAttribute(AttributesHolder.LOCALE, locale);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
