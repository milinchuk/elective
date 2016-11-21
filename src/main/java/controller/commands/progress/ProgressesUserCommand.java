package controller.commands.progress;

import controller.commands.Command;
import model.entity.Progress;
import model.service.ProgressServiceImpl;
import model.service.interfaces.ProgressService;
import utils.AttributeHolder;
import utils.PathHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by click on 11/20/2016.
 */
public class ProgressesUserCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // from session
        Integer id = 5;
        ProgressService progressService = ProgressServiceImpl.getInstance();
        List<Progress> progresses = progressService.findByUser(id);
        request.setAttribute(AttributeHolder.PROGRESSES, progresses);
        return PathHolder.MY_COURSES;
    }
}
