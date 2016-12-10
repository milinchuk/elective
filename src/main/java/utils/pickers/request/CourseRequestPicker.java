package utils.pickers.request;

import model.entity.Course;
import model.entity.User;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import utils.constants.AttributesHolder;
import utils.constants.DateHolder;
import utils.constants.LoggingMessagesHanldler;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
            if(request.getParameter(AttributesHolder.ID) != null) {
                course.setId(Integer.valueOf(String.valueOf(request.getParameter(AttributesHolder.ID))));
            }
            course.setTutor(tutor);
//            Calendar startDate = new GregorianCalendar();
//            fillCalendar(request.getParameter(AttributesHolder.START_DAY),
//                    request.getParameter(AttributesHolder.START_MONTH),
//                    request.getParameter(AttributesHolder.START_YEAR), startDate);
//
//            Calendar endDate = new GregorianCalendar();
//            fillCalendar(request.getParameter(AttributesHolder.END_DAY),
//                    request.getParameter(AttributesHolder.END_MONTH),
//                    request.getParameter(AttributesHolder.END_YEAR), endDate);
//
//            course.setStartDate(startDate);
//            course.setEndDate(endDate);
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_PICK_DATA);
            return course;
        } catch (Exception e) {
            logger.error(LoggingMessagesHanldler.ERROR_PICK, e);
            return null;
        }
    }

    private void fillCalendar(String day, String month, String year, Calendar date) {
        fillDayYear(day, Calendar.DAY_OF_MONTH, date);
        fillMonth(month, Calendar.MONTH, date);
        fillDayYear(year, Calendar.YEAR, date);
    }

    private void fillDayYear(String param, int field, Calendar date) {
        if (StringUtils.isNumeric(param)){
            date.add(field, Integer.parseInt(param));
        }
    }

    private void fillMonth(String param, int field, Calendar date){
        Integer month = DateHolder.MONTHS.indexOf(param) + 1;
        date.add(field, month);
    }
}
