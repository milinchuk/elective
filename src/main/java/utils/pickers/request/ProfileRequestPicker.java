package utils.pickers.request;

import model.entity.User;
import org.apache.log4j.Logger;
import utils.constants.AttributesHolder;
import utils.constants.LoggingMessagesHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by click on 11/23/2016.
 */
public class ProfileRequestPicker extends RequestPicker<User>{
    private static final Logger logger = Logger.getLogger(ProfileRequestPicker.class);

    @Override
    public User pick(HttpServletRequest request) {
        try {
            User user = new User();
            user.setId(Integer.parseInt(String.valueOf(request.getSession().getAttribute(AttributesHolder.ID))));
            user.setEmail(String.valueOf(request.getParameter(AttributesHolder.EMAIL)));
            user.setFirstName(String.valueOf(request.getParameter(AttributesHolder.FIRST_NAME)));
            System.out.println("FIRST :"+ user.getFirstName());
            user.setLastName(String.valueOf(request.getParameter(AttributesHolder.LAST_NAME)));
            logger.info(LoggingMessagesHolder.SUCCESSFUL_PICK_DATA);
            return user;
        } catch (RuntimeException e) {
            logger.error(LoggingMessagesHolder.ERROR_PICK, e);
            return null;
        }
    }
}
