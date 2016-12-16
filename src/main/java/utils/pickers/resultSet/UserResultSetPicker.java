package utils.pickers.resultSet;

import model.entity.User;
import org.apache.log4j.Logger;
import utils.constants.AttributesHolder;
import utils.constants.LoggingMessagesHolder;

import java.sql.ResultSet;

/**
 * Created by click on 11/5/2016.
 */
public class UserResultSetPicker implements ResultSetPicker<User> {
    private static final Logger logger = Logger.getLogger(UserResultSetPicker.class);
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String ROLE_ID = "role_id";

    public User pick(ResultSet resultSet){
        User user = new User();
        try {
            user.setId(resultSet.getInt(AttributesHolder.USERS + AttributesHolder.DOT + AttributesHolder.ID));
            user.setEmail(resultSet.getString(AttributesHolder.EMAIL));
            user.setPassword(resultSet.getString(AttributesHolder.PASSWORD));
            user.setFirstName(resultSet.getString(FIRST_NAME));
            user.setLastName(resultSet.getString(LAST_NAME));
            user.setRole(resultSet.getInt(ROLE_ID));
            logger.info(LoggingMessagesHolder.SUCCESSFUL_PICK_DATA);
            return user;
        } catch (Exception e){
            logger.error(LoggingMessagesHolder.ERROR_PICK, e);
            throw new RuntimeException(LoggingMessagesHolder.ERROR_PICK, e);
        }

    }
}
