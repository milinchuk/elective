package validators;

import controller.commands.Command;

/**
 * Created by click on 11/20/2016.
 */
public class CommandValidator implements Validator <Command> {
    @Override
    public boolean validate(Command command, Errors errors) {
        if(command == null){
            errors.addMessage("Page Not Found");
            errors.setResult(true);
            return false;
        }
        return true;
    }
}
