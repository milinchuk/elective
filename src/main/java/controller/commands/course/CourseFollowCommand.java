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
import utils.pickers.request.ProgressRequestPicker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by click on 11/18/2016.
 */
public class CourseFollowCommand implements Command {
    protected ProgressService progressService = ProgressServiceImpl.getInstance();
    protected CourseService courseService = CourseServiceImpl.getInstance();
    private ProgressRequestPicker progressRequestPicker;

    public CourseFollowCommand(ProgressRequestPicker progressRequestPicker) {
        this.progressRequestPicker = progressRequestPicker;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // create progress
        Progress progress = progressRequestPicker.pick(request);
        progressService.create(progress);
        List<Course> courses = courseService.findUnfollow(progress.getStudent().getId());
        request.setAttribute(AttributesHolder.COURSES, courses);
        return PagesHolder.FIND_COURSES;
    }
}
