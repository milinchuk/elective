package controller;
/**
 * Created by click on 11/5/2016.
 */
import controller.commands.Command;
import controller.commands.CommandHolder;
import controller.commands.authorization.LoginCommand;
import controller.commands.authorization.LogoutCommand;
import controller.commands.authorization.SignUpCommand;
import controller.commands.course.*;
import controller.commands.progress.ProgressStudentsCommand;
import controller.commands.progress.ProgressUpdateCommand;
import controller.commands.user.ProfileCommand;
import controller.commands.user.UpdateProfileCommand;
import controller.security.Permission;
import utils.UrlHolder;
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
        Map<String, Command> getCommand = new HashMap<>();
        Map<String, Command> postCommand = new HashMap<>();
        // ---------- get commands
        // for unregister
        getCommand.put(UrlHolder.LOGIN, new LoginCommand());
        getCommand.put(UrlHolder.SIGNUP, new SignUpCommand());

        // for register
        getCommand.put(UrlHolder.PROFILE, new ProfileCommand());
        getCommand.put(UrlHolder.MY_COURSES, new UserCoursesCommand());
        getCommand.put(UrlHolder.FIND, new FindCoursesCommand());
        getCommand.put(UrlHolder.STUDENTS + UrlHolder.SUFFIX, new ProgressStudentsCommand());
        getCommand.put(UrlHolder.LOGOUT, new LogoutCommand());
        getCommand.put(UrlHolder.COURSE, new OpenCourseCommand());
        getCommand.put(UrlHolder.COURSE_ADD, new CourseCommand());

        // ----------- post commands
        // create
        postCommand.put(UrlHolder.COURSE_ADD, new CourseCreateCommand());
        postCommand.put(UrlHolder.FOLLOW, new CourseFollowCommand());
        // update
        postCommand.put(UrlHolder.PROFILE, new UpdateProfileCommand());
        postCommand.put(UrlHolder.COURSE_EDIT, new CourseUpdateCommand());
        postCommand.put(UrlHolder.STUDENT_EDIT, new ProgressUpdateCommand());
        // delete
        postCommand.put(UrlHolder.UNFOLLOW, new CourseFollowCommand());
        postCommand.put(UrlHolder.COURSE_DELETE, new CourseDeleteCommand());

        commandHolder = new CommandHolder();
        commandHolder.setGetCommands(getCommand);
        commandHolder.setPostCommands(postCommand);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // imitate authorization
        Permission permission = new Permission();
        permission.hasPermission(request);

        // check on FILE NOT FOUND
        Command command = commandHolder.getGetCommand(request.getRequestURI());
        Validator commandValidator = new CommandValidator();
        if(!commandValidator.validate(command)){
            command = commandHolder.getPageNotFoundCommand();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(command.execute(request, response));
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = doRequest(request, response);
        dispatcher.forward(request, response);
    }

    protected RequestDispatcher doRequest(HttpServletRequest request, HttpServletResponse response){
        Permission permission = new Permission();
        permission.hasPermission(request);

        Command command = commandHolder.getPostCommand(request.getRequestURI());
        Validator commandValidator = new CommandValidator();
        if(!commandValidator.validate(command)){
            command = commandHolder.getPageNotFoundCommand();
        }
       return request.getRequestDispatcher(command.execute(request, response));
    }


}