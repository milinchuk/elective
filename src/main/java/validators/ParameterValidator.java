package validators;

import org.apache.commons.lang.StringUtils;
import utils.constants.ErrorsMessagesHolder;

/**
 * Created by click on 11/21/2016.
 */
public class ParameterValidator implements Validator {
    @Override
    public boolean validate(Object o, Errors errors) {
        if(!validate(o)){
            errors.addMessage(ErrorsMessagesHolder.INVALID_NUMBER_VALUE);
            errors.setResult(true);
            return false;
        }
        return true;
    }

    @Override
    public boolean validate(Object o) {
        if(!StringUtils.isNumeric(String.valueOf(o))){
            return false;
        }
        return true;
    }
}
