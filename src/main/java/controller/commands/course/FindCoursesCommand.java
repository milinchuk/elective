package controller.commands.course;

import controller.commands.Command;
import model.entity.Course;
import model.service.CourseServiceImpl;
import model.service.interfaces.CourseService;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by click on 11/22/2016.
 */
public class FindCoursesCommand implements Command {
    protected CourseService service = CourseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        setAttributesFromSession(request);
        List<Course> courseList = service.findUnfollow(Integer.valueOf(String.valueOf(request.getSession().
                getAttribute(AttributesHolder.ID))));
        request.setAttribute(AttributesHolder.COURSES, courseList);
        return PagesHolder.FIND_COURSES;
    }

    private void setAttributesFromSession(HttpServletRequest request){
        if (request.getSession().getAttribute(AttributesHolder.ERRORS) != null) {
            request.setAttribute(AttributesHolder.ERRORS, request.getSession().
                    getAttribute(AttributesHolder.ERRORS));
            request.getSession().setAttribute(AttributesHolder.ERRORS, null);
        }
        if (request.getSession().getAttribute(AttributesHolder.ADD_MESSAGE) != null) {
            request.setAttribute(AttributesHolder.ADD_MESSAGE,
                    request.getSession().getAttribute(AttributesHolder.ADD_MESSAGE));
            request.getSession().setAttribute(AttributesHolder.ADD_MESSAGE, null);
        }
    }
}
