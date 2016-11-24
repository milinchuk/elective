package utils.pickers.request;

import model.entity.User;
import utils.constants.AttributesHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by click on 11/24/2016.
 */
public class LoginDataPicker extends RequestPicker<User> {
    @Override
    public User pick(HttpServletRequest request) {
        User user = new User();
        user.setEmail(request.getParameter(AttributesHolder.EMAIL));
        user.setPassword(request.getParameter(AttributesHolder.PASSWORD));
        return user;
    }
}
