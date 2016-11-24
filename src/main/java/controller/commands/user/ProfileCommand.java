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
 * Created by click on 11/18/2016.
 */
public class ProfileCommand implements Command {
    UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // get id from session
        Integer id = Integer.valueOf(String.valueOf(request.getSession().getAttribute(AttributesHolder.ID)));
        User user = userService.findOne(id);
        request.setAttribute(AttributesHolder.USER, user);
        return PagesHolder.PROFILE;
    }
}
