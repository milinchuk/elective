package config;

import controller.commands.Command;
import controller.commands.authorization.*;
import utils.constants.AttributesHolder;
import utils.constants.UrlHolder;
import utils.pickers.request.LoginDataRequestPicker;
import utils.pickers.request.SignupDataRequestPicker;
import validators.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This class initialize DEFAULT commands: commands, which can use user without rights.
 * Example commands: log in, sign up, etc.
 *
 * @author Anastasia Milichuk
 * @see controller.commands.Command
 * @see config.CommandsInit
 */
public class DefaultCommandsInit implements CommandsInit {
    /**
     * Properties file that contain regular expressions
     */
    private Properties regex;


    /**
     * Constructor which obtain properties file with regular expression and set it.
     *
     * @param regex is file with regular expressions
     */
    public DefaultCommandsInit(Properties regex) {
        this.regex = regex;
    }

    /**
     * Initialization of POST commands for DEFAULT
     *
     * @return map with key as path for command and directly Command
     */
    @Override
    public Map<String, Command> initPostCommands() {
        Map<String, Command> postCommand = new HashMap<>();
        postCommand.put(UrlHolder.NULL + UrlHolder.LOGIN, new LoginCommand(new LoginDataRequestPicker(),
                getUserLoginValidator()));
        postCommand.put(UrlHolder.NULL + UrlHolder.SIGNUP, new SignUpCommand(new SignupDataRequestPicker(),
                getUserSignUpValidator()));
        return postCommand;
    }

    /**
     * Initialization of GET commands for DEFAULT
     *
     * @return map with key as path for command and directly Command
     */
    @Override
    public Map<String, Command> initGetCommands() {
        Map<String, Command> getCommand = new HashMap<>();
        getCommand.put(UrlHolder.NULL + UrlHolder.LOGIN, new OpenLoginCommand());
        getCommand.put(UrlHolder.NULL + UrlHolder.SIGNUP, new OpenSignUpCommand());
        return getCommand;
    }

    /**
     * This method for creating {@link validators.UserLoginValidator}
     *
     * @return validator for user data which gets from log in page
     */
    private UserLoginValidator getUserLoginValidator(){
        return new UserLoginValidator();
    }

    /**
     * This method for creating {@link validators.UserSignUpValidator}
     *
     * @return validator for user data which gets from sign up page
     */
    private UserSignUpValidator getUserSignUpValidator(){
        return new UserSignUpValidator( new EmailValidator(regex.getProperty(AttributesHolder.EMAIL)),
                regex.getProperty(AttributesHolder.PASSWORD),
                regex.getProperty(AttributesHolder.NAME));
    }
}
