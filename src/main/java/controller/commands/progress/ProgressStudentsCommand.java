package controller.commands.progress;

import controller.commands.Command;
import model.entity.Progress;
import model.entity.User;
import model.service.ProgressServiceImpl;
import model.service.UserServiceImpl;
import model.service.interfaces.ProgressService;
import model.service.interfaces.UserService;
import utils.AttributesHolder;
import utils.PagesHolder;
import validators.Errors;
import validators.ParameterValidator;
import validators.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by click on 11/22/2016.
 */
public class ProgressStudentsCommand implements Command {
    ProgressService progressService = ProgressServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // get course id from request
        Validator parameterValidator = new ParameterValidator();
        Object courseId = request.getParameter(AttributesHolder.COURSE);
        if(!parameterValidator.validate(courseId)){
            return PagesHolder.PAGE_NOT_FOUND_JSP;
        }

        Integer id = Integer.parseInt(String.valueOf(courseId));
        List<Progress> students = progressService.findByCourse(id);
        request.setAttribute(AttributesHolder.STUDENTS, students);
        return PagesHolder.STUDENTS;
    }
}
