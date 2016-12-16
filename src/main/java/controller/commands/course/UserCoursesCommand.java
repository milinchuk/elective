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
 * @author Anastasia Milinchuk
 */
public class UserCoursesCommand implements Command {
    CourseService courseService = CourseServiceImpl.getInstance();
    ProgressService progressService = ProgressServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // check role
        Integer id = Integer.valueOf(String.valueOf(request.getSession().getAttribute(AttributesHolder.ID)));
        Integer role = Integer.valueOf(String.valueOf(request.getSession().getAttribute(AttributesHolder.ROLE)));

        // if tutor
        if (role == User.TUTOR) {
            List<Course> courses = courseService.findByTutor(id);
            request.setAttribute(AttributesHolder.COURSES, courses);
            return PagesHolder.TUTOR_COURSES;
        }

        // if student
        if (role == User.STUDENT) {
            List<Progress> progresses = progressService.findByUser(id);
            request.setAttribute(AttributesHolder.PROGRESSES, progresses);
            return PagesHolder.STUDENT_COURSES;
        }

        return PagesHolder.LOGIN;
    }
}
