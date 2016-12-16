package utils.pickers.request;

import model.entity.User;
import org.apache.log4j.Logger;
import utils.constants.AttributesHolder;
import utils.constants.LoggingMessagesHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by click on 11/24/2016.
 */
public class SignupDataRequestPicker extends RequestPicker<User> {
    private static final Logger logger = Logger.getLogger(SignupDataRequestPicker.class);
    @Override
    public User pick(HttpServletRequest request) {
        try {
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
            logger.info(LoggingMessagesHolder.SUCCESSFUL_PICK_DATA);
            return user;
        } catch (RuntimeException e) {
            logger.error(LoggingMessagesHolder.ERROR_PICK, e);
            return null;
        }
    }
}
