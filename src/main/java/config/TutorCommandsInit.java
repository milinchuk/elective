package config;

import controller.commands.Command;
import controller.commands.authorization.LogoutCommand;
import controller.commands.course.*;
import controller.commands.progress.*;
import controller.commands.user.*;
import model.entity.User;
import utils.constants.AttributesHolder;
import utils.constants.ResourseNames;
import utils.constants.UrlHolder;
import utils.pickers.request.CourseRequestPicker;
import utils.pickers.request.ProgressRequestPicker;
import validators.CourseValidator;
import validators.ParameterValidator;
import validators.ProgressValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by click on 12/2/2016.
 */
public class TutorCommandsInit implements CommandsInit {
    private Properties regex;

    public TutorCommandsInit(Properties regex) {
        this.regex = regex;
    }

    @Override
    public Map<String, Command> initGetCommands() {
        Map<String, Command> getCommand = new HashMap<>();
        // ---------- get commands
        // for register
        getCommand.put(User.TUTOR + UrlHolder.PROFILE, new ProfileCommand());
        getCommand.put(User.TUTOR + UrlHolder.MY_COURSES, new UserCoursesCommand());
        getCommand.put(User.TUTOR + UrlHolder.STUDENTS + UrlHolder.SUFFIX,
                new ProgressStudentsCommand(new ParameterValidator()));
        getCommand.put(User.TUTOR + UrlHolder.LOGOUT, new LogoutCommand());
        getCommand.put(User.TUTOR + UrlHolder.COURSE, new OpenCourseCommand());
        getCommand.put(User.TUTOR + UrlHolder.COURSE_ADD, new CourseCommand());
        return getCommand;
    }

    @Override
    public Map<String, Command> initPostCommands() {
        Map<String, Command> postCommand = new HashMap<>();
        CourseValidator courseValidator = getCourseValidator();
        // ----------- post commands
        // create
        postCommand.put(User.TUTOR + UrlHolder.COURSE_ADD, new CourseCreateCommand(courseValidator));
        // update
        postCommand.put(User.TUTOR + UrlHolder.PROFILE, new UpdateProfileCommand());
        postCommand.put(User.TUTOR + UrlHolder.COURSE_EDIT, new CourseUpdateCommand(courseValidator,
                new CourseRequestPicker()));
        postCommand.put(User.TUTOR + UrlHolder.STUDENT_EDIT,
                new ProgressUpdateCommand(new ProgressRequestPicker(), getProgressValidator()));
        // delete
        postCommand.put(User.TUTOR + UrlHolder.COURSE_DELETE, new CourseDeleteCommand());
        return postCommand;
    }

    private CourseValidator getCourseValidator(){
        return new CourseValidator(regex.getProperty(AttributesHolder.ABOUT));
    }

    private ProgressValidator getProgressValidator(){
        return new ProgressValidator(regex.getProperty(AttributesHolder.NOTE),
                regex.getProperty(AttributesHolder.MARK));
    }
}
