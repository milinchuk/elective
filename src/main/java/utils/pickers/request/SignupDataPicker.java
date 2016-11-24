package utils.pickers.request;

import model.entity.User;
import utils.constants.AttributesHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by click on 11/24/2016.
 */
public class SignupDataPicker extends RequestPicker<User> {
    @Override
    public User pick(HttpServletRequest request) {
        User user = new User();
        user.setEmail(request.getParameter(AttributesHolder.EMAIL));
        user.setPassword(request.getParameter(AttributesHolder.PASSWORD));
        user.setConfirmPassword(request.getParameter(AttributesHolder.CONFIRM_PASSWORD));
        user.setFirstName(request.getParameter(AttributesHolder.FIRST_NAME));
        user.setLastName(request.getParameter(AttributesHolder.LAST_NAME));
        Integer roleInt = null;
        String role = request.getParameter(AttributesHolder.ROLE);
        if (AttributesHolder.STUDENT.equals(role)) {
            roleInt = User.STUDENT;
        }
        if (AttributesHolder.TUTOR.equals(role)) {
            roleInt = User.TUTOR;
        }
        user.setRole(roleInt);
        return user;
    }
}
