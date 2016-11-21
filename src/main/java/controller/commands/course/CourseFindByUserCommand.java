package controller.commands.course;

import controller.commands.Command;
import model.entity.Course;
import model.entity.User;
import model.service.CourseServiceImpl;
import model.service.interfaces.CourseService;
import utils.AttributeHolder;
import validators.Errors;
import validators.ParameterValidator;
import validators.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import utils.PathHolder;

/**
 * Created by click on 11/20/2016.
 */
public class CourseFindByUserCommand implements Command {




    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Course> courses;
        Errors errors = new Errors();
        Validator<String> parameterValidator = new ParameterValidator();
        if(!parameterValidator.validate(request.getParameter(AttributeHolder.ID), errors)){
            return PathHolder.PAGE_NOT_FOUND_JSP;
        }

        Integer id = Integer.parseInt(request.getParameter(AttributeHolder.ID));
        CourseService courseService = CourseServiceImpl.getInstance();
        if(((User)request.getSession().getAttribute(AttributeHolder.USER)).getRole() == 1){
            courses = courseService.findByStudent(id);
        } else {
            courses = courseService.findByTutor(id);
        }
        request.setAttribute(AttributeHolder.COURSES, courses);
        return PathHolder.COURSES;
    }
}
