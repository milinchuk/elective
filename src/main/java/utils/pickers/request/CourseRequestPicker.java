package utils.pickers.request;

import model.entity.Course;
import model.entity.User;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import utils.constants.AttributesHolder;
import utils.constants.DateHolder;
import utils.constants.LoggingMessagesHanldler;
import utils.constants.PagesHolder;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 *
 */
public class CourseRequestPicker extends RequestPicker<Course> {
    /**
     *
     */
    private static final Logger logger = Logger.getLogger(CourseRequestPicker.class);
    @Override
    public Course pick(HttpServletRequest request) {
        try {
            User tutor = new User();
            tutor.setId(Integer.valueOf(String.valueOf(request.getSession().getAttribute(AttributesHolder.ID))));
            Course course = new Course();
            course.setName(String.valueOf(request.getParameter(AttributesHolder.NAME)));
            course.setAbout(String.valueOf(request.getParameter(AttributesHolder.ABOUT)));
            if(!StringUtils.isEmpty(request.getParameter(AttributesHolder.ID))) {
                course.setId(Integer.valueOf(String.valueOf(request.getParameter(AttributesHolder.ID))));
            }
            course.setTutor(tutor);

            Date startDate = pickDate(request.getParameter(AttributesHolder.START_DATE));
            Date endDate = pickDate(request.getParameter(AttributesHolder.END_DATE));
            course.setStartDate(startDate);
            course.setEndDate(endDate);
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_PICK_DATA);
            return course;
        } catch (Exception e) {
            logger.error(LoggingMessagesHanldler.ERROR_PICK, e);
            return null;
        }
    }

    private Date pickDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateHolder.DATE_FORMAT);
        Date formatDate = null;
        try {
            formatDate = sdf.parse(date);
        } catch (ParseException e) {
            logger.error(LoggingMessagesHanldler.ERROR_PICK_DATE);
        }
        return formatDate;
    }

}
