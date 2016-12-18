package controller.commands.course;

import controller.commands.Command;
import model.entity.Course;
import model.service.CourseServiceImpl;
import model.service.interfaces.CourseService;
import utils.constants.AttributesHolder;
import utils.constants.UrlHolder;
import utils.pickers.request.CourseRequestPicker;
import validators.CourseValidator;
import validators.entity.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Anastasia Milinchuk
 */
public class CourseCreateCommand implements Command {
    private CourseValidator courseValidator;
    private CourseRequestPicker courseRequestPicker;
    protected CourseService courseService = CourseServiceImpl.getInstance();

    public CourseCreateCommand(CourseValidator courseValidator, CourseRequestPicker courseRequestPicker) {
        this.courseValidator = courseValidator;
        this.courseRequestPicker = courseRequestPicker;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // get attributes
        Course course = courseRequestPicker.pick(request);
        // validate
        Errors errors = new Errors();
        if (courseValidator.validate(course, errors)){
            courseService.create(course);
            List<Course> courses = courseService.findByTutor(course.getTutor().getId());
            request.setAttribute(AttributesHolder.COURSES, courses);
            return UrlHolder.MY_COURSES;
        } else {
            request.getSession().setAttribute(AttributesHolder.COURSE, course);
            request.getSession().setAttribute(AttributesHolder.ERRORS, errors);
            return UrlHolder.COURSE_ADD;
        }
    }
}
