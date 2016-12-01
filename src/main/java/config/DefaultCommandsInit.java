package config;

import controller.commands.Command;
import controller.commands.authorization.LoginCommand;
import controller.commands.authorization.OpenLoginCommand;
import controller.commands.authorization.OpenSignUpCommand;
import controller.commands.authorization.SignUpCommand;
import utils.constants.UrlHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by click on 12/2/2016.
 */
public class DefaultCommandsInit implements CommandsInit {
    @Override
    public Map<String, Command> initPostCommands() {
        Map<String, Command> postCommand = new HashMap<>();
        postCommand.put(UrlHolder.NULL + UrlHolder.LOGIN, new LoginCommand());
        postCommand.put(UrlHolder.NULL + UrlHolder.SIGNUP, new SignUpCommand());
        return postCommand;
    }

    @Override
    public Map<String, Command> initGetCommands() {
        Map<String, Command> getCommand = new HashMap<>();
        getCommand.put(UrlHolder.NULL + UrlHolder.LOGIN, new OpenLoginCommand());
        getCommand.put(UrlHolder.NULL + UrlHolder.SIGNUP, new OpenSignUpCommand());
        return getCommand;
    }
}
