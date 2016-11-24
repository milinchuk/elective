package validators;

import org.apache.commons.lang.StringUtils;

/**
 * Created by click on 11/21/2016.
 */
public class ParameterValidator implements Validator {
    public static final String INVALID_NUMBER_VALUE = "Invalid number value";

    @Override
    public boolean validate(Object o, Errors errors) {
        if(!validate(o)){
            errors.addMessage(INVALID_NUMBER_VALUE);
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
