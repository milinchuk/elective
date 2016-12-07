package controller.commands.course;

import controller.commands.Command;
import model.service.CourseServiceImpl;
import model.service.interfaces.CourseService;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by click on 11/18/2016.
 */
public class CourseDeleteCommand implements Command {
    protected CourseService courseService = CourseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer tutorId = Integer.valueOf(String.valueOf(request.getSession().getAttribute(AttributesHolder.ID)));
        Integer id = Integer.valueOf(request.getParameter(AttributesHolder.ID));
        courseService.delete(id);
        request.setAttribute(AttributesHolder.COURSES, courseService.findByTutor(tutorId));
        return PagesHolder.TUTOR_COURSES;
    }
}
