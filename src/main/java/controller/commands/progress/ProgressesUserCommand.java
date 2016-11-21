package controller.commands.progress;

import controller.commands.Command;
import model.entity.Progress;
import model.service.ProgressServiceImpl;
import model.service.interfaces.ProgressService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by click on 11/20/2016.
 */
public class ProgressesUserCommand implements Command{

    public static final String MY_COURSES = "/WEB-INF/myCourses.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // from session
        Integer id = 5;
        ProgressService progressService = ProgressServiceImpl.getInstance();
        List<Progress> progresses = progressService.findByUser(id);
        request.setAttribute("progresses", progresses);
        return MY_COURSES;
    }
}
