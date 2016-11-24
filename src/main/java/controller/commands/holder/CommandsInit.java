package controller.commands.holder;

import controller.commands.Command;
import controller.commands.authorization.LoginCommand;
import controller.commands.authorization.LogoutCommand;
import controller.commands.authorization.SignUpCommand;
import controller.commands.course.*;
import controller.commands.progress.CourseUnfollowCommand;
import controller.commands.progress.ProgressStudentsCommand;
import controller.commands.progress.ProgressUpdateCommand;
import controller.commands.user.ProfileCommand;
import controller.commands.user.UpdateProfileCommand;
import model.entity.User;
import utils.constants.UrlHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by click on 11/24/2016.
 */
public class CommandsInit {
    public void initCommandHolder(CommandHolder commandHolder){
        initStudentCommands(commandHolder);
        initTutorCommands(commandHolder);
        initUnregisterCommands(commandHolder);
    }

    public void initStudentCommands(CommandHolder commandHolder){
        Map<String, Command> getCommand = commandHolder.getGetCommands();
        Map<String, Command> postCommand = commandHolder.getPostCommands();
        // ---------- get commands ----------
        getCommand.put(User.STUDENT + UrlHolder.PROFILE, new ProfileCommand());
        getCommand.put(User.STUDENT + UrlHolder.MY_COURSES, new UserCoursesCommand());
        getCommand.put(User.STUDENT + UrlHolder.FIND, new FindCoursesCommand());
        getCommand.put(User.STUDENT + UrlHolder.LOGOUT, new LogoutCommand());

        // ----------- post commands
        // create
        postCommand.put(User.STUDENT + UrlHolder.FOLLOW, new CourseFollowCommand());
        // update
        postCommand.put(User.STUDENT + UrlHolder.PROFILE, new UpdateProfileCommand());
        // delete
        postCommand.put(User.STUDENT + UrlHolder.UNFOLLOW, new CourseUnfollowCommand());
    }

    public void initTutorCommands(CommandHolder commandHolder){
        Map<String, Command> getCommand = commandHolder.getGetCommands();
        Map<String, Command> postCommand = commandHolder.getPostCommands();
        // ---------- get commands
        // for register
        getCommand.put(User.TUTOR + UrlHolder.PROFILE, new ProfileCommand());
        getCommand.put(User.TUTOR + UrlHolder.MY_COURSES, new UserCoursesCommand());
        getCommand.put(User.TUTOR + UrlHolder.STUDENTS + UrlHolder.SUFFIX, new ProgressStudentsCommand());
        getCommand.put(User.TUTOR + UrlHolder.LOGOUT, new LogoutCommand());
        getCommand.put(User.TUTOR + UrlHolder.COURSE, new OpenCourseCommand());
        getCommand.put(User.TUTOR + UrlHolder.COURSE_ADD, new CourseCommand());

        // ----------- post commands
        // create
        postCommand.put(User.TUTOR + UrlHolder.COURSE_ADD, new CourseCreateCommand());
        // update
        postCommand.put(User.TUTOR + UrlHolder.PROFILE, new UpdateProfileCommand());
        postCommand.put(User.TUTOR + UrlHolder.COURSE_EDIT, new CourseUpdateCommand());
        postCommand.put(User.TUTOR + UrlHolder.STUDENT_EDIT, new ProgressUpdateCommand());
        // delete
        postCommand.put(User.TUTOR + UrlHolder.COURSE_DELETE, new CourseDeleteCommand());
    }

    public void initUnregisterCommands(CommandHolder commandHolder){
        // get commands
        // for unregister
        Map<String, Command> getCommand = commandHolder.getGetCommands();
        getCommand.put(UrlHolder.LOGIN, new LoginCommand());
        getCommand.put(UrlHolder.SIGNUP, new SignUpCommand());
    }

}
