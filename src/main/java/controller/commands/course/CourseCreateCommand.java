package controller.commands.course;

import controller.commands.Command;
import model.entity.Course;
import model.entity.User;
import model.service.CourseServiceImpl;
import model.service.interfaces.CourseService;
import org.apache.log4j.Logger;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;
import validators.CourseValidator;
import validators.entity.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by click on 11/18/2016.
 */
public class CourseCreateCommand implements Command {
    private CourseValidator courseValidator;
    protected CourseService courseService = CourseServiceImpl.getInstance();
    private static final Logger logger = Logger.getLogger(CourseCreateCommand.class);

    public CourseCreateCommand(CourseValidator courseValidator) {
        this.courseValidator = courseValidator;
    }

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
        // validate
        Errors errors = new Errors();
        if (courseValidator.validate(course, errors)){
            courseService.create(course);
            List<Course> courses = courseService.findByTutor(tutorId);
            request.setAttribute(AttributesHolder.COURSES, courses);
            return PagesHolder.TUTOR_COURSES;
        } else {
            request.setAttribute(AttributesHolder.COURSE, course);
            request.setAttribute(AttributesHolder.ERRORS, errors);
            return PagesHolder.ADD_COURSE;
        }
    }
}
