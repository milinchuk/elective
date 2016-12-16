package utils.pickers.request;

import model.entity.Course;
import model.entity.Progress;
import org.apache.log4j.Logger;
import utils.constants.AttributesHolder;
import utils.constants.LoggingMessagesHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by click on 11/23/2016.
 */
public class ProgressRequestPicker extends RequestPicker<Progress>{
    private static final Logger logger = Logger.getLogger(ProgressRequestPicker.class);
    @Override
    public Progress pick(HttpServletRequest request) {
        try {
            Progress progress = new Progress();
            Course course = new Course();
            course.setId(Integer.valueOf(request.getParameter(AttributesHolder.COURSE)));
            progress.setId(Integer.valueOf(request.getParameter(AttributesHolder.ID)));
            progress.setCourse(course);
            progress.setMark(request.getParameter(AttributesHolder.MARK));
            progress.setNote(request.getParameter(AttributesHolder.NOTE));
            logger.info(LoggingMessagesHolder.SUCCESSFUL_PICK_DATA);
            return progress;
        } catch (Exception e) {
            logger.error(LoggingMessagesHolder.ERROR_PICK, e);
            return null;
        }
    }
}
