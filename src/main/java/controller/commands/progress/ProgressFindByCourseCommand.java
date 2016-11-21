package controller.commands.progress;

import controller.commands.Command;
import model.entity.Progress;
import model.service.ProgressServiceImpl;
import model.service.interfaces.ProgressService;
import utils.AttributeHolder;
import utils.PathHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

/**
 * Created by click on 11/20/2016.
 */
public class ProgressFindByCourseCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // Check if id integer
        Integer id = Integer.parseInt(request.getParameter(AttributeHolder.COURSE));
        ProgressService progressService = ProgressServiceImpl.getInstance();
        List<Progress> progresses = progressService.findByCourse(id);
        request.setAttribute(AttributeHolder.PROGRESSES, progresses);
        return PathHolder.STUDENTS;
    }
}
