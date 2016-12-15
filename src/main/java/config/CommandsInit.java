package config;

import controller.commands.Command;

import java.util.Map;

/**
 * This interface provide behaviour for initialization of get and post commands.
 *
 * @author Anastasia Milichuk
 * @see controller.commands.Command
 */
public interface CommandsInit {
    /**
     * Initialization of GET commands
     *
     * @return map with key as path for command and directly Command
     */
    Map<String, Command> initGetCommands();

    /**
     * Initialization of POST commands
     *
     * @return map with key as path for command and directly Command
     */
    Map<String, Command> initPostCommands();
}
