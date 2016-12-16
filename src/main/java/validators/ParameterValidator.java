package validators;

import i18n.messages.error.ErrorsMessages;
import org.apache.commons.lang.StringUtils;
import utils.constants.AttributesHolder;
import validators.entity.Errors;

/**
 * @author Anastasia Milinchuk
 */
public class ParameterValidator implements Validator {
    @Override
    public boolean validate(Object o, Errors errors) {
        if(!validate(o)){
            errors.addMessage(AttributesHolder.URL_PARAM, ErrorsMessages.INVALID_NUMBER_VALUE);
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
