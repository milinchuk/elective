package controller.commands.authorization;

import controller.commands.Command;
import controller.security.EncryptPassword;
import model.entity.User;
import model.service.UserServiceImpl;
import model.service.interfaces.UserService;
import utils.constants.AttributesHolder;
import utils.constants.ErrorsMessagesHolder;
import utils.constants.PagesHolder;
import utils.pickers.request.LoginDataPicker;
import validators.Errors;
import validators.entity.UserLoginValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by click on 11/18/2016.
 */
public class LoginCommand implements Command {
    protected UserService userService = UserServiceImpl.getInstance();
    protected LoginDataPicker loginDataPicker = new LoginDataPicker();
    protected UserLoginValidator userLoginValidator = new UserLoginValidator();
    private EncryptPassword encryptPassword = new EncryptPassword();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = loginDataPicker.pick(request);
        // validate
        Errors errors = new Errors();
        if (userLoginValidator.validate(user, errors)){
            // encrypt password
            user.setPassword(encryptPassword.encrypt(user.getPassword()));
            // find profile
            User existUser = userService.findOne(user.getEmail());
            if ( (existUser != null) && (user.getEmail().equals(existUser.getEmail())
                    && user.getPassword().equals(existUser.getPassword()))) {
                // user authenticate , redirect to profile
                HttpSession session = request.getSession();
                session.setAttribute(AttributesHolder.ID, existUser.getId());
                session.setAttribute(AttributesHolder.ROLE, existUser.getRole());

                request.setAttribute(AttributesHolder.USER, existUser);
                return PagesHolder.PROFILE;
            } else {
                // invalid auth data
                errors.addMessage(AttributesHolder.EMAIL, ErrorsMessagesHolder.INVALID_AUTH_DATA);
                errors.setResult(false);
            }
        }

        request.setAttribute(AttributesHolder.USER, user);
        request.setAttribute(AttributesHolder.ERRORS, errors);
        return PagesHolder.LOGIN;
    }
}
