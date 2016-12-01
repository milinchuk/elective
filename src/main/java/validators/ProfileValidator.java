package validators;

import i18n.messages.error.ErrorsMessages;
import model.entity.User;
import utils.constants.AttributesHolder;
import validators.entity.Errors;

import java.util.regex.Pattern;

/**
 * Created by click on 12/1/2016.
 */
public class ProfileValidator implements Validator<User> {
    private Pattern namePattern;
    private EmailValidator emailValidator;
    @Override
    public boolean validate(User user, Errors errors) {
        if (!namePattern.matcher(user.getFirstName()).matches()) {
            setError(errors, AttributesHolder.FIRST_NAME, ErrorsMessages.FIRST_NAME_INVALID);
        }

        if (!namePattern.matcher(user.getLastName()).matches()) {
            setError(errors, AttributesHolder.LAST_NAME, ErrorsMessages.LAST_NAME_INVALID);
        }

        emailValidator.validate(user.getEmail(), errors);

        return errors.getResult();
    }

    @Override
    public boolean validate(User user) {
        if ( (!namePattern.matcher(user.getFirstName()).matches()) ||
                (!namePattern.matcher(user.getLastName()).matches()) ||
                (!emailValidator.validate(user.getEmail())) ) {
            return false;
        }
        return true;
    }

    private void setError(Errors errors, String attribute, String errorMessage) {
        errors.setResult(false);
        errors.addMessage(attribute, errorMessage);
    }
}
