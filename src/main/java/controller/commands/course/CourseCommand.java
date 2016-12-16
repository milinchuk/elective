package controller.commands.course;

import controller.commands.Command;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;
import utils.constants.UrlHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anastasia Milinchuk
 */
public class CourseCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute(AttributesHolder.ERRORS) != null) {
            request.setAttribute(AttributesHolder.ERRORS, request.getSession().getAttribute(AttributesHolder.ERRORS));
            request.getSession().setAttribute(AttributesHolder.ERRORS, null);
            request.setAttribute(AttributesHolder.COURSE, request.getSession().getAttribute(AttributesHolder.COURSE));
            request.getSession().setAttribute(AttributesHolder.COURSE, null);
        }
        return PagesHolder.ADD_COURSE;
    }
}
