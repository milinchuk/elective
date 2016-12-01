package config;

import controller.commands.Command;
import controller.commands.holder.CommandHolder;

import java.util.Map;

/**
 * Created by click on 12/2/2016.
 */
public class CommandHolderFullInit {
    public static CommandHolder init(){
        DefaultCommandsInit defaultCommandsInit = new DefaultCommandsInit();
        StudentCommandsInit studentCommandsInit = new StudentCommandsInit();
        TutorCommandsInit tutorCommandsInit = new TutorCommandsInit();

        Map<String, Command> getCommands = defaultCommandsInit.initGetCommands();
        getCommands.putAll(studentCommandsInit.initGetCommands());
        getCommands.putAll(tutorCommandsInit.initGetCommands());

        Map<String,Command> postCommands = defaultCommandsInit.initPostCommands();
        postCommands.putAll(studentCommandsInit.initPostCommands());
        postCommands.putAll(tutorCommandsInit.initPostCommands());

        CommandHolder commandHolder = new CommandHolder();
        commandHolder.setGetCommands(getCommands);
        commandHolder.setPostCommands(postCommands);
        return commandHolder;
    }

}
