package controller.commands.progress;

import controller.commands.Command;
import model.entity.Progress;
import model.service.ProgressServiceImpl;
import model.service.interfaces.ProgressService;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by click on 11/23/2016.
 */
public class CourseUnfollowCommand implements Command {
    protected ProgressService progressService = ProgressServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(String.valueOf(request.getParameter(AttributesHolder.PROGRESS)));
        progressService.delete(id);
        List<Progress> progresses = progressService.findByUser(Integer.valueOf(String.
                valueOf(request.getSession().getAttribute(AttributesHolder.ID))));
        request.setAttribute(AttributesHolder.PROGRESSES, progresses);
        return PagesHolder.STUDENT_COURSES;
    }
}
