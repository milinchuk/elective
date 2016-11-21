package controller.commands.course;

import controller.commands.Command;
import model.entity.Course;
import model.service.CourseServiceImpl;
import model.service.interfaces.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by click on 11/19/2016.
 */
public class CourseFindOneCommand implements Command {
    public static final String COURSE = "/WEB-INF/course.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        CourseService courseService = CourseServiceImpl.getInstance();
        Course course = courseService.findOne(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("course", course);
        return COURSE;
    }
}
