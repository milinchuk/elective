package config;

import controller.commands.Command;
import controller.commands.holder.CommandHolder;
import utils.constants.ResourseNames;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by click on 12/2/2016.
 */
public class CommandHolderFullInit {
    public static CommandHolder init(){
        Properties properties = new Properties();
        try {
            properties.load(CommandHolderFullInit.class.getClassLoader().getResourceAsStream(ResourseNames.REGEX));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DefaultCommandsInit defaultCommandsInit = new DefaultCommandsInit(properties);
        StudentCommandsInit studentCommandsInit = new StudentCommandsInit(properties);
        TutorCommandsInit tutorCommandsInit = new TutorCommandsInit(properties);

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
