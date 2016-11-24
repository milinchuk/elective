package utils.pickers.request;

import model.entity.Progress;
import model.entity.User;
import org.w3c.dom.Attr;
import utils.constants.AttributesHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by click on 11/23/2016.
 */
public class ProfileRequestPicker extends RequestPicker<User>{
    @Override
    public User pick(HttpServletRequest request) {
        User user = new User();
        user.setId(Integer.parseInt(String.valueOf(request.getSession().getAttribute(AttributesHolder.ID))));
        user.setEmail(String.valueOf(request.getParameter(AttributesHolder.EMAIL)));
        user.setFirstName(String.valueOf(request.getParameter(AttributesHolder.FIRST_NAME)));
        user.setLastName(String.valueOf(request.getParameter(AttributesHolder.LAST_NAME)));
        return user;
    }
}
