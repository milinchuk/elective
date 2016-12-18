package controller;
/**
 * Created by click on 11/5/2016.
 */
import config.CommandHolderFullInit;
import controller.commands.Command;
import controller.commands.holder.CommandHolder;
import org.apache.log4j.Logger;
import utils.constants.AttributesHolder;
import utils.constants.UrlHolder;
import validators.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main servlet. All of requests go here.
 *
 * @author Anastasia Milinchuk
*/
public class DispatcherServlet extends HttpServlet {
    /**
     * Holder of all commands that present in project
     */
    private CommandHolder commandHolder = CommandHolderFullInit.init();

    /**
     * Logger for logging errors and operations
     */
    private static final Logger logger = Logger.getLogger(DispatcherServlet.class);

    /**
     * This method obtain all GET request
     *
     * @param request is request from client
     * @param response is response to client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = commandHolder.getGetCommand(
                String.valueOf(request.getSession().getAttribute(AttributesHolder.ROLE))
                        + request.getRequestURI());
        String path = doRequest(request, response, command);
        request.getRequestDispatcher(path).forward(request, response);
    }

    /**
     * This method obtain all of POST request.
     *
     * @param request is clients request
     * @param response is response to client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = commandHolder.getPostCommand(
                String.valueOf(request.getSession().getAttribute(AttributesHolder.ROLE))
                + request.getRequestURI());
        String path = doRequest(request, response, command);
        response.sendRedirect(path);
    }

    /**
     * Retrieve string with url to page.
     * Execute command, which equal to request url.
     *
     * @param request is request from client
     * @param response is response to client
     * @param command is command that call client
     * @return page url
     * @throws ServletException
     * @throws IOException
     */
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
            return UrlHolder.ERROR;
        }
    }
}