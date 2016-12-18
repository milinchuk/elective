package controller.commands.course;

import controller.commands.Command;
import model.entity.Course;
import model.entity.Progress;
import model.entity.User;
import service.CourseServiceImpl;
import service.ProgressServiceImpl;
import service.interfaces.CourseService;
import service.interfaces.ProgressService;
import utils.constants.AttributesHolder;
import utils.constants.UrlHolder;
import validators.entity.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Anastasia Milinchuk
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
        Errors errors = new Errors();
        try {
            progressService.create(progress);
            request.getSession().setAttribute(AttributesHolder.ADD_MESSAGE, true);
        } catch (Exception e) {
            errors.addMessage(AttributesHolder.FOLLOWING, e.getMessage());
            request.getSession().setAttribute(AttributesHolder.ERRORS, errors);
        }
        List<Course> courses = courseService.findUnfollow(user.getId());
        request.setAttribute(AttributesHolder.COURSES, courses);
        return UrlHolder.FIND;
    }
}
