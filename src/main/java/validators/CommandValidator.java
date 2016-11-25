package validators;

import controller.commands.Command;
import utils.constants.AttributesHolder;
import utils.constants.ErrorsMessagesHolder;

/**
 * Created by click on 11/20/2016.
 */
public class CommandValidator implements Validator {
    @Override
    public boolean validate(Object command, Errors errors) {
        if(validate(command)){
            errors.addMessage(AttributesHolder.COMMAND, ErrorsMessagesHolder.PAGE_NOT_FOUND);
            errors.setResult(true);
            return false;
        }
        return true;
    }

    @Override
    public boolean validate(Object command) {
        if(command == null){
            return false;
        }
        return true;
    }
}
