package validators.entity;

import model.entity.User;
import org.apache.commons.lang.StringUtils;
import validators.Errors;
import validators.Validator;

import java.util.SplittableRandom;
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
            reject(errors, "first.name.invalid");
        }
        if (!namePattern.matcher(user.getLastName()).matches()){
            reject(errors, "last.name.invalid");
        }
        if (user.getRole() == null) {
            reject(errors, "role.invalid");
        }
        if (!emailPattern.matcher(user.getEmail()).matches()) {
            reject(errors, "email.invalid");
        }
        if (!passwordPattern.matcher(user.getPassword()).matches() ||
                !user.getPassword().equals(user.getConfirmPassword())) {
            reject(errors, "password.invalid");
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

    private boolean reject(Errors errors, String message){
        errors.addMessage(message);
        errors.setResult(false);
        return false;
    }

}
