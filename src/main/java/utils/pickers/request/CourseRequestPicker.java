package utils.pickers.request;

import model.entity.Course;
import model.entity.User;
import org.apache.log4j.Logger;
import utils.constants.AttributesHolder;
import utils.constants.LoggingMessagesHanldler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by click on 11/22/2016.
 */
public class CourseRequestPicker extends RequestPicker<Course> {
    private static final Logger logger = Logger.getLogger(CourseRequestPicker.class);
    @Override
    public Course pick(HttpServletRequest request) {
            try {
            User tutor = new User();
            tutor.setId(Integer.valueOf(String.valueOf(request.getSession().getAttribute(AttributesHolder.ID))));
            Course course = new Course();
            course.setName(String.valueOf(request.getParameter(AttributesHolder.NAME)));
            course.setAbout(String.valueOf(request.getParameter(AttributesHolder.ABOUT)));
            if(request.getParameter(AttributesHolder.ID) != null) {
                course.setId(Integer.valueOf(String.valueOf(request.getParameter(AttributesHolder.ID))));
            }
            course.setTutor(tutor);
//        course.setStartDate(Date.valueOf(request.getAttribute(AttributesHolder.START_DATE)));
//        course.setEndDate();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_PICK_DATA);
            return course;
        } catch (Exception e) {
            logger.error(LoggingMessagesHanldler.ERROR_PICK, e);
            return null;
        }
    }
}
