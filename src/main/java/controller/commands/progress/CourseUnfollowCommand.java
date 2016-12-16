package controller.commands.progress;

import controller.commands.Command;
import model.entity.Progress;
import model.service.ProgressServiceImpl;
import model.service.interfaces.ProgressService;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;
import utils.constants.UrlHolder;
import validators.ParameterValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Anastasia Milinchuk
 */
public class CourseUnfollowCommand implements Command {
    protected ProgressService progressService = ProgressServiceImpl.getInstance();
    private ParameterValidator parameterValidator;

    public CourseUnfollowCommand(ParameterValidator parameterValidator) {
        this.parameterValidator = parameterValidator;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // validate
        if (!parameterValidator.validate(String.valueOf(request.getParameter(AttributesHolder.PROGRESS)))) {
            return UrlHolder.PAGE_NOT_FOUND;
        }

        Integer id = Integer.valueOf(String.valueOf(request.getParameter(AttributesHolder.PROGRESS)));
        progressService.delete(id);
        List<Progress> progresses = progressService.findByUser(Integer.valueOf(String.
             valueOf(request.getSession().getAttribute(AttributesHolder.ID))));
        request.setAttribute(AttributesHolder.PROGRESSES, progresses);
        return UrlHolder.MY_COURSES;
    }
}
