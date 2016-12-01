package validators;

import i18n.messages.error.ErrorsMessages;
import utils.constants.AttributesHolder;

/**
 * Created by click on 11/20/2016.
 */
public class CommandValidator implements Validator {
    @Override
    public boolean validate(Object command, Errors errors) {
        if(validate(command)){
            errors.addMessage(AttributesHolder.COMMAND, ErrorsMessages.PAGE_NOT_FOUND);
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
