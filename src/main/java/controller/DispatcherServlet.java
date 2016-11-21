package controller; /**
 * Created by click on 11/5/2016.
 */
import controller.commands.Command;
import controller.commands.CommandHolder;
import utils.UrlHolder;
import controller.commands.authorization.LoginCommand;
import controller.commands.authorization.SignUpCommand;
import controller.commands.course.*;
import controller.commands.progress.ProgressFindByCourseCommand;
import controller.commands.progress.ProgressFindCommand;
import controller.commands.progress.ProgressesUserCommand;
import controller.commands.user.*;
import controller.security.Permition;
import model.entity.User;
import validators.CommandValidator;
import validators.Errors;
import validators.Validator;

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
        getCommand.put(UrlHolder.PREFIX, new CourseFindAllCommand());
        getCommand.put(UrlHolder.COURSES, new CourseFindAllCommand());
        getCommand.put(UrlHolder.COURSES + UrlHolder.SUFFIX, new CourseFindByUserCommand());
        getCommand.put(UrlHolder.COURSE + UrlHolder.SUFFIX, new CourseFindOneCommand());
        getCommand.put(UrlHolder.STUDENTS + UrlHolder.SUFFIX, new ProgressFindByCourseCommand());
        getCommand.put(UrlHolder.STUDENT + UrlHolder.SUFFIX, new ProgressFindCommand());
        getCommand.put(UrlHolder.LOGIN, new LoginCommand());
        getCommand.put(UrlHolder.SIGNUP, new SignUpCommand());
        getCommand.put(UrlHolder.PROFILE + UrlHolder.SUFFIX, new ProfileCommand());
        getCommand.put(UrlHolder.MY_COURSES + UrlHolder.SUFFIX, new ProgressesUserCommand());
//        // post commands
//        Map<String, Command> postCommands = new HashMap<>();
//        postCommands.put("/course", new CourseCreateCommand());
//        postCommands.put("/login", new LoginCommand());
//        postCommands.put("/follow", new CourseFollowCommand());
//        postCommands.put("/signup", new SignUpCommand());
//
//        // put commands
//        Map<String, Command> putCommands = new HashMap<>();
//        putCommands.put("/course/{id}", new CourseUpdateCommand());
//        putCommands.put("/profile/{id}", new UserUpdateCommand());
//        putCommands.put("/student/{id}", new ProgressUpdateCommand());
        commandHolder = new CommandHolder();
        commandHolder.setGetCommands(getCommand);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // imitate autorization
        Permition permition = new Permition();
        User user = new User();
        permition.hasPermition(request, user);

        System.out.println(request.getRequestURI());

        // check on FILE NOT FOUND
        Errors errors = new Errors();
        Command command = commandHolder.getGetCommand(request.getRequestURI());
        Validator<Command> commandValidator = new CommandValidator();
        if(!commandValidator.validate(command, errors)){
            command = commandHolder.getPageNotFoundCommand();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(command.execute(request, response));
        dispatcher.forward(request, response);
    }

//    @Override
//    protected void doPut(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//    }
}