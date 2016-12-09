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
 * Created by click on 12/2/2016.
 */
public class StudentCommandsInit implements CommandsInit {
    private Properties regex;

    public StudentCommandsInit(Properties regex) {
        this.regex = regex;
    }

    @Override
    public Map<String, Command> initGetCommands() {
        Map<String, Command> getCommand = new HashMap<>();
        // ---------- get commands ----------
        getCommand.put(User.STUDENT + UrlHolder.PROFILE, new ProfileCommand());
        getCommand.put(User.STUDENT + UrlHolder.MY_COURSES, new UserCoursesCommand());
        getCommand.put(User.STUDENT + UrlHolder.FIND, new FindCoursesCommand());
        return getCommand;
    }

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

    private ProfileValidator getProfileValidator() {
        return new ProfileValidator(regex.getProperty(AttributesHolder.NAME),
                new EmailValidator(regex.getProperty(AttributesHolder.EMAIL)));
    }
}
