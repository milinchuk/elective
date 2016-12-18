package controller.commands.user;

import controller.commands.Command;
import model.entity.User;
import model.service.UserServiceImpl;
import model.service.interfaces.UserService;
import utils.constants.AttributesHolder;
import utils.constants.UrlHolder;
import utils.pickers.request.ProfileRequestPicker;
import validators.ProfileValidator;
import validators.entity.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anastasia Milinchuk
 */
public class UpdateProfileCommand implements Command {
    protected UserService userService = UserServiceImpl.getInstance();
    private ProfileValidator profileValidator;
    private ProfileRequestPicker profileRequestPicker;

    public UpdateProfileCommand(ProfileValidator profileValidator, ProfileRequestPicker profileRequestPicker) {
        this.profileValidator = profileValidator;
        this.profileRequestPicker = profileRequestPicker;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = profileRequestPicker.pick(request);
        Errors errors = new Errors();
        if (profileValidator.validate(user, errors)) {
            userService.update(user);
            user = userService.findOne(user.getId());
            request.setAttribute(AttributesHolder.USER, user);
        } else {
            request.getSession().setAttribute(AttributesHolder.ERRORS, errors);
            request.getSession().setAttribute(AttributesHolder.USER, user);
        }
        return UrlHolder.PROFILE;
    }
}
