package controller.commands;

import controller.commands.authorization.PageNotFoundCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by click on 11/18/2016.
 */
public class CommandHolder {
    private Map<String, Command> postCommands;
    private Map<String, Command> getCommands;
    private Map<String, Command> putCommands;
    private Map<String, Command> deleteCommands;

    public CommandHolder() {
        postCommands = new HashMap<>();
        getCommands = new HashMap<>();
        putCommands = new HashMap<>();
        putCommands = new HashMap<>();
    }

    public Command getPostCommand(String url){
        return postCommands.get(url);
    }

    public Command getGetCommand(String url){
        return getCommands.get(url);
    }

    public Command getPutCommand(String url){
        return putCommands.get(url);
    }

    public Command getDeleteCommand(String url){
        return deleteCommands.get(url);
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

    public Map<String, Command> getPutCommands() {
        return putCommands;
    }

    public void setPutCommands(Map<String, Command> putCommands) {
        this.putCommands = putCommands;
    }

    public Map<String, Command> getDeleteCommands() {
        return deleteCommands;
    }

    public void setDeleteCommands(Map<String, Command> deleteCommands) {
        this.deleteCommands = deleteCommands;
    }

    public Command getPageNotFoundCommand() {
        return new PageNotFoundCommand();
    }
}
