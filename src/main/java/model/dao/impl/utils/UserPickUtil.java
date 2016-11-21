package model.dao.impl.utils;

import model.entity.User;

import java.sql.ResultSet;

/**
 * Created by click on 11/5/2016.
 */
public class UserPickUtil implements PickUtil {
    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String ROLE_ID = "role_id";

    public User pick(ResultSet resultSet){
        User user = new User();
        try {
            user.setId(resultSet.getInt(ID));
            user.setEmail(resultSet.getString(EMAIL));
            user.setPassword(resultSet.getString(PASSWORD));
            user.setFirstName(resultSet.getString(FIRST_NAME));
            user.setLastName(resultSet.getString(LAST_NAME));
            user.setRole(resultSet.getInt(ROLE_ID));
            return user;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
