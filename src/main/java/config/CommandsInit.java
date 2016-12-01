package config;

import controller.commands.Command;

import java.util.Map;

/**
 * Created by click on 12/2/2016.
 */
public interface CommandsInit {
    Map<String, Command> initGetCommands();
    Map<String, Command> initPostCommands();
}
