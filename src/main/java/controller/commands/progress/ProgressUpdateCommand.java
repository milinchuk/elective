package controller.commands.progress;

import controller.commands.Command;
import model.entity.Progress;
import model.service.ProgressServiceImpl;
import model.service.interfaces.ProgressService;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;
import utils.pickers.request.ProgressRequestPicker;
import validators.ProgressValidator;
import validators.entity.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by click on 11/18/2016.
 */
public class ProgressUpdateCommand implements Command {
    protected ProgressService progressService = ProgressServiceImpl.getInstance();
    private ProgressRequestPicker progressRequestPicker;
    private ProgressValidator progressValidator;

    public ProgressUpdateCommand(ProgressRequestPicker progressRequestPicker,
                                 ProgressValidator progressValidator) {
        this.progressRequestPicker = progressRequestPicker;
        this.progressValidator = progressValidator;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Progress progress = progressRequestPicker.pick(request);
        Errors errors = new Errors();
        if (progressValidator.validate(progress, errors)) {
            progressService.update(progress);
            request.setAttribute(AttributesHolder.STUDENTS,
                    progressService.findByCourse(progress.getCourse().getId()));
            return PagesHolder.STUDENTS;
        } else {
            request.setAttribute(AttributesHolder.ERRORS, errors);
            request.setAttribute(AttributesHolder.STUDENTS,
                    progressService.findByCourse(progress.getCourse().getId()));
            return PagesHolder.STUDENTS;
        }
    }
}
