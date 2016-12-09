package controller.commands.course;

import controller.commands.Command;
import model.entity.Course;
import model.service.CourseServiceImpl;
import model.service.interfaces.CourseService;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;
import utils.constants.UrlHolder;
import utils.pickers.request.CourseRequestPicker;
import validators.CourseValidator;
import validators.entity.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by click on 11/18/2016.
 */
public class CourseUpdateCommand implements Command {
    protected CourseService courseService = CourseServiceImpl.getInstance();
    private CourseValidator courseValidator;
    private CourseRequestPicker picker;

    public CourseUpdateCommand(CourseValidator courseValidator, CourseRequestPicker picker) {
        this.courseValidator = courseValidator;
        this.picker = picker;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(String.valueOf(request.getSession().getAttribute(AttributesHolder.ID)));
        Course course = picker.pick(request);
        // validate
        Errors errors = new Errors();
        if (courseValidator.validate(course,errors)) {
            courseService.update(course);
            request.setAttribute(AttributesHolder.COURSES, courseService.findByTutor(id));
            return UrlHolder.MY_COURSES;
        } else {
            request.setAttribute(AttributesHolder.COURSE, course);
            request.setAttribute(AttributesHolder.ERRORS, errors);
            return PagesHolder.COURSE;
        }
    }
}
