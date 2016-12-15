package config;

import controller.commands.Command;
import controller.commands.authorization.LogoutCommand;
import controller.commands.course.CourseFollowCommand;
import controller.commands.course.FindCoursesCommand;
import controller.commands.course.UserCoursesCommand;
import controller.commands.progress.CourseUnfollowCommand;
import controller.commands.user.ProfileCommand;
import controller.commands.user.UpdateProfileCommand;
import model.entity.User;
import utils.constants.AttributesHolder;
import utils.constants.UrlHolder;
import utils.pickers.request.ProfileRequestPicker;
import utils.pickers.request.ProgressRequestPicker;
import validators.EmailValidator;
import validators.ParameterValidator;
import validators.ProfileValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This class initialize STUDENT commands: only student has access for this commands.
 *
 * @author Anastasia Milichuk
 * @see controller.commands.Command
 * @see config.CommandsInit
 */
public class StudentCommandsInit implements CommandsInit {
    /**
     * Properties file that contain regular expressions for validators
     */
    private Properties regex;

    /**
     * Constructor which obtain properties file with regular expression and set it.
     *
     * @param regex is file with regular expressions
     */
    public StudentCommandsInit(Properties regex) {
        this.regex = regex;
    }

    /**
     * Initialization of GET commands for STUDENT
     *
     * @return map with key as path for command and directly Command
     */
    @Override
    public Map<String, Command> initGetCommands() {
        Map<String, Command> getCommand = new HashMap<>();
        // ---------- get commands ----------
        getCommand.put(User.STUDENT + UrlHolder.PROFILE, new ProfileCommand());
        getCommand.put(User.STUDENT + UrlHolder.MY_COURSES, new UserCoursesCommand());
        getCommand.put(User.STUDENT + UrlHolder.FIND, new FindCoursesCommand());
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
        // ----------- post commands
        // create
        postCommand.put(User.STUDENT + UrlHolder.FOLLOW, new CourseFollowCommand());
        // update
        postCommand.put(User.STUDENT + UrlHolder.PROFILE, new UpdateProfileCommand(getProfileValidator(),
                new ProfileRequestPicker()));
        // delete
        postCommand.put(User.STUDENT + UrlHolder.UNFOLLOW, new CourseUnfollowCommand(new ParameterValidator()));
        postCommand.put(User.STUDENT + UrlHolder.LOGOUT, new LogoutCommand());
        return postCommand;
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
