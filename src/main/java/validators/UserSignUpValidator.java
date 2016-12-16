package validators;

import i18n.messages.error.ErrorsMessages;
import model.entity.User;
import utils.constants.AttributesHolder;
import validators.entity.Errors;

import java.util.regex.Pattern;

/**
 * @author Anastasia Milinchuk
 */
public class UserSignUpValidator implements Validator<User> {
    private EmailValidator emailValidator;
    private Pattern passwordPattern;
    private Pattern namePattern;

    public UserSignUpValidator(EmailValidator emailValidator, String passwordRegex, String nameRegex) {
        this.emailValidator = emailValidator;
        passwordPattern = Pattern.compile(passwordRegex);
        namePattern = Pattern.compile(nameRegex);
    }

    @Override
    public boolean validate(User user, Errors errors) {
        if (user != null) {
            if (!namePattern.matcher(user.getFirstName()).matches()) {
                reject(errors, AttributesHolder.FIRST_NAME, ErrorsMessages.FIRST_NAME_INVALID);
            }
            if (!namePattern.matcher(user.getLastName()).matches()) {
                reject(errors, AttributesHolder.LAST_NAME, ErrorsMessages.LAST_NAME_INVALID);
            }
            if (user.getRole() == null) {
                reject(errors, AttributesHolder.ROLE, ErrorsMessages.ROLE_INVALID);
            }
            emailValidator.validate(user.getEmail(), errors);
            if (!passwordPattern.matcher(user.getPassword()).matches() ||
                    !user.getPassword().equals(user.getConfirmPassword())) {
                reject(errors, AttributesHolder.PASSWORD, ErrorsMessages.PASSWORD_INVALID);
            }
        } else {
            reject(errors, AttributesHolder.USER, ErrorsMessages.INVALID);
        }
        return !errors.hasError();
    }

    @Override
    public boolean validate(User user) {
        return !((namePattern.matcher(user.getFirstName()).matches()) ||
                (namePattern.matcher(user.getLastName()).matches()) ||
                (user.getRole() == null) ||
                (!emailValidator.validate(user.getEmail())) ||
                (!passwordPattern.matcher(user.getPassword()).matches() ||
                !user.getPassword().equals(user.getConfirmPassword())));
    }

    private void reject(Errors errors, String attribute, String message){
        errors.addMessage(attribute, message);
        errors.setResult(false);
    }

}
