package controller.commands.progress;

import controller.commands.Command;
import model.entity.Progress;
import service.ProgressServiceImpl;
import service.interfaces.ProgressService;
import org.apache.log4j.Logger;
import utils.constants.AttributesHolder;
import utils.constants.LoggingMessagesHolder;
import utils.constants.UrlHolder;
import utils.pickers.request.ProgressRequestPicker;
import validators.ProgressValidator;
import validators.entity.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command for progress update
 *
 * @author Anastasia Milinchuk
 */
public class ProgressUpdateCommand implements Command {
    /**
     * Logger for logging errors and operations
     */
    private static final Logger logger = Logger.getLogger(ProgressUpdateCommand.class);
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
            try {
                progressService.update(progress);
            } catch (Exception e) {
                logger.error(LoggingMessagesHolder.ERROR_UPDATE);
                errors.addMessage(AttributesHolder.PROGRESS, e.getMessage());
                request.getSession().setAttribute(AttributesHolder.ERRORS, errors);
            }
            request.setAttribute(AttributesHolder.STUDENTS,
                    progressService.findByCourse(progress.getCourse().getId()));
        } else {
            request.getSession().setAttribute(AttributesHolder.ERRORS, errors);
            request.getSession().setAttribute(AttributesHolder.STUDENT, progress.getId());
        }
        return (UrlHolder.STUDENTS_COURSE_GET + progress.getCourse().getId().toString());
    }
}
