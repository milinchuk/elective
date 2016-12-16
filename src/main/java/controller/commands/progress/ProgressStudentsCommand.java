package controller.commands.progress;

import controller.commands.Command;
import model.entity.Progress;
import model.service.ProgressServiceImpl;
import model.service.interfaces.ProgressService;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;
import utils.constants.UrlHolder;
import validators.ParameterValidator;
import validators.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Anastasia Milinchuk
 */
public class ProgressStudentsCommand implements Command {
    protected ProgressService progressService = ProgressServiceImpl.getInstance();
    private ParameterValidator parameterValidator;

    public ProgressStudentsCommand(ParameterValidator parameterValidator) {
        this.parameterValidator = parameterValidator;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // get course id from request
        Object courseId = request.getParameter(AttributesHolder.COURSE);
        if(!parameterValidator.validate(courseId)){
            return PagesHolder.PAGE_NOT_FOUND_JSP;
        }

        Integer id = Integer.parseInt(String.valueOf(courseId));
        List<Progress> students = progressService.findByCourse(id);
        request.setAttribute(AttributesHolder.STUDENTS, students);
        if (request.getSession().getAttribute(AttributesHolder.ERRORS) != null) {
            request.setAttribute(AttributesHolder.ERRORS, request.getSession().getAttribute(AttributesHolder.ERRORS));
            request.getSession().setAttribute(AttributesHolder.ERRORS, null);
            request.setAttribute(AttributesHolder.STUDENT,
                    request.getSession().getAttribute(AttributesHolder.STUDENT));
            request.getSession().setAttribute(AttributesHolder.STUDENT, null);
        }
        return PagesHolder.STUDENTS;
    }
}
