package utils.pickers.resultSet;

import model.entity.Course;
import model.entity.User;
import org.apache.log4j.Logger;
import utils.constants.AttributesHolder;
import utils.constants.LoggingMessagesHanldler;

import java.sql.ResultSet;

/**
 * Created by click on 11/5/2016.
 */
public class CourseResultSetPicker implements ResultSetPicker<Course> {
    private static final Logger logger = Logger.getLogger(CourseResultSetPicker.class);
    public static final String START_DATE = "start_date";
    public static final String END_DATE = "end_date";

    public Course pick(ResultSet resultSet){
        try {
            Course course = new Course();
            course.setId(resultSet.getInt(AttributesHolder.COURSES + AttributesHolder.DOT + AttributesHolder.ID));
            course.setAbout(resultSet.getString(AttributesHolder.ABOUT));
            course.setName(resultSet.getString(AttributesHolder.NAME));
            course.setStartDate(resultSet.getDate(START_DATE));
            course.setEndDate(resultSet.getDate(END_DATE));
            UserResultSetPicker userPickUtil = new UserResultSetPicker();
            User user = userPickUtil.pick(resultSet);
            course.setTutor(user);
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_PICK_DATA);
            return course;
        } catch (Exception e){
            logger.error(LoggingMessagesHanldler.ERROR_PICK, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_PICK, e);
        }
    }
}
