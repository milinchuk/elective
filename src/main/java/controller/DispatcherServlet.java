package controller;
/**
 * Created by click on 11/5/2016.
 */
import config.CommandHolderFullInit;
import controller.commands.Command;
import controller.commands.holder.CommandHolder;
import org.apache.log4j.Logger;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;
import validators.*;
import validators.entity.Errors;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
*/
public class DispatcherServlet extends HttpServlet {
    private CommandHolder commandHolder = CommandHolderFullInit.init();
    private static final Logger logger = Logger.getLogger(DispatcherServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Command command = commandHolder.getGetCommand(
                String.valueOf(request.getSession().getAttribute(AttributesHolder.ROLE))
                        + request.getRequestURI());
        String path = doRequest(request, response, command);
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
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