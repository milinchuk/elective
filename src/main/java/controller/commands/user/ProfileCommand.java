package controller.commands.user;

import controller.commands.Command;
import model.entity.User;
import model.service.UserServiceImpl;
import model.service.interfaces.UserService;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anastasia Milinchuk
 */
public class ProfileCommand implements Command {
    UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // get id from session
        if (request.getSession().getAttribute(AttributesHolder.ERRORS) != null) {
            request.setAttribute(AttributesHolder.ERRORS, request.getSession().getAttribute(AttributesHolder.ERRORS));
            request.getSession().setAttribute(AttributesHolder.ERRORS, null);
            request.setAttribute(AttributesHolder.USER,
                    request.getSession().getAttribute(AttributesHolder.USER));
            request.getSession().setAttribute(AttributesHolder.USER, null);
        } else {
            Integer id = Integer.valueOf(String.valueOf(request.getSession().getAttribute(AttributesHolder.ID)));
            User user = userService.findOne(id);
            request.setAttribute(AttributesHolder.USER, user);
        }
        return PagesHolder.PROFILE;
    }
}
