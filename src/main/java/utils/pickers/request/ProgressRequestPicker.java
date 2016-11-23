package utils.pickers.request;

import model.entity.Course;
import model.entity.Progress;
import utils.constants.AttributesHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by click on 11/23/2016.
 */
public class ProgressRequestPicker extends RequestPicker<Progress>{
    @Override
    public Progress pick(HttpServletRequest request) {
        Progress progress = new Progress();
        Course course = new Course();
        course.setId(Integer.valueOf(String.valueOf(request.getParameter(AttributesHolder.COURSE))));
        progress.setId(Integer.valueOf(String.valueOf(request.getParameter(AttributesHolder.ID))));
        progress.setCourse(course);
        progress.setMark(String.valueOf(request.getParameter(AttributesHolder.MARK)));
        progress.setNote(String.valueOf(request.getParameter(AttributesHolder.NOTE)));
        return progress;
    }
}
