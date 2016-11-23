package utils.pickers.request;

import com.sun.deploy.net.HttpRequest;
import model.entity.Course;
import model.entity.User;
import utils.constants.AttributesHolder;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;

/**
 * Created by click on 11/22/2016.
 */
public class CourseRequestPicker extends RequestPicker<Course> {
    @Override
    public Course pick(HttpServletRequest request) {
        User tutor = new User();
        tutor.setId(Integer.valueOf(String.valueOf(request.getSession().getAttribute(AttributesHolder.ID))));
        Course course = new Course();
        course.setName(String.valueOf(request.getParameter(AttributesHolder.NAME)));
        course.setAbout(String.valueOf(request.getParameter(AttributesHolder.ABOUT)));
        course.setId(Integer.valueOf(String.valueOf(request.getParameter(AttributesHolder.ID))));
        course.setTutor(tutor);

//        course.setStartDate(Date.valueOf(request.getAttribute(AttributesHolder.START_DATE)));
//        course.setEndDate();
        return course;
    }
}
