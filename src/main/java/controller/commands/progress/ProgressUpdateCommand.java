package controller.commands.progress;

import controller.commands.Command;
import model.entity.Progress;
import model.service.ProgressServiceImpl;
import model.service.interfaces.ProgressService;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;
import utils.pickers.request.ProgressRequestPicker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by click on 11/18/2016.
 */
public class ProgressUpdateCommand implements Command {
    protected ProgressService progressService = ProgressServiceImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ProgressRequestPicker picker = new ProgressRequestPicker();
        Progress progress = picker.pick(request);
        progressService.update(progress);
        request.setAttribute(AttributesHolder.STUDENTS, progressService.findByCourse(progress.getCourse().getId()));
        return PagesHolder.STUDENTS;
    }
}
