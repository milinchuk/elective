package validators;

import controller.commands.Command;

/**
 * Created by click on 11/20/2016.
 */
public class CommandValidator implements Validator {

    public static final String PAGE_NOT_FOUND = "Page not found";

    @Override
    public boolean validate(Object command, Errors errors) {
        if(validate(command)){
            errors.addMessage(PAGE_NOT_FOUND);
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
