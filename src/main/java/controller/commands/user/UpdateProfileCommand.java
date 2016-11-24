package controller.commands.user;

import com.sun.deploy.net.HttpRequest;
import controller.commands.Command;
import model.entity.User;
import model.service.UserServiceImpl;
import model.service.interfaces.UserService;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;
import utils.pickers.Picker;
import utils.pickers.request.CourseRequestPicker;
import utils.pickers.request.ProfileRequestPicker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by click on 11/18/2016.
 */
public class UpdateProfileCommand implements Command {
    protected UserService userService = UserServiceImpl.getInstance();
    private static Picker<User,HttpServletRequest> picker = request ->  {
        User user = new User();
        user.setId(Integer.parseInt(String.valueOf(request.getSession().getAttribute(AttributesHolder.ID))));
        user.setEmail(String.valueOf(request.getParameter(AttributesHolder.EMAIL)));
        user.setFirstName(String.valueOf(request.getParameter(AttributesHolder.FIRST_NAME)));
        user.setLastName(String.valueOf(request.getParameter(AttributesHolder.LAST_NAME)));
        return user;
    }; /*new ProfileRequestPicker();*/

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = picker.pick(request);
        userService.update(user);
        user = userService.findOne(user.getId());
        request.setAttribute(AttributesHolder.USER, user);
        return PagesHolder.PROFILE;
    }
}
