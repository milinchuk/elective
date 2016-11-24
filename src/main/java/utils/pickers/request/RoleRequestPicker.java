package utils.pickers.request;

import model.entity.User;
import utils.constants.AttributesHolder;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by click on 11/24/2016.
 */
public class RoleRequestPicker extends RequestPicker<Integer> {
    @Override
    public Integer pick(HttpServletRequest request) {
        String role = request.getParameter(AttributesHolder.ROLE);
        if (AttributesHolder.STUDENT.equals(role)) {
            return User.STUDENT;
        }
        if (AttributesHolder.TUTOR.equals(role)) {
            return User.TUTOR;
        }
        return null;
    }
}
