package validators;

import org.apache.commons.lang.StringUtils;

/**
 * Created by click on 11/21/2016.
 */
public class ParameterValidator implements Validator<String> {
    @Override
    public boolean validate(String s, Errors errors) {
        if(StringUtils.isNotEmpty(s) && StringUtils.isNumeric(s)){
            return true;
        }
        return false;
    }
}
