package controller.commands.authorization;

import controller.commands.Command;
import controller.security.EncryptPassword;
import controller.i18n.messages.error.ErrorsMessages;
import model.entity.User;
import service.UserServiceImpl;
import service.interfaces.UserService;
import utils.constants.AttributesHolder;
import utils.constants.UrlHolder;
import utils.pickers.request.SignupDataRequestPicker;
import validators.entity.Errors;
import validators.UserSignUpValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Anastasia Milinchuk
 */
public class SignUpCommand implements Command {
    protected UserService userService = UserServiceImpl.getInstance();
    private SignupDataRequestPicker signupDataRequestPicker;
    private UserSignUpValidator userSignUpValidator;

    public SignUpCommand(SignupDataRequestPicker signupDataRequestPicker, UserSignUpValidator userSignUpValidator) {
        this.signupDataRequestPicker = signupDataRequestPicker;
        this.userSignUpValidator = userSignUpValidator;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = signupDataRequestPicker.pick(request);
        Errors errors = new Errors();
        if (userSignUpValidator.validate(user, errors)) {
            // generate hash password
            user.setPassword(EncryptPassword.encrypt(user.getPassword()));

            // check if user exist
            if (userService.findOne(user.getEmail()) == null) {
                // save
                userService.create(user);
                user = userService.findOne(user.getEmail());

                // update session
                HttpSession session = request.getSession();
                session.setAttribute(AttributesHolder.ID, user.getId());
                session.setAttribute(AttributesHolder.ROLE, user.getRole());

                // redirect to profile
                request.setAttribute(AttributesHolder.USER, userService.findOne(user.getEmail()));
                return UrlHolder.PROFILE;
            }
            // user exist
            errors.addMessage(AttributesHolder.EMAIL, ErrorsMessages.USER_EXIST);
            errors.setResult(false);
        }
        user.setPassword(null);
        user.setConfirmPassword(null);
        request.getSession().setAttribute(AttributesHolder.USER, user);
        request.getSession().setAttribute(AttributesHolder.ERRORS, errors);
        return UrlHolder.SIGNUP;
    }
}
