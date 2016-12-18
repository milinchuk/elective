package validators;

import controller.i18n.messages.error.ErrorsMessages;
import utils.constants.AttributesHolder;
import validators.entity.Errors;

import java.util.regex.Pattern;

/**
 * @author Anastasia Milinchuk
 */
public class EmailValidator implements Validator<String>{
    private Pattern emailPattern;

    public EmailValidator(String emailRegex) {
        emailPattern = Pattern.compile(emailRegex);
    }

    @Override
    public boolean validate(String email, Errors errors) {
        if (!validate(email)) {
            errors.setResult(false);
            errors.addMessage(AttributesHolder.EMAIL, ErrorsMessages.EMAIL_INVALID);
            return errors.getResult();
        }
        return true;
    }

    @Override
    public boolean validate(String email) {
        if (!emailPattern.matcher(email).matches()) {
            return false;
        }
        return true;
    }
}
