package controller.commands.authorization;

import controller.commands.Command;
import controller.security.EncryptPassword;
import controller.i18n.messages.error.ErrorsMessages;
import model.entity.User;
import service.UserServiceImpl;
import service.interfaces.UserService;
import utils.constants.AttributesHolder;
import utils.constants.UrlHolder;
import utils.pickers.request.LoginDataRequestPicker;
import validators.entity.Errors;
import validators.UserLoginValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Anastasia Milinchuk
 */
public class LoginCommand implements Command {
    protected UserService userService = UserServiceImpl.getInstance();
    private LoginDataRequestPicker loginDataRequestPicker;
    private UserLoginValidator userLoginValidator;

    public LoginCommand(LoginDataRequestPicker loginDataRequestPicker, UserLoginValidator userLoginValidator) {
        this.loginDataRequestPicker = loginDataRequestPicker;
        this.userLoginValidator = userLoginValidator;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = loginDataRequestPicker.pick(request);
        // validate
        Errors errors = new Errors();
        if (userLoginValidator.validate(user, errors)){
            // encrypt password
            user.setPassword(EncryptPassword.encrypt(user.getPassword()));
            // find profile
            User existUser = userService.findOne(user.getEmail());
            if ( (existUser != null) && (user.getEmail().equals(existUser.getEmail())
                    && user.getPassword().equals(existUser.getPassword()))) {
                // user authenticate , redirect to profile
                HttpSession session = request.getSession();
                session.setAttribute(AttributesHolder.ID, existUser.getId());
                session.setAttribute(AttributesHolder.ROLE, existUser.getRole());
                request.setAttribute(AttributesHolder.USER, existUser);
                return UrlHolder.PROFILE;
            } else {
                // invalid auth data
                errors.addMessage(AttributesHolder.EMAIL, ErrorsMessages.INVALID_AUTH_DATA);
                errors.setResult(false);
            }
        }

        request.getSession().setAttribute(AttributesHolder.USER, user);
        request.getSession().setAttribute(AttributesHolder.ERRORS, errors);
        return UrlHolder.LOGIN;
    }
}
