package controller.commands.authorization;

import controller.commands.Command;
import utils.constants.AttributesHolder;
import utils.constants.PagesHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by click on 11/24/2016.
 */
public class OpenSignUpCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute(AttributesHolder.ERRORS) != null) {
            request.setAttribute(AttributesHolder.ERRORS, request.getSession().getAttribute(AttributesHolder.ERRORS));
            request.getSession().setAttribute(AttributesHolder.ERRORS, null);
            request.setAttribute(AttributesHolder.USER, request.getSession().getAttribute(AttributesHolder.USER));
            request.getSession().setAttribute(AttributesHolder.USER, null);
        }
        return PagesHolder.SIGNUP;
    }
}
