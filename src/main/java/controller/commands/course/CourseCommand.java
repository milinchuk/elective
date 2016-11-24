package controller.commands.course;

import controller.commands.Command;
import utils.constants.PagesHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by click on 11/22/2016.
 */
public class CourseCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PagesHolder.ADD_COURSE;
    }
}
