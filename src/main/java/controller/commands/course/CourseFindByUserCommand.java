package controller.commands.course;

import controller.commands.Command;
import model.entity.Course;
import model.entity.User;
import model.service.CourseServiceImpl;
import model.service.interfaces.CourseService;
import validators.Errors;
import validators.ParameterValidator;
import validators.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import controller.commands.PathHolder;

/**
 * Created by click on 11/20/2016.
 */
public class CourseFindByUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Course> courses;
        Errors errors = new Errors();
        Validator<String> parameterValidator = new ParameterValidator();
        if(!parameterValidator.validate(request.getParameter("id"), errors)){
            return PathHolder.PAGE_NOT_FOUND_JSP;
        }

        Integer id = Integer.parseInt(request.getParameter("id"));
        CourseService courseService = CourseServiceImpl.getInstance();
        if(((User)request.getSession().getAttribute("user")).getRole() == 1){
            courses = courseService.findByStudent(id);
        } else {
            courses = courseService.findByTutor(id);
        }
        request.setAttribute("courses", courses);
        return PathHolder.COURSES;
    }
}
