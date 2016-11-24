package controller.commands.authorization;

import controller.commands.Command;
import controller.security.EncryptPassword;
import model.entity.User;
import model.service.UserServiceImpl;
import model.service.interfaces.UserService;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;
import utils.pickers.request.RoleRequestPicker;
import utils.pickers.request.SignupDataPicker;
import validators.Errors;
import validators.entity.UserSignUpValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by click on 11/18/2016.
 */
public class SignUpCommand implements Command {
    protected UserSignUpValidator userSignUpValidator = new UserSignUpValidator();
    private SignupDataPicker signupDataPicker = new SignupDataPicker();
    private EncryptPassword encryptPassword = new EncryptPassword();
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = signupDataPicker.pick(request);
        Errors errors = new Errors();
        if (userSignUpValidator.validate(user, errors)) {
            // generate hash password
            user.setPassword(encryptPassword.encrypt(user.getPassword()));

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
                return PagesHolder.PROFILE;
            }
            // user exist
            errors.addMessage("user.exist");
            errors.setResult(false);
        }
        user.setPassword("");
        user.setConfirmPassword("");
        request.setAttribute(AttributesHolder.USER, user);
        request.setAttribute(AttributesHolder.ERRORS, errors);
        return PagesHolder.SIGNUP;
    }
}
