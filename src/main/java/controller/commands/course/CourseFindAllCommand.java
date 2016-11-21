package controller.commands.course;

import controller.commands.Command;
import model.entity.Course;
import model.service.CourseServiceImpl;
import model.service.interfaces.CourseService;
import utils.AttributeHolder;
import utils.PathHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by click on 11/18/2016.
 */
public class CourseFindAllCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        CourseService service = CourseServiceImpl.getInstance();
        List<Course> courseList = service.findAll();
        request.setAttribute(AttributeHolder.COURSES, courseList);
        return PathHolder.COURSES;
    }
}
