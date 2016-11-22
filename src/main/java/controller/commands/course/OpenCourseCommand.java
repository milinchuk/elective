package controller.commands.course;

import controller.commands.Command;
import model.entity.Course;
import model.service.CourseServiceImpl;
import model.service.interfaces.CourseService;
import utils.AttributesHolder;
import utils.PagesHolder;
import validators.Errors;
import validators.ParameterValidator;
import validators.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by click on 11/22/2016.
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
        return PagesHolder.COURSE;
    }
}
