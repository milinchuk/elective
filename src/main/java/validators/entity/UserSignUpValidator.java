package validators.entity;

import i18n.messages.error.ErrorsMessages;
import model.entity.User;
import utils.constants.AttributesHolder;
import validators.Errors;
import validators.Validator;

import java.util.regex.Pattern;

/**
 * Created by click on 11/24/2016.
 */
public class UserSignUpValidator implements Validator<User> {
    private Pattern emailPattern;
    private Pattern passwordPattern;
    private Pattern namePattern;

    public UserSignUpValidator() {
        String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String passwordRegex = "[^\\s]{4,}";
        String nameRegex = "^[\\p{L} \\.'\\-]+$";
        emailPattern = Pattern.compile(emailRegex);
        passwordPattern = Pattern.compile(passwordRegex);
        namePattern = Pattern.compile(nameRegex);
    }

    @Override
    public boolean validate(User user, Errors errors) {
        if (!namePattern.matcher(user.getFirstName()).matches()){
            reject(errors, AttributesHolder.FIRST_NAME, ErrorsMessages.FIRST_NAME_INVALID);
        }
        if (!namePattern.matcher(user.getLastName()).matches()){
            reject(errors, AttributesHolder.LAST_NAME, ErrorsMessages.LAST_NAME_INVALID);
        }
        if (user.getRole() == null) {
            reject(errors, AttributesHolder.ROLE, ErrorsMessages.ROLE_INVALID);
        }
        if (!emailPattern.matcher(user.getEmail()).matches()) {
            reject(errors, AttributesHolder.EMAIL, ErrorsMessages.EMAIL_INVALID);
        }
        if (!passwordPattern.matcher(user.getPassword()).matches() ||
                !user.getPassword().equals(user.getConfirmPassword())) {
            reject(errors, AttributesHolder.PASSWORD, ErrorsMessages.PASSWORD_INVALID);
        }
        return !errors.hasError();
    }

    @Override
    public boolean validate(User user) {
        if (namePattern.matcher(user.getFirstName()).matches()){
            return false;
        }
        if (namePattern.matcher(user.getLastName()).matches()){
            return false;
        }
        if (user.getRole() == null) {
            return false;
        }
        if (!emailPattern.matcher(user.getEmail()).matches()) {
            return false;
        }
        if (!passwordPattern.matcher(user.getPassword()).matches() ||
                !user.getPassword().equals(user.getConfirmPassword())) {
            return false;
        }
        return true;
    }

    private boolean reject(Errors errors, String attribute, String message){
        errors.addMessage(attribute, message);
        errors.setResult(false);
        return false;
    }

}
