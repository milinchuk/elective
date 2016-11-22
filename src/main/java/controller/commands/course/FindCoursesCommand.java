package controller.commands.course;

import controller.commands.Command;
import model.entity.Course;
import model.service.CourseServiceImpl;
import model.service.interfaces.CourseService;
import utils.AttributesHolder;
import utils.PagesHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by click on 11/22/2016.
 */
public class FindCoursesCommand implements Command {
    CourseService service = CourseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Course> courseList = service.findAll();
        request.setAttribute(AttributesHolder.COURSES, courseList);
        return PagesHolder.COURSES;
    }
}
