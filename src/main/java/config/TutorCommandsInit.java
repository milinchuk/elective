package config;

import controller.commands.Command;
import controller.commands.authorization.LogoutCommand;
import controller.commands.course.*;
import controller.commands.progress.*;
import controller.commands.user.*;
import model.entity.User;
import utils.constants.UrlHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by click on 12/2/2016.
 */
public class TutorCommandsInit implements CommandsInit {
    @Override
    public Map<String, Command> initGetCommands() {
        Map<String, Command> getCommand = new HashMap<>();
        // ---------- get commands
        // for register
        getCommand.put(User.TUTOR + UrlHolder.PROFILE, new ProfileCommand());
        getCommand.put(User.TUTOR + UrlHolder.MY_COURSES, new UserCoursesCommand());
        getCommand.put(User.TUTOR + UrlHolder.STUDENTS + UrlHolder.SUFFIX, new ProgressStudentsCommand());
        getCommand.put(User.TUTOR + UrlHolder.LOGOUT, new LogoutCommand());
        getCommand.put(User.TUTOR + UrlHolder.COURSE, new OpenCourseCommand());
        getCommand.put(User.TUTOR + UrlHolder.COURSE_ADD, new CourseCommand());
        return getCommand;
    }

    @Override
    public Map<String, Command> initPostCommands() {
        Map<String, Command> postCommand = new HashMap<>();
        // ----------- post commands
        // create
        postCommand.put(User.TUTOR + UrlHolder.COURSE_ADD, new CourseCreateCommand());
        // update
        postCommand.put(User.TUTOR + UrlHolder.PROFILE, new UpdateProfileCommand());
        postCommand.put(User.TUTOR + UrlHolder.COURSE_EDIT, new CourseUpdateCommand());
        postCommand.put(User.TUTOR + UrlHolder.STUDENT_EDIT, new ProgressUpdateCommand());
        // delete
        postCommand.put(User.TUTOR + UrlHolder.COURSE_DELETE, new CourseDeleteCommand());
        return postCommand;
    }
}
