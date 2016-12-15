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
import utils.pickers.request.ProfileRequestPicker;
import utils.pickers.request.ProgressRequestPicker;
import validators.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This class initialize TUTOR commands: only tutor has access for this commands.
 *
 * @author Anastasia Milichuk
 * @see controller.commands.Command
 * @see config.CommandsInit
 */
public class TutorCommandsInit implements CommandsInit {
    /**
     * Properties file that contain regular expressions for validators
     */
    private Properties regex;

    /**
     * Constructor which obtain properties file with regular expression and set it.
     *
     * @param regex is file with regular expressions
     */
    public TutorCommandsInit(Properties regex) {
        this.regex = regex;
    }

    /**
     * Initialization of GET commands for TUTOR
     *
     * @return map with key as path for command and directly Command
     */
    @Override
    public Map<String, Command> initGetCommands() {
        Map<String, Command> getCommand = new HashMap<>();
        // ---------- get commands
        // for register
        getCommand.put(User.TUTOR + UrlHolder.PROFILE, new ProfileCommand());
        getCommand.put(User.TUTOR + UrlHolder.MY_COURSES, new UserCoursesCommand());
        getCommand.put(User.TUTOR + UrlHolder.STUDENTS + UrlHolder.SUFFIX,
                new ProgressStudentsCommand(new ParameterValidator()));
        getCommand.put(User.TUTOR + UrlHolder.COURSE, new OpenCourseCommand());
        getCommand.put(User.TUTOR + UrlHolder.COURSE_ADD, new CourseCommand());
        return getCommand;
    }

    /**
     * Initialization of POST commands for STUDENT
     *
     * @return map with key as path for command and directly Command
     */
    @Override
    public Map<String, Command> initPostCommands() {
        Map<String, Command> postCommand = new HashMap<>();
        CourseValidator courseValidator = getCourseValidator();
        // ----------- post commands
        // create
        postCommand.put(User.TUTOR + UrlHolder.COURSE_ADD, new CourseCreateCommand(courseValidator, new CourseRequestPicker()));
        // update
        postCommand.put(User.TUTOR + UrlHolder.PROFILE, new UpdateProfileCommand(getProfileValidator(), new ProfileRequestPicker()));
        postCommand.put(User.TUTOR + UrlHolder.COURSE_EDIT, new CourseUpdateCommand(courseValidator,
                new CourseRequestPicker()));
        postCommand.put(User.TUTOR + UrlHolder.STUDENT_EDIT,
                new ProgressUpdateCommand(new ProgressRequestPicker(), getProgressValidator()));
        // delete
        postCommand.put(User.TUTOR + UrlHolder.COURSE_DELETE, new CourseDeleteCommand());
        postCommand.put(User.TUTOR + UrlHolder.LOGOUT, new LogoutCommand());
        return postCommand;
    }

    /**
     * This method for creating {@link validators.CommandValidator}
     *
     * @return validator for data of course
     */
    private CourseValidator getCourseValidator(){
        return new CourseValidator(regex.getProperty(AttributesHolder.ABOUT));
    }

    /**
     * This method for creating {@link validators.ProgressValidator}
     *
     * @return validator for progress of student
     */
    private ProgressValidator getProgressValidator(){
        return new ProgressValidator(regex.getProperty(AttributesHolder.NOTE),
                regex.getProperty(AttributesHolder.MARK));
    }

    /**
     * This method for creating {@link validators.ProfileValidator}
     *
     * @return validator for user data which gets from profile page
     */
    private ProfileValidator getProfileValidator() {
        return new ProfileValidator(regex.getProperty(AttributesHolder.NAME),
                new EmailValidator(regex.getProperty(AttributesHolder.EMAIL)));
    }
}
