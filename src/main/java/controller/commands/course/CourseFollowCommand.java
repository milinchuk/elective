package controller.commands.course;

import controller.commands.Command;
import model.entity.Course;
import model.entity.Progress;
import model.entity.User;
import model.service.CourseServiceImpl;
import model.service.ProgressServiceImpl;
import model.service.interfaces.CourseService;
import model.service.interfaces.ProgressService;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by click on 11/18/2016.
 */
public class CourseFollowCommand implements Command {
    protected ProgressService progressService = ProgressServiceImpl.getInstance();
    protected CourseService courseService = CourseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // create progress
        Progress progress = new Progress();
        Course course = new Course();
        course.setId(Integer.parseInt(String.valueOf(request.getParameter(AttributesHolder.COURSE))));
        User user = new User();
        user.setId(Integer.parseInt(String.valueOf(request.getSession().getAttribute(AttributesHolder.ID))));
        progress.setCourse(course);
        progress.setStudent(user);
        progressService.create(progress);
        List<Course> courses = courseService.findUnfollow(user.getId());
        request.setAttribute(AttributesHolder.COURSES, courses);
        return PagesHolder.FIND_COURSES;
    }
}
