package config;

import controller.commands.Command;
import controller.commands.holder.CommandHolder;
import utils.constants.ResourseNames;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * This class put together all commands that configure in project
 * and init {@link controller.commands.holder.CommandHolder}. Commands divide by purpose,
 * for example: default, for student, for tutor. And for type: get/post.
 *
 * @author Anastasia Milinchuk
 * @see controller.commands.holder.CommandHolder
 */
public class CommandHolderFullInit {
    /**
     * This method gather commands and fill CommandHolder with these commands.
     * Get properties file with regular expression, which use in commands.
     *
     * @return holder of commands that contain all commands that use in project.
     */
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
