package controller; /**
 * Created by click on 11/5/2016.
 */
import controller.commands.Command;
import controller.commands.CommandHolder;
import controller.commands.authorization.LoginCommand;
import controller.commands.authorization.SignUpCommand;
import controller.commands.course.*;
import controller.commands.progress.ProgressFindByCourseCommand;
import controller.commands.progress.ProgressFindCommand;
import controller.commands.progress.ProgressUpdateCommand;
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
        getCommand.put("/", new CourseFindAllCommand());
        getCommand.put("/courses", new CourseFindAllCommand());
        getCommand.put("/courses/", new CourseFindByUserCommand());
        getCommand.put("/course/", new CourseFindOneCommand());
        getCommand.put("/students/", new ProgressFindByCourseCommand());
        getCommand.put("/student/", new ProgressFindCommand());
        getCommand.put("/login", new LoginCommand());
        getCommand.put("/signup", new SignUpCommand());
        getCommand.put("/profile", new ProfileCommand());
        getCommand.put("/myCourses", new ProgressesUserCommand());
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

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        super.doPost(request, response);
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        while (request.getAttributeNames().hasMoreElements()){
            request.removeAttribute(request.getAttributeNames().nextElement());
        }
        Permition permition = new Permition();
        User user = new User();
        permition.hasPermition(request, user);

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