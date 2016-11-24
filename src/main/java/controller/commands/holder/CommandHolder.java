package controller.commands.holder;

import controller.commands.Command;
import controller.commands.authorization.PageNotFoundCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by click on 11/18/2016.
 */
public class CommandHolder {
    private Map<String, Command> postCommands;
    private Map<String, Command> getCommands;

    public CommandHolder() {
        postCommands = new HashMap<>();
        getCommands = new HashMap<>();
    }

    public Command getPostCommand(String url){
        return postCommands.get(url);
    }

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

    public Command getPageNotFoundCommand() {
        return new PageNotFoundCommand();
    }
}
