package validators;

import i18n.messages.error.ErrorsMessages;
import model.entity.User;
import org.apache.commons.lang.StringUtils;
import utils.constants.AttributesHolder;
import validators.entity.Errors;

/**
 * Created by click on 11/24/2016.
 */
public class UserLoginValidator implements Validator<User> {
    @Override
    public boolean validate(User user, Errors errors) {
        boolean res = true;
        if (StringUtils.isEmpty(user.getEmail())){
            errors.addMessage(AttributesHolder.EMAIL, ErrorsMessages.EMAIL_EMPTY);
            res = false;
        }

        if (StringUtils.isEmpty(user.getPassword())){
            errors.addMessage(AttributesHolder.PASSWORD, ErrorsMessages.PASSWORD_EMPTY);
            res = false;
        }
        return res;
    }

    @Override
    public boolean validate(User user) {
        if (StringUtils.isEmpty(user.getEmail())){
            return false;
        }

        if (StringUtils.isEmpty(user.getPassword())){
            return false;
        }
        return true;
    }
}
