package utils.pickers.request;

import model.entity.User;
import org.apache.log4j.Logger;
import utils.constants.AttributesHolder;
import utils.constants.LoggingMessagesHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by click on 11/24/2016.
 */
public class RoleRequestPicker extends RequestPicker<Integer> {
    private static final Logger logger = Logger.getLogger(RoleRequestPicker.class);
    @Override
    public Integer pick(HttpServletRequest request) {
        try {
            String role = request.getParameter(AttributesHolder.ROLE);
            if (AttributesHolder.STUDENT.equals(role)) {
                return User.STUDENT;
            }
            if (AttributesHolder.TUTOR.equals(role)) {
                return User.TUTOR;
            }
        } catch (Exception e) {
            logger.error(LoggingMessagesHolder.ERROR_PICK, e);
        }
        return null;
    }
}
