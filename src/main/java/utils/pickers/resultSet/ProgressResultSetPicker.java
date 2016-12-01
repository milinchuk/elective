package utils.pickers.resultSet;

import model.entity.Course;
import model.entity.Progress;
import model.entity.User;
import org.apache.log4j.Logger;
import utils.constants.AttributesHolder;
import utils.constants.LoggingMessagesHanldler;

import java.sql.ResultSet;

/**
 * Created by click on 11/20/2016.
 */
public class ProgressResultSetPicker implements ResultSetPicker<Progress> {
    private static final Logger logger = Logger.getLogger(ProgressResultSetPicker.class);
    @Override
    public Progress pick(ResultSet resultSet) {
        try {
            Progress progress = new Progress();
            progress.setId(resultSet.getInt(AttributesHolder.PROGRESS + AttributesHolder.DOT + AttributesHolder.ID));
            progress.setMark(resultSet.getString(AttributesHolder.MARK));
            progress.setNote(resultSet.getString(AttributesHolder.NOTE));

            UserResultSetPicker userPickUtil = new UserResultSetPicker();
            User user = userPickUtil.pick(resultSet);
            CourseResultSetPicker coursePickUtil = new CourseResultSetPicker();
            Course course = coursePickUtil.pick(resultSet);

            progress.setCourse(course);
            progress.setStudent(user);
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_PICK_DATA);
            return progress;
        } catch (Exception e){
            logger.error(LoggingMessagesHanldler.ERROR_PICK, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_PICK, e);
        }
    }
}
