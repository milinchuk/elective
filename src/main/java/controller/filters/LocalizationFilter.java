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
 * Localization filter provides for handling get request with
 * language parameters. It changes and sets a locale. Check correctness of parameter.
 * Fill correct url for locale.
 *
 * @author Anastasia Milinchuk
 */
public class LocalizationFilter implements Filter {
    /**
     * Logger for logging operations.
     */
    private static final Logger logger = Logger.getLogger(LocalizationFilter.class);

    /**
     * This holder has a current locale and array with supported locales.
     */
    protected LocaleHolder localeHolder;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        localeHolder = new LocaleHolder(LocaleHolder.DEFAULT);
    }

    /**
     * In this method sets a locale. Get locale parameter, check if it is correct,
     * set locale.
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = ((HttpServletRequest) request);
        req.setCharacterEncoding(AttributesHolder.UTF_8);
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

    /**
     * This method parse string with get parameters for setting unique lang field
     * and construct correct queries string.
     *
     * @param request is request from client
     * @return queries string
     */
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
        return queries;
    }
}

