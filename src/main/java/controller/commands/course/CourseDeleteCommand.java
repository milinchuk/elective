package controller.commands.course;

import controller.commands.Command;
import service.CourseServiceImpl;
import service.interfaces.CourseService;
import utils.constants.AttributesHolder;
import utils.constants.UrlHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anastasia Milinchuk
 */
public class CourseDeleteCommand implements Command {
    protected CourseService courseService = CourseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer tutorId = Integer.valueOf(String.valueOf(request.getSession().getAttribute(AttributesHolder.ID)));
        Integer id = Integer.valueOf(request.getParameter(AttributesHolder.ID));
        courseService.delete(id);
        request.setAttribute(AttributesHolder.COURSES, courseService.findByTutor(tutorId));
        return UrlHolder.MY_COURSES;
    }
}
