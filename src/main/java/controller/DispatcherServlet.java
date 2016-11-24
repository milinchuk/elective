package controller;
/**
 * Created by click on 11/5/2016.
 */
import controller.commands.Command;
import controller.commands.holder.CommandHolder;
import controller.commands.authorization.LoginCommand;
import controller.commands.authorization.LogoutCommand;
import controller.commands.authorization.SignUpCommand;
import controller.commands.course.*;
import controller.commands.holder.CommandsInit;
import controller.commands.progress.CourseUnfollowCommand;
import controller.commands.progress.ProgressStudentsCommand;
import controller.commands.progress.ProgressUpdateCommand;
import controller.commands.user.ProfileCommand;
import controller.commands.user.UpdateProfileCommand;
import controller.security.Permission;
import utils.constants.AttributesHolder;
import utils.constants.UrlHolder;
import validators.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
*/
public class DispatcherServlet extends HttpServlet {
    private CommandHolder commandHolder;

    @Override
    public void init() throws ServletException {
        super.init();
        commandHolder = new CommandHolder();
        CommandsInit commandsInit = new CommandsInit();
        commandsInit.initCommandHolder(commandHolder);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = commandHolder.getGetCommand(
                String.valueOf(request.getSession().getAttribute(AttributesHolder.ROLE))
                        + request.getRequestURI());
        RequestDispatcher dispatcher = doRequest(request, response, command);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = commandHolder.getPostCommand(
                String.valueOf(request.getSession().getAttribute(AttributesHolder.ROLE))
                + request.getRequestURI());
        RequestDispatcher dispatcher = doRequest(request, response, command);
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