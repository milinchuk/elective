package controller.commands.user;

import controller.commands.Command;
import model.entity.User;
import model.service.UserServiceImpl;
import model.service.interfaces.UserService;
import utils.AttributeHolder;
import utils.PathHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by click on 11/19/2016.
 */
public class UserFindManyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // Check id integer
        Integer id = Integer.parseInt(request.getParameter(AttributeHolder.COURSE));
        UserService userService = UserServiceImpl.getInstance();
        List<User> users = userService.findByCourse(id);
        request.setAttribute(PathHolder.STUDENTS, users);
        return PathHolder.STUDENTS;
    }
}
