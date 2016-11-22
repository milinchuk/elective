package controller.commands.course;

import controller.commands.Command;
import model.entity.Course;
import model.entity.User;
import model.service.CourseServiceImpl;
import model.service.interfaces.CourseService;
import utils.AttributesHolder;
import utils.PagesHolder;
import validators.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by click on 11/18/2016.
 */
public class CourseCreateCommand implements Command {
    protected CourseService courseService = CourseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // get attributes
        Course course = new Course();
        // get user id from session
        Integer tutorId = Integer.parseInt(String.valueOf(request.getSession().getAttribute(AttributesHolder.ID)));
        User tutor = new User();
        tutor.setId(tutorId);
        // set course
        course.setAbout(String.valueOf(request.getParameter(AttributesHolder.ABOUT)));
        course.setName(String.valueOf(request.getParameter(AttributesHolder.NAME)));
        course.setTutor(tutor);
        courseService.create(course);

        List<Course> courses = courseService.findByTutor(tutorId);
        request.setAttribute(AttributesHolder.COURSES, courses);
        return PagesHolder.TUTOR_COURSES;
    }
}
