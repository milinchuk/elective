package controller;
/**
 * Created by click on 11/5/2016.
 */
import config.CommandHolderFullInit;
import controller.commands.Command;
import controller.commands.holder.CommandHolder;
import org.apache.log4j.Logger;
import sun.plugin.dom.core.Attr;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;
import utils.constants.UrlHolder;
import validators.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
*/
public class DispatcherServlet extends HttpServlet {
    public static final String UTF_8 = "UTF-8";
    private CommandHolder commandHolder = CommandHolderFullInit.init();
    private static final Logger logger = Logger.getLogger(DispatcherServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding(UTF_8);
        response.setCharacterEncoding(UTF_8);
        Command command = commandHolder.getGetCommand(
                String.valueOf(request.getSession().getAttribute(AttributesHolder.ROLE))
                        + request.getRequestURI());
        String path = doRequest(request, response, command);
        System.out.println(path);
        System.out.println(request.getAttribute(AttributesHolder.URL_PARAM));

        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding(UTF_8);
        response.setCharacterEncoding(UTF_8);
        Command command = commandHolder.getPostCommand(
                String.valueOf(request.getSession().getAttribute(AttributesHolder.ROLE))
                + request.getRequestURI());
        String path = doRequest(request, response, command);
        response.sendRedirect(path);
    }

    protected String doRequest(HttpServletRequest request, HttpServletResponse response, Command command)
            throws ServletException, IOException {
        try {
            CommandValidator commandValidator = new CommandValidator();
            if (!commandValidator.validate(command)) {
                command = commandHolder.getPageNotFoundCommand();
            }
            return command.execute(request, response);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return PagesHolder.ERROR_PAGE;
        }
    }


}