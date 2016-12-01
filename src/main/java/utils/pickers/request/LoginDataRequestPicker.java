package utils.pickers.request;

import model.entity.User;
import org.apache.log4j.Logger;
import utils.constants.AttributesHolder;
import utils.constants.LoggingMessagesHanldler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by click on 11/24/2016.
 */
public class LoginDataRequestPicker extends RequestPicker<User> {
    private static final Logger logger = Logger.getLogger(LoginDataRequestPicker.class);

    @Override
    public User pick(HttpServletRequest request) {
        try {
            User user = new User();
            user.setEmail(request.getParameter(AttributesHolder.EMAIL));
            user.setPassword(request.getParameter(AttributesHolder.PASSWORD));
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_PICK_DATA);
            return user;
        } catch (RuntimeException e) {
            logger.error(LoggingMessagesHanldler.ERROR_PICK, e);
            return null;
        }
    }
}
