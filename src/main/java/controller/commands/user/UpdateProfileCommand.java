package controller.commands.user;

import controller.commands.Command;
import model.entity.User;
import model.service.UserServiceImpl;
import model.service.interfaces.UserService;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;
import utils.pickers.Picker;
import utils.pickers.request.ProfileRequestPicker;
import validators.ProfileValidator;
import validators.entity.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by click on 11/18/2016.
 */
public class UpdateProfileCommand implements Command {
    protected UserService userService = UserServiceImpl.getInstance();
    private ProfileValidator profileValidator;
    private ProfileRequestPicker profileRequestPicker;
//    private static Picker<User,HttpServletRequest> picker = request ->  {
//        User user = new User();
//        user.setId(Integer.parseInt(String.valueOf(request.getSession().getAttribute(AttributesHolder.ID))));
//        user.setEmail(String.valueOf(request.getParameter(AttributesHolder.EMAIL)));
//        user.setFirstName(String.valueOf(request.getParameter(AttributesHolder.FIRST_NAME)));
//        user.setLastName(String.valueOf(request.getParameter(AttributesHolder.LAST_NAME)));
//        return user;
//    };


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
            return PagesHolder.PROFILE;
        } else {
            request.setAttribute(AttributesHolder.ERRORS, errors);
            request.setAttribute(AttributesHolder.USER, user);
            return PagesHolder.PROFILE;
        }
    }
}
