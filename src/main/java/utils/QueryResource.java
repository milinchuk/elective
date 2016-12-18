package utils;

import utils.constants.ResourceNames;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by click on 11/11/2016.
 */
public class QueryResource implements ResourceNames {
    private Properties properties;

    public static final String CREATE = "create";
    public static final String DELETE = "delete";
    public static final String SOFT_DELETE = "soft.delete";
    public static final String UPDATE = "update";
    public static final String FIND_ONE = "find.one";
    public static final String FIND_BY_COURSE = "find.by.course";
    public static final String FIND_ALL = "find.all";
    public static final String FIND_BY_TUTOR = "find.by.tutor";
    public static final String FIND_BY_STUDENT = "find.by.student";
    public static final String FIND_UNFOLLOW = "find.unfollow";
    public static final String FIND_BY_USER_COURSE = "find.by.user.course";
    public static final String FIND_ONE_BY_EMAIL = "find.by.email";

    public QueryResource(String resourceUrl){
        properties = new Properties();
        try {
            properties.load(QueryResource.class.getClassLoader().getResourceAsStream(resourceUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getQuery(String operation){
        return properties.getProperty(operation);
    }
}
