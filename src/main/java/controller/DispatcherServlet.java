package controller;
/**
 * Created by click on 11/5/2016.
 */
import config.CommandHolderFullInit;
import controller.commands.Command;
import controller.commands.holder.CommandHolder;
import utils.constants.AttributesHolder;
import validators.*;
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

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Command command = commandHolder.getGetCommand(
                String.valueOf(request.getSession().getAttribute(AttributesHolder.ROLE))
                        + request.getRequestURI());
        RequestDispatcher dispatcher = doRequest(request, response, command);
        response.setCharacterEncoding("UTF-8");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Command command = commandHolder.getPostCommand(
                String.valueOf(request.getSession().getAttribute(AttributesHolder.ROLE))
                + request.getRequestURI());
        RequestDispatcher dispatcher = doRequest(request, response, command);
        response.setCharacterEncoding("UTF-8");
        dispatcher.forward(request, response);
    }

    protected RequestDispatcher doRequest(HttpServletRequest request, HttpServletResponse response, Command command){
        Validator commandValidator = new CommandValidator();
        if(!commandValidator.validate(command)){
            command = commandHolder.getPageNotFoundCommand();
        }
       return request.getRequestDispatcher(command.execute(request, response));
    }


}