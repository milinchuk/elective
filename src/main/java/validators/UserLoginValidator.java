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
        if (user != null) {
            if (StringUtils.isEmpty(user.getEmail())) {
                setError(errors, AttributesHolder.EMAIL, ErrorsMessages.EMAIL_EMPTY);
            }

            if (StringUtils.isEmpty(user.getPassword())) {
                setError(errors, AttributesHolder.PASSWORD, ErrorsMessages.PASSWORD_EMPTY);
            }
        } else {
            setError(errors, AttributesHolder.USER, ErrorsMessages.INVALID);
        }
        return errors.getResult();
    }

    @Override
    public boolean validate(User user) {
        return  !( (StringUtils.isEmpty(user.getEmail())) ||
                    (StringUtils.isEmpty(user.getPassword())) );
    }

    private void setError(Errors errors, String attribute, String errorMessage) {
        errors.setResult(false);
        errors.addMessage(attribute, errorMessage);
    }
}
