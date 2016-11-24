package controller.commands.course;

import controller.commands.Command;
import model.entity.Course;
import model.service.CourseServiceImpl;
import model.service.interfaces.CourseService;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;
import utils.pickers.request.CourseRequestPicker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by click on 11/18/2016.
 */
public class CourseUpdateCommand implements Command {
    protected CourseService courseService = CourseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(String.valueOf(request.getSession().getAttribute(AttributesHolder.ID)));
        CourseRequestPicker picker = new CourseRequestPicker();
        Course course = picker.pick(request);
        courseService.update(course);
        request.setAttribute(AttributesHolder.COURSES, courseService.findByTutor(id));
        return PagesHolder.TUTOR_COURSES;
    }
}
