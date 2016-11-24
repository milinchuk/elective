package controller.commands.progress;

import controller.commands.Command;
import model.entity.Progress;
import model.service.ProgressServiceImpl;
import model.service.interfaces.ProgressService;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;
import validators.ParameterValidator;
import validators.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by click on 11/20/2016.
 */
public class ProgressTutorCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // Check id integer
        Validator parameterValidator = new ParameterValidator();
        if(!parameterValidator.validate(request.getParameter(AttributesHolder.ID))){
            return PagesHolder.PAGE_NOT_FOUND_JSP;
        }

        Integer id = Integer.parseInt(request.getParameter(AttributesHolder.COURSE));
        ProgressService progressService = ProgressServiceImpl.getInstance();
        List<Progress> progresses = progressService.findByCourse(id);
        request.setAttribute(AttributesHolder.PROGRESSES, progresses);
        return PagesHolder.STUDENTS;
    }
}
