package controller.filters;

import i18n.LocaleHolder;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import utils.constants.AttributesHolder;
import utils.constants.LoggingMessagesHanldler;
import utils.constants.UrlHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by click on 11/25/2016.
 */
public class LocalizationFilter implements Filter {
    private static final Logger logger = Logger.getLogger(LocalizationFilter.class);
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
            logger.info(LoggingMessagesHanldler.INFO_SET_LOCALE);
            for (Locale loc : LocaleHolder.SUPPORTED) {
                if (loc.getLanguage().equals(req.getParameter(AttributesHolder.LANG))) {
                    localeHolder.setCurrentLocale(loc);
                    locale = loc;
                    break;
                }
            }
        }
        logger.info(LoggingMessagesHanldler.INFO_SET_LOCALE);
        req.setAttribute(AttributesHolder.LOCALE, locale);
        req.setAttribute(AttributesHolder.URL_PARAM, getQueriesWithoutLang(req));
        response.setLocale(locale);
        session.setAttribute(AttributesHolder.LOCALE, locale);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private String getQueriesWithoutLang(HttpServletRequest request){
        String queries = UrlHolder.QUESTION_SYMBOL;
        String queryString = request.getQueryString();
        if (!StringUtils.isEmpty(queryString)) {
            String[] params = queryString.split(UrlHolder.AND_SYMBOL);
            for (String param: params) {
                String[] parseParam = param.split(UrlHolder.EQUAL_SYMBOL);
                if ( !(parseParam.length < 2) ) {
                    if (!parseParam[0].equals(AttributesHolder.LANG)) {
                        queries += param + UrlHolder.AND_SYMBOL;
                    }
                }

            }
        }
        queries += UrlHolder.LANG_GET;
        System.out.println(queries);
        return queries;
    }
}

