package validators.entity;

import model.entity.User;
import org.apache.commons.lang.StringUtils;
import utils.constants.ErrorsMessagesHolder;
import validators.Errors;
import validators.Validator;

/**
 * Created by click on 11/24/2016.
 */
public class UserLoginValidator implements Validator<User> {
    @Override
    public boolean validate(User user, Errors errors) {
        boolean res = true;
        if (StringUtils.isEmpty(user.getEmail())){
            errors.addMessage(ErrorsMessagesHolder.EMAIL_EMPTY);
            res = false;
        }

        if (StringUtils.isEmpty(user.getPassword())){
            errors.addMessage(ErrorsMessagesHolder.PASSWORD_EMPTY);
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
