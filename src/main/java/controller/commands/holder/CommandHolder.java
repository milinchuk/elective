package controller.commands.holder;

import controller.commands.Command;
import controller.commands.authorization.PageNotFoundCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * This is holder of commands. It contains with post commands and get commands.
 * All of commands store in maps.
 *
 * @author Anastasia Milichuk
 * @see controller.commands.Command
 */
public class CommandHolder {
    /**
     * Map with post commands. Key is path to command.
     * Value is directly command.
     */
    private Map<String, Command> postCommands;

    /**
     * Map with get commands. Key is path to command.
     * Value is directly command.
     */
    private Map<String, Command> getCommands;

    public CommandHolder() {
        postCommands = new HashMap<>();
        getCommands = new HashMap<>();
    }

    /**
     * This method retrieves post command by URL
     * @param url is request URL
     * @return command
     */
    public Command getPostCommand(String url){
        return postCommands.get(url);
    }

    /**
     * This method retrieve get command by URL
     * @param url is request URL
     * @return command
     */
    public Command getGetCommand(String url){
        return getCommands.get(url);
    }

    public Map<String, Command> getPostCommands() {
        return postCommands;
    }

    public void setPostCommands(Map<String, Command> postCommands) {
        this.postCommands = postCommands;
    }

    public Map<String, Command> getGetCommands() {
        return getCommands;
    }

    public void setGetCommands(Map<String, Command> getCommands) {
        this.getCommands = getCommands;
    }

    /**
     * This method return page not found.
     *
     * @return page not found command
     */
    public Command getPageNotFoundCommand() {
        return new PageNotFoundCommand();
    }
}
