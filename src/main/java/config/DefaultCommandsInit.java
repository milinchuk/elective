package config;

import controller.commands.Command;
import controller.commands.authorization.LoginCommand;
import controller.commands.authorization.OpenLoginCommand;
import controller.commands.authorization.OpenSignUpCommand;
import controller.commands.authorization.SignUpCommand;
import utils.constants.AttributesHolder;
import utils.constants.UrlHolder;
import utils.pickers.request.LoginDataRequestPicker;
import utils.pickers.request.SignupDataRequestPicker;
import validators.UserLoginValidator;
import validators.UserSignUpValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by click on 12/2/2016.
 */
public class DefaultCommandsInit implements CommandsInit {
    private Properties regex;

    public DefaultCommandsInit(Properties regex) {
        this.regex = regex;
    }

    @Override
    public Map<String, Command> initPostCommands() {
        Map<String, Command> postCommand = new HashMap<>();
        postCommand.put(UrlHolder.NULL + UrlHolder.LOGIN, new LoginCommand(new LoginDataRequestPicker(),
                getUserLoginValidator()));
        postCommand.put(UrlHolder.NULL + UrlHolder.SIGNUP, new SignUpCommand(new SignupDataRequestPicker(),
                getUserSignUpValidator()));
        return postCommand;
    }

    @Override
    public Map<String, Command> initGetCommands() {
        Map<String, Command> getCommand = new HashMap<>();
        getCommand.put(UrlHolder.NULL + UrlHolder.LOGIN, new OpenLoginCommand());
        getCommand.put(UrlHolder.NULL + UrlHolder.SIGNUP, new OpenSignUpCommand());
        return getCommand;
    }

    private UserLoginValidator getUserLoginValidator(){
        return new UserLoginValidator();
    }

    private UserSignUpValidator getUserSignUpValidator(){
        return new UserSignUpValidator(regex.getProperty(AttributesHolder.EMAIL),
                regex.getProperty(AttributesHolder.PASSWORD),
                regex.getProperty(AttributesHolder.NAME));
    }
}
