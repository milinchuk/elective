package controller.commands.course;

import controller.commands.Command;
import model.entity.Course;
import model.service.CourseServiceImpl;
import model.service.interfaces.CourseService;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;
import utils.constants.UrlHolder;
import validators.entity.Errors;
import validators.ParameterValidator;
import validators.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anastasia Milinchuk
 */
public class OpenCourseCommand implements Command {
    CourseService courseService = CourseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // check correct id
        Validator parameterValidator = new ParameterValidator();
        if(!parameterValidator.validate(request.getParameter(AttributesHolder.ID), new Errors())){
            return PagesHolder.PAGE_NOT_FOUND_JSP;
        }

        Integer id = Integer.valueOf(request.getParameter(AttributesHolder.ID));
        Course course = courseService.findOne(id);
        request.setAttribute(AttributesHolder.COURSE, course);
        if (request.getSession().getAttribute(AttributesHolder.ERRORS) != null) {
            request.setAttribute(AttributesHolder.ERRORS, request.getSession().getAttribute(AttributesHolder.ERRORS));
            request.getSession().setAttribute(AttributesHolder.ERRORS, null);
            request.setAttribute(AttributesHolder.COURSE, request.getSession().getAttribute(AttributesHolder.COURSE));
            request.getSession().setAttribute(AttributesHolder.COURSE, null);
        }
        return PagesHolder.COURSE;
    }
}
