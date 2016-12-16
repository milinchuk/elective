package validators;

import i18n.messages.error.ErrorsMessages;
import utils.constants.AttributesHolder;
import validators.entity.Errors;

/**
 * @author Anastasia Milinchuk
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
        return !(command == null);
    }
}
